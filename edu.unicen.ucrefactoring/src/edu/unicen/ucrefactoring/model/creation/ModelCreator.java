package edu.unicen.ucrefactoring.model.creation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

import uima.cas.CasFactory;
import uima.cas.CasPackage;
import uima.tcas.TCasPackage;


import edu.isistan.dal.srs.model.SRSModelPackage;
import edu.isistan.dal.srs.model.Section;
import edu.isistan.dal.ucs.model.UCSModelFactory;
import edu.isistan.dal.ucs.model.UCSModelPackage;
import edu.isistan.dal.ucs.model.UCSProject;
import edu.isistan.dal.ucs.model.UseCaseSpecification;
import edu.isistan.reassistant.model.REAssistantModelPackage;
import edu.isistan.uima.model.edit.UIMAEditPlugin;
import edu.isistan.uima.unified.UIMAFactory;
import edu.isistan.uima.unified.typesystems.TypesystemsPackage;
import edu.isistan.uima.unified.typesystems.nlp.NLPPackage;
import edu.isistan.uima.unified.typesystems.srl.SRLPackage;
import edu.isistan.uima.unified.typesystems.wordnet.WordNetPackage;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.ActorTypeEnum;
import edu.unicen.ucrefactoring.model.Context;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.model.impl.ActorImpl;
import edu.unicen.ucrefactoring.uima.UIMAQuery;

public class ModelCreator {
	
	public static UIMAQuery uimaRoot;
	
	public ModelCreator(EList<EObject> contenidos){
		uimaRoot = new UIMAQuery(contenidos);;
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
		
		//Fin de carga del UCS=====================================
		
	
		
		// Initialize the model===================================
		UCRefactoringPackage.eINSTANCE.eClass();
		// Retrieve the default factory singleton
		UCRefactoringFactory factory = UCRefactoringFactory.eINSTANCE;
		//========================================================
		
		
		//Create the content of the model via this program=========
		
		/*MyWeb myWeb = factory.createMyWeb();
		Webpage page = factory.createWebpage();
		page.setName("index");
		page.setDescription("Main webpage");
		page.setKeywords("Eclipse, EMF");
		page.setTitle("Eclipse EMF");
		myWeb.getPages().add(page);*/
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
		 
		 //Especificaciones de casos de uso
		 for (UseCaseSpecification ucs : project.getUseCaseSpecifications()){
			 UseCase useCase = factory.createUseCase();
			 useCase.setName(ucs.getName());
			 useCase.setDescription(ucs.getContent());
			 
			 //falta marcar con anotaciones las condiciones. agregar lista de strings al modelo ucrefactoring
			 Context context = factory.createContext();
			 for (Section s : ucs.getPreconditions()){
				 context.setPrecondition(context.getPrecondition()+" - "+s.getContent());
			 }
			 for (Section s : ucs.getPostconditions()){
				 context.setPostcondition(context.getPostcondition()+" - "+s.getContent());
			 }
			 
			 
			 
			 
		 }
		
		 System.out.println(uimaRoot.getProject().getName() + " -SOFA: " + uimaRoot.getProject().getSofa().getSofaString()); 
		
		
		//=========================================================
		
		
		//Guardamos la nueva instancia del modelo==================
		// As of here we preparing to save the model content
		// Register the XMI resource factory for the .website extension
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("UseCaseModel", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Create a resource
		Resource resourceSalida = resSet.createResource(URI
				.createURI("/home/migue/Escritorio/test.ucrefactoring"));
		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resourceSalida.getContents().add(useCaseModel);

		// Now save the content.
		try {
			resourceSalida.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//==========================================================
	

	}
	
	public void load2(File file) throws IOException {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		
		EPackage.Registry.INSTANCE.put(UCRefactoringPackage.eNS_URI, UCRefactoringPackage.eINSTANCE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createFileURI(file.getAbsolutePath());
		Resource resource = resourceSet.createResource(uri);
		resource.load(Collections.EMPTY_MAP);
		UseCaseModel ucModel = (UseCaseModel) resource.getContents().get(0);
		
		//=====Creacion de resource para salida
//		URI outUri = URI.createFileURI("/home/migue/Escritorio/out2");
//		Resource outResource = resourceSet.createResource(outUri);
//		resource.load(Collections.EMPTY_MAP);
//		
		
//		for(UseCaseModel useCaseModel : ucModel.getUseCaseSpecifications()) {
			
			System.out.println(ucModel.getName());
			
//		}
				
	}

	public static void main(String[] args) throws IOException {
		
//		//Creamos el UIMAQuery==========================================
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
//		EPackage.Registry.INSTANCE.put(CasPackage.eNS_URI, CasPackage.eINSTANCE);
		
		EPackage.Registry.INSTANCE.put(CasPackage.eNS_URI, CasPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(TCasPackage.eNS_URI, TCasPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(NLPPackage.eNS_URI, NLPPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(SRLPackage.eNS_URI, SRLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(SRSModelPackage.eNS_URI, SRSModelPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(WordNetPackage.eNS_URI, WordNetPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(REAssistantModelPackage.eNS_URI, REAssistantModelPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(TypesystemsPackage.eNS_URI, TypesystemsPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(UCSModelPackage.eNS_URI, UCSModelPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		URI fileURI = URI.createFileURI("/home/migue/workspace/prueba/runtime-EclipseApplication/Test/src/HWS.uima");
		Resource resource = null;
		try {
			resource = resourceSet.getResource(fileURI, true);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//==============================================================
		
		
		ModelCreator l = new ModelCreator(resource.getContents());
		l.load(new File("/home/migue/workspace/prueba/runtime-EclipseApplication/Test/src/HWS.ucs"));
					
		
	}
	
	
//	@SuppressWarnings("unused")
//	public void createModel() {
//		URI resourceURI = EditUIUtil.getURI(getEditorInput());
//		Exception exception = null;
//		Resource resource = null;
//		try {
//			// Load the resource through the editing domain.
//			resource = editingDomain.getResourceSet().getResource(resourceURI, true);
//		}
//		catch (Exception e) {
//			exception = e;
//			resource = editingDomain.getResourceSet().getResource(resourceURI, false);
//		}
//		modelRoot = (UCSProject) resource.getContents().get(0);
//	}
	
	
}
