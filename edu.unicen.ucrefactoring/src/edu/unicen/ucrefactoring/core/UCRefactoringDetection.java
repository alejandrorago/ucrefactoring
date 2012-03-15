package edu.unicen.ucrefactoring.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import uima.cas.CasPackage;
import edu.unicen.ucrefactoring.analyzer.SequenceAligner;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.UCRefactoringFactory;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.model.creation.ModelCreator;
import edu.unicen.ucrefactoring.util.Constants;

public class UCRefactoringDetection  {
	
	private static ModelCreator modelCreator;
	private static UseCaseModel useCaseModel;
	
	private SimilarityAnalyzer similarityAnalizer;
	
	
	public SimilarityAnalyzer getSimilarityAnalizer() {
		return similarityAnalizer;
	}

	public void setSimilarityAnalizer(SimilarityAnalyzer similarityAnalizer) {
		this.similarityAnalizer = similarityAnalizer;
	}

	public  ModelCreator getModelCreator() {
		return modelCreator;
	}

	public void setModelCreator(ModelCreator modelCreator) {
		UCRefactoringDetection.modelCreator = modelCreator;
	}

	public UseCaseModel getUseCaseModel() {
		return useCaseModel;
	}

	public void setUseCaseModel(UseCaseModel useCaseModel) {
		UCRefactoringDetection.useCaseModel = useCaseModel;
	}
	
	//=========Constructor===========

	public UCRefactoringDetection(Boolean loadNew){
		super();
		initUCRefactoringDetection(loadNew);
	}
	
	//=========Servicios==============
	
	private static Resource loadUIMA(String filePath){
		//Registramos los archivos necesarios para el UIMA
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
		EPackage.Registry.INSTANCE.put(CasPackage.eNS_URI, CasPackage.eINSTANCE);

		// Levantamos el Resource *.uima
		ResourceSet resourceSet = new ResourceSetImpl();
		URI fileURI = URI.createFileURI(filePath);
		
		Resource resource = null;
		try {
			resource = resourceSet.getResource(fileURI, true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	public static void initUCRefactoringDetection(Boolean loadNew){
		try{
			
			//========PATH DE ARCHIVOS UIMA================
			//String uimaPath=Constants.REA_PATH+"HWS-short.uima";
			String uimaPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.uima";
			//=============================================
	
			//=======PATH DE ARCHIVOS UCS==================
			//String ucsPath=Constants.REA_PATH+"HWS-short.ucs";
			String ucsPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.ucs";
			//=============================================

			if(!loadNew){
				modelCreator = new ModelCreator();
				modelCreator.loadExistingFile(new File(Constants.OUTPUT_RESOURCE_DIR));
				UCRefactoringDetection.useCaseModel = modelCreator.getParsedUseCaseModel();
				//UCRefactoringDetection.updateDomainActions();
				//modelCreator.parsedUseCaseModel = UCRefactoringDetection.useCaseModel;
				//modelCreator.exportModel(); 
				modelCreator.printModel(new File(Constants.OUTPUT_RESOURCE_DIR));

			}else{
				//Cargo el archivo UIMA
				modelCreator = new ModelCreator(loadUIMA(uimaPath).getContents());
				
				//Cargo el archivo UCS
				modelCreator.load(new File(ucsPath));
				
				//Imprimo el modelo generado
				//modelCreator.printModel(new File(Constants.OUTPUT_RESOURCE_DIR));
				
				useCaseModel = modelCreator.getParsedUseCaseModel();
			}
			
		}
		catch (Exception e){
			System.out.println("");
		}
	}
	

	public static void main(String[] args) {
		
		//String sequence1 = "syevy"; //LOGIN 
		//String sequence1 = "syevvysmsuysc"; //CANCEL ORDER
		//String sequence1 = "syeysyscuun"; //ADD NEW PROD.
		//String sequence1 = "syevnullysetvc"; //ADD NEW PROD ALTER.
		//String sequence2 = "syevvysysyevcysc";//BUY PROD.
		//String sequence2 = "syevmstvcn";//ADD SUPP.
		UCRefactoringDetection.initUCRefactoringDetection(false);
		SimilarityAnalyzer sa = new SimilarityAnalyzer(UCRefactoringDetection.useCaseModel);
		sa.compareUCSequences(SequenceAligner.JALIGNER_SW_SA,SequenceAligner.UCMATRIX);
		//sa.testSequenceAlignment(SequenceAligner.JALIGNER_SW_SA, sequence1, sequence2, SequenceAligner.UCMATRIX2);
	}
	
	public static void updateDomainActions(){
		UCRefactoringFactory factory = UCRefactoringFactory.eINSTANCE;
		String inputLine = "";
		for(UseCase uc : useCaseModel.getUseCases()){
			for(Flow f : uc.getFlows()){
				for(Event e : f.getEvents()){
					if(e instanceof FunctionalEvent){
						FunctionalEvent fe = (FunctionalEvent) e;
						System.out.println("Evento: " + fe.getDetail());
						System.out.println("Accion: ");
						fe.getActionClasses().clear();
						ActionClass newAC = factory.createActionClass();
						
						InputStreamReader converter = new InputStreamReader(System.in);
						BufferedReader in = new BufferedReader(converter);
						try {
							inputLine = in.readLine();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						newAC.setName(inputLine);
						fe.getActionClasses().add(newAC);			
					}
				}
					
			}
		}
	}
	
	public void compareUseCases(){
		similarityAnalizer = new SimilarityAnalyzer(UCRefactoringDetection.useCaseModel);
		similarityAnalizer.compareUCSequences(SequenceAligner.JALIGNER_SW_SA,SequenceAligner.UCMATRIX);
	}
	

	

}
