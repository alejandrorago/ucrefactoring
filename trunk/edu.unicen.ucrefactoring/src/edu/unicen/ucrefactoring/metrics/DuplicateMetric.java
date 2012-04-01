package edu.unicen.ucrefactoring.metrics;

public class DuplicateMetric implements Metric{

	private String type = Metric.DUPLICATE;
	
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

}
