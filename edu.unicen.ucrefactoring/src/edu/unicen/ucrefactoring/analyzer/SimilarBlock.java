package edu.unicen.ucrefactoring.analyzer;

public class SimilarBlock {
	
	private Integer beginIndex;
	private Integer endIndex;
	
	
	public SimilarBlock(Integer begin,  Integer end ){
		this.beginIndex=begin;
		this.endIndex=end;

	}


	public Integer getBeginIndex() {
		return beginIndex;
	}


	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}


	public Integer getEndIndex() {
		return endIndex;
	}


	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	


}
