package edu.unicen.ucrefactoring.analyzer;

public class SimilarBlock {
	
	private Integer beginIndexA;
	private Integer endIndexA;
	
	private Integer beginIndexB;
	private Integer endIndexB;
	
	public SimilarBlock(Integer bA, Integer bB, Integer eA, Integer eB){
		this.beginIndexA=bA;
		this.beginIndexB=bB;
		this.endIndexA=eA;
		this.endIndexB=eB;
	}
	
	public Integer getBeginIndexA() {
		return beginIndexA;
	}
	public void setBeginIndexA(Integer beginIndexA) {
		this.beginIndexA = beginIndexA;
	}
	public Integer getEndIndexA() {
		return endIndexA;
	}
	public void setEndIndexA(Integer endIndexA) {
		this.endIndexA = endIndexA;
	}
	public Integer getBeginIndexB() {
		return beginIndexB;
	}
	public void setBeginIndexB(Integer beginIndexB) {
		this.beginIndexB = beginIndexB;
	}
	public Integer getEndIndexB() {
		return endIndexB;
	}
	public void setEndIndexB(Integer endIndexB) {
		this.endIndexB = endIndexB;
	}

}
