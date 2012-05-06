package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;

public class DuplicateMetric implements Metric{

	private String type = Metric.DUPLICATE;
	
	public HashMap<String,AlignmentX2Result> alignments;
	
	
	public DuplicateMetric(HashMap<String, AlignmentX2Result> alignments){
		this.alignments = alignments;
	}
	
	@Override
	public String getdata() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public boolean isType(String type) {
		return (type!=null && this.type.equals(type));
	}
	
	/**
	 * Returns the alignment corresponding to the key passed as parameter.
	 * The string key is : UC1nameFlow1name:UC2nameFlow2name
	 * @param key
	 * @return Alignment
	 */
	public AlignmentX2Result getDuplicateAlignment(String key){
		return this.alignments.get(key);
	}
	

}
