package edu.unicen.ucrefactoring.refactorings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.metrics.DuplicateMetric;
import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.NonModularFRMetric;
import edu.unicen.ucrefactoring.metrics.NonSenseActorMetric;
import edu.unicen.ucrefactoring.metrics.NonSenseUseCaseMetric;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.UseCase;

public class RefactoringCreator {

	private HashMap<String, Refactoring> refactorings = new HashMap<String, Refactoring>();
	private HashMap<String, Metric> metrics = new HashMap<String, Metric>();
	
	public RefactoringCreator(HashMap<String, Metric> metrics){
		this.metrics = metrics;
	}
	
	public HashMap<String,Refactoring> createRefactorings(){
		for (Metric metric : metrics.values()){
			if (metric.isType(Metric.DUPLICATE)){
				getDuplicateRefactorings(metric);
			}
			if (metric.isType(Metric.ENCAPSULATED_FUNCTIONAL)){
				getNonModularRefactoring(metric);
			}
			if (metric.isType(Metric.ENCAPSULATED_NON_FUNCTIONAL)){
				
			}
			if (metric.isType(Metric.NON_SENSE_ACTOR)){
				getNonSenseActorRefactorings(metric);
			}
			if (metric.isType(Metric.NON_SENSE_USECASE)){
				getNonSenseUseCaseRefactorings(metric);
			}
			if (metric.isType(Metric.LARGE_USECASE)){
				getLargeRefactorings(metric);
			}
			if (metric.isType(Metric.SHORT_USECASE)){
				
			}
			if (metric.isType(Metric.HAPPY_USECASE)){
				
			}
			if (metric.isType(Metric.WRONG_USECASE_RELATIONSHIP)){
				
			}
		}
		this.setIds();
		return this.refactorings;
	}
	
	private void getDuplicateRefactorings(Metric metric){
		String extension = "Extension";
		int i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				ExtensionRefactoring extRefactoring = new ExtensionRefactoring(alignment);
				if (extRefactoring.canApply()){
					this.refactorings.put(extension+i, extRefactoring);
				}
				i++;
			}
		}
		
		String inclusion = "Inclusion ";
		i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				InclusionRefactoring intRefactoring = new InclusionRefactoring(alignment);
				if (intRefactoring.canApply()){
					this.refactorings.put(inclusion+i, intRefactoring);
				}
				i++;
			}
		}
		
		String generalization = "Generalization ";
		i = 1;
		for (AlignmentX2Result alignment : ((DuplicateMetric)metric).alignments.values()){
			if (alignment.getSimilarBlocksFromA().size() >0 && alignment.getSimilarBlocksFromB().size() >0){
				GeneralizationRefactoring genRefactoring = new GeneralizationRefactoring(alignment);
				if (genRefactoring.canApply()){
					this.refactorings.put(generalization+i, genRefactoring);
				}
				i++;
			}
		}
		
	}
	
	private void getLargeRefactorings(Metric metric){
		String extraction = "Extraction";
		int i = 1;
		for (UseCase uc : ((LargeUseCaseMetric)metric).useCases.values()){
			ExtractUseCaseRefactoring extractRefactoring = new ExtractUseCaseRefactoring(uc, metric);
			if (extractRefactoring.canApply()){
				this.refactorings.put(extraction+i, extractRefactoring);
			}
			i++;
		}
	}
	
	
	
	private void	getNonModularRefactoring(Metric metric){
		String merge = "Merge";
		int i = 1;
		for (java.util.List<UseCase> list : ((NonModularFRMetric)metric).getNonModularUseCases().values()){
			if (list.size()==2){
				MergeUseCasesRefactoring mergeRefactoring = new MergeUseCasesRefactoring(list.get(0), list.get(1));
				mergeRefactoring.addMetric(metrics.get(Metric.SHORT_USECASE));
				if (mergeRefactoring.canApply()){
					this.refactorings.put(merge+i, mergeRefactoring);
				}
				i++;
			}
		}
	}
	
	public void getNonSenseUseCaseRefactorings(Metric metric){
		String delete = "DeleteUsCase";
		int i = 1;
		for (UseCase useCase : ((NonSenseUseCaseMetric)metric).useCases.values()){
			DeleteUseCaseRefactoring deleteUseCaseRefactoring = new DeleteUseCaseRefactoring(useCase);
			if (deleteUseCaseRefactoring.canApply()){
				this.refactorings.put(delete+i, deleteUseCaseRefactoring);
			}
			i++;
		}	
	}
	
	public void getNonSenseActorRefactorings(Metric metric){
		String delete = "DeleteActor";
		int i = 1;
		for (Actor actor : ((NonSenseActorMetric)metric).actors.values()){
			DeleteActorRefactoring deleteActorRefactoring = new DeleteActorRefactoring(actor);
			if (deleteActorRefactoring.canApply()){
				this.refactorings.put(delete+i, deleteActorRefactoring);
			}
			i++;
		}	
	}
	
	public void setIds(){
		Long id = 1l;
		Refactoring top = null;
		List<Refactoring> ordered = new ArrayList<Refactoring>();
		while (ordered.size()<refactorings.size()){
			int count = 0;
			for (Refactoring ref : this.refactorings.values()){
				if (!ordered.contains(ref) && (top == null || (top!=null && ref.getScore()>top.getScore()))){
					ref.setID(id);
					top = ref;
				}
			}
			ordered.add(top);
			top = null;
			id++;
		}
	}
	
}
