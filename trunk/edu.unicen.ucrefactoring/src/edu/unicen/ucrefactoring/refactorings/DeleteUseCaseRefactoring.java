package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.UseCase;

public class DeleteUseCaseRefactoring implements Refactoring {

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.LOW_PRIORITY;
	private UseCase useCase;
	private String problem = "Use Case can never be activated";
	private String refactoringName = "Delete non sense Use Case";
	private List<String> artifacts; 
	
	public DeleteUseCaseRefactoring(UseCase useCase){
		this.score = null;
		this.name = null;
		//this.alignment = alignment;
		this.useCase = useCase;
		this.metrics = new HashMap<String, Metric>();
		this.artifacts = new ArrayList<String>();
		this.artifacts.add(this.useCase.getName());
	}
	
	@Override
	public boolean canApply() {
		// TODO create canApply method
		return true;
	}

	@Override
	public boolean applyRefactoring() {
		// TODO Auto-generated method stub
		return false;
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
			this.name = this.useCase.getName();
		}
		return name;
	}


	@Override
	public UseCase getUseCase() {
		return this.useCase;
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

	@Override
	public String getProblem() {
		return this.problem;
	}

	@Override
	public List<String> getArtifacts() {
		return this.artifacts;
	}

	@Override
	public String getRefactoringName() {
		return this.refactoringName;
	}
	
	@Override
	public String getPriorityText() {
		return LOW_PRIORITY_TEXT;
	}
}
