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

public class UCRCompareView extends ViewPart {

	public static final String ID = "edu.unicen.ucrefactoring.gui.UCRCompareView"; //$NON-NLS-1$

	//Widgets
	static public TextViewer leftViewer;
	static public TextViewer rightViewer;	
	private Composite container_1;
	
	static public TreeViewer ucRight;
	static public TreeViewer ucLeft;
	
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
		container_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		
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
				if (isLeft){
					for (SimilarBlock sb : similarBlocksLeft){
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								sb.getSimilarEvents().removeAll(candidates);
								flow = sb.getFlow();
								align = sb.getParent();
								break;
							}
						}
					}
					UCRUseCasesView.setCompareView(ucLeft, useCaseLeft, flow, similarBlocksLeft);
					UCRUseCasesView.updateAlignmentLeft(similarBlocksLeft, useCaseLeft, useCaseRight , align);
				}
				else{
					for (SimilarBlock sb : similarBlocksRight){
						for (Event e : candidates){
							if (sb.getSimilarEvents().contains(e)){
								sb.getSimilarEvents().removeAll(candidates);
								flow = sb.getFlow();
								align = sb.getParent();
								break;
							}
						}
					}
					UCRUseCasesView.setCompareView(ucRight, useCaseRight, flow, similarBlocksRight);
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
		
		ucRight = new TreeViewer(container_1, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		
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
	}

	
}

