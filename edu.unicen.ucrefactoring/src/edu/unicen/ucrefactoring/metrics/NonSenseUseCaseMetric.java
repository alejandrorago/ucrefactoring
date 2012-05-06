package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.model.UseCase;

public class NonSenseUseCaseMetric implements Metric{


	private String type = Metric.NON_SENSE_USECASE;
	
	public HashMap<String,UseCase> useCases;
	
	public NonSenseUseCaseMetric() {
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
		return this.type.equals(type);
	}

	public void addNonSenseUseCase(UseCase useCase){
		this.useCases.put(useCase.getName(), useCase);
	}
}
