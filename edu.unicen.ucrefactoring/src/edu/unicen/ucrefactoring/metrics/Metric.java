package edu.unicen.ucrefactoring.metrics;

import java.util.HashMap;

public interface Metric {
	
	public static final String DUPLICATE = "DUPLICATE_METRIC";
	public static final String ENCAPSULATED_NON_FUNCTIONAL = "ENCAPSULATED_NON_FUNCTIONAL";
	public static final String ENCAPSULATED_FUNCTIONAL = "ENCAPSULATED_FUNCTIONAL";
	public static final String NON_SENSE_ACTOR = "NON_SENSE_ACTOR";
	public static final String NON_SENSE_USECASE = "NON_SENSE_USECASE";
	
	public String getdata();
	
	public String getType();
	
	public boolean isType(String type);
	
}
