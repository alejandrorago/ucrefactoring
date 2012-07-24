package edu.unicen.ucrefactoring.util;

import java.io.File;

public class Constants {
	
	public final static String SLASH = File.separator; 
	//Path de recursos ***DEPRECATED & NOT USED
	public final static String RESOURCE_PATH =  System.getProperty("user.dir")+"src"+SLASH+"edu"+SLASH+"unicen"+SLASH+"ucrefactoring"+SLASH+"resources"+SLASH;

	//Path de archivos de stopwords
    public final static String STOPWORDS_FILE = File.separator+"edu"+File.separator+"unicen"+File.separator+"ucrefactoring"+File.separator+"resources"+File.separator+"englishStopwords.txt";
    public final static String WEB_STOPWORDS_FILE = File.separator+"edu"+File.separator+"unicen"+File.separator+"ucrefactoring"+File.separator+"resources"+File.separator+"webStopwords.txt";
    public final static String DICTIONARY_FILE = File.separator+"edu"+File.separator+"unicen"+File.separator+"ucrefactoring"+File.separator+"resources"+File.separator+"diccionarioIngles.words";
    
	//Path de archivos ucs y uima propios - ***DEPRECATED & NOT USED
	public final static String USE_CASE_SPECS_PATH = "/home/migue/workspace/prueba/runtime-EclipseApplication/ucspecs/src/";
	
	//Path de archivos ucs y uima de ale - ***DEPRECATED & NOT USED
	public final static String REA_PATH = "/home/migue/workspace/prueba/runtime-EclipseApplication/test/src/";
	
	//Propiedades de los casos de uso
	public final static String BASIC_FLOW = "BasicFlow";
	public final static String SYSTEM_ACTOR_NAME = "System";
	public final static String SYSTEM_ACTOR_DESC = "El actor que representa al sistema";
	public final static String ALTERNATIVE_FLOW = "AlternativeFlow";
	public final static String PRECONDITION = "Precondition";
	public final static String POSTCONDITION = "Postcondition";
	public final static String USE_CASE_MODEL_CLASS_NAME = "UseCaseModel";
	
	//Constantes de recursos  - ***DEPRECATED & NOT USED
	public final static String OUTPUT_RESOURCE_DIR = "/home/migue/workspace/REAssistant/edu.unicen.ucrefactoring/src/edu/unicen/ucrefactoring/model/parsedUCModel.ucrefactoring";

	//View names Constants
	public final static String UCRUseCaseView = "Use Case List";
	public final static String UCRCompareView = "Compare Panel";
	public final static String UCRDataView = "Refactoring List";
	public final static String Visualiser = "Visualiser";
	public final static String VisualiserMenu = "Visualiser Menu";
	
}
