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
	private Double textSimilarityScore;
	

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
	
	public List<SimilarBlock> getSimilarBlocksFromA2(){
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
	
	public List<SimilarBlock> getSimilarBlocksFromA(){
		if (similarBlocksA==null){
			List<SimilarBlock> similarBlocks = new ArrayList<SimilarBlock>();
			int initial = 0;
			int i = 0;
			int realIndex = 0;
			
			char arrayA[] = new char[this.alignmentA.length()];
			this.getAlignmentA().getChars(0, this.alignmentA.length(), arrayA, 0);
			
			char arrayB[] = new char[this.alignmentB.length()];
			this.getAlignmentB().getChars(0, this.alignmentB.length(), arrayB, 0);
			
			int unaligned = 0;
			//initial = realIndex + 1 + unaligned + 1;
			int aligned = 0;
			
			for (char a : arrayA){
				if (i < arrayB.length){
					if (arrayB[i] == a){
						//continue block
						aligned++;
						unaligned = 0;
					}
					else if ((arrayB[i] != "-".toCharArray()[0] && a != "-".toCharArray()[0])){
						//for now align points too (not same char but not dash either)
						//continue block
						aligned++;
						unaligned = 0;
					}
					else if (aligned > 0){
						//not matched
						//accept only one unaligned char
						if (unaligned < 1){
							// continue block
							aligned++;
							unaligned++;
						}
						else{
							//break block
							aligned = aligned - unaligned;
							realIndex = this.startA + initial + aligned - 1;// +1?
							//if has more than X aligned chars
							if (aligned > 2 ){
								//save block
								SimilarBlock sb = new SimilarBlock(useCaseA, flowA, this.startA + initial, realIndex, this);
								similarBlocks.add(sb);
							}
							initial = realIndex + 1 + unaligned + 1;
							aligned = 0;
							unaligned = 0;
						}
					}
					else if (a == "-".toCharArray()[0]){
						//if this secuence has dashes, move the initial
						initial+=1;
					}
					else if (arrayB[i] == "-".toCharArray()[0]){
						//the other secuence has the dashes, move the initial
						initial-= 2;
					}
				}
				i++;
			}
			//if reached the end with a block check aligneds
			if (aligned > 2 ){
				realIndex = this.startA + initial + aligned - unaligned;// +1?
				//save block
				SimilarBlock sb = new SimilarBlock(useCaseA, flowA, this.startA + initial, realIndex , this);
				similarBlocks.add(sb);
			}
			
			similarBlocksA = similarBlocks;
		}
		return similarBlocksA;
	}
	
	public List<SimilarBlock> getSimilarBlocksFromB2(){
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
	
	public List<SimilarBlock> getSimilarBlocksFromB(){
		if (similarBlocksB==null){
			List<SimilarBlock> similarBlocks = new ArrayList<SimilarBlock>();
			int initial = 0;
			int i = 0;
			int realIndex = 0;
			
			char arrayB[] = new char[this.alignmentB.length()];
			this.getAlignmentB().getChars(0, this.alignmentB.length(), arrayB, 0);
			
			char arrayA[] = new char[this.alignmentA.length()];
			this.getAlignmentA().getChars(0, this.alignmentA.length(), arrayA, 0);
			
			int unaligned = 0;
			int aligned = 0;
			
			for (char b : arrayB){
				if (i < arrayA.length){
					if (arrayA[i] == b){
						//continue block
						aligned++;
						unaligned = 0;
					}
					else if ((arrayA[i] != "-".toCharArray()[0] && b != "-".toCharArray()[0])){
						//for now align "points" too
						//continue block
						aligned++;
						unaligned = 0;
					}
					//if starts with dash, avoid it
					else if (aligned > 0){
						//not matched
						//accept only one unaligned char
						if (unaligned <	 1){
							// continue block
							aligned++;
							unaligned++;
						}
						else{
							//break block
							aligned = aligned - unaligned;
							realIndex = this.startB + initial + aligned - 1;// +1?
							//if has more than X aligned chars
							if (aligned > 2 ){
								//save block
								SimilarBlock sb = new SimilarBlock(useCaseB, flowB, this.startB + initial, realIndex, this);
								similarBlocks.add(sb);
							}
							initial = realIndex + 1 + unaligned + 1;
							aligned = 0;
							unaligned = 0;
						}
					}
					else if (b == "-".toCharArray()[0]){
						//if this secuence has dashes, move the initial
						initial+=1;
					}
					else if (arrayA[i] == "-".toCharArray()[0]){
						//the other secuence has the dashes, move the initial
						initial-=2;
					}
				}
				i++;
			}
			//if reached the end with a block check aligned
			if (aligned > 2 ){
				realIndex = this.startB + initial + aligned - unaligned;// +1?
				//save block
				SimilarBlock sb = new SimilarBlock(useCaseB, flowB, this.startB + initial, realIndex, this);
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
	

	public Double getTextSimilarityScore() {
		return textSimilarityScore;
	}

	public void setTextSimilarityScore(Double textSimilarityScore) {
		this.textSimilarityScore = textSimilarityScore;
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
