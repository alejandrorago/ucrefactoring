package edu.unicen.ucrefactoring.refactorings;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.InclusionCall;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;

public class InclusionRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.HIGH_PRIORITY;
	
	public InclusionRefactoring(AlignmentX2Result alignment){
		this.score = null;
		this.name = null;
		this.alignment = alignment;
	}
	
	@Override
	public boolean canApply() {
		// TODO INCORPORAR CHEQUEO DE IGUALES FLUJOS ALTERNATIVOS / EXT 
		if ((this.alignment.getFlowA().getName().equals("Basic Flow") 
			&& this.alignment.getFlowB().getName().equals("Basic Flow"))
			&& (this.alignment.getSimilarBlocksFromA().size()==1
			&& this.alignment.getSimilarBlocksFromB().size()==1)){
					return true;
		}
		return false;
	}

	@Override
	public void applyRefactoring() {
		/**
		 * 1-
		 * 2-
		 * 3-
		 * 4-
		 * 
		 */
		UseCase baseUseCaseA = null;
		UseCase baseUseCaseB = null;
		UseCase includedUC = null;
		if ((this.alignment.getFlowA().getEvents().size() != this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents().size())&& 
		    (this.alignment.getFlowB().getEvents().size() != this.alignment.getSimilarBlocksFromB().get(0).getSimilarEvents().size())){
			// if no similar block is a defined uc, create one for the duplicated functionality
			baseUseCaseA = this.alignment.getUseCaseA();
			baseUseCaseB = this.alignment.getUseCaseB();
			// Create Use Case
			// TODO MAKE THE USER COMPLETE THE NAME AND DESCRIPTION
			includedUC = UCRefactoringFactory.eINSTANCE.createUseCase();
			includedUC.setName("Default Name");
			includedUC.setDescription("Default Description");
			includedUC.setPrimaryActor(null); // No actor in this new use case
			Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
			basicFlow.setName("Basic Flow");
			// TODO CLONE EVENTS
			basicFlow.getEvents().addAll(this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents());
			basicFlow.setUseCase(includedUC);
			includedUC.getFlows().add(basicFlow);
			// Add new uc to the model
			UCRUseCasesView.ucref.getUseCaseModel().getUseCases().add(includedUC);
		}
		else{
			if (this.alignment.getFlowA().getEvents().size() == this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents().size()){
				baseUseCaseB = this.alignment.getUseCaseB();
				includedUC = this.alignment.getUseCaseA();
			}
			else{
				baseUseCaseA =this.alignment.getUseCaseA();
				includedUC = this.alignment.getUseCaseB();
			}
		}
		if(baseUseCaseA != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromA().get(0), includedUC);
		}
		if(baseUseCaseB != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromB().get(0), includedUC);
		}
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
		// Replace in initial pos the new event with the inclusion event
		InclusionCall ic = UCRefactoringFactory.eINSTANCE.createInclusionCall();
		ic.setEventId(finalRemoved.getEventId());
		ic.setNumber(finalRemoved.getNumber());
		ic.setDetail("Call " + "'"+ includedUC.getName() +"'");
		ic.getIncludedUseCases().add(includedUC);
		// TODO Revisar action del ic
		basicFlow.getEvents().add(beginIndex, ic);
		basicFlow.getEvents().remove(finalRemoved);
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
