package edu.unicen.ucrefactoring.refactorings;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.UseCase;

public interface Refactoring {
	
	public static Float HIGH_PRIORITY = 1f;
	public static Float MEDIUM_PRIORITY = 0.7f;
	public static Float LOW_PRIORITY = 0.4f;
	
	public boolean canApply();
	
	public void applyRefactoring();

	public Float getScore();
	
	public String getName();
	
	public AlignmentX2Result getAlignment();
	
	public UseCase getUseCase();
	
	public void addMetric(Metric metric);
	
	public Float getPriority();

}
