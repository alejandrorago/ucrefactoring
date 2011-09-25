package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.List;

public class AlignmentX2Result {
	
	private String sequenceAName;
	private String sequenceBName;
	
	private String alignmentA;
	private String alignmentB;
	
	private Integer startA;
	private Integer startB;
	
	private Float score;
	
	
	public AlignmentX2Result(String s1, String s2, Integer i1, Integer i2, Float score){
		this.alignmentA = s1;
		this.alignmentB = s2;
		this.startA = i1;
		this.startB = i2;
		this.score = score;
	}
	
	public String getSequenceAName() {
		return sequenceAName;
	}
	public void setSequenceAName(String sequenceAName) {
		this.sequenceAName = sequenceAName;
	}
	public String getSequenceBName() {
		return sequenceBName;
	}
	public void setSequenceBName(String sequenceBName) {
		this.sequenceBName = sequenceBName;
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
	
	public List<SimilarBlock> getSimilarBlocks(){
		List<SimilarBlock> l = new ArrayList<SimilarBlock>();
		int initial = 0;
		int i = 0;
		int missmatch = 0;
		while (i < this.alignmentA.length()){
			char a = this.alignmentA.charAt(i);
			char b = this.alignmentB.charAt(i);
			if(a == b){
				// Todo sigue bien
				i++;
				missmatch = 0;
			}
			else if (missmatch==0){
				// Por ahora le doy changui
				missmatch++;
			}
			else{
				// Se pudrio todo (2 errores consecutivos)
				int steps = i - 2;
				if(steps > 2){
					//SimilarBlock sb = new SimilarBlock();
				}
			}
		}
		return l;
	}
}