package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRDataView;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.model.impl.UCRefactoringFactoryImpl;


/**
 * Class that models an extension refactoring.
 * @author migue
 *
 */
public class ExtensionRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.HIGH_PRIORITY;
	
	public ExtensionRefactoring(AlignmentX2Result alignment){
		this.score = null;
		this.name = null;
		this.alignment = alignment;
	}
	
	@Override
	public boolean canApply() {
		// TODO Auto-generated method stub
		if ((!this.alignment.getFlowA().getName().equals("Basic Flow") 
		|| !this.alignment.getFlowB().getName().equals("Basic Flow"))
		&& (this.alignment.getSimilarBlocksFromA().size()==1
		&& this.alignment.getSimilarBlocksFromB().size()==1)){
			return true;
		}
		return false;
	}

	@Override
	public void applyRefactoring() {
		/**
		 * Extension refactoring.
		 * 1- Create new use case
		 * 2- Name the use case with alternative flow name
		 * 3- Add flow and events to new use case
		 * 4- Remove alternative flow and/or duplicate events from use cases
		 * 5- Add new use case to use case model
		 */
		UseCase baseUseCaseA = null;
		UseCase baseUseCaseB = null;
		UseCase extendingUC = null;
		if (!this.alignment.getFlowA().getName().equals("Basic Flow") && !this.alignment.getFlowB().getName().equals("Basic Flow")){
			baseUseCaseA = this.alignment.getUseCaseA();
			baseUseCaseB = this.alignment.getUseCaseB();
			// Create Use Case
			extendingUC = UCRefactoringFactory.eINSTANCE.createUseCase();
			extendingUC.setName("Default Name");
			extendingUC.setDescription("Default Description");
			extendingUC.setPrimaryActor(null); // No actor in this new use case
			Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
			basicFlow.setName("Basic Flow");
			basicFlow.getEvents().addAll(this.alignment.getFlowA().getEvents());
			basicFlow.setUseCase(extendingUC);
			extendingUC.getFlows().add(basicFlow);
		}
		else{
			if (this.alignment.getFlowA().getName().equals("Basic Flow")){
				baseUseCaseB = this.alignment.getUseCaseB();
				extendingUC = this.alignment.getUseCaseA();
			}
			else{
				baseUseCaseA =this.alignment.getUseCaseA();
				extendingUC = this.alignment.getUseCaseB();
			}
		}
		if(baseUseCaseA != null){
			Flow f = this.alignment.getFlowA();
			//f.get
		}
		
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

	public String getName() {
		if (name == null){
			this.name = alignment.getUseCaseA().getName()+" - "
			+alignment.getFlowA().getName()+" | "
			+alignment.getUseCaseB().getName()+" - "
			+alignment.getFlowB().getName();
		}
		return name;
	}


	public AlignmentX2Result getAlignment() {
		return alignment;
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
