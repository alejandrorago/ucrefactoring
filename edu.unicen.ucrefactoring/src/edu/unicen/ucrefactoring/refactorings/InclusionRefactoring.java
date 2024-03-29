package edu.unicen.ucrefactoring.refactorings; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SequenceAligner;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRUseCasesView;
import edu.unicen.ucrefactoring.metrics.Metric;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.InclusionCall;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;

public class InclusionRefactoring implements Refactoring{

	private String type = Refactoring.REF_INCLUSION;

	private Float score;
	private HashMap<String,Metric> metrics;
	private AlignmentX2Result alignment;
	private String name; 
	private Float priority = Refactoring.HIGH_PRIORITY;
	private String problem = "Duplicated Functionality";
	private String refactoringName = "Generate Inclusion Relationship";
	private List<String> artifacts; 
	private Long ID;

	
	public InclusionRefactoring(AlignmentX2Result alignment){
		this.score = null;
		this.name = null;
		this.alignment = alignment;
		this.artifacts = new ArrayList<String>();
		this.artifacts.add(this.alignment.getUseCaseA().getName());
		this.artifacts.add(this.alignment.getUseCaseB().getName());
	}
	
	@Override
	public boolean canApply() {
		// TODO INCORPORAR CHEQUEO DE IGUALES FLUJOS ALTERNATIVOS / EXT 
		if ((this.alignment.getFlowA().getName().toLowerCase().contains("basic flow") 
			&& this.alignment.getFlowB().getName().toLowerCase().contains("basic flow"))
			&& (this.alignment.getSimilarBlocksFromA().size()==1
			&& this.alignment.getSimilarBlocksFromB().size()==1)){
			
			// CHEQUEO IGUAL CANTIDAD DE FB EN LA DUPLICACION
			List<Flow> altA = new ArrayList<Flow>();
			List<Flow> altB = new ArrayList<Flow>();
			
			for(Flow f : this.alignment.getUseCaseA().getFlows()){
				if (!f.getName().equals(this.alignment.getFlowA().getName())){
					Integer index = f.getName().indexOf('.');
					Integer eA = -1; // EVENTO FB
					if(index >= 1){
						String evA = f.getName().substring(0, index);
						eA = new Integer(evA)-1;
					} else{
						String number = "";
						for(int i =0; i<f.getName().length(); i++){
							if("1234567890".contains(String.valueOf(f.getName().charAt(i)))){
								number = number + f.getName().charAt(i);
							} else{
								if (number.length() > 0){
									break;
								}
							}
						}
						if (number.length() > 0){
							eA = new Integer(number) -1;
						}
					}
					
					Boolean pertenece = false;
					for(SimilarBlock sb : alignment.getSimilarBlocksFromA()){
						if(sb.getBeginIndex() <= eA && sb.getEndIndex() >= eA){
							pertenece = true;
							break;
						}
					}
					if(pertenece){
						altA.add(f);
					}
				}
			}
			for(Flow f : this.alignment.getUseCaseB().getFlows()){
				if (!f.getName().equals(this.alignment.getFlowB().getName())){
					Integer index = f.getName().indexOf('.');
					Integer eB = -1; // EVENTO FB
					if(index >= 1){
						String evB = f.getName().substring(0, index);
						eB = new Integer(evB)-1;
					} else{
						String number = "";
						for(int i =0; i<f.getName().length(); i++){
							if("1234567890".contains(String.valueOf(f.getName().charAt(i)))){
								number = number + f.getName().charAt(i);
							} else{
								if (number.length() > 0){
									break;
								}
							}
						}
						if (number.length() > 0){
							eB = new Integer(number) -1;
						}
					}
					Boolean pertenece = false;
					for(SimilarBlock sb : alignment.getSimilarBlocksFromB()){
						if(sb.getBeginIndex() <= eB && sb.getEndIndex() >= eB){
							pertenece = true;
							break;
						}
					}
					if(pertenece){
						altB.add(f);
					}
				}
			}
			if(altA.size() == altB.size()){
				if(altA.size() == 0){
					return true;
				}
				// CHEQUEO CONTENIDO DE LOS FLUJOS
				for(int i = 0; i < altA.size();i++){
					Flow fA = altA.get(i);
					Flow fB = altB.get(i);
					AlignmentX2Result result = UCRUseCasesView.ucref.getSimilarityAnalizer().compareThisSequences(fA, fB,SequenceAligner.JALIGNER_SW_SA,SequenceAligner.UCMATRIX2);
					if((result.getSimilarBlocksFromA() != null && result.getSimilarBlocksFromB() != null)&&
							(result.getSimilarBlocksFromA().size() > 0 && result.getSimilarBlocksFromB().size()>0)){
						if(result.getSimilarBlocksFromA().size() == 1 && result.getSimilarBlocksFromB().size() == 1){
							if (UCRUseCasesView.ucref.getSimilarityAnalizer().areSimilar(result)){
								return true;
							}
						}
					} else{
						if(fA.getEvents().size() < 3 || fB.getEvents().size() < 3){
							return UCRUseCasesView.ucref.getSimilarityAnalizer().areSimilar(fA, fB);
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean applyRefactoring() {
		/**
		 * 1-
		 * 2-
		 * 3-
		 * 4-
		 * 
		 */
		UseCase baseUseCaseA = null;
		UseCase baseUseCaseB = null;
		UseCase includedUC = null;
		if ((this.alignment.getFlowA().getEvents().size() != this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents().size())&& 
		    (this.alignment.getFlowB().getEvents().size() != this.alignment.getSimilarBlocksFromB().get(0).getSimilarEvents().size())){
			// if no similar block is a defined uc, create one for the duplicated functionality
			baseUseCaseA = this.alignment.getUseCaseA();
			baseUseCaseB = this.alignment.getUseCaseB();
			// Create Use Case
			includedUC = UCRefactoringFactory.eINSTANCE.createUseCase();
			
			int cancel = UCRUseCasesView.newUseCaseDialog();
			
			if (cancel == 0){
				
				includedUC.setName(UCRUseCasesView.UCRDialog.getUseCaseName());
				includedUC.setDescription(UCRUseCasesView.UCRDialog.getUseCaseDescription());
				includedUC.setPrimaryActor(null); // No actor in this new use case
				Flow basicFlow = UCRefactoringFactory.eINSTANCE.createFlow();
				basicFlow.setName("Basic Flow");
				Integer order = 1;
				for(Event e : this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents()){
					Event newE = e.cloneEvent();
					newE.setEventId(e.getEventId().replaceFirst(e.getNumber().toString(), order.toString()));
					newE.setNumber(order);
					basicFlow.getEvents().add(newE);
					order++;
				}
				basicFlow.setUseCase(includedUC);
				includedUC.getFlows().add(basicFlow);
				// Add new uc to the model
				UCRUseCasesView.ucref.getUseCaseModel().getUseCases().add(includedUC);
			}
			else{
				return false;
			}
		}
		else{
			if (this.alignment.getFlowA().getEvents().size() == this.alignment.getSimilarBlocksFromA().get(0).getSimilarEvents().size()){
				baseUseCaseB = this.alignment.getUseCaseB();
				includedUC = this.alignment.getUseCaseA();
			}
			else{
				baseUseCaseA =this.alignment.getUseCaseA();
				includedUC = this.alignment.getUseCaseB();
			}
		}
		if(baseUseCaseA != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromA().get(0), includedUC);
		}
		if(baseUseCaseB != null){
			this.editBaseFlow(this.alignment.getSimilarBlocksFromB().get(0), includedUC);
		}
		return true;
	}
	
	private void editBaseFlow(SimilarBlock simBlockToRemove, UseCase includedUC){
		// Edit the Basic Flow
		UseCase baseUC = simBlockToRemove.getUseCase();
		Flow basicFlow = baseUC.getBasicFlow();
		int beginIndex = simBlockToRemove.getSimilarEvents().get(0).getNumber()-1;
		int endIndex = simBlockToRemove.getSimilarEvents().get(simBlockToRemove.getSimilarEvents().size()-1).getNumber()-1;
		for (int i = endIndex; i > beginIndex; i--){
			basicFlow.getEvents().remove(i);
			for (int j = i; j< basicFlow.getEvents().size(); j++){
				Event e = basicFlow.getEvents().get(j);
				Integer newNumber = e.getNumber()-1;
				e.setEventId(e.getEventId().replaceFirst(e.getNumber().toString(), newNumber.toString()));
				e.setNumber(newNumber);
			}
		}
		Event finalRemoved = basicFlow.getEvents().get(beginIndex);
		// Replace in initial pos the new event with the inclusion event
		InclusionCall ic = UCRefactoringFactory.eINSTANCE.createInclusionCall();
		ic.setEventId(finalRemoved.getEventId());
		ic.setNumber(finalRemoved.getNumber());
		ic.setDetail("<<include>> " + "'"+ includedUC.getName() +"'");
		ic.getIncludedUseCases().add(includedUC);
		basicFlow.getEvents().add(beginIndex, ic);
		basicFlow.getEvents().remove(finalRemoved);
	}

	@Override
	public Float getScore() {
		if (this.score == null){
			Float sizeCoef = 0f;
			Float totalSize = 0f;
			Float blocksSize = 0f;
			Float confiability = 0f;
			for (Flow f1 : this.alignment.getUseCaseA().getFlows()){
				totalSize += f1.getEvents().size();
			}
			for (Flow f2 : this.alignment.getUseCaseB().getFlows()){
				totalSize += f2.getEvents().size();
			}
			for (SimilarBlock sb1 : this.alignment.getSimilarBlocksFromA()){
				blocksSize += sb1.getSimilarEvents().size();
			}
			for (SimilarBlock sb2 : this.alignment.getSimilarBlocksFromB()){
				blocksSize += sb2.getSimilarEvents().size();
			}

			//get size coef
			sizeCoef = ((blocksSize) / (totalSize));
			//get confiability
			confiability = (float) (sizeCoef * alignment.getTextSimilarityScore());
			
			//getScore
			this.score = (float)((confiability*(0.4) + this.getPriority()*(0.6)))*100;
		}
		return this.score;
	}

	@Override
	public String getName() {
		if (name == null){
			this.name = alignment.getUseCaseA().getName()+" - "
			+alignment.getFlowA().getName()+" | "
			+alignment.getUseCaseB().getName()+" - "
			+alignment.getFlowB().getName();
		}
		return name;
	}

	@Override
	public AlignmentX2Result getAlignment() {
		return this.alignment;
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
		return this.problem;
	}

	@Override
	public List<String> getArtifacts() {
		return this.artifacts;
	}

	@Override
	public String getRefactoringName() {
		return this.refactoringName;
	}
	
	@Override
	public String getPriorityText() {
		return HIGH_PRIORITY_TEXT;
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
		return alignment.getUseCaseA().getName().equals(useCase.getName()) 
				|| alignment.getUseCaseB().getName().equals(useCase.getName());
	}
	
	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDetail() {
		return "Use Cases: ["+this.getAlignment().getUseCaseA().getName()+"]["+this.getAlignment().getUseCaseB().getName() +"]\n"
			   +"Flows: [" + this.getAlignment().getFlowA().getName()+"]["+this.getAlignment().getFlowB().getName()+"]\n";
	}
	
}
