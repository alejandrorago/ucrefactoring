package edu.unicen.ucrefactoring.core;

import java.io.File;
import java.io.IOException;

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
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.model.creation.ModelCreator;
import edu.unicen.ucrefactoring.util.Constants;

public class UCRefactoringDetection implements IStructuredContentProvider {
	
	private ModelCreator modelCreator;
	private UseCaseModel useCaseModel;
	
	
	public  ModelCreator getModelCreator() {
		return modelCreator;
	}

	public void setModelCreator(ModelCreator modelCreator) {
		this.modelCreator = modelCreator;
	}

	public UseCaseModel getUseCaseModel() {
		return useCaseModel;
	}

	public void setUseCaseModel(UseCaseModel useCaseModel) {
		this.useCaseModel = useCaseModel;
	}
	
	//=========Constructor===========

	public UCRefactoringDetection(){
		super();
		initUCRefactoringDetection();
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
	
	public void initUCRefactoringDetection(){
		try{
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
			//modelCreator.printModel(new File(Constants.OUTPUT_RESOURCE_DIR));
			
			this.useCaseModel = modelCreator.getParsedUseCaseModel();
		}
		catch (Exception e){
			
		}
	}
	
//	//========MAIN==================
//	public static void main(String[] args) throws IOException {
//		
//		//========PATH DE ARCHIVOS UIMA================
//		//String uimaPath=Constants.REA_PATH+"HWS-short.uima";
//		String uimaPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.uima";
//		//=============================================
//
//		//=======PATH DE ARCHIVOS UCS==================
//		//String ucsPath=Constants.REA_PATH+"HWS-short.ucs";
//		String ucsPath=Constants.USE_CASE_SPECS_PATH+"ShoppingOnline.ucs";
//		//=============================================
//		
//		//Cargo el archivo UIMA
//		modelCreator = new ModelCreator(loadUIMA(uimaPath).getContents());
//		//Cargo el archivo UCS
//		modelCreator.load(new File(ucsPath));
//		//Imprimo el modelo generado
//		modelCreator.printModel(new File(Constants.OUTPUT_RESOURCE_DIR));
//		
//		//String sequence1 = "syevy"; //LOGIN 
//		String sequence1 = "syevvysmsuysc"; //CANCEL ORDER
//		//String sequence1 = "syeysyscuun"; //ADD NEW PROD.
//		//String sequence1 = "syevnullysetvc"; //ADD NEW PROD ALTER.
//		//String sequence2 = "syevvysysyevcysc";//BUY PROD.
//		String sequence2 = "syevmstvcn";//ADD SUPP.
//		
//		SimilarityAnalyzer sa = new SimilarityAnalyzer(modelCreator.getParsedUseCaseModel());
//		//sa.compareUCSequences(SequenceAligner.JALIGNER_SW_SA,SequenceAligner.UCMATRIX);
//		sa.testSequenceAlignment(SequenceAligner.JALIGNER_SW_SA, sequence1, sequence2, SequenceAligner.UCMATRIX2);
//	}
	
	
	//=========Implementacion del content provider==============================

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return useCaseModel.getUseCases().toArray();
	}
	

}
