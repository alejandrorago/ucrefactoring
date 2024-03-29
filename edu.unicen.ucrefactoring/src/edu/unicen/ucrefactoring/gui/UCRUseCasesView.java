package edu.unicen.ucrefactoring.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Condition;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.util.Constants;
import edu.unicen.ucrefactoring.visualiser.UCRVisualContentProvider;

/**
 * View that shows the use case and aspect list from the model
 * @author migue
 *
 */
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
	private Action setPrimaryActorAction;
	private Action addSecondaryActorAction;
	private Action deleteUseCaseAction;

	// widgets
	private static ListViewer ucList;
	private org.eclipse.swt.widgets.List list;
	private Button btnLoadUseCase;
	private Button btnSave;
	private Label lblUseCaseModel;
	private static FileDialog fileChooser;
	private static FileDialog fileSaver;

	private static FileDialog swtDialog; 
	public static UCRNewUseCaseDialog UCRDialog;
	//public static AssignToAspectDialog aspectDialog;
	public static UCRAssignToAspectDialog aspectDialog;
	//public static DeleteEntityDialog deleteDialog;
	public static UCRWarningDialog warningDialog;
	public static UCRMessageDialog messageDialog;

	//public static SetPrimaryActorDialog primaryActorDialog;
	public static UCRSetActorDialog primaryActorDialog;


	// special flag for double-clicking the use case list
	private boolean ucflag = true;
	
	public UCRUseCasesView() {
		initUseCasesView();
	}

	// ==============Servicios==========================

	public static void initUseCasesView() {
		// init file choosers
		Shell shell = new Shell();
		fileChooser = new FileDialog(shell,SWT.OPEN);
		fileChooser.setFilterNames(new String[] {"UCRefactoring", "UCS" , "All Files" });
		fileChooser.setFilterExtensions(new String[] {"*.ucrefactoring" , "*.ucs" , "*.*" });
			
		fileSaver =  new FileDialog(shell,SWT.SAVE);
		fileSaver.setFilterNames(new String[] {"UCRefactoring" });
		fileSaver.setFilterExtensions(new String[] {"*.ucrefactoring" });
				  	
		//=========================  	
		
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
		populateUCList();
		// TODO: Find out a better way to ini•••••••tialize and compare use cases
		ucref.compareUseCases();
		alignResults = ucref.getSimilarityAnalizer().getAlignmentResult();

		useCaseA = null;
		useCaseB = null;
	}

	private static void populateUCList() {
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

		btnLoadUseCase.setText("Load Model");

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
		
		btnSave = new Button(parent, SWT.NONE);
		btnSave.setText("Save");
		GroupLayout gl_parent = new GroupLayout(parent);
		gl_parent.setHorizontalGroup(
			gl_parent.createParallelGroup(GroupLayout.LEADING)
				.add(container_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
				.add(gl_parent.createSequentialGroup()
					.add(12)
					.add(btnLoadUseCase, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnSave, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_parent.setVerticalGroup(
			gl_parent.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_parent.createSequentialGroup()
					.add(container_1, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_parent.createParallelGroup(GroupLayout.LEADING)
						.add(btnLoadUseCase)
						.add(btnSave))
					.addContainerGap())
		);
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

		ucList = new ListViewer(container, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
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
		
		setPrimaryActorAction = new Action() {
			public void run (){
				if(UCRUseCasesView.setPrimaryActor() == 0){
					String aName = primaryActorDialog.getActorName();
					Actor newActor = null;
					for(Actor a: ucref.getUseCaseModel().getActors()){
						if (a.getName().equalsIgnoreCase(aName)){
							newActor = a;
						}
					}
					if(newActor == null){
						newActor = UCRefactoringFactory.eINSTANCE.createActor();
						newActor.setName(aName);
						ucref.getUseCaseModel().getActors().add(newActor);
					}
					((UseCase) (((IStructuredSelection) ucList
							.getSelection()).toList().get(0))).setPrimaryActor(newActor);
				}
			}
		};
		setPrimaryActorAction.setText("Set Primary Actor");
		setPrimaryActorAction.setToolTipText("Sets the primary actor for the use case.");
		
		addSecondaryActorAction = new Action() {
			public void run (){
				if(UCRUseCasesView.setPrimaryActor() == 0){
					String aName = primaryActorDialog.getActorName();
					boolean exists = false;
					for(int i = 0 ; i < ucref.getUseCaseModel().getActors().size(); i++){
						Actor a = ucref.getUseCaseModel().getActors().get(i);
						if (a.getName().equalsIgnoreCase(aName)){
							String ucname = ((UseCase) (((IStructuredSelection) ucList
									.getSelection()).toList().get(0))).getName();
							for(int j=0; j < ucref.getUseCaseModel().getUseCases().size(); j++){
								UseCase uc = ucref.getUseCaseModel().getUseCases().get(j);
								if(uc.getName().equalsIgnoreCase(ucname)){
									uc.getSecondaryActors().add(a);
									ucref.getUseCaseModel().getActors().add(a);
								}
							}
							exists = true;
						}
					}
					if(!exists){
						Actor newActor = UCRefactoringFactory.eINSTANCE.createActor();
						newActor.setName(aName);
						ucref.getUseCaseModel().getActors().add(newActor);
						((UseCase) (((IStructuredSelection) ucList
								.getSelection()).toList().get(0))).getSecondaryActors().add(newActor);
					}
				}
			}
		};
		addSecondaryActorAction.setText("Add Secondary Actor");
		addSecondaryActorAction.setToolTipText("Adds a secondary actor for the use case.");
		
		deleteUseCaseAction = new Action() {
			public void run (){
				UseCase candidate = ((UseCase) (((IStructuredSelection) ucList.getSelection()).toList().get(0)));

				String question = "Are you sure you want to delete the Use Case '"+candidate.getName()+"' ? ";
				int cancel = warningDialog(question);				
				if (cancel == 0){	
					ucref.getUseCaseModel().getUseCases().remove(candidate);
					UCRDataView.resetView(ucref);
					UCRCompareView.resetView();
					resetView();
					updateUseCasesView();
				}
			}
		};
		deleteUseCaseAction.setText("Delete Use Case");
		deleteUseCaseAction.setToolTipText("Removes Use Case from Use Case Model.");
	}

	/**
	 * TODO: Figure out whether to use MenuManager or the default IMenuManager
	 * to add context menu Create the context popup menu
	 */
	private void initListContextMenu() {
		  
		  MenuManager menuManager = new MenuManager("#PopupMenu");
		  menuManager.setRemoveAllWhenShown(true);
		  
		  menuManager.addMenuListener(new
		  IMenuListener() {
		  
		  @Override public void menuAboutToShow(IMenuManager manager) {
		            manager.add(setPrimaryActorAction);
		            manager.add(addSecondaryActorAction);
		            manager.add(deleteUseCaseAction);
		            }
		  
		            });
		            Menu menu = menuManager.createContextMenu(ucList.getList());
		            ucList.getList().setMenu(menu);
		            getSite().registerContextMenu(menuManager, ucList);
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
										.getFullDescription());
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
					if (ucflag){
						useCaseA = (UseCase) selection.toList().get(0);
						ucflag = !ucflag;
					}
					else{
						useCaseB = (UseCase) selection.toList().get(0);
						ucflag = !ucflag;
					}
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
		

		
//		btnLoadUseCase.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				
//				String path = swtDialog.open();
//				if (path != null && path.endsWith(".ucrefactoring")) {
//					File file = new File(path);
//					String s = file.getName();
//					System.out.println(s);
//				}
//				
//				
//			}
//
//		});
		

		btnLoadUseCase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String returnVal = fileChooser.open();
			
				if (returnVal !=null) {
					//String fileName = fileChooser.getSelectedFile().getName();
					String fileName = returnVal;
					//if user selects ucs file					
					if (fileName.substring(fileName.lastIndexOf("."), fileName.length()).equals(".ucs")){
						// get ucm file and root file path
						File useCaseModel = new File(fileName);
						
						String filePath = useCaseModel.getAbsolutePath();
						// get uima file
						String uimaPath = fileName.substring(0, fileName.lastIndexOf("."))+ ".uima";
						//get the rea file - aspects data
						String reaPath = fileName.substring(0, fileName.lastIndexOf("."))+ ".rea";
						
						File useCaseUima = new File(uimaPath);
						File useCaseRea = new File(reaPath);

						if (useCaseUima.exists()){
							if(useCaseRea.exists()){
								ucref = new UCRefactoringDetection(useCaseModel,
										useCaseUima, useCaseRea, true);
							}
							else{
								ucref = new UCRefactoringDetection(useCaseModel,
										useCaseUima, null, true);
							}
							ucLabel = new UseCaseLabelProvider();
							ucListContentProvider = new UseCaseListContentProvider(
									ucref.getUseCaseModel());
		
							// set use case model name
							lblUseCaseModel.setText(ucref.getUseCaseModel().getName());
		
							// populate list
							UCRUseCasesView.populateUCList();
		
							// enable analyze button
							UCRDataView.btnAnalyze.setEnabled(true);
		
							System.out.println("Opening: " + useCaseModel.getName()+ ".");
							
							UCRUseCasesView.resetView();
							UCRDataView.resetView(ucref);
							UCRCompareView.resetView();
							if (!useCaseRea.exists()){
								String message = "The REA file asociated with the selected UCS cannot be found.\nAspect Refactoring analysis will not be performed.";
								messageDialog(message);
								//JOptionPane.showMessageDialog(fileChooser, "The REA file asociated with the selected UCS cannot be found.\nAspect Refactoring analysis will not be performed.", "REA file not found", JOptionPane.WARNING_MESSAGE);
							}
	
						}
						else{
							//MessageDialog messageDialog = new MessageDialog();
							
							String message = "The UIMA file asociated with the selected UCS cannot be found.\nPlease select another file.";
							messageDialog(message);
							//JOptionPane.showMessageDialog(fileChooser, "The UIMA file asociated with the selected UCS cannot be found.\nPlease select another file.", "UIMA file not found", JOptionPane.ERROR_MESSAGE);
						}
					}
					//else, if user selects ucrefactoring file

					else if(new File(fileName).exists()){
						File useCaseRefactoringDetection = new File(fileName);


						if (fileName.substring(fileName.lastIndexOf("."), fileName.length()).equals(".ucrefactoring")){
							// get uima file
							String uimaPath = fileName.substring(0, fileName.lastIndexOf("."))+ ".uima";
							//get the rea file - aspects data
							String reaPath = fileName.substring(0, fileName.lastIndexOf("."))+ ".rea";
							
							File useCaseRea = new File(reaPath);
							File useCaseUima = new File(uimaPath);
							if(useCaseUima.exists()){
								if(useCaseRea.exists()){
									ucref = new UCRefactoringDetection(useCaseRefactoringDetection, useCaseUima, useCaseRea, false);
								}
								else{
									ucref = new UCRefactoringDetection(useCaseRefactoringDetection, useCaseUima, null, false);
								}
							}
							else{
								if(useCaseRea.exists()){
									ucref = new UCRefactoringDetection(useCaseRefactoringDetection,null, useCaseRea, false);
								}
								else{
									ucref = new UCRefactoringDetection(useCaseRefactoringDetection, null, null, false);
								}
							}
							
							
							ucLabel = new UseCaseLabelProvider();
							ucListContentProvider = new UseCaseListContentProvider(
									ucref.getUseCaseModel());
		
							// parse use case model
							ucref.compareUseCases();
							alignResults = ucref.getSimilarityAnalizer().getAlignmentResult();
		
							// set use case model name
							lblUseCaseModel.setText(ucref.getUseCaseModel().getName());
		
							// populate list
							UCRUseCasesView.populateUCList();
		
							// enable analyze button
							UCRDataView.btnAnalyze.setEnabled(true);
		
							System.out.println("Opening: " + useCaseRefactoringDetection.getName()+ ".");
							
							UCRUseCasesView.resetView();
							UCRDataView.resetView(ucref);
							UCRCompareView.resetView();
							String message = "";
							if (!useCaseUima.exists() && !useCaseRea.exists()){
								message = "The REA and UIMA files asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.";
								messageDialog(message);
								//JOptionPane.showMessageDialog(fileChooser, "The REA and UIMA files asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.", "REA and UIMA file not found", JOptionPane.WARNING_MESSAGE);
							}
							else if (!useCaseRea.exists()){
								message = "The REA file asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.";
								messageDialog(message);
								//JOptionPane.showMessageDialog(fileChooser, "The REA file asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.", "REA file not found", JOptionPane.WARNING_MESSAGE);
							}
							else if (!useCaseUima.exists()){
								message = "The UIMA file asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.";
								messageDialog(message);
								//JOptionPane.showMessageDialog(fileChooser, "The UIMA file asociated with the selected UCREFACTORING cannot be found.\nAspect Refactoring analysis will not be performed.", "UIMA file not found", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
					
					UCRVisualContentProvider.setUseCaseModel(ucref.getUseCaseModel());
					
				} else {
					System.out.println("Open command cancelled by user.");
				}

			}
		});
	
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) { 
				String returnVal = fileSaver.open();

				if (returnVal != null) {
					String fileName = returnVal;
					String ext="";
			        String extension = fileSaver.getFilterExtensions()[0];
			        if(extension.equals("*.ucrefactoring")){ 
			           ext=".ucrefactoring"; 
			        }
			        if (fileName.endsWith(".ucrefactoring")){
			        	ucref.exportRefactoredModel(fileName);
			        }
			        else{
			        	ucref.exportRefactoredModel(fileName+ext);
			        }
					
				} else {
					System.out.println("Save command cancelled by user.");
				}

			}
		});
		
		
		getViewSite().getPage().addPartListener(new IPartListener2() {
			
			
			@Override
			public void partOpened(IWorkbenchPartReference arg0) {
				try {
					if (arg0!=null){
						if (arg0.getPartName().equals(Constants.UCRCompareView) && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("edu.unicen.ucrefactoring.gui.UCRUseCasesView")!=null){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRUseCasesView");
						}
						else if (arg0.getPartName().equals(Constants.UCRUseCaseView) && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()!=null ){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRCompareView");
						}
						else if (arg0.getPartName().equals(Constants.Visualiser) && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.contribution.visualiser.views.Menu")!=null){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.contribution.visualiser.views.Menu");
						}
						else if (arg0.getPartName().equals(Constants.VisualiserMenu) && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.eclipse.contribution.visualiser.views.Visualiser")!=null){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.contribution.visualiser.views.Visualiser");
						}
						
					}
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void partVisible(IWorkbenchPartReference arg0) {
				try {
					if (arg0!=null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()!=null){
						if (arg0.getPartName().equals(Constants.UCRCompareView)){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRUseCasesView");
						}
						else if (arg0.getPartName().equals(Constants.UCRUseCaseView)){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRCompareView");
						}
						else if (arg0.getPartName().equals(Constants.Visualiser)){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.contribution.visualiser.views.Menu");
						}
						else if (arg0.getPartName().equals(Constants.VisualiserMenu)){
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.eclipse.contribution.visualiser.views.Visualiser");
						}
						
					}
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void partActivated(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partBroughtToTop(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partClosed(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partDeactivated(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partHidden(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void partInputChanged(IWorkbenchPartReference arg0) {
				// TODO Auto-generated method stub
				
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

		int[] colors = { SWT.COLOR_YELLOW, SWT.COLOR_GREEN, SWT.COLOR_CYAN,
				SWT.COLOR_RED, SWT.COLOR_MAGENTA };
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
		
		int[] colors = { SWT.COLOR_YELLOW, SWT.COLOR_GREEN, SWT.COLOR_CYAN,
				SWT.COLOR_RED, SWT.COLOR_MAGENTA };
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
		Shell shell = UCRUseCasesView.container_1.getShell();
		NewUseCaseDialog dialog = new NewUseCaseDialog(shell);
		return dialog.openDialog();
	}

	public static int setPrimaryActor() {
		Shell shell = UCRUseCasesView.container_1.getShell();
		//primaryActorDialog = new SetPrimaryActorDialog(shell);
		primaryActorDialog = new UCRSetActorDialog(shell);

		return primaryActorDialog.open();
	}
	
	public static int newUseCaseDialog(){
		Shell shell = UCRUseCasesView.container_1.getShell();
	    UCRDialog = new UCRNewUseCaseDialog(shell);
	    return UCRDialog.open();
	}
	
//	public static int extractAspectDialog(){
//		Shell shell = UCRUseCasesView.container_1.getShell();
//	    aspectDialog = new AssignToAspectDialog(shell);
//	    return aspectDialog.open();
//	}
	
	public static int extractAspectDialog(){
		Shell shell = UCRUseCasesView.container_1.getShell();
	    aspectDialog = new UCRAssignToAspectDialog(shell);
	    return aspectDialog.open();
	}
	
//	public static int deleteEntityDialog(String question){
//		Shell shell = new Shell();
//	    deleteDialog = new DeleteEntityDialog(shell, question);
//	    return deleteDialog.open();
//	}
	
	public static int warningDialog(String question){
		Shell shell = UCRUseCasesView.container_1.getShell();
	    warningDialog = new UCRWarningDialog(shell, question);
	    return warningDialog.open();
	}
	
	public static int messageDialog(String message){
		Shell shell = UCRUseCasesView.container_1.getShell();
	    messageDialog = new UCRMessageDialog(shell, message);
	    return messageDialog.open();
	}
	
	public static void resetView(){
		useCaseA = null;
		useCaseB = null;
		
	}
	
	public static void compareUseCases(){
		// parse use case model
		ucref.compareUseCases();
		alignResults = ucref.getSimilarityAnalizer().getAlignmentResult();
	}
}
