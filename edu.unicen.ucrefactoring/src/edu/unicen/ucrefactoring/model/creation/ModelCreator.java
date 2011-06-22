package edu.unicen.ucrefactoring.model.creation;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import uima.cas.CasPackage;
import edu.isistan.dal.ucs.model.UCSModelPackage;
import edu.isistan.dal.ucs.model.UCSProject;
import edu.isistan.dal.ucs.model.UseCaseSpecification;
import edu.isistan.uima.unified.typesystems.domain.DomainNumber;
import edu.isistan.uima.unified.typesystems.nlp.Sentence;
import edu.isistan.uima.unified.typesystems.srs.Document;
import edu.isistan.uima.unified.typesystems.srs.Project;
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

public class ModelCreator {
	
	public static UIMAQuery uimaRoot;
	
	public ModelCreator(EList<EObject> contenidos){
		uimaRoot = new UIMAQuery(contenidos);
	}
	public ModelCreator(){
	}
	
	public void load(File file) throws IOException {
		
		//Carga el archivo ucs del File pasado como parámetro=======
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(UCSModelPackage.eNS_URI, UCSModelPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		UCSProject project = (UCSProject) resource.getContents().get(0);
		
		for(UseCaseSpecification useCaseSpecification : project.getUseCaseSpecifications()) {
			
			System.out.println(useCaseSpecification.getName());
			
		}
		
		// Initialize the model===================================
		UCRefactoringPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		UCRefactoringFactory factory = UCRefactoringFactory.eINSTANCE;
		//========================================================
			
		//Set the content of the model		
		UseCaseModel useCaseModel = factory.createUseCaseModel();
		
		//Nombre y descripcion
		useCaseModel.setName(project.getName());
		useCaseModel.setDescription(project.getContent());
		
		//Actores
		 for (edu.isistan.dal.ucs.model.Actor a : project.getActors()){
			 Actor actor = factory.createActor();
			 actor.setName(a.getName());
			 actor.setDescription(a.getContent());
			 actor.setType((ActorTypeEnum.valueOf(a.getStereotype().toUpperCase())));
			 useCaseModel.getActors().add(actor);			 
		 }
		 
		 
		 // Extraigo todos los documentos del proyecto uima
		 Project uimaProject = ModelCreator.uimaRoot.getProject();
		 EList<Document> allDocs = ModelCreator.uimaRoot.getDocuments(uimaProject);
		 
		 //Especificaciones de casos de uso
		 for (UseCaseSpecification ucs : project.getUseCaseSpecifications()){
			 UseCase useCase = factory.createUseCase();
			 useCase.setName(ucs.getName());
			 useCase.setDescription(ucs.getContent());			 
			
			 // Busco el Caso de Uso correspondiente
			 Document actualDoc = null;
			 for(Document d : allDocs){
				 if (d.getId().equals(ucs.getID())){
					 actualDoc = d;
				 }
			 }
			 // Obtengo las secciones del Caso de Uso
			 EList<edu.isistan.uima.unified.typesystems.srs.Section> secs = ModelCreator.uimaRoot.getSections(actualDoc);	 
			 
			 // Creo el contexto del Caso de Uso 
			 Context context = factory.createContext();
			 // Busco: F Basico, F Alternativos, Precondiciones y Postcondiciones
			 for(edu.isistan.uima.unified.typesystems.srs.Section uimaSection : secs){
				 // TODO Modificar Modelo 
				 // Context tiene una lista de Precondition y postcondition
				 // Crear clase Preconditon y Postcondition: agregar nombre y contenido como atributo - strings 
				 if (uimaSection.getKind().equals("Precondition")){
					 //System.out.println(uimaSection.getName());
					 // new Precondition()...
					 // context.setPrecondition()...
					 Condition precondition = factory.createCondition();
					 precondition.setName(uimaSection.getName());
					 precondition.setDescription(uimaRoot.getCoveredText(uimaSection));
					 context.getPreconditions().add(precondition);
				 }
				 else if (uimaSection.getKind().equals("Postcondition")){
					 //System.out.println(uimaSection.getName());
					 Condition postcondition = factory.createCondition();
					 postcondition.setName(uimaSection.getName());
					 postcondition.setDescription(uimaRoot.getCoveredText(uimaSection));
					 context.getPostconditions().add(postcondition);
				 }
				 else if(uimaSection.getKind().equals("BasicFlow")){
					 Flow basicFlow = factory.createFlow();
					 basicFlow.setName(uimaSection.getName());				 
					 // Recupero los numeros de la sección (Flujo Básico)
					 EList<DomainNumber> numbers = ModelCreator.uimaRoot.getDomainNumbers(uimaSection);
					 Integer numberIndex = 0;
					 Integer order = 1;
					 // Recupero las sentencias del Flujo Básico
					 EList<Sentence> sentences = ModelCreator.uimaRoot.getSentences(uimaSection);
					 for(Sentence sentence : sentences){
						 FunctionalEvent event = factory.createFunctionalEvent();
						 event.setDetail(ModelCreator.uimaRoot.getCoveredText(sentence));
						 // TODO agregar para guardar los nros originales del evento tb
						 //ModelCreator.uimaRoot.getCoveredText(numbers.get(numberIndex));
						 event.setNumber(order);
						 basicFlow.getEvents().add(event);
						 numberIndex++;
						 order++;
						 
						 //ModelCreator.uimaRoot.getDomainActions(predicate)
					 }	
					 // Agrego el Flujo Basico al Caso de Uso
					 useCase.getFlows().add(basicFlow);
				 }
				 else if(uimaSection.getKind().equals("AlternativeFlow")){
					 // Ver bien como sacar los números
				 }
			 }
			 // Seteo el contexto al Caso de Uso
			 useCase.setContext(context);
			 // Seteo el Caso de Uso al Modelo
			 useCaseModel.getUseCases().add(useCase);
		 }
		
		 //System.out.println(uimaRoot.getProject().getName() + " -SOFA: " + uimaRoot.getProject().getSofa().getSofaString()); 
		
		//Guardamos el modelo
		// Register the XMI resource factory for the .website extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("UseCaseModel", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resourceSalida = resSet.createResource(URI
				.createURI("/home/migue/Escritorio/test.ucrefactoring"));
		resourceSalida.getContents().add(useCaseModel);

		// Now save the content.
		try {
			resourceSalida.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showUCRefactoring(File file) throws IOException {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		
		EPackage.Registry.INSTANCE.put(UCRefactoringPackage.eNS_URI, UCRefactoringPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		UseCaseModel ucModel = (UseCaseModel) resource.getContents().get(0);
		for (UseCase uc : ucModel.getUseCases()){
			EList<Flow> fs = uc.getFlows();
			for (Condition c : uc.getContext().getPreconditions()){
				System.out.println("Preconditions: ");
				System.out.println(c.getName() +" - "+c.getDescription());
			}
			for (Condition c : uc.getContext().getPostconditions()){
				System.out.println("Postconditions: ");
				System.out.println(c.getName() +" - "+c.getDescription());
			}
			for(Flow f : fs){
				System.out.print(f.getName() + "\n");
				for(Event e : f.getEvents()){
					System.out.print(e.getNumber() + "$" + e.getDetail() + "\n");
				}
			}
		}
				
	}

	public static void main(String[] args) throws IOException {
		
		//Registramos los archivos necesarios para el UIMA
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(CasPackage.eNS_URI, CasPackage.eINSTANCE);

		// Levantamos el Resource *.uima
		ResourceSet resourceSet = new ResourceSetImpl();
		// TODO ver ingreso de ruta
		URI fileURI = URI.createFileURI("/home/migue/workspace/prueba/runtime-EclipseApplication/Test/src/HWS-short.uima");
		Resource resource = null;
		try {
			resource = resourceSet.getResource(fileURI, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelCreator l = new ModelCreator(resource.getContents());
		l.load(new File("/home/migue/workspace/prueba/runtime-EclipseApplication/Test/src/HWS-short.ucs"));
		l.showUCRefactoring(new File("/home/migue/Escritorio/test.ucrefactoring"));
	}
}
