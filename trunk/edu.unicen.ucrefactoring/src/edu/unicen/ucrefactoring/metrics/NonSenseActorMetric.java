package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.UseCase;

public class NonSenseActorMetric implements Metric{

	private String type = Metric.NON_SENSE_ACTOR;
	
	//public HashMap<String,UseCase> useCases;
	public HashMap<String, Actor> actors;
	
	public NonSenseActorMetric() {
		//this.useCases = new HashMap<String, UseCase>();
		this.actors = new HashMap<String, Actor>();
	}
		
	@Override
	public String getdata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean isType(String type) {
		return this.type.equals(type);
	}

//	public void addNonSenseUseCase(UseCase useCase){
//		this.useCases.put(useCase.getName(), useCase);
//	}
	
	public void addNonSenseActor(Actor actor){
		this.actors.put(actor.getName(), actor);
	}
}
