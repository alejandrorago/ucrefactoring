package edu.unicen.ucrefactoring.refactorings;

import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.UseCase;

public interface Refactoring {
	
	public static Float HIGH_PRIORITY = 1f;
	public static Float MEDIUM_PRIORITY = 0.7f;
	public static Float LOW_PRIORITY = 0.4f;
	
	public static String HIGH_PRIORITY_TEXT = "HIGH";
	public static String MEDIUM_PRIORITY_TEXT = "MEDIUM";
	public static String LOW_PRIORITY_TEXT = "LOW";
	
	public boolean canApply();
	
	public boolean applyRefactoring();

	public Float getScore();
	
	public String getName();
	
	public AlignmentX2Result getAlignment();
	
	public UseCase getUseCase();
	
	public void addMetric(Metric metric);
	
	public Float getPriority();
	
	public String getProblem();
	
	public List<String> getArtifacts();

	public String getRefactoringName();
	
	public String getPriorityText();
	
	public Long getID();

	public void setID(Long ID);

}
