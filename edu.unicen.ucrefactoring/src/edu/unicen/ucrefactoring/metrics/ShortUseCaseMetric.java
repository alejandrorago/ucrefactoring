package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.model.UseCase;

public class ShortUseCaseMetric implements Metric {

	private String type = Metric.SHORT_USECASE;
	public static Integer BASIC_FLOW_LIMIT = 3;
	
	public HashMap<String,UseCase> useCases;
	public HashMap<String, Integer> basicEvents;
	
	
	public ShortUseCaseMetric(){
		this.basicEvents = new HashMap<String, Integer>();
		this.useCases = new HashMap<String, UseCase>();
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
		return (type!=null && this.type.equals(type));
	}
	
	public void addShortUseCase(UseCase useCase, Integer basicFlowEventsSize){
		this.useCases.put(useCase.getName(), useCase);
		this.addUseCaseBasicEventsSize(useCase, basicFlowEventsSize);
	}
	
	public UseCase getShortUseCase(String useCaseName){
		return this.useCases.get(useCaseName);
	}
	
	public HashMap<String, UseCase> getShortUseCases(){
		return this.useCases;
	}

	private void addUseCaseBasicEventsSize(UseCase useCase, Integer size ){
		this.basicEvents.put(useCase.getName(), size);
	}
	
	public Integer getUseCaseBasicEventsSize(UseCase useCase){
		return this.basicEvents.get(useCase.getName());
	}
}
