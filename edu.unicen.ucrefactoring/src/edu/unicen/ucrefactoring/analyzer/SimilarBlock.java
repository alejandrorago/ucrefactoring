package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.List;

import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;

public class SimilarBlock {
	
	private Integer beginIndex;
	private Integer endIndex;
	private UseCase useCase;
	private Flow flow;
	private AlignmentX2Result parent;

	private List<Event> similarEvents;
	
	
	public SimilarBlock(UseCase useCase, Flow flow, Integer begin,  Integer end, AlignmentX2Result parent ){
		this.beginIndex=begin;
		this.endIndex=end;
		this.useCase = useCase;
		this.flow = flow;
		this.parent = parent;
		this.similarEvents = new ArrayList<Event>();
		initEventList();
	}
	
	public UseCase getUseCase() {
		return useCase;
	}
	public void setUseCase(UseCase useCase) {
		this.useCase = useCase;
	}
	public List<Event> getSimilarEvents() {
		return similarEvents;
	}
	public void setSimilarEvents(List<Event> similarEvents) {
		this.similarEvents = similarEvents;
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
	
	public void initEventList(){
		int startEvent = beginIndex;
		for (Flow flow : useCase.getFlows()){
			if (flow.getName().equals(this.flow.getName())){
				for (Event event : flow.getEvents()){
					if (startEvent <= endIndex && event.getNumber().equals(startEvent+1)){
						similarEvents.add(event);
						startEvent++;
					}
					if (startEvent == endIndex){
						break;
					}
				}
			}
		}
	}
	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public AlignmentX2Result getParent() {
		return parent;
	}

	public void setParent(AlignmentX2Result parent) {
		this.parent = parent;
	}
	
	


}
