package edu.unicen.ucrefactoring.core;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import uima.cas.CasPackage;
import edu.unicen.ucrefactoring.analyzer.SequenceAligner;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
import edu.unicen.ucrefactoring.model.creation.ModelCreator;
import edu.unicen.ucrefactoring.util.Constants;

public class UCRefactoringDetection {
	
	private static ModelCreator modelCreator;
	
	
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
	
	//========MAIN==================
	public static void main(String[] args) throws IOException {
		
		//========PATH DE ARCHIVOS UIMA================
		//String uimaPath=Constants.REA_PATH+"HWS-short.uima";
		String uimaPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.uima";
		//=============================================
		
		//=======PATH DE ARCHIVOS UCS==================
		//String ucsPath=Constants.REA_PATH+"HWS-short.ucs";
		String ucsPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.ucs";
		//=============================================
		
		//Cargo el archivo UIMA
		modelCreator = new ModelCreator(loadUIMA(uimaPath).getContents());
		//Cargo el archivo UCS
		modelCreator.load(new File(ucsPath));
		//Imprimo el modelo generado
		modelCreator.printModel(new File(Constants.OUTPUT_RESOURCE_DIR));
		
		
		SimilarityAnalyzer sa = new SimilarityAnalyzer(modelCreator.getParsedUseCaseModel());
		sa.compareUCSequences(SequenceAligner.JALIGNER_SW_SA);
	}
	

}
