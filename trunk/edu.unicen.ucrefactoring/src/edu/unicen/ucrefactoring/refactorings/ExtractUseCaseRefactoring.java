package edu.unicen.ucrefactoring.refactorings; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRCompareView;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.InclusionCall;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;

public class ExtractUseCaseRefactoring implements Refactoring {

	private String type = Refactoring.REF_EXTRACT_UC;

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private UseCase useCase;
	private Float priority = Refactoring.MEDIUM_PRIORITY;
	private String problem = "Use Case is too long / complicated";
	private String refactoringName = "Extract Use Case";
	private List<String> artifacts; 
	private Long ID;


	
	public ExtractUseCaseRefactoring(UseCase useCase, Metric metric){
		this.score = null;
		this.name = null;
		this.metrics = new HashMap<String, Metric>();
		//this.alignment = alignment;
		this.useCase = useCase;
		this.metrics.put(useCase.getName(), metric);
		this.artifacts = new ArrayList<String>();
		this.artifacts.add(useCase.getName());
	}
	
	@Override
	public boolean canApply() {
		// TODO CONTENT ANALYSYS CAN BE ADDED
		return true;
	}

	@Override
	public boolean applyRefactoring() {
		// TODO Auto-generated method stub
		/**
		 * 1- Get new Events
		 * 2- Delete events from old UC
		 * 3- Create new UC with events
		 * 4-
		 * 
		 */
		if (UCRCompareView.useCaseLeft != null && UCRCompareView.useCaseLeft.getName().equals(this.useCase.getName()) && UCRCompareView.similarBlocksLeft.size()>0){
			UseCase newUseCase = UCRefactoringFactory.eINSTANCE.createUseCase();
			
			int cancel = UCRUseCasesView.newUseCaseDialog();
			
			if (cancel == 0){
				
				newUseCase.setName(UCRUseCasesView.UCRDialog.getUseCaseName());
				newUseCase.setDescription(UCRUseCasesView.UCRDialog.getUseCaseDescription());
				newUseCase.setPrimaryActor(null); // No actor in this new use case
				Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
				basicFlow.setName("Basic Flow");
				Integer order = 1;
				for (SimilarBlock sb : UCRCompareView.similarBlocksLeft){
					for (Event e : sb.getSimilarEvents()){
						Event newE = e.cloneEvent();
						newE.setEventId(e.getEventId().replaceFirst(e.getNumber().toString(), order.toString()));
						newE.setNumber(order);
						basicFlow.getEvents().add(newE);
						order++;
					}
				}
				basicFlow.setUseCase(newUseCase);
				newUseCase.getFlows().add(basicFlow);
				
				//Set Primary Actor
				if(UCRUseCasesView.setPrimaryActor() == 0){
					String aName = UCRUseCasesView.primaryActorDialog.getActorName();
					Actor newActor = null;
					for(Actor a: UCRUseCasesView.ucref.getUseCaseModel().getActors()){
						if (a.getName().equalsIgnoreCase(aName)){
							newActor = a;
						}
					}
					if(newActor == null){
						newActor = UCRefactoringFactory.eINSTANCE.createActor();
						newActor.setName(aName);
						UCRUseCasesView.ucref.getUseCaseModel().getActors().add(newActor);
					}
					newUseCase.setPrimaryActor(newActor);
				}				
				// Add new uc to the model
				UCRUseCasesView.ucref.getUseCaseModel().getUseCases().add(newUseCase);
				for (SimilarBlock sb : UCRCompareView.similarBlocksLeft ){
					editFlow(sb, this.getUseCase());	
				}				
			} 
			return true;

		}
		else{
			String message = "Please, select events to extract \nto a new Use Case";
			UCRUseCasesView.messageDialog(message);
			//JOptionPane.showMessageDialog(null, "Please, select events to extract \nto a new Use Case.", "Extract Use Case", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	@Override
	public Float getScore() {
		Metric metric = this.metrics.get(this.useCase.getName());
		
		if (metric.isType(Metric.LARGE_USECASE)){
			Float totalCoef = (float)(1 - (((float)LargeUseCaseMetric.TOTAL_LIMIT/((LargeUseCaseMetric)metric).getUseCaseTotalEventsSize(this.useCase))));
			Float basicCoef = (float)(1 - (((float)LargeUseCaseMetric.BASIC_FLOW_LIMIT/((LargeUseCaseMetric)metric).getUseCaseBasicEventsSize(this.useCase))));
			
			if (this.score == null){
				totalCoef = (totalCoef<0)?1:totalCoef;
				basicCoef = (basicCoef<0)?1:basicCoef;
				Float confiability = (float)(totalCoef / basicCoef)/2;
				//getScore
				this.score = (confiability*(0.4f)+this.getPriority()*(0.6f))*100;
			}
		}
		else score = 0f;
		return this.score;
	}
	
	private void editBaseFlow(SimilarBlock simBlockToRemove, UseCase includedUC){
		// Edit the Basic Flow
		UseCase baseUC = simBlockToRemove.getUseCase();
		Flow basicFlow = baseUC.getBasicFlow();
		int beginIndex = simBlockToRemove.getSimilarEvents().get(0).getNumber()-1;
		int endIndex = simBlockToRemove.getSimilarEvents().get(simBlockToRemove.getSimilarEvents().size()-1).getNumber()-1;
		for (int i = endIndex; i > beginIndex; i--){
			basicFlow.getEvents().remove(i);
			for (int j = i; j< basicFlow.getEvents().size(); j++){
				Event e = basicFlow.getEvents().get(j);
				Integer newNumber = e.getNumber()-1;
				e.setEventId(e.getEventId().replaceFirst(e.getNumber().toString(), newNumber.toString()));
				e.setNumber(newNumber);
			}
		}
		Event finalRemoved = basicFlow.getEvents().get(beginIndex);		
		basicFlow.getEvents().remove(finalRemoved);
	}
	
	private void editFlow(SimilarBlock simBlockToRemove, UseCase includedUC){
		// Edit the Flow
		Flow flow = simBlockToRemove.getFlow();
		int beginIndex = simBlockToRemove.getSimilarEvents().get(0).getNumber()-1;
		int endIndex = simBlockToRemove.getSimilarEvents().get(simBlockToRemove.getSimilarEvents().size()-1).getNumber()-1;
		for (int i = endIndex; i >= beginIndex; i--){
			flow.getEvents().remove(i);
			for (int j = i; j< flow.getEvents().size(); j++){
				Event e = flow.getEvents().get(j);
				Integer newNumber = e.getNumber()-1;
				e.setEventId(e.getEventId().replaceFirst(e.getNumber().toString(), newNumber.toString()));
				e.setNumber(newNumber);
			}
		}
		//Event finalRemoved = flow.getEvents().get(beginIndex);		
		//flow.getEvents().remove(finalRemoved);
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = useCase.getName();
		}
		return name;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		return null;
	}

	@Override
	public UseCase getUseCase() {
		return this.useCase;
	}

	@Override
	public void addMetric(Metric metric) {
		this.metrics.put(metric.getType(), metric);
	}

	@Override
	public Float getPriority() {
		return this.priority;
	}

	@Override
	public String getProblem() {
		return this.problem;
	}

	@Override
	public List<String> getArtifacts() {
		return this.artifacts;
	}

	@Override
	public String getRefactoringName() {
		return this.refactoringName;
	}
	
	@Override
	public String getPriorityText() {
		return MEDIUM_PRIORITY_TEXT;
	}
	
	@Override
	public Long getID() {
		return this.ID;
	}
	
	public void setID(Long ID){
		this.ID = ID;
	}
	
	@Override
	public boolean affectsUseCase(UseCase useCase) {
		return this.useCase.getName().equals(useCase.getName());
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDetail() {
		return this.getUseCase().getName()+"\n";
	}
	
}
