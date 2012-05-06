package edu.unicen.ucrefactoring.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.model.UseCase;

public class NonModularFRMetric implements Metric {

	private String type = Metric.ENCAPSULATED_FUNCTIONAL;
	public static Float TEXT_SIMILARITY_THRESHOLD = 0.5f;
	public static Float ACTOR_SIMILARITY_THRESHOLD = 0.75f;
	
	
	public HashMap<String,List<UseCase>> useCases;
	public HashMap<String, Float> actorSimilarity;
	public HashMap<String, Float> textSimilarity;
	
	public NonModularFRMetric(){
		this.useCases = new HashMap<String, List<UseCase>>();
		this.actorSimilarity = new HashMap<String, Float>();
		this.textSimilarity = new HashMap<String, Float>();
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
	
	public void addNonModularUseCases(UseCase useCaseA, UseCase useCaseB, Float textSimilarity, Float actorSimilarity){
		List<UseCase> useCases = new ArrayList<UseCase>();
		useCases.add(useCaseA);
		useCases.add(useCaseB);
		this.useCases.put(useCaseA.getName()+"-"+useCaseB.getName(), useCases);
		this.addNonModularUseCasesActorSimilarity(useCaseA, useCaseB, actorSimilarity);
		this.addNonModularUseCasesTextSimilarity(useCaseA, useCaseB, textSimilarity);
	}
	
	public List<UseCase> getNonModularUseCase(String useCaseNameA, String useCaseNameB){
		return null;//this.useCases.get(useCaseNameA+"-"+useCaseNameB);
	}
	
	public HashMap<String, List<UseCase>> getNonModularUseCases(){
		return this.useCases;
	}

	private void addNonModularUseCasesTextSimilarity(UseCase useCaseA, UseCase useCaseB, Float textSimilarity){
		this.textSimilarity.put(useCaseA.getName()+"-"+useCaseB.getName(), textSimilarity);
	}
	
	public Float getNonModularUseCasesTextSimilarity(UseCase useCaseA, UseCase useCaseB){
		return this.textSimilarity.get(useCaseA.getName()+"-"+useCaseB.getName());
	}
	
	private void addNonModularUseCasesActorSimilarity(UseCase useCaseA, UseCase useCaseB, Float actorSimilarity){
		this.actorSimilarity.put(useCaseA.getName()+"-"+useCaseB.getName(), actorSimilarity);
	}
	
	public Float getNonModularUseCasesActorSimilarity(UseCase useCaseA, UseCase useCaseB){
		return this.actorSimilarity.get(useCaseA.getName()+"-"+useCaseB.getName());
	}
}
