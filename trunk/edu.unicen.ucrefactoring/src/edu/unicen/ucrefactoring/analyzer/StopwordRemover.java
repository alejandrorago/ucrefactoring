/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.unicen.ucrefactoring.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migue
 */
public  class StopwordRemover {

    protected List<String> stopwords;
    protected List<String> allwords;
    protected String stopWordsFile;
    protected String diccionarioFile;
    protected int similitud;
    
    private static StopwordRemover self;


    /**
     * 
     */
    private StopwordRemover(){
	       stopwords = new ArrayList<String>();
	       allwords = new ArrayList<String>();
	
	       stopWordsFile="/edu/unicen/ucrefactoring/resources/englishStopwords.txt";
	       diccionarioFile="/edu/unicen/ucrefactoring/resources/diccionarioIngles.words";
	     
	       this.similitud = 1;
	       stopwords.addAll(cargarPalabras(stopWordsFile));
	       stopwords.addAll(cargarPalabras("/edu/unicen/ucrefactoring/resources/webStopwords.txt"));
	
	       allwords.addAll( cargarPalabras(diccionarioFile));
  
    }
    
    /**
     * Devuelve una instancia de StopwordRemover
     * (Singleton, para utilizar solo una instancia de esta clase.)
     * @return stopwordRemover
     */
    public static StopwordRemover getInstance(){
    	if (self==null){
    		self = new StopwordRemover();
    	}
    	return self;
    }

	public List<String> cargarPalabras(String dir){
        List<String> stops = new ArrayList<String>();
        try {
            BufferedReader in = null;
            InputStream is = getClass().getResourceAsStream(dir);
            if (is != null)
                 in = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = in.readLine()) != null) {
                stops.add(str);
            }
            in.close();
        } catch (IOException e) { return stops;}
        return stops;
    }

	public List<String> removeStopwords(String texto){
        List<String> result = new ArrayList<String>();
        for (int j = 0; j<texto.length();j++){
            for (int k=j+1; k<=texto.length();k++){
                if ( !Character.isLetter(texto.charAt(k-1))||(k==texto.length())){
                    if (k==texto.length()){
                        if (!stopwords.contains(texto.substring(j, k).toLowerCase())&&!result.contains(texto.substring(j,k).toLowerCase()) && texto.substring(j,k).length()>2 )
                            result.add(texto.substring(j,k).toLowerCase());
                    }
                    else if(!stopwords.contains(texto.substring(j, k - 1).toLowerCase())&&!result.contains(texto.substring(j,k-1).toLowerCase())&& texto.substring(j,k-1).length()>2){
                        result.add(texto.substring(j,k-1).toLowerCase());
                    }
                    j=k;
                }
            }
        }
        return result;
     }

	//POR AHORA NO UTILIZADO, NECESITO CARGAR EL JAR COMMON.LANG de apache
//     public List<String> getSimilar(String texto,int similarity) {
//          double distance = similarity;
//            List<String> result = new ArrayList<String>();
//            for (int j = 0; j<texto.length();j++){
//                for (int k=j+1; k<=texto.length();k++){
//                    if ( (!Character.isLetter(texto.charAt(k-1)) &&  !(k==1 && texto.substring(j,k).equals(" ")))||(k==texto.length() )){
//
//                        if (k==texto.length()){
//
//                            for (String s : allwords){
//                                if (StringUtils.getLevenshteinDistance(texto.substring(j, k).toLowerCase(), s.toLowerCase())<distance)
//                                    result.add(s.toLowerCase());
//                            }
//                        }
//                        else {
//                            for (String s : allwords){
//                                if (StringUtils.getLevenshteinDistance(texto.substring(j, k-1).toLowerCase(), s.toLowerCase())<distance)
//                                    result.add(s.toLowerCase());
//                            }
//                        }
//                        j=k;
//                    }
//                }
//            }
//          return result;
//     }

     public boolean hasSearchLogic(String texto){
             for (int k=1; k<=texto.length();k++){
                 if (texto.substring(k-1,k).equals("\"")||texto.substring(k-1,k).equals("-") || texto.substring(k-1,k).equals("+")
                         ||( k<texto.length()-2 && texto.substring(k-1,k+2).equals("not") )
                         ||(k<texto.length()-2 &&texto.substring(k-1,k+2).equals("and"))
                         || texto.substring(k-1,k).equals(":")
                     || texto.substring(k-1,k).equals("?")
                             || texto.substring(k-1,k).equals(")")
                             || texto.substring(k-1,k).equals("*")
                             ||( k<texto.length()-1&&texto.substring(k-1,k+1).equals("to"))
                             || texto.substring(k-1,k).equals("^"))
                     return true;
             }
             return false;

     }

     public String removeSearchLogic(String texto){

            String result = new String();
            for (int k=1; k<=texto.length();k++){
                    int pos = k;
                    if (texto.substring(k-1,k).equals("\"")){
                        
                        k++;
                        while (!texto.substring(k-1,k).equals("\"")&&k<=texto.length())
                            k++;
                        if (!texto.substring(k-1,k).equals("\"")&&k<=texto.length())
                            k=pos;
                    }else if (texto.substring(k-1,k).equals("-") || texto.substring(k-1,k).equals("+")
                            ||(k<texto.length()-2&& texto.substring(k-1,k+2).equals("and"))
                            ||(k<texto.length()-2&& texto.substring(k-1,k+2).equals("not"))){
                        while (!texto.substring(k-1,k).equals(" ")&&k<=texto.length())
                            k++;
                        if (!texto.substring(k-1,k).equals(" ")&&k<=texto.length())
                            k=pos;

//                        }else if (texto.substring(k-1,k).equals("\"")){
//
//                        }
                    }
                    else{
                       result+=texto.substring(k-1,k);
                    }
            }
            System.out.println("BUSQUEDA SIN LOGICA: "+result);
            return result;
     }


}
