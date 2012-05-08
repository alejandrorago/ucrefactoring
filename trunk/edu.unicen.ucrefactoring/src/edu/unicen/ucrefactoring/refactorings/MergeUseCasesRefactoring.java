package edu.unicen.ucrefactoring.refactorings;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.NonModularFRMetric;
import edu.unicen.ucrefactoring.metrics.ShortUseCaseMetric;
import edu.unicen.ucrefactoring.model.UseCase;


/**
 * Class that models a Merge Use Cases refactoring.
 * @author migue
 *
 */
public class MergeUseCasesRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.MEDIUM_PRIORITY;
	private UseCase useCaseA;
	private UseCase useCaseB;
	
	public MergeUseCasesRefactoring(UseCase useCase1, UseCase useCase2){
		this.score = null;
		this.name = null;
		this.useCaseA = useCase1;
		this.useCaseB = useCase2;
		//this.alignment = alignment;
		this.metrics = new HashMap<String, Metric>();
	}
	
	@Override
	public boolean canApply() {
		for (Metric metric : this.metrics.values()){
			if (metric.isType(Metric.ENCAPSULATED_FUNCTIONAL)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void applyRefactoring() {
		// TODO Auto-generated method stub
		/**
		 * Extension refactoring.
		 * 1- Create new use case
		 * 2- Name the use case with alternative flow name
		 * 3- Add flow and events to new use case
		 * 4- Remove alternative flow and/or duplicate events from use cases
		 * 5- Add new use case to use case model
		 */
		
		
	}

	@Override
	public Float getScore() {
		if (this.score == null && this.useCaseA!=null && this.useCaseB!=null){
			Float actorCoef = 0f;
			Float textCoef = 0f;
			Float shortCoef = 0f;
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
			//get confiability score
			confiability = (actorCoef*textCoef);
			
			//getScore
			this.score = (float)((confiability*(0.25)+shortCoef*(0.15)+ this.getPriority()*(0.6)))*100;
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
	
	
}
