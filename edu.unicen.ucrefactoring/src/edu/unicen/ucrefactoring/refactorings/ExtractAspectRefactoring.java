package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.NonModularNFRMetric;
import edu.unicen.ucrefactoring.model.UseCase;


/**
 * Class that models an aspect refactoring.
 * @author migue
 *
 */
public class ExtractAspectRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	private String ccName;
	
	private String name; 
	private Float priority = Refactoring.MEDIUM_PRIORITY;
	private String problem = "Crosscuting Concern";
	private String refactoringName = "Extract To Aspect";
	private List<String> artifacts; 
	private Long ID;

	
	public ExtractAspectRefactoring(String ccName){
		this.score = null;
		this.name = null;
		this.ccName = ccName;
		this.artifacts = new ArrayList<String>();
		this.metrics = new HashMap<String, Metric>();
	}
	
	@Override
	public boolean canApply() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean applyRefactoring() {
		return true;
	}

	@Override
	public Float getScore() {
		if (this.score == null){
			this.score = 0f;
		}
		return this.score;
	}

	public String getName() {
		if(name == null || name == ""){
			NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			for (String s : met.concerns.keySet()){
				this.name = name + s + " | ";
			}
		}
		return name;
	}

	@Override
	public UseCase getUseCase() {
		return null;
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
	public String getProblem() {
		return this.problem + ": " + this.ccName;
	}

	@Override
	public List<String> getArtifacts() {
		if(artifacts == null || artifacts.size() == 0){
			NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			for (UseCase uc : met.ccUseCases.get(ccName)){
				this.artifacts.add(uc.getName());
			}
		}
		return this.artifacts;
	}

	@Override
	public String getRefactoringName() {
		return this.refactoringName;
	}
	
	@Override
	public String getPriorityText() {
		return MEDIUM_PRIORITY_TEXT;
	}
	
	@Override
	public Long getID() {
		return this.ID;
	}
	
	public void setID(Long ID){
		this.ID = ID;
	}
	
	@Override
	public boolean affectsUseCase(UseCase useCase) {
		NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
		for (UseCase u : met.ccUseCases.get(ccName)){
			if(u.getName().equals(useCase)){
				return true;
			}
		}
		return false;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
