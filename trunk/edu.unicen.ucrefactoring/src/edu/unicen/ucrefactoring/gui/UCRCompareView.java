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
		ucLeft = new TreeViewer(container_1, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		Tree tree = ucLeft.getTree();
		
		ucRight = new TreeViewer(container_1, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		Tree tree_1 = ucRight.getTree();
		{
			composite = new Composite(container_1, SWT.NONE);
		}
		GroupLayout gl_container_1 = new GroupLayout(container_1);
		gl_container_1.setHorizontalGroup(
			gl_container_1.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container_1.createSequentialGroup()
					.addContainerGap()
					.add(gl_container_1.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, composite, GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
						.add(gl_container_1.createSequentialGroup()
							.add(tree, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
							.add(18)
							.add(tree_1, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_container_1.setVerticalGroup(
			gl_container_1.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container_1.createSequentialGroup()
					.addContainerGap()
					.add(gl_container_1.createParallelGroup(GroupLayout.LEADING)
						.add(tree_1, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
						.add(tree, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
					.addContainerGap())
		);
		
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
					.add(48)
					.add(btnCleanLeft, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.add(113)
					.add(btnCompare, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.add(113)
					.add(btnCleanRight, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.add(48))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(btnCleanLeft, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(btnCompare, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(btnCleanRight, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.add(5))
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
						UCRCompareView.updateButtons();
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
						btnCompare.setEnabled(false);
				}
			}
		});
		
	}
	
	
	public static void updateButtons(){
		if (useCaseLeft!=null && useCaseLeft.getFlows().size()>0){
			btnCleanLeft.setEnabled(true);
		}
		else{
			btnCleanLeft.setEnabled(false);
		}
		if (useCaseRight != null && useCaseRight.getFlows().size()>0){
			btnCleanRight.setEnabled(true);
		}
		else{
			btnCleanRight.setEnabled(false);
		}
		if (useCaseLeft != null && useCaseRight != null && useCaseLeft.getFlows().size()>0 && useCaseRight.getFlows().size()>0){
			btnCompare.setEnabled(true);
		}
		else{
			btnCompare.setEnabled(false);
		}
	}
	
}
