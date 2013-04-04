package edu.unicen.ucrefactoring.refactorings; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRCompareView;
import edu.unicen.ucrefactoring.gui.UCRMessageDialog;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.HappyUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.MetricCollector;
import edu.unicen.ucrefactoring.metrics.NonModularFRMetric;
import edu.unicen.ucrefactoring.metrics.ShortUseCaseMetric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.util.Constants;


/**
 * Class that models a Merge Use Cases refactoring.
 * @author migue
 *
 */
public class MergeUseCasesRefactoring implements Refactoring{

	private String type = Refactoring.REF_MERGE;

	private Float score;
	private HashMap<String,Metric> metrics;
	private List<Event> mergeEvents;
	//private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.MEDIUM_PRIORITY;
	private UseCase useCaseA;
	private UseCase useCaseB;
	private String problem = "Scattered Functionality";
	private String refactoringName = "Merge Use Cases";
	private List<String> artifacts; 
	private Long ID;

	
	public MergeUseCasesRefactoring(UseCase useCase1, UseCase useCase2){
		this.score = null;
		this.name = null;
		this.useCaseA = useCase1;
		this.useCaseB = useCase2;
		//this.alignment = alignment;
		this.metrics = new HashMap<String, Metric>();
		this.artifacts = new ArrayList<String>();
		this.artifacts.add(useCase1.getName());
		this.artifacts.add(useCase2.getName());
		this.mergeEvents = new ArrayList<Event>();
	}
	
	@Override
	public boolean canApply() {
		boolean apply = true;
		int cant = 0;
		int cantBasic = this.useCaseA.getBasicFlow().getEvents().size();
		cantBasic = cantBasic + this.useCaseB.getBasicFlow().getEvents().size();
		for (Flow f : this.useCaseA.getFlows()){
			cant = cant + f.getEvents().size();
		}
		for (Flow f : this.useCaseB.getFlows()){
			cant = cant + f.getEvents().size();
		}
		//  Si las longitudes no superan los limites aumentados en un 10% (al mergear se pueden reducir los eventos)
		if (cant > LargeUseCaseMetric.TOTAL_LIMIT * 1.1 || cantBasic > LargeUseCaseMetric.BASIC_FLOW_LIMIT * 1.1){
			apply = false;
		}
		return apply;
	}

	@Override
	public boolean applyRefactoring() {
		// TODO Auto-generated method stub
		/**
		 * Extension refactoring.
		 * 0- Check if blocks were created
		 * 1- Create new use case
		 * 2- Let User give the name, description and actors
		 * 3- Add blocks to new use case base flow
		 * 4- Remove blocks from source use case
		 * 5- Add new use case to use case model
		 */	
		if ((UCRCompareView.useCaseLeft != null && UCRCompareView.useCaseLeft.getName().equals(this.useCaseA.getName()) && UCRCompareView.similarBlocksLeft.size()>0) 
				|| (UCRCompareView.useCaseRight != null && UCRCompareView.useCaseRight.getName().equals(this.useCaseB.getName()) && UCRCompareView.similarBlocksRight.size()>0)){
			UseCase newUseCase = UCRefactoringFactory.eINSTANCE.createUseCase();
			
			int cancel = UCRUseCasesView.newUseCaseDialog();
			
			if (cancel == 0){
				
				newUseCase.setName(UCRUseCasesView.UCRDialog.getUseCaseName());
				newUseCase.setDescription(UCRUseCasesView.UCRDialog.getUseCaseDescription());
				newUseCase.setPrimaryActor(null); // No actor in this new use case
				Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
				basicFlow.setName("Basic Flow");
				Integer order = 1;
				
				for (Event event : this.mergeEvents){
					Event newE = event.cloneEvent();
					newE.setEventId(event.getEventId().replaceFirst(event.getNumber().toString(), order.toString()));
					newE.setNumber(order);
					basicFlow.getEvents().add(newE);
					order++;
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
				
				//delete use cases
				UCRUseCasesView.ucref.getUseCaseModel().getUseCases().remove(this.getUseCaseA());
				UCRUseCasesView.ucref.getUseCaseModel().getUseCases().remove(this.getUseCaseB());
				
			} 
			return true;

		}
		else{
			String message = "Please, select events from both Use Cases to merge into a new Use Case.\nEvents will be added in order.";
			UCRUseCasesView.messageDialog(message);
			//JOptionPane.showMessageDialog(null, "Please, select events from both Use Cases to merge into a new Use Case.\nEvents will be added in order.", "Merge Use Cases", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
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
	public Float getScore() {
		if (this.score == null && this.useCaseA!=null && this.useCaseB!=null){
			Float actorCoef = 0f;
			Float textCoef = 0f;
			Float shortCoef = 0f;
			Float happyCoef = 0f;
			Float confiability = 0f;

			NonModularFRMetric nonModularMetric = (NonModularFRMetric) this.metrics.get(Metric.ENCAPSULATED_FUNCTIONAL);
			//get actor coef
			actorCoef = nonModularMetric.getNonModularUseCasesActorSimilarity(useCaseA, useCaseB);
			//get text coef
			textCoef = nonModularMetric.getNonModularUseCasesTextSimilarity(useCaseA, useCaseB);
			//get short coef
			if (this.metrics.get(Metric.SHORT_USECASE)!=null){
				ShortUseCaseMetric shortMetric = (ShortUseCaseMetric) this.metrics.get(Metric.SHORT_USECASE);
				shortCoef = 0f;
				if  (shortMetric.getShortUseCase(useCaseA.getName())!=null){
					shortCoef += 0.5f;
				}
				if  (shortMetric.getShortUseCase(useCaseB.getName())!=null){
					shortCoef += 0.5f;
				}
			}
			//get happy coef
			if (this.metrics.get(Metric.HAPPY_USECASE)!=null){
				HappyUseCaseMetric happyMetric = (HappyUseCaseMetric) this.metrics.get(Metric.HAPPY_USECASE);
				happyCoef = 0f;
				if  (happyMetric.getHappyUseCase(useCaseA.getName())!=null){
					happyCoef += 0.5f;
				}
				if  (happyMetric.getHappyUseCase(useCaseB.getName())!=null){
					happyCoef += 0.5f;
				}
			}
			//get confiability score
			confiability = (actorCoef*textCoef);
			
			//getScore
			this.score = (float)((confiability*(0.2)+shortCoef*(0.1)+ happyCoef*(0.1)+ this.getPriority()*(0.6)))*100;
		}
		return this.score;
	}

	public String getName() {
		if (name == null){
			this.name = getUseCaseA().getName()+" | "
			+getUseCaseB().getName();
		}
		return name;
	}
	
	@Override
	public void addMetric(Metric metric) {
		this.metrics.put(metric.getType(), metric);
	}


	@Override
	public Float getPriority() {
		return this.priority;
	}
	
	public UseCase getUseCaseA() {
		return this.useCaseA;
	}
	
	public UseCase getUseCaseB() {
		return this.useCaseB;
	}
	
	
//NOT USED =================================
	public AlignmentX2Result getAlignment() {
		return null;
	}

	@Override
	public UseCase getUseCase() {
		return null;
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
		return this.useCaseA.getName().equals(useCase.getName()) || 
				this.useCaseB.getName().equals(useCase.getName());
	}
	
	@Override
	public String getType() {
		return this.type;
	}
	
	public void setUseCaseA(UseCase useCaseA) {
		this.useCaseA = useCaseA;
	}

	public void setUseCaseB(UseCase useCaseB) {
		this.useCaseB = useCaseB;
	}
	
	@Override
	public String getDetail() {
		if (this.getAlignment()!=null){
			return "Use Cases: ["+this.getAlignment().getUseCaseA().getName()+"]["+this.getAlignment().getUseCaseB().getName() +"]\n"
			   +"Flows: [" + this.getAlignment().getFlowA().getName()+"]["+this.getAlignment().getFlowB().getName()+"]\n";
		}
		else{
			return "Use Cases: ["+this.getUseCaseA().getName()+"]["+this.getUseCaseB().getName() +"]\n";
		}
	}
	
	public void populateMergeEventsList(List<Event> mergeEvents){
		//populate mergeEvent list
		for (Event e : mergeEvents){
			if (!this.mergeEvents.contains(e)){
				this.mergeEvents.add(e);
			}
		}
	}
	
}
