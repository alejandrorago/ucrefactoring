package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.UseCase;

public class DeleteWrongUseCaseRelationshipRefactoring implements Refactoring {

	private String type = Refactoring.REF_DELETE_UC_RELATIONSHIP;

	private Float score;
	private HashMap<String,Metric> metrics;
	//private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.LOW_PRIORITY;
	private UseCase useCase1;
	private UseCase useCase2;
	private String relType;
	private String problem = relType + " between this two use cases is not right.";
	private String refactoringName = "Delete Non Sense Use Case Relationship";
	private List<String> artifacts; 
	private Long ID;

	
	public DeleteWrongUseCaseRelationshipRefactoring(UseCase useCase1, UseCase useCase2, String relType){
		this.score = null;
		this.name = null;
		//this.alignment = alignment;
		this.useCase1 = useCase1;
		this.useCase2 = useCase2;
		this.relType = relType; 
		this.metrics = new HashMap<String, Metric>();
		this.artifacts = new ArrayList<String>();
		this.artifacts.add(this.useCase1.getName());
		this.artifacts.add(this.useCase2.getName());
	}
	
	@Override
	public boolean canApply() {
		// TODO create canApply method
		return true;
	}

	@Override
	public boolean applyRefactoring() {
		String q = "¿Desea eliminar la relación de '";
		q = q + this.relType;
		q = q + "' , entre ambos casos de uso?";
		int cancel = UCRUseCasesView.warningDialog(q);
		if (cancel == 0){
			// REMOVER LA RELACION
			return true;
		}
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
			this.name = this.useCase1.getName() + "/" + this.useCase2.getName();
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
	
	@Override
	public Long getID() {
		return this.ID;
	}
	
	public void setID(Long ID){
		this.ID = ID;
	}
	@Override
	public boolean affectsUseCase(UseCase useCase) {
		return this.useCase1.getName().equals(useCase.getName()) || 
				this.useCase2.getName().equals(useCase.getName());
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDetail() {
		return this.getUseCase().getName()+"\n";
	}
	
}
