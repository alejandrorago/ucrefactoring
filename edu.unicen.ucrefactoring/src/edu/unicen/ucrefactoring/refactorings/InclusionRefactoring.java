package edu.unicen.ucrefactoring.refactorings;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;

public class InclusionRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.HIGH_PRIORITY;
	
	public InclusionRefactoring(AlignmentX2Result alignment){
		this.score = null;
		this.name = null;
		this.alignment = alignment;
	}
	
	@Override
	public boolean canApply() {
		// TODO INCORPORAR CHEQUEO DE IGUALES FLUJOS ALTERNATIVOS / EXT 
		if ((this.alignment.getFlowA().getName().equals("Basic Flow") 
			&& this.alignment.getFlowB().getName().equals("Basic Flow"))
			&& (this.alignment.getSimilarBlocksFromA().size()==1
			&& this.alignment.getSimilarBlocksFromB().size()==1)){
					return true;
		}
		return false;
	}

	@Override
	public void applyRefactoring() {
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
		if (this.score == null){
			Float sizeCoef = 0f;
			Float totalSize = 0f;
			Float blocksSize = 0f;
			Float confiability = 0f;
			for (Flow f1 : this.alignment.getUseCaseA().getFlows()){
				totalSize += f1.getEvents().size();
			}
			for (Flow f2 : this.alignment.getUseCaseB().getFlows()){
				totalSize += f2.getEvents().size();
			}
			for (SimilarBlock sb1 : this.alignment.getSimilarBlocksFromA()){
				blocksSize += sb1.getSimilarEvents().size();
			}
			for (SimilarBlock sb2 : this.alignment.getSimilarBlocksFromB()){
				blocksSize += sb2.getSimilarEvents().size();
			}

			//get size coef
			sizeCoef = ((blocksSize) / (totalSize));
			//get confiability
			confiability = (float) (sizeCoef * alignment.getTextSimilarityScore());
			
			//getScore
			this.score = (float)((confiability*(0.4) + this.getPriority()*(0.6)))*100;
		}
		return this.score;
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = alignment.getUseCaseA().getName()+" - "
			+alignment.getFlowA().getName()+" | "
			+alignment.getUseCaseB().getName()+" - "
			+alignment.getFlowB().getName();
		}
		return name;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		return this.alignment;
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

}
