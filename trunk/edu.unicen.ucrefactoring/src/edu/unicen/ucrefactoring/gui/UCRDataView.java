package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;

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
				}
				
				UCRUseCasesView.initUseCasesView();
				
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
				
				if (ref != null){
					if (ref instanceof ExtractUseCaseRefactoring || ref instanceof DeleteUseCaseRefactoring){
						UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,ref.getUseCase(),new ArrayList<SimilarBlock>());
						UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
					
						UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
						UCRCompareView.useCaseLeft = ref.getUseCase();
						UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
						UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
						UCRCompareView.updateButtons();
					}
					else if (ref instanceof DeleteActorRefactoring){
						UseCase useCase = UCRefactoringFactory.eINSTANCE.createUseCase();
						Flow flow = UCRefactoringFactory.eINSTANCE.createFlow();
						flow.setName(((DeleteActorRefactoring) ref).getActor().getName());
						useCase.getFlows().add(flow);
						
						UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCase,new ArrayList<SimilarBlock>());
						UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
					
						UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
						UCRCompareView.useCaseLeft = useCase;
						UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
						UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
						UCRCompareView.updateButtons();
					}
					else{
						AlignmentX2Result align = ref.getAlignment();
						UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,align.getUseCaseA(),align.getSimilarBlocksFromA());
						UCRUseCasesView.setCompareView(UCRCompareView.ucRight,align.getUseCaseB(),align.getSimilarBlocksFromB());
					
						UCRCompareView.similarBlocksLeft = (align.getSimilarBlocksFromA());
						UCRCompareView.useCaseLeft = align.getUseCaseA();
						UCRCompareView.similarBlocksRight = (align.getSimilarBlocksFromB());
						UCRCompareView.useCaseRight = align.getUseCaseB();
						UCRCompareView.updateButtons();
					}
				}
				
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
		for (Metric metric : metrics.values()){
			if (metric.isType(Metric.DUPLICATE)){
				getDuplicateRefactorings(metric);
			}
			if (metric.isType(Metric.ENCAPSULATED_FUNCTIONAL)){
				getNonModularRefactoring(metric);
			}
			if (metric.isType(Metric.ENCAPSULATED_NON_FUNCTIONAL)){
				
			}
			if (metric.isType(Metric.NON_SENSE_ACTOR)){
				getNonSenseActorRefactorings(metric);
			}
			if (metric.isType(Metric.NON_SENSE_USECASE)){
				getNonSenseUseCaseRefactorings(metric);
			}
			if (metric.isType(Metric.LARGE_USECASE)){
				getLargeRefactorings(metric);
			}
			if (metric.isType(Metric.SHORT_USECASE)){
				
			}
			if (metric.isType(Metric.HAPPY_USECASE)){
				
			}
			if (metric.isType(Metric.WRONG_USECASE_RELATIONSHIP)){
				
			}
		}
		
	}
	
	
	private void getDuplicateRefactorings(Metric metric){
		String extension = "Extension";
		int i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				ExtensionRefactoring extRefactoring = new ExtensionRefactoring(alignment);
				if (extRefactoring.canApply()){
					this.refactorings.put(extension+i, extRefactoring);
				}
				i++;
			}
		}
		
		String inclusion = "Inclusion ";
		i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				InclusionRefactoring intRefactoring = new InclusionRefactoring(alignment);
				if (intRefactoring.canApply()){
					this.refactorings.put(inclusion+i, intRefactoring);
				}
				i++;
			}
		}
		
		String generalization = "Generalization ";
		i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				GeneralizationRefactoring genRefactoring = new GeneralizationRefactoring(alignment);
				if (genRefactoring.canApply()){
					this.refactorings.put(generalization+i, genRefactoring);
				}
				i++;
			}
		}
		
	}
	
	private void getLargeRefactorings(Metric metric){
		String extraction = "Extraction";
		int i = 1;
		for (UseCase uc : ((LargeUseCaseMetric)metric).useCases.values()){
			ExtractUseCaseRefactoring extractRefactoring = new ExtractUseCaseRefactoring(uc, metric);
			if (extractRefactoring.canApply()){
				this.refactorings.put(extraction+i, extractRefactoring);
			}
			i++;
		}
	}
	
	
	
	private void	getNonModularRefactoring(Metric metric){
		String merge = "Merge";
		int i = 1;
		for (java.util.List<UseCase> list : ((NonModularFRMetric)metric).getNonModularUseCases().values()){
			if (list.size()==2){
				MergeUseCasesRefactoring mergeRefactoring = new MergeUseCasesRefactoring(list.get(0), list.get(1));
				mergeRefactoring.addMetric(metrics.get(Metric.SHORT_USECASE));
				if (mergeRefactoring.canApply()){
					this.refactorings.put(merge+i, mergeRefactoring);
				}
				i++;
			}
		}
	}
	
	public void getNonSenseUseCaseRefactorings(Metric metric){
		String delete = "DeleteUsCase";
		int i = 1;
		for (UseCase useCase : ((NonSenseUseCaseMetric)metric).useCases.values()){
			DeleteUseCaseRefactoring deleteUseCaseRefactoring = new DeleteUseCaseRefactoring(useCase);
			if (deleteUseCaseRefactoring.canApply()){
				this.refactorings.put(delete+i, deleteUseCaseRefactoring);
			}
			i++;
		}	
	}
	
	public void getNonSenseActorRefactorings(Metric metric){
		String delete = "DeleteActor";
		int i = 1;
		for (Actor actor : ((NonSenseActorMetric)metric).actors.values()){
			DeleteActorRefactoring deleteActorRefactoring = new DeleteActorRefactoring(actor);
			if (deleteActorRefactoring.canApply()){
				this.refactorings.put(delete+i, deleteActorRefactoring);
			}
			i++;
		}	
	}
}
