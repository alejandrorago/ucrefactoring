package edu.unicen.ucrefactoring.refactorings;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.UseCase;

public class DeleteActorRefactoring implements Refactoring {

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.LOW_PRIORITY;
	//private UseCase useCase;
	private Actor actor;
	
	public DeleteActorRefactoring(Actor actor){
		this.score = null;
		this.name = null;
		//this.alignment = alignment;
		//this.useCase = useCase;
		this.metrics = new HashMap<String, Metric>();
		this.actor = actor;
	}
	
	@Override
	public boolean canApply() {
		// TODO create canApply method
		return true;
	}

	@Override
	public void applyRefactoring() {
		// TODO Auto-generated method stub
	}

	@Override
	public Float getScore() {
		if (this.score == null){
			Float confiability = 1f;
			
			//get score
			this.score = (float) ((confiability*(0.4) + this.priority*(0.6)) * 100); 
		}
		return score;
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = this.actor.getName();
		}
		return name;
	}


	@Override
	public UseCase getUseCase() {
		//return this.useCase;
		return null;
	}
	
	public Actor getActor() {
		return this.actor;
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
	public AlignmentX2Result getAlignment() {
		return null;
	}
	
}
