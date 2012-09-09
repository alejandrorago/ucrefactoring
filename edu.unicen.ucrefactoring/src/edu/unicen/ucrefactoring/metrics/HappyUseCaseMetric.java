package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.model.UseCase;

public class HappyUseCaseMetric implements Metric {

	private String type = Metric.HAPPY_USECASE;
	public static Integer FLOW_LIMIT = 1;
	
	public HashMap<String,UseCase> useCases;
	
	
	public HappyUseCaseMetric(){
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
	
	public void addHappyUseCase(UseCase useCase){
		this.useCases.put(useCase.getName(), useCase);
	}
	
	public UseCase getHappyUseCase(String useCaseName){
		return this.useCases.get(useCaseName);
	}
	
	public HashMap<String, UseCase> getHappyUseCases(){
		return this.useCases;
	}

}
