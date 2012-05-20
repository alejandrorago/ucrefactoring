package edu.unicen.ucrefactoring.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.part.ViewPart;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.model.Condition;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.gui.NewUseCaseDialog;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UCRUseCasesView extends ViewPart {

	public static final String ID = "edu.unicen.ucrefactoring.gui.UCRUseCasesView"; //$NON-NLS-1$

	// Providers
	public static UCRefactoringDetection ucref;
	private static UseCaseLabelProvider ucLabel;
	private static UseCaseTreeContentProvider ucTreeContentProvider;
	private static UseCaseListContentProvider ucListContentProvider;

	static public HashMap<String, AlignmentX2Result> alignResults;
	public static Composite container_1;

	// UseCases
	static public UseCase useCaseA;
	static public UseCase useCaseB;

// Actions
	private Action compareAction;

	// widgets
	private static ListViewer ucList;
	private org.eclipse.swt.widgets.List list;
	private Button btnLoadUseCase;
	private Label lblUseCaseModel;
	private static JFileChooser fileChooser;
	public static UCRNewUseCaseDialog UCRDialog;

	public UCRUseCasesView() {
		initUseCasesView();
	}

	// ==============Servicios==========================

	public static void initUseCasesView() {
		// init file chooser
		fileChooser = new JFileChooser();

		useCaseA = null;
		useCaseB = null;
	}

	public static void updateUseCasesView() {
		// Providers
		ucref = new UCRefactoringDetection(ucref.getUseCaseModel());
		ucLabel = new UseCaseLabelProvider();
		// ucContentProvider = new
		// UseCaseContentProvider(ucref.getUseCaseModel());
		ucListContentProvider = new UseCaseListContentProvider(
				ucref.getUseCaseModel());
		// ucTreeContentProvider = new
		// UseCaseTreeContentProvider(ucref.getUseCaseModel());
		// assumes listviewer already created
		populatesUCList();
		// TODO: Find out a better way to initialize and compare use cases
		ucref.compareUseCases();
		alignResults = ucref.getSimilarityAnalizer().getAlignmentResult();

		useCaseA = null;
		useCaseB = null;
	}

	private static void populatesUCList() {
		ucList.setContentProvider(ucListContentProvider);
		ucList.setLabelProvider(ucLabel);
		ucList.setInput(ucref);
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		container_1 = new Composite(parent, SWT.NONE);

		createWidgets(container_1);

		btnLoadUseCase = new Button(parent, SWT.CENTER);

		btnLoadUseCase.setText("Load Use Case Model");

		lblUseCaseModel = new Label(container_1, SWT.NONE);
		GroupLayout gl_container_1 = new GroupLayout(container_1);
		gl_container_1.setHorizontalGroup(gl_container_1.createParallelGroup(
				GroupLayout.LEADING).add(
				GroupLayout.TRAILING,
				gl_container_1
						.createSequentialGroup()
						.addContainerGap()
						.add(gl_container_1
								.createParallelGroup(GroupLayout.TRAILING)
								.add(GroupLayout.LEADING, lblUseCaseModel,
										GroupLayout.DEFAULT_SIZE, 175,
										Short.MAX_VALUE)
								.add(GroupLayout.LEADING, list,
										GroupLayout.DEFAULT_SIZE, 175,
										Short.MAX_VALUE)).addContainerGap()));
		gl_container_1.setVerticalGroup(gl_container_1.createParallelGroup(
				GroupLayout.LEADING).add(
				gl_container_1
						.createSequentialGroup()
						.add(4)
						.add(lblUseCaseModel)
						.addPreferredGap(LayoutStyle.RELATED)
						.add(list, GroupLayout.DEFAULT_SIZE, 262,
								Short.MAX_VALUE).addContainerGap()));
		container_1.setLayout(gl_container_1);
		GroupLayout gl_parent = new GroupLayout(parent);
		gl_parent.setHorizontalGroup(gl_parent
				.createParallelGroup(GroupLayout.LEADING)
				.add(container_1, GroupLayout.DEFAULT_SIZE, 199,
						Short.MAX_VALUE)
				.add(gl_parent.createSequentialGroup().add(22)
						.add(btnLoadUseCase)
						.addContainerGap(22, Short.MAX_VALUE)));
		gl_parent.setVerticalGroup(gl_parent.createParallelGroup(
				GroupLayout.TRAILING).add(
				gl_parent
						.createSequentialGroup()
						.add(container_1, GroupLayout.DEFAULT_SIZE, 301,
								Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.RELATED)
						.add(btnLoadUseCase).add(8)));
		parent.setLayout(gl_parent);

		// modified
		createActions();
		initListContextMenu();
		createListeners();
		// =========

		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	/**
	 * Create Widgets
	 * 
	 * @param container
	 */
	public void createWidgets(Composite container) {

		ucList = new ListViewer(container, SWT.MULTI | SWT.BORDER);
		list = ucList.getList();
		list.setToolTipText("");
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions

		// Adding
		compareAction = new Action() {
			public void run() {

				if (useCaseA != null && useCaseB != null) {
					List<SimilarBlock> similarBlocksLeft = new ArrayList<SimilarBlock>();
					List<SimilarBlock> similarBlocksRight = new ArrayList<SimilarBlock>();
					for (Flow flowA : useCaseA.getFlows()) {
						for (Flow flowB : useCaseB.getFlows()) {
							String key = SimilarityAnalyzer.getAlignmentKey(
									useCaseA, flowA, useCaseB, flowB);
							AlignmentX2Result alignResult = alignResults
									.get(key);
							if (alignResult != null) {
								List<SimilarBlock> similarBlocksA = alignResult
										.getSimilarBlocksFromA();
								List<SimilarBlock> similarBlocksB = alignResult
										.getSimilarBlocksFromB();
								if (useCaseA.getName().compareTo(
										useCaseB.getName()) > 0) {
									similarBlocksLeft.addAll(similarBlocksA);
									similarBlocksRight.addAll(similarBlocksB);
								} else {
									similarBlocksLeft.addAll(similarBlocksB);
									similarBlocksRight.addAll(similarBlocksA);
								}
							}
						}
					}

					setCompareView(UCRCompareView.ucLeft, useCaseA,
							similarBlocksLeft);
					setCompareView(UCRCompareView.ucRight, useCaseB,
							similarBlocksRight);

					UCRCompareView.similarBlocksLeft = (similarBlocksLeft);
					UCRCompareView.useCaseLeft = useCaseA;
					UCRCompareView.similarBlocksRight = (similarBlocksRight);
					UCRCompareView.useCaseRight = useCaseB;
					UCRCompareView.updateButtonsAndLabels();

					UCRCompareView.lblLeft.setText(UCRCompareView.useCaseLeft
							.getName());
					UCRCompareView.lblRight.setText(UCRCompareView.useCaseRight
							.getName());
				}
			}
		};

		compareAction.setText("Compare");
		compareAction.setToolTipText("Compares selected elements");
	}

	/**
	 * TODO: Figure out whether to use MenuManager or the default IMenuManager
	 * to add context menu Create the context popup menu
	 */
	private void initListContextMenu() {
		/**
		 * Commented. now compare action is executed with a button and not
		 * context menu.
		 * 
		 * MenuManager menuManager = new MenuManager("#PopupMenu");
		 * menuManager.setRemoveAllWhenShown(true);
		 * 
		 * //Listener for contextual menu menuManager.addMenuListener(new
		 * IMenuListener() {
		 * 
		 * @Override public void menuAboutToShow(IMenuManager manager) {
		 *           manager.add(compareAction); }
		 * 
		 *           });
		 * 
		 *           Menu menu =
		 *           menuManager.createContextMenu(ucList.getList());
		 *           ucList.getList().setMenu(menu);
		 *           getSite().registerContextMenu(menuManager, ucList);
		 **/

	}

	public void createListeners() {
		// Listener de cambio de selección en la lista(Double click)
		/**
		 * Commented selection changed event. now compare action is executed by
		 * a button.
		 */
		ucList.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) ucList
						.getSelection();
				if (selection != null) {
					if (selection.size() == 1) {
						ucList.getList().setToolTipText(
								((UseCase) (selection.toList().get(0)))
										.getDescription());
					} else {
						ucList.getList().setToolTipText("");
					}
				} else {
					ucList.getList().setToolTipText("");
				}

				/**
				 * IStructuredSelection selection = (IStructuredSelection)
				 * ucList.getSelection();
				 * 
				 * //Si elegi uno solo, es el A if (selection.size()==1)
				 * useCaseA = (UseCase)selection.toList().get(0); //cuando tengo
				 * 2, seteo el B else if (selection.size()==2){ if
				 * (!(((UseCase)(
				 * selection.toList().get(0))).getName().equals(useCaseA
				 * .getName())) &&
				 * !(((UseCase)(selection.toList().get(1))).getName
				 * ().equals(useCaseA.getName()))) //deseleccione el A, lo
				 * cambio useCaseA = (UseCase)(selection.toList().get(0));
				 * //Como están ordenados alfabeticamente, pregunto cual de los
				 * dos no es el que ya utilice if
				 * ((((UseCase)(selection.toList()
				 * .get(0))).getName().equals(useCaseA.getName()))) //si el
				 * primero es el que utilice en A, uso el segundo useCaseB =
				 * (UseCase)(selection.toList().get(1)); else //si no es el
				 * primero, uso ese useCaseB =
				 * (UseCase)(selection.toList().get(0)); } if (selection != null
				 * && selection.size()==2){ compareAction.setEnabled(true); }
				 * else compareAction.setEnabled(false);
				 **/
			}
		});
		/**/

		// Listener for double click event on use cases list
		ucList.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				// TODO Auto-generated method stub
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() == 1) {
					if (useCaseA == null)
						useCaseA = (UseCase) selection.toList().get(0);
					else
						useCaseB = (UseCase) selection.toList().get(0);
				}

				if (useCaseA != null) {
					UCRCompareView.useCaseLeft = useCaseA;
					setCompareView(UCRCompareView.ucLeft, useCaseA);
					UCRCompareView.btnCleanLeft.setEnabled(true);
					UCRCompareView.lblLeft.setText(UCRCompareView.useCaseLeft
							.getName());

				}

				if (useCaseB != null) {
					UCRCompareView.useCaseRight = useCaseB;
					setCompareView(UCRCompareView.ucRight, useCaseB);
					UCRCompareView.btnCleanRight.setEnabled(true);
					UCRCompareView.lblRight.setText(UCRCompareView.useCaseRight
							.getName());
				}

				if (useCaseA != null && useCaseB != null) {
					UCRCompareView.btnCompare.setEnabled(true);
				} else {
					UCRCompareView.btnCompare.setEnabled(false);
				}

			}
		});

		btnLoadUseCase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int returnVal = fileChooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// get ucm file
					File useCaseModel = fileChooser.getSelectedFile();
					// get uima file
					String uimaPath = fileChooser.getSelectedFile()
							.getAbsolutePath();
					uimaPath = uimaPath.substring(0, uimaPath.lastIndexOf("/"))
							+ useCaseModel.getName().substring(0,
									useCaseModel.getName().lastIndexOf("."))
							+ "uima";
					System.out.println(uimaPath);
					File useCaseUima = new File(uimaPath);

					ucref = new UCRefactoringDetection(useCaseModel,
							useCaseUima);
					ucLabel = new UseCaseLabelProvider();
					ucListContentProvider = new UseCaseListContentProvider(
							ucref.getUseCaseModel());

					// parse use case model
					ucref.compareUseCases();
					alignResults = ucref.getSimilarityAnalizer()
							.getAlignmentResult();

					// set use case model name
					lblUseCaseModel.setText(ucref.getUseCaseModel().getName());

					// populate list
					UCRUseCasesView.populatesUCList();

					// enable analyze button
					UCRDataView.btnAnalyze.setEnabled(true);

					System.out.println("Opening: " + useCaseModel.getName()
							+ ".");
				} else {
					System.out.println("Open command cancelled by user.");
				}

			}
		});
	}

	/**
	 * presents use cases on screen for compare actions
	 * 
	 * @param textViewer
	 * @param useCase
	 */
	@Deprecated
	public void setUseCaseContentToTextViewer(TextViewer textViewer,
			UseCase useCase, List<SimilarBlock> similarBlocks) {

		String result = "";
		result += "======= " + useCase.getName() + " ========\n";
		if (useCase.getContext().getPreconditions().size() > 0)
			result += ("\nPRECONDITIONS: ");
		for (Condition c : useCase.getContext().getPreconditions()) {
			result += (" - " + c.getDescription());
		}
		if (useCase.getContext().getPostconditions().size() > 0)
			result += ("\nPOSTCONDITIONS: ");
		for (Condition c : useCase.getContext().getPostconditions()) {
			result += (" - " + c.getDescription());
		}
		for (Flow flow : useCase.getFlows()) {
			result += ("\nFlow: " + flow.getName());
			result += ("\nEvents: ");
			for (Event e : flow.getEvents())
				result += ("\n#" + e.getNumber() + " - " + "ID: "
						+ e.getEventId() + " - " + e.getDetail() + "");
		}
		textViewer.getTextWidget().setText(result);

		int[] colors = { SWT.COLOR_RED, SWT.COLOR_BLUE, SWT.COLOR_GREEN,
				SWT.COLOR_YELLOW, SWT.COLOR_GRAY };
		int s = 0;
		for (SimilarBlock sb : similarBlocks) {
			int startEvent = sb.getBeginIndex() + 1;
			int endEvent = sb.getEndIndex() + 1;
			int shift = 0;
			for (int i = (result.indexOf("#" + endEvent)); i < result.length()
					&& !result.substring(i, i + 1).equals("\n"); i++) {
				shift++;
			}

			// ==Aplico un estilo al texto similar==
			StyleRange aStyle = new StyleRange();
			aStyle.fontStyle = SWT.BOLD;
			aStyle.foreground = Display.getCurrent().getSystemColor(
					s < 5 ? colors[s] : SWT.COLOR_MAGENTA);

			aStyle.start = result.indexOf("#" + startEvent);
			aStyle.length = (result.indexOf("#" + endEvent)) - aStyle.start
					+ shift;

			textViewer.getTextWidget().setStyleRange(aStyle);
			s++;
			// ====================================
		}
		textViewer.refresh();
	}

	/**
	 * Método que presenta los casos de uso en las pantallas para comparación
	 * 
	 * @param textViewer
	 * @param useCase
	 */
	public static void setCompareView(TreeViewer treeViewer, UseCase useCase,
			List<SimilarBlock> similarBlocks) {

		treeViewer.setContentProvider(new UseCaseTreeContentProvider(useCase));
		treeViewer.setLabelProvider(ucLabel);
		treeViewer.setInput(ucref);
		treeViewer.expandToLevel(TreeViewer.ALL_LEVELS);

		TreeItem[] tree = treeViewer.getTree().getItems();
		TreeItem[] innerTree = null;
		int count = 0;
		int[] colors = { SWT.COLOR_RED, SWT.COLOR_BLUE, SWT.COLOR_GREEN,
				SWT.COLOR_YELLOW, SWT.COLOR_MAGENTA };
		for (int i = 0; i < tree.length; i++) {
			System.out.println(tree[i].getData());
			if (tree[i].getData() instanceof Flow) {
				innerTree = tree[i].getItems();
				for (SimilarBlock similar : similarBlocks) {
					boolean isSimilar = false;
					if (similar.getFlow().getName()
							.equals(((Flow) tree[i].getData()).getName())) {
						for (Event e : similar.getSimilarEvents()) {
							for (TreeItem ti : innerTree) {
								if (ti.getData() instanceof Event) {
									Event treeEvent = (Event) ti.getData();
									if (treeEvent.getNumber().equals(
											e.getNumber())) {
										if (ti.getBackground().getBlue() == 255
												&& ti.getBackground()
														.getGreen() == 255
												&& ti.getBackground().getRed() == 255) {
											ti.setBackground(Display
													.getCurrent()
													.getSystemColor(
															colors[count % 5]));
										} else {
											ti.setForeground(Display
													.getCurrent()
													.getSystemColor(
															colors[count % 5]));
										}
										isSimilar = true;
										break;
									}
								}
							}
						}
					}
					if (isSimilar)
						count++;
				}
			}
		}
	}

	/**
	 * Método that presents a use case on screen
	 * 
	 * @param textViewer
	 * @param useCase
	 */
	public static void setCompareView(TreeViewer treeViewer, UseCase useCase) {

		if (useCase == null) {
			UCRefactoringFactory factory = UCRefactoringFactory.eINSTANCE;
			UseCase emptyUseCase = factory.createUseCase();
			treeViewer.setContentProvider(new UseCaseTreeContentProvider(
					emptyUseCase));
			treeViewer.setLabelProvider(ucLabel);
			treeViewer.setInput(ucref);
		} else {
			treeViewer.setContentProvider(new UseCaseTreeContentProvider(
					useCase));
			treeViewer.setLabelProvider(ucLabel);
			treeViewer.setInput(ucref);
			treeViewer.expandToLevel(TreeViewer.ALL_LEVELS);
		}
	}

	public static void updateAlignmentLeft(List<SimilarBlock> similarBlocks,
			UseCase useCaseA, UseCase useCaseB, AlignmentX2Result align) {
		// Chek to only add the blocks corresponding to the updated align
		List<SimilarBlock> newSimilars = new ArrayList<SimilarBlock>();
		if (align != null) {
			for (SimilarBlock sb : similarBlocks) {
				if (sb.getParent() != null && sb.getParent().equals(align)) {
					newSimilars.add(sb);
				}
			}
			if (useCaseA.getName().compareTo(useCaseB.getName()) > 0) {
				align.setSimilarBlocksA(newSimilars);
			} else {
				align.setSimilarBlocksB(newSimilars);
			}
		}
	}

	public static void updateAlignmentRight(List<SimilarBlock> similarBlocks,
			UseCase useCaseA, UseCase useCaseB, AlignmentX2Result align) {
		// Chek to only add the blocks corresponding to the updated align
		List<SimilarBlock> newSimilars = new ArrayList<SimilarBlock>();
		if (align != null) {
			for (SimilarBlock sb : similarBlocks) {
				if (sb.getParent() != null && sb.getParent().equals(align)) {
					newSimilars.add(sb);
				}
			}
			if (useCaseA.getName().compareTo(useCaseB.getName()) > 0) {
				align.setSimilarBlocksB(newSimilars);
			} else {
				align.setSimilarBlocksA(newSimilars);
			}
		}
	}

	public static void updateAlignment(List<SimilarBlock> similarBlocks,
			UseCase useCaseA, Flow flowA, UseCase useCaseB, Flow flowB) {
		String key = SimilarityAnalyzer.getAlignmentKey(useCaseA, flowA,
				useCaseB, flowB);
		AlignmentX2Result align = alignResults.get(key);
		if (useCaseA.getName().compareTo(useCaseB.getName()) > 0) {
			align.setSimilarBlocksA(similarBlocks);
		} else {
			align.setSimilarBlocksB(similarBlocks);
		}
	}

	public static String askForUCName() {
		Shell shell = new Shell();
		NewUseCaseDialog dialog = new NewUseCaseDialog(shell);
		return dialog.openDialog();
	}

	public static int newUseCaseDialog(){
		Shell shell = UCRUseCasesView.container_1.getShell();
	    UCRDialog = new UCRNewUseCaseDialog(shell);
	    return UCRDialog.open();
	}
}
