package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.List;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;

public class AlignmentX2Result {
	
	private UseCase useCaseA;
	private Flow flowA;
	private UseCase useCaseB;
	private Flow flowB;

	private String alignmentA;
	private String alignmentB;
	
	private Integer startA;
	private Integer startB;
	
	private Float score;
	
	public List<SimilarBlock> similarBlocksA = null;
	public List<SimilarBlock> similarBlocksB = null;
	
	public AlignmentX2Result(String s1, String s2, Integer i1, Integer i2, Float score){
		this.alignmentA = s1;
		this.alignmentB = s2;
		this.startA = i1;
		this.startB = i2;
		this.score = score;
		
	}
	
	public void setSimilarBlocksB(List<SimilarBlock> similarBlocks){
		this.similarBlocksB = similarBlocks;
	}
	
	public void setSimilarBlocksA(List<SimilarBlock> similarBlocks){
		this.similarBlocksA = similarBlocks;
	}

	public UseCase getUseCaseA() {
		return useCaseA;
	}


	public void setUseCaseA(UseCase useCaseA) {
		this.useCaseA = useCaseA;
	}


	public UseCase getUseCaseB() {
		return useCaseB;
	}


	public void setUseCaseB(UseCase useCaseB) {
		this.useCaseB = useCaseB;
	}


	public String getAlignmentA() {
		return alignmentA;
	}
	public void setAlignmentA(String alignmentA) {
		this.alignmentA = alignmentA;
	}
	public String getAlignmentB() {
		return alignmentB;
	}
	public void setAlignmentB(String alignmentB) {
		this.alignmentB = alignmentB;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getStartA() {
		return startA;
	}
	public void setStartA(Integer startA) {
		this.startA = startA;
	}
	public Integer getStartB() {
		return startB;
	}
	public void setStartB(Integer startB) {
		this.startB = startB;
	}
	
	public List<SimilarBlock> getSimilarBlocksFromA(){
		if (similarBlocksA==null){
			List<SimilarBlock> similarBlocks = new ArrayList<SimilarBlock>();
			int initial = 0;
			int i = 0;
			int realIndex = 0;
			while (i < this.alignmentA.length() - 2){
				char a = this.alignmentA.charAt(i);
				char b = this.alignmentB.charAt(i);
				char a1 = this.alignmentA.charAt(i+1);
				char b1 = this.alignmentB.charAt(i+1);
				char a2 = this.alignmentA.charAt(i+2);
				char b2 = this.alignmentB.charAt(i+2);
				if (a1 != b1 && a2 != b2){ 	
					int steps = (realIndex - initial) + 1; // Cantidad de pasos
					if(steps > 2){
						SimilarBlock sb = new SimilarBlock(useCaseA, flowA, this.startA + initial, realIndex, this);
						similarBlocks.add(sb);
					}
					i++;
					if (this.alignmentA.charAt(i) != '-')
						realIndex++;
					while (i < this.alignmentA.length()-2 && this.alignmentA.charAt(i) != this.alignmentB.charAt(i)){
						i++;
						if (this.alignmentA.charAt(i) != '-')
							realIndex++;
					}
					initial = realIndex;
				}
				else{
					i++;
					realIndex++;
				}
			}
			int steps = realIndex - initial +2; // Cantidad de pasos
			if(steps > 2){
				SimilarBlock sb = new SimilarBlock(useCaseA, flowA, this.startA + initial, realIndex+1, this);
				similarBlocks.add(sb);
			}
			similarBlocksA = similarBlocks;
		}
		return similarBlocksA;
	}
	
	public List<SimilarBlock> getSimilarBlocksFromB(){
		if (similarBlocksB==null){
			List<SimilarBlock> similarBlocks = new ArrayList<SimilarBlock>();
			int initial = 0;
			int i = 0;
			int realIndex = 0;
			while (i < this.alignmentB.length() - 2){
				char a = this.alignmentA.charAt(i);
				char b = this.alignmentB.charAt(i);
				char a1 = this.alignmentA.charAt(i+1);
				char b1 = this.alignmentB.charAt(i+1);
				char a2 = this.alignmentA.charAt(i+2);
				char b2 = this.alignmentB.charAt(i+2);
				if (a1 != b1 && a2 != b2){ 	
					int steps = (realIndex - initial) + 1; // Cantidad de pasos
					if(steps > 2){
						SimilarBlock sb = new SimilarBlock(useCaseB, flowB, this.startB + initial, realIndex, this);
						similarBlocks.add(sb);
					}
					i++;
					if (this.alignmentB.charAt(i) != '-')
						realIndex++;
					while (i < this.alignmentB.length()-2 && this.alignmentA.charAt(i) != this.alignmentB.charAt(i)){
						i++;
						if (this.alignmentB.charAt(i) != '-')
							realIndex++;
					}
					initial = realIndex;
				}
				else{
					i++;
					realIndex++;
				}
			}
			int steps = realIndex - initial +2; // Cantidad de pasos
			if(steps > 2){
				SimilarBlock sb = new SimilarBlock(useCaseB, flowB, this.startB + initial, realIndex+1, this);
				similarBlocks.add(sb);
			}
			similarBlocksB = similarBlocks;
		}
		return similarBlocksB;
	}

	public void setUseCases(UseCase uc1,Flow flow1, UseCase uc2, Flow flow2) {
		if(uc1.getName().compareTo(uc2.getName())>0){
			setUseCaseA(uc1);
			setFlowA(flow1);
			setUseCaseB(uc2);
			setFlowB(flow2);
		}
		else{
			setUseCaseA(uc2);
			setFlowA(flow2);
			setUseCaseB(uc1);
			setFlowB(flow1);
		}
	}
	

	public Flow getFlowA() {
		return flowA;
	}

	public void setFlowA(Flow flowA) {
		this.flowA = flowA;
	}

	public Flow getFlowB() {
		return flowB;
	}

	public void setFlowB(Flow flowB) {
		this.flowB = flowB;
	}
	
}
