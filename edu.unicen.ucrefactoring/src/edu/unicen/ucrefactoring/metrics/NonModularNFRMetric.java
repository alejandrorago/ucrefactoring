package edu.unicen.ucrefactoring.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import edu.isistan.reassistant.model.CrosscuttingConcern;
import edu.unicen.ucrefactoring.model.UseCase;

public class NonModularNFRMetric implements Metric {

	private String type = Metric.ENCAPSULATED_NON_FUNCTIONAL;
	
	public Map<String,List<UseCase>> ccUseCases;
	public Map<String,CrosscuttingConcern> concerns;
	public Map<String,UseCase> useCases;
		
	public NonModularNFRMetric(){
		this.ccUseCases = new HashMap<String, List<UseCase>>();	
		this.concerns = new HashMap<String, CrosscuttingConcern>();
		this.useCases = new HashMap<String, UseCase>();
	}
	
	public void addCrosscutingConcern(CrosscuttingConcern cc, UseCase uc){
		if(this.ccUseCases.get(cc.getName()) == null){
			List<UseCase> l = new ArrayList<UseCase>();
			l.add(uc);
			this.ccUseCases.put(cc.getName(), l);
			
			this.concerns.put(cc.getName(), cc);
		}
		else{
			List<UseCase> l = this.ccUseCases.get(cc.getName());
			boolean exists = false;
			for(UseCase u : l){
				if(u.getName().equals(uc.getName())){
					exists = true;
				}
			}
			if(!exists){
				this.ccUseCases.get(cc.getName()).add(uc);
			}
		}
		
		if(this.useCases.get(uc.getName()) == null){
			this.useCases.put(uc.getName(), uc);
		}
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

	
}
