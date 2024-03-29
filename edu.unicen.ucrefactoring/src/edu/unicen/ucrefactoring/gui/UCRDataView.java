package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.MetricCollector;
import edu.unicen.ucrefactoring.metrics.NonModularNFRMetric;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.refactorings.DeleteActorRefactoring;
import edu.unicen.ucrefactoring.refactorings.DeleteUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtractAspectRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtractUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.MergeUseCasesRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;
import edu.unicen.ucrefactoring.refactorings.RefactoringCreator;

/**
 * View that shows a list of detected refactorings, and their actions
 * @author migue
 *
 */
public class UCRDataView extends ViewPart {

	public static final String ID = "edu.unicen.ucrefactoring.gui.UCRDataView"; //$NON-NLS-1$

	//Table headers
	public static final String PROBLEM_HEADER = "Problem";
	public static final String REFACTORING_HEADER = "Refactoring";
	public static final String ARTIFACT_HEADER = "Artifact";
	public static final String SCORE_HEADER = "Score";
	public static final String PRIORITY_HEADER = "Priority";
	public static final String ID_HEADER = "ID";
	
	//Providers
	private static UCRefactoringDetection ucref;
	private static RefactoringLabelProvider extLabel;
	private RefactoringListContentProvider extListContentProvider;
	private RefactoringTableContentProvider extTableContentProvider;
	
	// widgets
	public static Button btnAnalyze; 
	public static Button btnApply;
	public static Label lblRefactoring;
	
	//core structures
	private HashMap<String,Metric> metrics;
	public static HashMap<String,Refactoring> refactorings;
	public static TableViewer tableViewer;	
	private Table table;
	
	//Selected Refactoring
	public static Refactoring selectedRefactoring;

	
	public UCRDataView() {	
		//Providers
		//ucref = new UCRefactoringDetection(false);
		extLabel = new RefactoringLabelProvider();
		refactorings = new HashMap<String,Refactoring>();
		metrics = new HashMap<String,Metric>();
		selectedRefactoring = null;
	}
	

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		//Add TableColumnLayout
//		TableColumnLayout tableLayout = new TableColumnLayout();
//		container.setLayout(tableLayout);
		
		btnAnalyze = new Button(container, SWT.NONE);
		btnAnalyze.setText("Analyze");
		btnAnalyze.setEnabled(false);
		
		btnApply = new Button(container, SWT.NONE);
		btnApply.setText("Apply");
		btnApply.setEnabled(false);
		tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		lblRefactoring = new Label(container, SWT.NONE);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(table, GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
					.add(18)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(btnApply, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.add(btnAnalyze, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.add(lblRefactoring, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(14)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(table, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
							.add(btnAnalyze, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED, 61, Short.MAX_VALUE)
							.add(lblRefactoring)
							.add(12)
							.add(btnApply, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		TableViewerColumn tableViewerColumn_0 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnID = tableViewerColumn_0.getColumn();
		tblclmnID.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(),SCORE_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnProblem, new ColumnPixelData(365, true, true));

		tblclmnID.setWidth(30);
		tblclmnID.setText(ID_HEADER);
		tableViewerColumn_0.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				return r.getID().toString();
			}
		});	
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnProblem = tableViewerColumn_2.getColumn();
		tblclmnProblem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(),PROBLEM_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnProblem, new ColumnPixelData(365, true, true));

		tblclmnProblem.setWidth(420);
		tblclmnProblem.setText(PROBLEM_HEADER);
		tableViewerColumn_2.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				return r.getProblem();
			}
		});	
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnRefactoring = tableViewerColumn.getColumn();
		tblclmnRefactoring.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(),REFACTORING_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnRefactoring, new ColumnWeightData(10, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnRefactoring.setWidth(280);
		tblclmnRefactoring.setText(REFACTORING_HEADER);
		tableViewerColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				return r.getRefactoringName();
			}
		});	
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnArtifacts = tableViewerColumn_1.getColumn();
		tblclmnArtifacts.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(), ARTIFACT_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnArtifacts, new ColumnWeightData(5, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnArtifacts.setWidth(250);
		tblclmnArtifacts.setText(ARTIFACT_HEADER);
		tableViewerColumn_1.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				String result = "";
				for (String s : r.getArtifacts()){
					result += "["+s+"] ";
				}
				return result;
			}
		});	
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnScore = tableViewerColumn_3.getColumn();
		tblclmnScore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(), SCORE_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnScore, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnScore.setWidth(50);
		tblclmnScore.setText(SCORE_HEADER);
		tableViewerColumn_3.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				return (r.getScore().toString().indexOf(".")>0)?r.getScore().toString().substring(0, r.getScore().toString().indexOf(".")):"";
			}
		});	
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPriority = tableViewerColumn_4.getColumn();
		tblclmnPriority.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values(), PRIORITY_HEADER);
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
			}
		});
		//tableLayout.setColumnData(tblclmnPriority, new ColumnWeightData(1, ColumnWeightData.MINIMUM_WIDTH, true));
		tblclmnPriority.setWidth(70);
		tblclmnPriority.setText(PRIORITY_HEADER);
		tableViewerColumn_4.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Refactoring r = (Refactoring) element;
				return r.getPriorityText();
			}
		});	
		
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
				// parse use case model
				UCRUseCasesView.compareUseCases();
				
				ucref = new UCRefactoringDetection(UCRUseCasesView.ucref.getUseCaseModel());
				metrics = new HashMap<String, Metric>();
				refactorings = new HashMap<String, Refactoring>();
				
				collectMetrics();
				analyzeRefactorings();
				extListContentProvider =  new RefactoringListContentProvider(refactorings.values());				
//				listViewer.setContentProvider(extListContentProvider);
//				listViewer.setLabelProvider(extLabel);
//				listViewer.setInput(ucref);
				
				extTableContentProvider = new RefactoringTableContentProvider(refactorings.values());
				tableViewer.setContentProvider(extTableContentProvider);
				tableViewer.setInput(ucref);
				tableViewer.refresh();
				UCRUseCasesView.updateUseCasesView();
				
				
			}
		});
		
		btnApply.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				Refactoring ref;
				if (selection.size()==1){
					ref = (Refactoring) selection.getFirstElement();
					if (ref.applyRefactoring()){
						UCRUseCasesView.updateUseCasesView();
						btnApply.setEnabled(false);
						cleanViews();
						//showRefactoring(ref,false);
					}
				}
			}
		});
		
		//Listener for double click event on refactoring list
		/**
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			
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
		**/
		
		//Listener to enable/disable APPLY button
		tableViewer.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
				if (selection.size()==1){
					btnApply.setEnabled(true);
 					lblRefactoring.setText("Selected Ref. ID: "+ ((Refactoring)(selection.getFirstElement())).getID());
 					selectedRefactoring = (Refactoring) selection.getFirstElement();
//					Refactoring r = (Refactoring) selection.getFirstElement();
//					for (TableItem tItem : tableViewer.getTable().getItems()){
//						if (((Refactoring)tItem.getData()).getDetail().equals(r.getDetail())){
//							tItem.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN));
//						}
//						else{
//							//tItem.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
//						}
//					}
//					tableViewer.refresh();
//					//tableViewer.getTable().getItem(0).setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE));
 					
 					//Show on compare view
 					Refactoring ref = null;
 					if (selection.size()==1){
 						ref = (Refactoring)selection.toList().get(0);
 					}
 					showRefactoring(ref,true);
				}
				else{
					btnApply.setEnabled(false);
					lblRefactoring.setText("None");
					selectedRefactoring = null;
				}
			}
		});
		
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	
	public void collectMetrics(){
		MetricCollector metricCollector = new MetricCollector(ucref.getUseCaseModel(), ucref.getReAssistantProject(), UCRUseCasesView.alignResults);
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
	
	public static void showRefactoring(Refactoring ref, boolean doPaint){
		if (ref != null){
			if (ref.getType().equals(Refactoring.REF_EXTRACT_UC) || ref.getType().equals(Refactoring.REF_DELETE_UC) ){
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,ref.getUseCase(),new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = ref.getUseCase();
				UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
				UCRCompareView.updateButtonsAndLabels();
			}
			else if (ref.getType().equals(Refactoring.REF_DELETE_ACTOR) ){
				UseCase useCase = UCRefactoringFactory.eINSTANCE.createUseCase();
				useCase.setName("NON SENSE ACTOR");
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
			else if (ref.getType().equals(Refactoring.REF_EXTRACT_ASPECT)){
				UseCase useCase = UCRefactoringFactory.eINSTANCE.createUseCase();
				useCase.setName(ref.getRefactoringName());
				Flow flow = UCRefactoringFactory.eINSTANCE.createFlow();
				flow.setName("Casos de Uso Afectados por el CCC '" + ((ExtractAspectRefactoring)ref).getCcName()+"'");
				//TODO: ADD REF. AFFECTED LINES AS EVENTS
				useCase.getFlows().add(flow);
				for (UseCase uc : ((NonModularNFRMetric)((ExtractAspectRefactoring)ref).getMetric(Metric.ENCAPSULATED_NON_FUNCTIONAL)).ccUseCases.get(((ExtractAspectRefactoring)ref).getCcName())){
					Event e = UCRefactoringFactory.eINSTANCE.createFunctionalEvent();
					e.setDetail(uc.getName());
					e.setEventId(new Integer(useCase.getFlows().get(0).getEvents().size() + 1).toString());
					//TODO: ADD REF. AFFECTED LINES AS EVENTS
					useCase.getFlows().get(0).getEvents().add(e);
				}
				
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCase,new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,UCRefactoringFactory.eINSTANCE.createUseCase(),new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = useCase;
				UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = UCRefactoringFactory.eINSTANCE.createUseCase();
				UCRCompareView.updateButtonsAndLabels();
			}
			else if (ref.getType().equals(Refactoring.REF_MERGE)){
				
				UseCase useCaseA = ((MergeUseCasesRefactoring)ref).getUseCaseA();
				UseCase useCaseB = ((MergeUseCasesRefactoring)ref).getUseCaseB();
				
				UCRUseCasesView.setCompareView(UCRCompareView.ucLeft,useCaseA,new ArrayList<SimilarBlock>());
				UCRUseCasesView.setCompareView(UCRCompareView.ucRight,useCaseB,new ArrayList<SimilarBlock>());
			
				UCRCompareView.similarBlocksLeft = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseLeft = useCaseA;
				UCRCompareView.similarBlocksRight = (new ArrayList<SimilarBlock>());
				UCRCompareView.useCaseRight = useCaseB;
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
	
	public static void resetView(UCRefactoringDetection ucref){
		UCRDataView.ucref = ucref;
		UCRDataView.extLabel = new RefactoringLabelProvider();
		RefactoringTableContentProvider extTableContentProvider = new RefactoringTableContentProvider(new HashMap<String, Refactoring>());
		UCRDataView.tableViewer.setContentProvider(extTableContentProvider);
		UCRDataView.tableViewer.setInput(ucref);
		UCRDataView.tableViewer.refresh();
		UCRDataView.btnApply.setEnabled(false);
		UCRDataView.btnAnalyze.setEnabled(true);
		lblRefactoring.setText("");
		
	}

}
