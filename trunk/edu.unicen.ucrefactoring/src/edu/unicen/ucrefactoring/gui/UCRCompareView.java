package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.part.ViewPart;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.RowData;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UCRCompareView extends ViewPart {

	public static final String ID = "edu.unicen.ucrefactoring.gui.UCRCompareView"; //$NON-NLS-1$

	//Widgets
	static public TextViewer leftViewer;
	static public TextViewer rightViewer;
	static public Label lblLeft;
	static public Label lblRight;
	private Composite container_1;
	
	static public TreeViewer ucRight;
	static public TreeViewer ucLeft;
	
	static public Button btnCleanLeft;	
	static public Button btnCleanRight;
	static public Button btnCompare;
	
	public static UseCase useCaseLeft;
	public static UseCase useCaseRight;

	// Actions
	private Action addBlockAction;
	private Action removeBlockAction;
	
	
	//Showed SimilarBlocks
	public static List<SimilarBlock> similarBlocksLeft;
	public static List<SimilarBlock> similarBlocksRight;
	private List<Event> candidates; 
	public boolean isLeft = true;
	private Composite composite;
	private Composite composite_1;

	
	public UCRCompareView() {
		similarBlocksLeft = new ArrayList<SimilarBlock>();
		similarBlocksRight = new ArrayList<SimilarBlock>();
		candidates = new ArrayList<Event>();
	}

	//==============Servicios==========================
	
	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {	
		// Create Layout
		container_1 = new Composite(parent, SWT.NONE);

		
		createWidgets();
		createListeners();
		createActions();
		initListContextMenu();
		
		
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		
		//Adding
		addBlockAction = new Action() {			
			public void run(){
				Flow flow = null;
				AlignmentX2Result align = null;
				//boolean a = (useCaseLeft.getName().compareTo(useCaseRight.getName()) > 0);

				if (isLeft){				
					for (SimilarBlock sb : similarBlocksLeft){
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								for (Event e2 : candidates){
									if (!e2.equals(e)){
										sb.getSimilarEvents().add(e2);		
									}
								}								
								flow = sb.getFlow();
								align = sb.getParent();
								break;
							}
						}
					}
					UCRUseCasesView.setCompareView(ucLeft, useCaseLeft,  similarBlocksLeft);
					UCRUseCasesView.updateAlignmentLeft(similarBlocksLeft, useCaseLeft, useCaseRight,  align);
				}
				else{
					for (SimilarBlock sb : similarBlocksRight){
						boolean hasEvent = false;
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								for (Event e2 : candidates){
									if (!e2.equals(e)){
										sb.getSimilarEvents().add(e2);		
									}
								}								
								flow = sb.getFlow();
								align = sb.getParent();
								hasEvent = true;
								break;
							}
						}
						if (hasEvent){
							break;
						}
					}
					UCRUseCasesView.setCompareView(ucRight, useCaseRight, similarBlocksRight);
					UCRUseCasesView.updateAlignmentRight(similarBlocksRight, useCaseLeft, useCaseRight, align );
				}
				
				
			
								
			}
		};
	
		addBlockAction.setText("Attach Event to Block");
		addBlockAction.setToolTipText("Attaches the selection of events to the selected block");
		
		removeBlockAction = new Action() {			
			public void run(){
				Flow flow = null;
				AlignmentX2Result align = null;
				List<SimilarBlock> removeCandidates = new ArrayList<SimilarBlock>();
				if (isLeft){
					for (SimilarBlock sb : similarBlocksLeft){
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								List<Event> aux = sb.getSimilarEvents();
								aux.removeAll(candidates);
								if (aux.size()==0){
									removeCandidates.add(sb);//similarBlocksLeft.remove(sb);
								}
								else{
									sb.setSimilarEvents(aux);
								}
								flow = sb.getFlow();
								align = sb.getParent();
								break;
							}
						}
					}
					for (SimilarBlock r : removeCandidates){
						similarBlocksLeft.remove(r);
					}
					UCRUseCasesView.setCompareView(ucLeft, useCaseLeft, similarBlocksLeft);
					UCRUseCasesView.updateAlignmentLeft(similarBlocksLeft, useCaseLeft, useCaseRight , align);
				}
				else{
					for (SimilarBlock sb : similarBlocksRight){
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								List<Event> aux = sb.getSimilarEvents();
								aux.removeAll(candidates);
								if (aux.size()==0){
									removeCandidates.add(sb);//similarBlocksRight.remove(sb);
								}
								else{
									sb.setSimilarEvents(aux);
								}
								flow = sb.getFlow();
								align = sb.getParent();
								break;
							}
						}
					}
					for (SimilarBlock r : removeCandidates){
						similarBlocksLeft.remove(r);
					}
					UCRUseCasesView.setCompareView(ucRight, useCaseRight, similarBlocksRight);
					UCRUseCasesView.updateAlignmentRight(similarBlocksRight, useCaseLeft, useCaseRight, align  );
					
				}
								
			}
		};
	
		removeBlockAction.setText("Remove Events from Block");
		removeBlockAction.setToolTipText("Remove the selection of events from the selected block");	
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
	
	/**
	 * TODO: Figure out whether to use MenuManager or the default IMenuManager to add context menu 
	 * Create the context popup menu
	 */
	private void initListContextMenu()	{
		MenuManager menuManager = new MenuManager("#PopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(addBlockAction);	
				manager.add(removeBlockAction);	

			}			

		});

		Menu menuLeft = menuManager.createContextMenu(ucLeft.getTree());
		ucLeft.getTree().setMenu(menuLeft);
		getSite().registerContextMenu(menuManager, ucLeft);
		

		Menu menuRight = menuManager.createContextMenu(ucRight.getTree());
		ucRight.getTree().setMenu(menuRight);
		getSite().registerContextMenu(menuManager, ucRight);
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	/**
	 * Create the view widgets
	 * @param container
	 */
	public void createWidgets(){
		{
			composite = new Composite(container_1, SWT.NONE);
		}
		
		composite_1 = new Composite(container_1, SWT.NONE);
		
		ucRight = new TreeViewer(composite_1, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		Tree tree_1 = ucRight.getTree();
		GroupLayout gl_container_1 = new GroupLayout(container_1);
		gl_container_1.setHorizontalGroup(
			gl_container_1.createParallelGroup(GroupLayout.LEADING)
				.add(composite, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
				.add(composite_1, GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
		);
		gl_container_1.setVerticalGroup(
			gl_container_1.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container_1.createSequentialGroup()
					.add(composite_1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		);
		ucLeft = new TreeViewer(composite_1, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		Tree tree = ucLeft.getTree();
		
		lblLeft = new Label(composite_1, SWT.NONE);
		
		lblRight = new Label(composite_1, SWT.NONE);
		GroupLayout gl_composite_1 = new GroupLayout(composite_1);
		gl_composite_1.setHorizontalGroup(
			gl_composite_1.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite_1.createSequentialGroup()
					.addContainerGap()
					.add(gl_composite_1.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, lblLeft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, tree, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
					.add(gl_composite_1.createParallelGroup(GroupLayout.LEADING)
						.add(gl_composite_1.createSequentialGroup()
							.add(17)
							.add(tree_1, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
						.add(gl_composite_1.createSequentialGroup()
							.add(18)
							.add(lblRight, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_composite_1.setVerticalGroup(
			gl_composite_1.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite_1.createSequentialGroup()
					.add(5)
					.add(gl_composite_1.createParallelGroup(GroupLayout.LEADING)
						.add(lblLeft)
						.add(lblRight))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_composite_1.createParallelGroup(GroupLayout.LEADING)
						.add(tree_1, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
						.add(tree, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
		);
		composite_1.setLayout(gl_composite_1);
		
		btnCleanLeft = new Button(composite, SWT.CENTER);
		btnCleanLeft.setText("Clean Left");
		btnCleanLeft.setEnabled(false);
		
		btnCleanRight = new Button(composite, SWT.CENTER);
		btnCleanRight.setText("Clean Right");
		btnCleanRight.setEnabled(false);
		
		btnCompare = new Button(composite, SWT.CENTER);		
		btnCompare.setText("Compare");
		btnCompare.setEnabled(false);
		
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(38)
					.add(btnCleanLeft, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.add(129)
					.add(btnCompare, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.add(125)
					.add(btnCleanRight, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.add(38))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.TRAILING)
				.add(GroupLayout.LEADING, gl_composite.createSequentialGroup()
					.add(7)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(gl_composite.createSequentialGroup()
							.add(btnCleanLeft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())
						.add(gl_composite.createSequentialGroup()
							.add(btnCleanRight)
							.add(5))
						.add(gl_composite.createSequentialGroup()
							.add(btnCompare, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())))
		);
		composite.setLayout(gl_composite);
		container_1.setLayout(gl_container_1);
		
	}
	
	/**
	 * Create view listeners
	 */
	public void createListeners(){
		//Listener de cambio de selección en la lista
		ucLeft.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				isLeft = true;
				IStructuredSelection selection = (IStructuredSelection) ucLeft.getSelection();
				
				if (selection != null && selection.size()>0){
					candidates = selection.toList();
					addBlockAction.setEnabled(true);
					removeBlockAction.setEnabled(true);
				}
				else{
					candidates = new ArrayList<Event>();
					addBlockAction.setEnabled(false);
					removeBlockAction.setEnabled(false);
				}
				
				
			}
		});
		
		ucRight.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				isLeft = false;
				IStructuredSelection selection = (IStructuredSelection) ucRight.getSelection();
				
				if (selection != null && selection.size()>0){	
					candidates = selection.toList();
					addBlockAction.setEnabled(true);
					removeBlockAction.setEnabled(true);
				}
				else{
					candidates = new ArrayList<Event>();
					addBlockAction.setEnabled(false);
					removeBlockAction.setEnabled(false);
				}
				
				
			}
		});
		
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					if (useCaseLeft != null && useCaseRight != null){
						List<SimilarBlock> similarBlocksLeft = new ArrayList<SimilarBlock>();
						List<SimilarBlock> similarBlocksRight = new ArrayList<SimilarBlock>();
						for (Flow flowA : useCaseLeft.getFlows()){					
							for (Flow flowB : useCaseRight.getFlows()){						
								String key = SimilarityAnalyzer.getAlignmentKey(useCaseLeft,flowA,useCaseRight,flowB);
								AlignmentX2Result alignResult = UCRUseCasesView.alignResults.get(key);
								if (alignResult!=null){
									List<SimilarBlock> similarBlocksA = alignResult.getSimilarBlocksFromA();
									List<SimilarBlock> similarBlocksB = alignResult.getSimilarBlocksFromB();
									if (useCaseLeft.getName().compareTo(useCaseRight.getName())>0){
										similarBlocksLeft.addAll(similarBlocksA);
										similarBlocksRight.addAll(similarBlocksB);
									}
									else{
										similarBlocksLeft.addAll(similarBlocksB);
										similarBlocksRight.addAll(similarBlocksA);
									}
								}
							}
						}
	
						UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCaseLeft,similarBlocksLeft);
						UCRUseCasesView.setCompareView(UCRCompareView.ucRight,useCaseRight,similarBlocksRight);
					
						UCRCompareView.similarBlocksLeft = (similarBlocksLeft);
						UCRCompareView.similarBlocksRight = (similarBlocksRight);
						UCRCompareView.updateButtonsAndLabels();
				}
			}
		});
		
		btnCleanLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					if (useCaseLeft != null){
						useCaseLeft = null;
						UCRUseCasesView.useCaseA = null;	
						UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,null);
						btnCleanLeft.setEnabled(false);
						if (useCaseRight != null)
							UCRUseCasesView.setCompareView(UCRCompareView.ucRight,useCaseRight);
						lblLeft.setText("");
						btnCompare.setEnabled(false);

				}
			}
		});
		
		btnCleanRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					if (useCaseRight != null){
						useCaseRight = null;
						UCRUseCasesView.useCaseB = null;	
						UCRUseCasesView.setCompareView(UCRCompareView.ucRight,null);
						btnCleanRight.setEnabled(false);
						if (useCaseLeft != null)
							UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCaseLeft);
						lblRight.setText("");
						btnCompare.setEnabled(false);
				}
			}
		});
		
	}
	
	
	public static void updateButtonsAndLabels(){
		if (useCaseLeft!=null && useCaseLeft.getFlows().size()>0){
			btnCleanLeft.setEnabled(true);
			lblLeft.setText(useCaseLeft.getName());
		}
		else{
			btnCleanLeft.setEnabled(false);
			lblLeft.setText(new String());
		}
		if (useCaseRight != null && useCaseRight.getFlows().size()>0){
			btnCleanRight.setEnabled(true);
			lblRight.setText(useCaseRight.getName());
		}
		else{
			btnCleanRight.setEnabled(false);
			lblRight.setText(new String());

		}
		if (useCaseLeft != null && useCaseRight != null && useCaseLeft.getFlows().size()>0 && useCaseRight.getFlows().size()>0){
			btnCompare.setEnabled(true);
		}
		else{
			btnCompare.setEnabled(false);
		}
	}
	
}

