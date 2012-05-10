package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.InclusionCall;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;

public class GeneralizationRefactoring implements Refactoring {

	
	private Float score;
	private HashMap<String,Metric> metrics;
	private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.HIGH_PRIORITY;
	
	public GeneralizationRefactoring(AlignmentX2Result alignment){
		this.score = null;
		this.name = null;
		this.alignment = alignment;
	}
	
	@Override
	public boolean canApply() {
		// TODO INCORPORAR CHEQUEO DE IGUALES FLUJOS ALTERNATIVOS / EXT. TB Q LOS AL MENOS UNO NO TENGA PADRE
		if ((this.alignment.getFlowA().getName().equals("Basic Flow") 
			&& this.alignment.getFlowB().getName().equals("Basic Flow"))
			&& (this.alignment.getSimilarBlocksFromA().size()>1
			&& this.alignment.getSimilarBlocksFromB().size()>1)){
					return true;
		}
		return false;
	}

	@Override
	public void applyRefactoring() {
		// TODO Auto-generated method stub
		/**
		 * 1-
		 * 2-
		 * 3-
		 * 4-
		 * 
		 */
		UseCase baseUseCaseA = null;
		UseCase baseUseCaseB = null;
		UseCase parentUC = null;
		if (!this.isThisParent(this.alignment.getFlowA()) && !isThisParent(this.alignment.getFlowB())){
			baseUseCaseA = this.alignment.getUseCaseA();
			baseUseCaseB = this.alignment.getUseCaseB();
			// Create Use Case
			// TODO MAKE THE USER COMPLETE THE NAME AND DESCRIPTION
			parentUC = UCRefactoringFactory.eINSTANCE.createUseCase();
			parentUC.setName("Default Name");
			parentUC.setDescription("Default Description");
			parentUC.setPrimaryActor(null); // TODO ABSTRAER ACTORES
			Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
			basicFlow.setName("Basic Flow");
			Integer order = 1;
			Boolean lastParticular = false;
			List<Event> allDuplicated = new ArrayList<Event>();
			for (SimilarBlock sb :this.alignment.getSimilarBlocksFromA()){
				allDuplicated.addAll(sb.getSimilarEvents());
			}
			for (Event actualEv : baseUseCaseA.getBasicFlow().getEvents()){
				if(allDuplicated.contains(actualEv)){
					Event newE = actualEv.cloneEvent();
					newE.setEventId(actualEv.getEventId().replaceFirst(actualEv.getNumber().toString(), order.toString()));
					newE.setNumber(order);
					basicFlow.getEvents().add(newE);
					order++;
					lastParticular = false;
				}
				else if (!lastParticular){
					InclusionCall newE = UCRefactoringFactory.eINSTANCE.createInclusionCall();
					newE.setEventId(order + ".");
					newE.setNumber(order);
					newE.setDetail("Delegated to particular implementation");
					basicFlow.getEvents().add(newE);
					order++;
					lastParticular = true;
				}
			}
			basicFlow.setUseCase(parentUC);
			parentUC.getFlows().add(basicFlow);
			// Add new uc to the model
			UCRUseCasesView.ucref.getUseCaseModel().getUseCases().add(parentUC);
		}
		else{
			if (isThisParent(this.alignment.getFlowA())){
				baseUseCaseB = this.alignment.getUseCaseB();
				parentUC = this.alignment.getUseCaseA();
			}
			else{
				baseUseCaseA =this.alignment.getUseCaseA();
				parentUC = this.alignment.getUseCaseB();
			}
		}
		if(baseUseCaseA != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromA(), parentUC);
		}
		if(baseUseCaseB != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromB(), parentUC);
		}
	}
	
	private void editBaseFlow(List<SimilarBlock> simBlockToRemove, UseCase parent){
		// Edit the Basic Flow
		UseCase baseUC = simBlockToRemove.get(0).getUseCase();
		Flow basicFlow = baseUC.getBasicFlow();
		List<Event> allDuplicated = new ArrayList<Event>();
		for (SimilarBlock sb :simBlockToRemove){
			allDuplicated.addAll(sb.getSimilarEvents());
		}
		Boolean lastAbstract = false;
		List<Event> newList = new ArrayList<Event>();
		for (Event e : basicFlow.getEvents()){
			if (allDuplicated.contains(e)){
				// Remove this abstract event
				if (!lastAbstract){
					InclusionCall newE = UCRefactoringFactory.eINSTANCE.createInclusionCall();
					Integer last = newList.size();
					newE.setNumber(last+1);
					newE.setEventId(last+1+".");
					newE.setDetail("Common behaviour defined in parent use case");
					newList.add(newE);
					lastAbstract = true;
				}
			}
			else{
				// it is particular, leave it here
				Integer last = newList.size();
				e.setNumber(last+1);
				e.setEventId(last+1+".");
				newList.add(e);
				lastAbstract = false;
			}
		}
		basicFlow.getEvents().clear();
		basicFlow.getEvents().addAll(newList);
		baseUC.setParent(parent);
	}

	public Boolean isThisParent(Flow f){
		// TODO VER SI UNO ES PADRE, ES DECIR SI YA FORMA PARTE DE UNA JERARQUIA 
		return false;
	}
	
	@Override
	public Float getScore() {
		if (this.score == null){
			Float sizeCoef = 0f;
			Float totalSize = 0f;
			Float blocksSize = 0f;
			Float confiability = 0f;
			for (Flow f1 : this.alignment.getUseCaseA().getFlows()){
				totalSize += f1.getEvents().size();
			}
			for (Flow f2 : this.alignment.getUseCaseB().getFlows()){
				totalSize += f2.getEvents().size();
			}
			for (SimilarBlock sb1 : this.alignment.getSimilarBlocksFromA()){
				blocksSize += sb1.getSimilarEvents().size();
			}
			for (SimilarBlock sb2 : this.alignment.getSimilarBlocksFromB()){
				blocksSize += sb2.getSimilarEvents().size();
			}

			//get size coef
			sizeCoef = ((blocksSize) / (totalSize));
			//get confiability
			confiability = (float) (sizeCoef * alignment.getTextSimilarityScore());
			
			//getScore
			this.score = (float)((confiability*(0.4) + this.getPriority()*(0.6)))*100;
		}
		return this.score;
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = alignment.getUseCaseA().getName()+" - "
			+alignment.getFlowA().getName()+" | "
			+alignment.getUseCaseB().getName()+" - "
			+alignment.getFlowB().getName();
		}
		return name;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		return this.alignment;
	}

	@Override
	public UseCase getUseCase() {
		return null;
	}
	
	@Override
	public void addMetric(Metric metric) {
		this.metrics.put(metric.getType(), metric);
	}

	@Override
	public Float getPriority() {
		return this.priority;
	}

}
