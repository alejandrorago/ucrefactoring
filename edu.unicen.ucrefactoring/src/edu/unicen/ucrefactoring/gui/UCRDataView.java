package edu.unicen.ucrefactoring.gui;

import java.awt.Event;
import java.beans.EventSetDescriptor;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.metrics.DuplicateMetric;
import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.MetricCollector;
import edu.unicen.ucrefactoring.metrics.NonModularFRMetric;
import edu.unicen.ucrefactoring.metrics.NonSenseActorMetric;
import edu.unicen.ucrefactoring.metrics.NonSenseUseCaseMetric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.refactorings.DeleteActorRefactoring;
import edu.unicen.ucrefactoring.refactorings.DeleteUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtensionRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtractUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.GeneralizationRefactoring;
import edu.unicen.ucrefactoring.refactorings.InclusionRefactoring;
import edu.unicen.ucrefactoring.refactorings.MergeUseCasesRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;
import edu.unicen.ucrefactoring.refactorings.RefactoringCreator;

public class UCRDataView extends ViewPart {

	public static final String ID = "edu.unicen.ucrefactoring.gui.UCRDataView"; //$NON-NLS-1$

	//Providers
	private static UCRefactoringDetection ucref;
	private static RefactoringLabelProvider extLabel;
	private RefactoringListContentProvider extListContentProvider;
	public static Button btnAnalyze; 
	public static Button btnApply;
	
	//core structures
	private HashMap<String,Metric> metrics;
	private HashMap<String,Refactoring> refactorings;
	ListViewer listViewer;
	
	
	
	public UCRDataView() {	
		//Providers
		ucref = new UCRefactoringDetection(false);
		extLabel = new RefactoringLabelProvider();
		refactorings = new HashMap<String,Refactoring>();
		metrics = new HashMap<String,Metric>();
	}
	

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		btnAnalyze = new Button(container, SWT.NONE);
		btnAnalyze.setText("Analyze");
		
		btnApply = new Button(container, SWT.NONE);
		btnApply.setText("Apply");
		btnApply.setEnabled(false);
		
		listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(list, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING, false)
						.add(btnAnalyze, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(btnApply, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(14)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(list, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
							.addContainerGap())
						.add(gl_container.createSequentialGroup()
							.add(btnAnalyze, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addPreferredGap(LayoutStyle.UNRELATED)
							.add(btnApply, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.add(90))))
		);
		container.setLayout(gl_container);

		createActions();
		initializeToolBar();
		initializeMenu();
		createListeners();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
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
	 *  Create the listeners
	 */
	private void createListeners(){
		
		btnAnalyze.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ucref = new UCRefactoringDetection(UCRUseCasesView.ucref.getUseCaseModel());
				metrics = new HashMap<String, Metric>();
				refactorings = new HashMap<String, Refactoring>();
				
				collectMetrics();
				analyzeRefactorings();
				extListContentProvider =  new RefactoringListContentProvider(refactorings.values());				
				listViewer.setContentProvider(extListContentProvider);
				listViewer.setLabelProvider(extLabel);
				listViewer.setInput(ucref);
				
				
				
			}
		});
		
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				Refactoring ref;
				if (selection.size()==1){
					ref = (Refactoring) selection.getFirstElement();
					ref.applyRefactoring();
					UCRUseCasesView.updateUseCasesView();
					btnApply.setEnabled(false);
					cleanViews();
					showRefactoring(ref,false);
				}
			}
		});
		
		//Listener for double click event on refactoring list
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				Refactoring ref = null;
				if (selection.size()==1){
					ref = (Refactoring)selection.toList().get(0);
				}
				showRefactoring(ref,true);
			}
		});
		
		//Listener to enable/disable APPLY button
		listViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) listViewer.getSelection();
				if (selection.size()==1){
					btnApply.setEnabled(true);
				}
				else{
					btnApply.setEnabled(false);
				}
			}
		});
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	
	public void collectMetrics(){
		MetricCollector metricCollector = new MetricCollector(ucref.getUseCaseModel(),UCRUseCasesView.alignResults);
		metricCollector.collectMetrics();
		this.metrics = metricCollector.getCollectedMetrics();
	}
	
	public void analyzeRefactorings(){
		RefactoringCreator refactoringCreator = new RefactoringCreator(this.metrics);
		this.refactorings = refactoringCreator.createRefactorings();
	}
	
	private void cleanViews(){
		UCRCompareView.btnCleanLeft.notifyListeners(SWT.Selection, new org.eclipse.swt.widgets.Event());
		UCRCompareView.btnCleanRight.notifyListeners(SWT.Selection, new org.eclipse.swt.widgets.Event());
		btnAnalyze.notifyListeners(SWT.Selection, null);
	}
	
	private void showRefactoring(Refactoring ref, boolean doPaint){
		if (ref != null){
			if (ref instanceof ExtractUseCaseRefactoring || ref instanceof DeleteUseCaseRefactoring){
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,ref.getUseCase(),new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = ref.getUseCase();
				UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
				UCRCompareView.updateButtonsAndLabels();
			}
			else if (ref instanceof DeleteActorRefactoring){
				UseCase useCase = UCRefactoringFactory.eINSTANCE.createUseCase();
				useCase.setName(((DeleteActorRefactoring) ref).getActor().getName());
				Flow flow = UCRefactoringFactory.eINSTANCE.createFlow();
				flow.setName(((DeleteActorRefactoring) ref).getActor().getName());
				useCase.getFlows().add(flow);
				
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCase,new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = useCase;
				UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
				UCRCompareView.updateButtonsAndLabels();
			}
			else{
				AlignmentX2Result align = ref.getAlignment();
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,align.getUseCaseA(),(doPaint)?align.getSimilarBlocksFromA():new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,align.getUseCaseB(),(doPaint)?align.getSimilarBlocksFromB():new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = ((doPaint)?align.getSimilarBlocksFromA():new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = align.getUseCaseA();
				UCRCompareView.similarBlocksRight = ((doPaint)?align.getSimilarBlocksFromB():new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = align.getUseCaseB();
				UCRCompareView.updateButtonsAndLabels();
			}
		}
	}
}
