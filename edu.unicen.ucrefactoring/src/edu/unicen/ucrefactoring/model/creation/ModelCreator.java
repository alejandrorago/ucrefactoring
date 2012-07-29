package edu.unicen.ucrefactoring.model.creation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import edu.isistan.dal.ucs.model.UCSModelPackage;
import edu.isistan.dal.ucs.model.UCSProject;
import edu.isistan.dal.ucs.model.UseCaseSpecification;
import edu.isistan.reassistant.model.CrosscuttingConcern;
import edu.isistan.reassistant.model.Impact;
import edu.isistan.reassistant.model.REAssistantModelPackage;
import edu.isistan.reassistant.model.REAssistantProject;
import edu.isistan.uima.unified.typesystems.domain.DomainAction;
import edu.isistan.uima.unified.typesystems.domain.DomainNumber;
import edu.isistan.uima.unified.typesystems.nlp.Sentence;
import edu.isistan.uima.unified.typesystems.srl.Predicate;
import edu.isistan.uima.unified.typesystems.srl.Structure;
import edu.isistan.uima.unified.typesystems.srs.Document;
import edu.isistan.uima.unified.typesystems.srs.Project;
import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.ActionCodeEnum;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.ActorTypeEnum;
import edu.unicen.ucrefactoring.model.Condition;
import edu.unicen.ucrefactoring.model.Context;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.uima.UIMAQuery;
import edu.unicen.ucrefactoring.util.Constants;

public class ModelCreator {
	

	public static UIMAQuery uimaRoot;
	private UCRefactoringFactory factory;
	public UseCaseModel parsedUseCaseModel;
	public REAssistantProject reaProject; 
	public Map<String, Integer> reaImpactedEvents;
	private HashMap<String,String> sequencias = new HashMap<String,String>();
	
	//=======Getters and Setters========================
	public UseCaseModel getParsedUseCaseModel() {
		return parsedUseCaseModel;
	}
	public REAssistantProject getReaProject() {
		return reaProject;
	}
	public void setParsedUseCaseModel(UseCaseModel parsedUseCaseModel) {
		this.parsedUseCaseModel = parsedUseCaseModel;
	}
	public HashMap<String, String> getSequencias() {
		return sequencias;
	}
	public void setSequencias(HashMap<String, String> sequencias) {
		this.sequencias = sequencias;
	}
	public Map<String, Integer> getReaImpactedEvents() {
		return reaImpactedEvents;
	}
	
	//========Constructores==================================

	public ModelCreator(EList<EObject> contenidos){
		uimaRoot = new UIMAQuery(contenidos);
	}
	public ModelCreator(){
	}
	
	//========Servicios====================================
	
	public void loadFromModel(UseCaseModel ucModel) {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());	
		EPackage.Registry.INSTANCE.put(UCRefactoringPackage.eNS_URI, UCRefactoringPackage.eINSTANCE);
		setParsedUseCaseModel(ucModel);
	}
	
	public void loadExistingFile(File file) throws IOException {
		//Carga el archivo ucs del File pasado como parámetro
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());	
		EPackage.Registry.INSTANCE.put(UCRefactoringPackage.eNS_URI, UCRefactoringPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		setParsedUseCaseModel((UseCaseModel) resource.getContents().get(0));
	}
	
	public void loadREA(File file) throws IOException {
		//Carga el archivo ucs del File pasado como parámetro
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());	
		EPackage.Registry.INSTANCE.put(REAssistantModelPackage.eNS_URI, REAssistantModelPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		this.reaProject = (REAssistantProject) resource.getContents().get(0);
		this.reaImpactedEvents = new HashMap<String, Integer>(); 
		List<CrosscuttingConcern> ccs = this.reaProject.getCrosscuttingConcerns();
		for(CrosscuttingConcern cc: ccs){
			for(Impact i: cc.getImpacts()){
				Sentence sentence = i.getSentence();
				Document document = i.getDocument();
				int offset = uimaRoot.indexOf(sentence, uimaRoot.getSentences(document));
				this.reaImpactedEvents.put(i.getID(), offset+1);
			}
		}
	}
	
	public void load(File file) throws IOException {
		
		//Carga el archivo ucs del File pasado como parámetro
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(UCSModelPackage.eNS_URI, UCSModelPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		UCSProject project = (UCSProject) resource.getContents().get(0);
		
//		for(UseCaseSpecification useCaseSpecification : project.getUseCaseSpecifications()) {
//			
//			System.out.println(useCaseSpecification.getName());
//			
//		}
		
		// Initialize the model===================================
		UCRefactoringPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		this.factory = UCRefactoringFactory.eINSTANCE;
		//========================================================
			
		//Set the content of the model		
		UseCaseModel useCaseModel = factory.createUseCaseModel();
		
		//Nombre y descripcion
		useCaseModel.setName(project.getName());
		useCaseModel.setDescription(project.getContent());
		
		EList<edu.isistan.dal.ucs.model.Actor> actors = project.getActors();
		//Actores
		 for (edu.isistan.dal.ucs.model.Actor a : actors){
			 Actor actor = factory.createActor();
			 actor.setName(a.getName());
			 actor.setDescription(a.getContent());
			 actor.setType((ActorTypeEnum.valueOf(a.getStereotype().toUpperCase())));
			 useCaseModel.getActors().add(actor);			 
		 }
		 //Añado manualmente el System
		 Actor system = factory.createActor();
		 system.setName(Constants.SYSTEM_ACTOR_NAME);
		 system.setDescription(Constants.SYSTEM_ACTOR_DESC);
		 system.setType(ActorTypeEnum.SYSTEM);
		 useCaseModel.getActors().add(system);
		 
		 
		 // Extraigo todos los documentos del proyecto uima
		 Project uimaProject = ModelCreator.uimaRoot.getProject();
		 EList<Document> allDocs = ModelCreator.uimaRoot.getDocuments(uimaProject);

		 //Especificaciones de casos de uso
		 for (UseCaseSpecification ucs : project.getUseCaseSpecifications()){
			 UseCase useCase = factory.createUseCase();
			 useCase.setName(ucs.getName());
			 useCase.setDescription(ucs.getContent());	
			 if (ucs.getMainActor() != null){
				 for (int i =0; i < useCaseModel.getActors().size(); i++){
					 if (ucs.getMainActor().getName().equalsIgnoreCase(useCaseModel.getActors().get(i).getName())){
						 useCase.setPrimaryActor(useCaseModel.getActors().get(i));
					 }
				 }
			 }
			 // TODO AGREGAR ACTORES SECUNDARIOS POR CASO DE USO
			 
			 // Busco el Caso de Uso correspondiente
			 Document actualDoc = null;
			 for(Document d : allDocs){
				 if (d.getId().equals(ucs.getID())){
					 actualDoc = d;
				 }
			 }
			 // Obtengo las secciones del Caso de Uso
			 EList<edu.isistan.uima.unified.typesystems.srs.Section> secs = ModelCreator.uimaRoot.getSections(actualDoc);	 
			 
			 /* Creo el contexto del Caso de Uso */ 
			 Context context = factory.createContext();
			 /* Busco: F Basico, F Alternativos, Precondiciones y Postcondiciones */
			 for(edu.isistan.uima.unified.typesystems.srs.Section uimaSection : secs){ 
				 /* La sección actual del Caso de Uso es una Precondición */
				 if (uimaSection.getKind().equals(Constants.PRECONDITION)){
					 Condition precondition = factory.createCondition();
					 precondition.setName(uimaSection.getName());
					 precondition.setDescription(uimaRoot.getCoveredText(uimaSection));
					 context.getPreconditions().add(precondition);
				 }
				 /* La sección actual del Caso de Uso es una Postcondición*/ 
				 else if (uimaSection.getKind().equals(Constants.POSTCONDITION)){
					 Condition postcondition = factory.createCondition();
					 postcondition.setName(uimaSection.getName());
					 postcondition.setDescription(uimaRoot.getCoveredText(uimaSection));
					 context.getPostconditions().add(postcondition);
				 }
				 /* La sección actual del Caso de Uso es el Flujo Básico */
				 else if(uimaSection.getKind().equals(Constants.BASIC_FLOW)){
					 Flow basicFlow = factory.createFlow();
					 basicFlow.setName(uimaSection.getName());
					 basicFlow.setUseCase(useCase);
					 // Recupero los numeros de la sección (Flujo Básico)
					 EList<DomainNumber> numbers = ModelCreator.uimaRoot.getDomainNumbers(uimaSection);
					 Integer order = 1;
					 // Recupero las sentencias del Flujo Básico
					 EList<Sentence> sentences = ModelCreator.uimaRoot.getSentences(uimaSection);
					 for(Sentence sentence : sentences){
						 FunctionalEvent event = factory.createFunctionalEvent();
						 event.setDetail(ModelCreator.uimaRoot.getCoveredText(sentence));
						 Actor primaryActor = getActorForSentence( sentence,  useCaseModel.getActors());
						 event.setSubject(primaryActor);
						 event.setEventId(this.getEventIdForSentence(sentence, numbers));
						 event.setNumber(order);
						 basicFlow.getEvents().add(event);
						 order++;						 
						 EList<Predicate> p = ModelCreator.uimaRoot.getPredicates(sentence);
						 
						 //Obtengo las acciones realizadas en el evento, según fueron clasificadas, para cada predicado
						 for(Predicate predicate : p){						
							 EList<DomainAction> actions = ModelCreator.uimaRoot.getDomainActions(predicate);
							 
							 for(DomainAction dAction : actions){						 
								 //convierto las domainActions to actionClass
								 if (dAction!=null && dAction.getLabel()!=null){
									 ActionClass actionClass = domainActionToActionClass(dAction);
									 event.getActionClasses().add(actionClass);
								 }
					 
								 ActionCodeEnum.getByName(dAction.getLabel());
								 
							 }

						 }
					 }	
					 // Agrego el Flujo Basico al Caso de Uso
					 useCase.getFlows().add(basicFlow);
				 }
				 /* La sección actual del Caso de Uso es un Flujo Alternativo */
				 else if(uimaSection.getKind().equals(Constants.ALTERNATIVE_FLOW)){
					 Flow alternative = factory.createFlow();
					 alternative.setName(uimaSection.getName());
					 alternative.setUseCase(useCase);
					 useCase.getFlows().add(alternative);
					 
					// Recupero los numeros de la sección
					 EList<DomainNumber> numbers = ModelCreator.uimaRoot.getDomainNumbers(uimaSection);
					 //alternative.setParentFlow(value);
					 //alternative.setReturnEvent
					 Integer numberIndex = 0;
					 Integer order = 1;
					 // Recupero las sentencias del Flujo Básico
					 EList<Sentence> sentences = ModelCreator.uimaRoot.getSentences(uimaSection);
					 for(Sentence sentence : sentences){
						 FunctionalEvent event = factory.createFunctionalEvent();
						 event.setDetail(ModelCreator.uimaRoot.getCoveredText(sentence));						 
						 event.setEventId(ModelCreator.uimaRoot.getCoveredText(numbers.get(numberIndex)));
						 event.setNumber(order);
						 alternative.getEvents().add(event);
						 numberIndex++;
						 order++;
					 	 EList<Predicate> p = ModelCreator.uimaRoot.getPredicates(sentence);
						 
						 //Obtengo las acciones realizadas en el evento, según fueron clasificadas, para cada predicado
						 for(Predicate predicate : p){						
							 EList<DomainAction> actions = ModelCreator.uimaRoot.getDomainActions(predicate);
							 
							 for(DomainAction dAction : actions){						 
								 //convierto las domainActions to actionClass
								 if (dAction!=null && dAction.getLabel()!=null){
									 ActionClass actionClass = domainActionToActionClass(dAction);
									 event.getActionClasses().add(actionClass);
								 }
					 
								 ActionCodeEnum.getByName(dAction.getLabel());
								 
							 }

						 }
					 }
				 }
			 }
			 // Seteo el contexto al Caso de Uso
			 useCase.setContext(context);
			 // Seteo el Caso de Uso al Modelo
			 useCaseModel.getUseCases().add(useCase);
		 }
		
		 //System.out.println(uimaRoot.getProject().getName() + " -SOFA: " + uimaRoot.getProject().getSofa().getSofaString()); 
		
		//Guardamos el modelo
		setParsedUseCaseModel(useCaseModel);
		
		//Exportamos el resultado a un archivo
		exportModel(file.getAbsolutePath().replace(".ucs", ".ucrefactoring"));

	}
	
	/**
	 * Método que exporta el modelo del UCS parseado
	 */
	public void exportModel(String filePath){
		// Register the XMI resource factory for the .website extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put(Constants.USE_CASE_MODEL_CLASS_NAME, new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resourceSalida = resSet.createResource(URI.createFileURI(filePath));
		resourceSalida.getContents().add(parsedUseCaseModel);

		// Now save the content.
		try {
			resourceSalida.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Convierte un DomainAction en ActionClass
	 * @param dAction
	 * @return actionClass
	 */
	private ActionClass domainActionToActionClass(DomainAction dAction){
		 ActionClass actionClass = factory.createActionClass();
		 actionClass.setName(dAction.getLabel());
		 actionClass.setConfidence(dAction.getConfidence());
		 actionClass.setRanking(dAction.getRanking());
		 actionClass.setPredicate(ModelCreator.uimaRoot.getCoveredText(dAction.getAction()));
		 if (dAction.getParent()!=null){
			 actionClass.setParent(domainActionToActionClass(dAction.getParent()));
		 }
//		 Por ahora no agregamos Childs TODO: Son necesarios los childs?
//		 if (dAction.getChilds()!=null && dAction.getChilds().size()>0){
//			 for (DomainAction da : dAction.getChilds()){
//				 actionClass.getChilds().add(domainActionToActionClass(da));
//			 }
//		 }
		 return actionClass;
	}
	
	/**
	 * Obtiene el identificador de un evento a partir de la sentence y los domain numbers
	 * pasados como parámetros
	 * @param sentence
	 * @param numbers
	 * @return
	 */
	private String getEventIdForSentence(Sentence sentence,EList<DomainNumber> numbers){
		int closestPos = 0;
		int minDif = 999;
		
		for (int i=0;(numbers!=null)&&(i<numbers.size()); i++){
			DomainNumber n = numbers.get(i);
			//si encontre el exacto, me lo quedo y termino
			if (n.getBegin()==sentence.getBegin()){
				return ModelCreator.uimaRoot.getCoveredText(numbers.get(i));
			}
			//si no, me quedo con el de menor diferencia (TODO: Es necesario?)
			else{
				//dejo la diferencia siempre positiva
				int dif = ((n.getBegin()>sentence.getBegin())?n.getBegin()-sentence.getBegin():sentence.getBegin()-n.getBegin());
				if (dif<minDif){
					minDif=dif;
					closestPos=i;
				}
			}
		}
		return ModelCreator.uimaRoot.getCoveredText(numbers.get(closestPos));	
	}
	
	/**
	 * Obtiene los actores que participan en la sentencia pasada como parámetro.
	 * Solo detecta como actores los incluidos en la Lista de actores del caso de uso
	 * TODO: Distinguir actor System
	 * @param sentence
	 * @param actors
	 * @return
	 */
	private Actor getActorForSentence(Sentence sentence, EList<Actor> actors){
		//edu.isistan.dal.ucs.model.Actor actor = uimaRoot.getStructures(sentence).get(0).;
		EList<Structure> structures = uimaRoot.getStructures(sentence);
		for (int i=0;i<structures.size();i++){
			Structure s = structures.get(i);
			if (s.getSubject()!=null){
				for (Actor actor : actors){
					if (actor.getName().toUpperCase().equals(s.getSubject().getDescriptions().get(0).toUpperCase())){
						return actor;
					}
				}
			}
		}
		return null;
		
	}
	
	
	/**
	 * Método no utilizado actualmente
	 * @param actors
	 * @param subject
	 * @return
	 */
	private Actor getContainedActor(EList<Actor> actors, String subject){
		for (Actor actor : actors){
			if (actor.getName().toUpperCase().equals(subject.toUpperCase()))
				return actor;
		}
		return null;
	}
	
	
	/**
	 * Servicio de TEST que imprime por pantalla el modelo generado
	 * @param file
	 * @throws IOException
	 */
	public void printModel(File file) throws IOException {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		
		EPackage.Registry.INSTANCE.put(UCRefactoringPackage.eNS_URI, UCRefactoringPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		UseCaseModel ucModel = (UseCaseModel) resource.getContents().get(0);
		System.out.println("\n================UCREFACTORING===================");
		
		for (UseCase uc : ucModel.getUseCases()){
			
			System.out.println("\nUC: "+uc.getName());
			EList<Flow> fs = uc.getFlows();
			System.out.println("\nPRECONDITIONS: ");
			for (Condition c : uc.getContext().getPreconditions()){
				System.out.println(" - "+c.getDescription());
			}
			System.out.println("POSTCONDITIONS: ");
			for (Condition c : uc.getContext().getPostconditions()){
				System.out.println(" - "+c.getDescription());
			}			

			for(Flow f : fs){
				System.out.println("\nFLOW: "+ f.getName());
				System.out.println("EVENTS: ");
				for(Event e : f.getEvents()){
					if (((FunctionalEvent)e).getSubject()!=null)System.out.println("ACTOR: "+((FunctionalEvent)e).getSubject().getName() );
					System.out.print("#"+e.getNumber() + " - " + "ID: "+e.getEventId() + " - "+e.getDetail() + "\n");
					
					for (ActionClass ac : ((FunctionalEvent)e).getActionClasses()){
						System.out.println("PREDICATE: "+ac.getPredicate() +" ACTION: "+ac.getRanking()+" "+ ac.getName() + " "+ac.getConfidence());

					}
				}
			}
		}
	}

	
}
