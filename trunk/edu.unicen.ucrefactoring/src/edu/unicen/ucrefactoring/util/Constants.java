package edu.unicen.ucrefactoring.util;

public class Constants {
	
	//Path local de la aplicación
	public static String LOCAL_PATH = System.getProperty("user.dir");
	
	//Path de recursos
	public final static String RESOURCE_PATH = LOCAL_PATH+"/src/edu/unicen/ucrefactoring/resources/";

	//Path de archivos ucs y uima propios
	public final static String USE_CASE_SPECS_PATH = "/home/pau/workspace/prueba/runtime-EclipseApplication/UseCaseSpecs/src/";
	
	//Path de archivos ucs y uima de ale
	public final static String REA_PATH = "/home/pau/workspace/prueba/runtime-EclipseApplication/test/src/";
	
	//Propiedades de los casos de uso
	public final static String BASIC_FLOW = "BasicFlow";
	public final static String SYSTEM_ACTOR_NAME = "System";
	public final static String SYSTEM_ACTOR_DESC = "El actor que representa al sistema";
	public final static String ALTERNATIVE_FLOW = "AlternativeFlow";
	public final static String PRECONDITION = "Precondition";
	public final static String POSTCONDITION = "Postcondition";
	public final static String USE_CASE_MODEL_CLASS_NAME = "UseCaseModel";
	
	//Constantes de recursos
	public final static String OUTPUT_RESOURCE_DIR = LOCAL_PATH+"/src/edu/unicen/ucrefactoring/model/parsedUCModel.ucrefactoring";

	
}
