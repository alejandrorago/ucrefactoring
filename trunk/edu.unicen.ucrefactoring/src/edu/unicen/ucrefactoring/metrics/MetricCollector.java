package edu.unicen.ucrefactoring.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.isistan.reassistant.model.CrosscuttingConcern;
import edu.isistan.reassistant.model.Impact;
import edu.isistan.reassistant.model.REAssistantProject;
import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Aspect;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;

/**
 * Collects metrics from the use cases of the model
 * @author migue
 *
 */
public class MetricCollector {

	
		private HashMap<String,Metric> metrics;
		private UseCaseModel useCaseModel;
		private REAssistantProject reaProject;
		private HashMap<String,AlignmentX2Result> alignments;
		
		private boolean hasCollected;
		
		public Metric getMetricByType(String type){
			return this.metrics.get(type);
		}
		
		public MetricCollector(UseCaseModel useCaseModel, REAssistantProject reaProject, HashMap<String,AlignmentX2Result> alignments){
			this.metrics = new HashMap<String,Metric>();
			this.useCaseModel = useCaseModel;
			this.reaProject = reaProject;
			this.alignments = alignments;
			this.hasCollected = false;
		}
		
		public void collectMetrics(){
			collectDuplicateMetrics();
			collectLargeMetrics();
			collectShortMetrics();
			collectHappyMetrics();
			collectNonModularFRMetrics();
			collectNonModularNFRMetrics();
			collectNonSenseUseCaseMetrics();
			collectNonSenseActorMetrics();
			collectWrongUseCaseRelationshipMetrics();
			this.hasCollected = true;	
		}
		
		public void collectDuplicateMetrics(){
			DuplicateMetric duplicateMetric = new DuplicateMetric(alignments);
			this.metrics.put(Metric.DUPLICATE, duplicateMetric);
		}
		
		public void collectLargeMetrics(){
			LargeUseCaseMetric largeMetric = new LargeUseCaseMetric();
			for (UseCase uc : useCaseModel.getUseCases()){
				int totalCantEvents = 0;
				int basicFlowCantEvents = 0;
				for (Flow f : uc.getFlows()){
					if (f.getName().equals("Basic Flow")){
						basicFlowCantEvents+=f.getEvents().size();
					}
					totalCantEvents+= f.getEvents().size();
				}
				if (totalCantEvents > LargeUseCaseMetric.TOTAL_LIMIT || basicFlowCantEvents > LargeUseCaseMetric.BASIC_FLOW_LIMIT ){
					largeMetric.addLargeUseCase(uc,totalCantEvents, basicFlowCantEvents );
				}
			}
			this.metrics.put(Metric.LARGE_USECASE, largeMetric);
		}
		
		public void collectShortMetrics(){
			ShortUseCaseMetric shortMetric = new ShortUseCaseMetric();
			for (UseCase uc : useCaseModel.getUseCases()){
				//int totalCantEvents = 0;
				int basicFlowCantEvents = 0;
				for (Flow f : uc.getFlows()){
					if (f.getName().equals("Basic Flow")){
						basicFlowCantEvents+=f.getEvents().size();
					}
					//totalCantEvents+= f.getEvents().size();
				}
				if ( basicFlowCantEvents < ShortUseCaseMetric.BASIC_FLOW_LIMIT ){
					shortMetric.addShortUseCase(uc, basicFlowCantEvents);//, totalCantEvents);
				}
			}
			this.metrics.put(Metric.SHORT_USECASE, shortMetric);
		}
		
		public void collectHappyMetrics(){
			HappyUseCaseMetric happyMetric = new HappyUseCaseMetric();
			for (UseCase uc : useCaseModel.getUseCases()){
				if ( uc.getFlows().size() > HappyUseCaseMetric.FLOW_LIMIT ){
					happyMetric.addHappyUseCase(uc);
				}
			}
			this.metrics.put(Metric.HAPPY_USECASE, happyMetric);
		}
		
		public void collectWrongUseCaseRelationshipMetrics(){
			WrongUseCaseRelationshipMetric wMetric = new WrongUseCaseRelationshipMetric();
			for (UseCase uc : useCaseModel.getUseCases()){
				// INCLUSION
				if (uc.getIncludedUseCases().size() > 0){
					// CHEQUEAR Q CADA INCLUIDO ESTE INCLUIDO POR OTRO (O Q SE ACTIVE DESDE AFUERA)
					for(UseCase inc : uc.getIncludedUseCases()){
						boolean another = false;
						if(inc.getPrimaryActor() != null){
							another =true;
						}
						else{
							for (UseCase aux: useCaseModel.getUseCases()){
								if(!aux.getName().equalsIgnoreCase(uc.getName())){
									for(UseCase incAux : aux.getIncludedUseCases()){
										if (incAux.getName().equalsIgnoreCase(inc.getName())){
											another = true;
											break;
										}
									}
								}
							}
						}
						if (!another){
							wMetric.addWrongUseCaseRelationship(uc, inc, WrongUseCaseRelationshipMetric.INCLUSION_RELATIONSHIP);
						}
					}
				}
				
				// EXTENSION
				if(uc.getExtendedUseCases().size() == 1){
					// SI EXTIENDE SOLO A UNO Y NO SE ACTIVA DESDE EL EXTERIOR -> ERROR
					if(uc.getPrimaryActor() == null){
						wMetric.addWrongUseCaseRelationship(uc, uc.getExtendedUseCases().get(0), WrongUseCaseRelationshipMetric.EXTENSION_RELATIONSHIP);
					}
				}
				
				// GENERALIZACION
				if(uc.getParent() != null){
					// SI ES UNICO HIJO DEL PADRE -> AGREGAR
					boolean another = false;
					for (UseCase aux: useCaseModel.getUseCases()){
						if ((!aux.getName().equalsIgnoreCase(uc.getName())) 
								&& (aux.getParent().getName().equalsIgnoreCase(uc.getParent().getName()))){
							another = true;
							break;
						}
					}
					if (!another){
						wMetric.addWrongUseCaseRelationship(uc, uc.getParent(), WrongUseCaseRelationshipMetric.GENERALIZATION_RELATIONSHIP);
					}
				}
			}
			this.metrics.put(Metric.HAPPY_USECASE, wMetric);
		}
		
		public void collectNonModularFRMetrics(){
			NonModularFRMetric nonModularMetric = new NonModularFRMetric();
			Float textSimilarity = 0f;
			Float actorSimilarity = 0f;
			for (UseCase useCase1 : useCaseModel.getUseCases()){
				for (UseCase useCase2 : useCaseModel.getUseCases()){
					if (!useCase1.getName().equals(useCase2.getName())){
						textSimilarity = SimilarityAnalyzer.getTextSimilarity(useCase1, useCase2);
						actorSimilarity = SimilarityAnalyzer.getActorSimilarity(useCase1, useCase2);
					}
				
					//compare & add
					if ( textSimilarity > NonModularFRMetric.TEXT_SIMILARITY_THRESHOLD && actorSimilarity > NonModularFRMetric.ACTOR_SIMILARITY_THRESHOLD ){
						nonModularMetric.addNonModularUseCases(useCase1, useCase2, textSimilarity, actorSimilarity);
					}
				}
			}
			this.metrics.put(Metric.ENCAPSULATED_FUNCTIONAL, nonModularMetric);
		}
		
		public void collectNonModularNFRMetrics(){
			if (reaProject != null){
				NonModularNFRMetric nonModularMetric = new NonModularNFRMetric();
				List<CrosscuttingConcern> concerns = this.reaProject.getCrosscuttingConcerns();
				List<String> ccAlreadySolved = new ArrayList<String>();
				for (Aspect a : this.useCaseModel.getAspects()){
					ccAlreadySolved.addAll(a.getCcNames());
				}
				for (CrosscuttingConcern cc : concerns){
					if(!ccAlreadySolved.contains(cc.getName())){
					for (Impact impact : cc.getImpacts()){
						String ucName = impact.getDocument().getName();
						for(UseCase uc : this.useCaseModel.getUseCases()){
							if(uc.getName().equals(ucName)){
								nonModularMetric.addCrosscutingConcern(cc, uc);
							}
						}
					}
					}
				}
				this.metrics.put(Metric.ENCAPSULATED_NON_FUNCTIONAL, nonModularMetric);
			}
		}
		
		public void collectNonSenseUseCaseMetrics(){
			NonSenseUseCaseMetric nonSenseMetric = new NonSenseUseCaseMetric();
			for (UseCase useCase : useCaseModel.getUseCases()){
				if (useCase.getPrimaryActor()==null){
					// Si no tiene actor primario
					boolean isIncluded = false;
					for (UseCase uc : useCaseModel.getUseCases()){
						List<UseCase> included = uc.getIncludedUseCases();
						for(UseCase u : included){
							if (useCase.getName().equals(u.getName())){
								isIncluded = true;
								break;
							}
						}
					}
					boolean parentHasActor = false;
					if (useCase.getParent() != null && useCase.getParent().getPrimaryActor() != null){
						parentHasActor = true;
					}
					boolean isParent = false;
					for (UseCase uc : useCaseModel.getUseCases()){
						if (uc.getParent() != null && uc.getParent().getName().equals(useCase.getName())){
							isParent = true;
						}
					}
					boolean extendsAnother = false;
					List<UseCase> extended = useCase.getExtendedUseCases();
					if (extended.size() > 0){
						extendsAnother = true;
					}
					if (!isIncluded && !parentHasActor && !isParent && !extendsAnother){
						nonSenseMetric.addNonSenseUseCase(useCase);
					}
				}
			}
			this.metrics.put(Metric.NON_SENSE_USECASE, nonSenseMetric);
		}
		
		public void collectNonSenseActorMetrics(){
			NonSenseActorMetric nonSenseActorMetric = new NonSenseActorMetric();
			for (Actor actor : useCaseModel.getActors()){
				boolean used = false;
				for (UseCase useCase : useCaseModel.getUseCases()){
					if ((useCase.getSecondaryActors()!=null && useCase.getSecondaryActors().contains(actor)) 
							|| (useCase.getPrimaryActor()!=null && useCase.getPrimaryActor().equals(actor))){
						used = true;
						break;
					}
				}
				if (!used && !actor.getName().equalsIgnoreCase("system")){
					nonSenseActorMetric.addNonSenseActor(actor);
				}
			}
			this.metrics.put(Metric.NON_SENSE_ACTOR, nonSenseActorMetric);
		}
		
		public HashMap<String,Metric> getCollectedMetrics(){
			if (!hasCollected)
				this.collectMetrics();
			return this.metrics;
		}
		
		

		
}
