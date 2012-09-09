package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import javax.jws.soap.SOAPBinding.Use;

import edu.unicen.ucrefactoring.model.UseCase;

public class WrongUseCaseRelationshipMetric implements Metric{

	public static final String GENERALIZATION_RELATIONSHIP = "GENERALIZATION";
	public static final String INCLUSION_RELATIONSHIP = "INCLUSION";
	public static final String EXTENSION_RELATIONSHIP = "EXTENSION";
	
	private String type = Metric.WRONG_USECASE_RELATIONSHIP;
	
	public HashMap<String,String> relationships;
	
	public WrongUseCaseRelationshipMetric() {
		this.relationships = new HashMap<String, String>();
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

	public void addWrongUseCaseRelationship(UseCase useCase1, UseCase useCase2, String type){
		this.relationships.put(this.getKey(useCase1, useCase2), type);
	}
	
	private String getKey(UseCase uc1, UseCase uc2){
		return uc1.getName() + ":" + uc2.getName();
	}
}
