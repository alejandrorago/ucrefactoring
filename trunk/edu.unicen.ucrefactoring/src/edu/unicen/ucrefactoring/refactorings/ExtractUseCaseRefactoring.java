package edu.unicen.ucrefactoring.refactorings;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.UseCase;

public class ExtractUseCaseRefactoring implements Refactoring {

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private UseCase useCase;
	private Float priority = Refactoring.MEDIUM_PRIORITY;

	
	public ExtractUseCaseRefactoring(UseCase useCase, Metric metric){
		this.score = null;
		this.name = null;
		this.metrics = new HashMap<String, Metric>();
		//this.alignment = alignment;
		this.useCase = useCase;
		this.metrics.put(useCase.getName(), metric);
	}
	
	@Override
	public boolean canApply() {
		// TODO CONTENT ANALYSYS CAN BE ADDED
		return true;
	}

	@Override
	public void applyRefactoring() {
		// TODO Auto-generated method stub
		/**
		 * 1-
		 * 2-
		 * 3-
		 * 4-
		 * 
		 */
	}

	@Override
	public Float getScore() {
		Metric metric = this.metrics.get(this.useCase.getName());
		
		if (metric.isType(Metric.LARGE_USECASE)){
			Float totalCoef = (float)(1 - ((float)(LargeUseCaseMetric.TOTAL_LIMIT/((LargeUseCaseMetric)metric).getUseCaseTotalEventsSize(this.useCase))));
			Float basicCoef = (float)(1 - ((float)(LargeUseCaseMetric.BASIC_FLOW_LIMIT/((LargeUseCaseMetric)metric).getUseCaseBasicEventsSize(this.useCase))));
			if (this.score == null){
				totalCoef = (totalCoef<0)?0:totalCoef;
				basicCoef = (basicCoef<0)?0:basicCoef;
				Float confiability = (totalCoef / basicCoef)/2;
				//getScore
				this.score = (confiability*(0.4f)+this.getPriority()*(0.6f))*100;
			}
		}
		else score = 0f;
		return this.score;
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = useCase.getName();
		}
		return name;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		return null;
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
	
}