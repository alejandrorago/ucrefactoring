package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.model.UseCase;

/**
 * Metric that represents a Long Use Case
 * @author migue
 *
 */
public class LargeUseCaseMetric implements Metric {

	private String type = Metric.LARGE_USECASE;
	public static Integer TOTAL_LIMIT = 16;
	public static Integer BASIC_FLOW_LIMIT = 9;
	
	public HashMap<String,UseCase> useCases;
	public HashMap<String, Integer> totalEvents;
	public HashMap<String, Integer> basicEvents;
	
	
	public LargeUseCaseMetric(){
		this.basicEvents = new HashMap<String, Integer>();
		this.totalEvents = new HashMap<String, Integer>();
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
	
	public void addLargeUseCase(UseCase useCase, Integer totalEventsSize, Integer basicFlowEventsSize){
		this.useCases.put(useCase.getName(), useCase);
		this.addUseCaseBasicEventsSize(useCase, basicFlowEventsSize);
		this.addUseCaseTotalEventsSize(useCase, totalEventsSize);
	}
	
	public UseCase getLargeUseCase(String useCaseName){
		return this.useCases.get(useCaseName);
	}
	
	public HashMap<String, UseCase> getLargeUseCases(){
		return this.useCases;
	}

	private void addUseCaseBasicEventsSize(UseCase useCase, Integer size ){
		this.basicEvents.put(useCase.getName(), size);
	}
	
	public Integer getUseCaseBasicEventsSize(UseCase useCase){
		return this.basicEvents.get(useCase.getName());
	}
	
	private void addUseCaseTotalEventsSize(UseCase useCase, Integer size ){
		this.totalEvents.put(useCase.getName(), size);
	}
	
	public Integer getUseCaseTotalEventsSize(UseCase useCase){
		return this.totalEvents.get(useCase.getName());
	}
}
