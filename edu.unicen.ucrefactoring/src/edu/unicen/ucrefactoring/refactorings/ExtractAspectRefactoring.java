package edu.unicen.ucrefactoring.refactorings; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.isistan.reassistant.model.CrosscuttingConcern;
import edu.isistan.reassistant.model.Impact;
import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.metrics.NonModularNFRMetric;
import edu.unicen.ucrefactoring.model.Aspect;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.JointPoint;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;


/**
 * Class that models an aspect refactoring.
 * @author migue
 *
 */
public class ExtractAspectRefactoring implements Refactoring{

	private Float score;
	private HashMap<String,Metric> metrics;
	private String ccName;

	private String type = Refactoring.REF_EXTRACT_ASPECT;

	private String name; 
	private Float priority = Refactoring.MEDIUM_PRIORITY;
	private String problem = "Crosscuting Concern";
	private String refactoringName = "Extract To Aspect";
	private List<String> artifacts; 
	private Long ID;
	
	Map<String, List<Integer> > eventMap;

	
	public ExtractAspectRefactoring(String ccName){
		this.score = null;
		this.name = null;
		this.ccName = ccName;
		this.artifacts = new ArrayList<String>();
		this.metrics = new HashMap<String, Metric>();
		eventMap = new HashMap<String, List<Integer>>();
		this.setAffectedEvents();
	}
	
	@Override
	public boolean canApply() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean applyRefactoring() {
		int cancel = UCRUseCasesView.extractAspectDialog();
		if (cancel == 0){
			// ASPECTO ELEGIDO
			String aspectName = UCRUseCasesView.aspectDialog.getAspectName();
			Aspect existent = null;
			for(Aspect a : UCRUseCasesView.ucref.getUseCaseModel().getAspects()){
				if(a.getName().equals(aspectName)){
					existent = a;
					break;
				}
			}
			if (existent == null){
				existent = UCRefactoringFactory.eINSTANCE.createAspect();
				existent.setName(aspectName);
				existent.setDescription(UCRUseCasesView.aspectDialog.getAspectDescription());
				UCRUseCasesView.ucref.getUseCaseModel().getAspects().add(existent);
			}
			// ARMO LOS JOIN POINTS
			NonModularNFRMetric metric =(NonModularNFRMetric)this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			CrosscuttingConcern cc = metric.concerns.get(ccName);
			if(!existent.getCcNames().contains(cc.getName())){
				existent.getCcNames().add(cc.getName());
			}
			for(Impact i: cc.getImpacts()){
				UseCase uc = metric.useCases.get(i.getDocument().getName());
				Integer evId = UCRUseCasesView.ucref.getReaImpactedEvents().get(i.getID());
				System.out.println(uc.getName());
				System.out.println(evId);
				JointPoint jp = UCRefactoringFactory.eINSTANCE.createJointPoint();
				jp.setImpactAspect(existent);
				setImpactedUseCases(existent, uc);
				this.addJoinPoint(uc, i.getSection().getName(), jp, evId);
			}
		}
		return true;
	}
	
	private void setAffectedEvents(){
		NonModularNFRMetric metric =(NonModularNFRMetric)this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
		CrosscuttingConcern cc = metric.concerns.get(ccName);
		
		for(Impact i: cc.getImpacts()){
			UseCase uc = metric.useCases.get(i.getDocument().getName());
			Integer evId = UCRUseCasesView.ucref.getReaImpactedEvents().get(i.getID());
			String section = i.getSection().getName();
			Integer fullId = 0;
			for(int j = 0; j < uc.getFlows().size(); j++){
				Flow f = uc.getFlows().get(j);
				if (f.getName().equalsIgnoreCase(section)){
					for (int k=0; k<j; k++){
						fullId += uc.getFlows().get(k).getEvents().size(); 
					}
					fullId += evId;
					break;
				}
			}
			if (this.eventMap.get(uc.getName()) == null){
				List<Integer> l = new ArrayList<Integer>();
				l.add(fullId);
				this.eventMap.put(uc.getName(), l);
			} 
			else{
				this.eventMap.get(uc.getName()).add(fullId);
			}
		}
	}

	private void setImpactedUseCases(Aspect existent, UseCase uc) {
		if(existent.getFlows().size() == 0){
			Flow flow = UCRefactoringFactory.eINSTANCE.createFlow();
			flow.setName("Casos de Uso Impactados: ");
			existent.getFlows().add(flow);
		}
		boolean alreadyExists = false;
		for (Event e : existent.getFlows().get(0).getEvents()){
			if (e.getDetail().equals(uc.getName())){
				alreadyExists = true;
				break;
			}
		}
		if (!alreadyExists){
			Event event = UCRefactoringFactory.eINSTANCE.createFunctionalEvent();
			event.setDetail(uc.getName());
			event.setNumber(existent.getFlows().get(0).getEvents().size() + 1);
			event.setEventId(new Integer(existent.getFlows().get(0).getEvents().size() + 1).toString());
			existent.getFlows().get(0).getEvents().add(event);
		}
	}

	private void addJoinPoint(UseCase uc, String sectionName, JointPoint jp, Integer evId) {
		for(Flow flow : uc.getFlows()){
			if(flow.getName().equalsIgnoreCase(sectionName)){
				for(Event e : flow.getEvents()){
					if (e.getNumber().equals(evId)){
						e.getAffectedByJoinPoint().add(jp);
						e.setDetail(e.getDetail() + "[Join Point:" + jp.getImpactAspect().getName() + "]");
					}
				}
			}
		}
	}

	@Override
	public Float getScore() {
		if (this.score == null){
			NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			
			Float affectedUcs = new Float(met.ccUseCases.get(ccName).size())  / new Float(UCRUseCasesView.ucref.getUseCaseModel().getUseCases().size());  
			this.score = (float)((affectedUcs*(0.4) + this.getPriority()*(0.6)))*100;
		}
		return this.score;
	}

	public String getName() {
		if(name == null || name == ""){
			NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			for (String s : met.concerns.keySet()){
				this.name = name + s + " | ";
			}
		}
		return name;
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

	@Override
	public String getProblem() {
		return this.problem + ": " + this.ccName;
	}

	@Override
	public List<String> getArtifacts() {
		if(artifacts == null || artifacts.size() == 0){
			NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
			for (UseCase uc : met.ccUseCases.get(ccName)){
				this.artifacts.add(uc.getName());
			}
		}
		return this.artifacts;
	}

	@Override
	public String getRefactoringName() {
		return this.refactoringName;
	}
	
	@Override
	public String getPriorityText() {
		return MEDIUM_PRIORITY_TEXT;
	}
	
	@Override
	public Long getID() {
		return this.ID;
	}
	
	public void setID(Long ID){
		this.ID = ID;
	}
	
	@Override
	public boolean affectsUseCase(UseCase useCase) {
		NonModularNFRMetric met = (NonModularNFRMetric) this.metrics.get(Metric.ENCAPSULATED_NON_FUNCTIONAL);
		for (UseCase u : met.ccUseCases.get(ccName)){
			if(u.getName().equals(useCase)){
				return true;
			}
		}
		return false;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDetail() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}
	
	public Metric getMetric(String metricName){
		return this.metrics.get(metricName);
	}
	
}
