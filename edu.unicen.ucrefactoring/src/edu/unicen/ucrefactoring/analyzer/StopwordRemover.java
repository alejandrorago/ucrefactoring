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

import edu.unicen.ucrefactoring.util.Constants;

/**
 *
 * @author migue
 */
public  class StopwordRemover {

    protected List<String> stopwords;
    protected List<String> allwords;
    protected int similitud;
    
    private static StopwordRemover self;


    /**
     * 
     */
    private StopwordRemover(){
	       stopwords = new ArrayList<String>();
	       allwords = new ArrayList<String>();
	       this.similitud = 1;
	       stopwords.addAll(cargarPalabras(Constants.STOPWORDS_FILE));
	       //stopwords.addAll(cargarPalabras(Constants.WEB_STOPWORDS_FILE));
	       allwords.addAll( cargarPalabras(Constants.DICTIONARY_FILE));
  
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
        }
        catch (IOException e) { 
        	return stops;
        }
        return stops;
    }

	public List<String> removeStopwords(String texto){
        List<String> result = new ArrayList<String>();
        for (int j = 0; j<texto.length();j++){
            for (int k=j+1; k<=texto.length();k++){
                if ( !Character.isLetter(texto.charAt(k-1))||(k==texto.length())){
                    if (k==texto.length()){
                        if (!stopwords.contains(texto.substring(j, k).toLowerCase())&&!result.contains(texto.substring(j,k).toLowerCase()) && texto.substring(j,k).length()>2 )
                        	if (!Character.isLetter(texto.substring(k-1, k).toCharArray()[0]))
                        		result.add(texto.substring(j,k-1).toLowerCase());
                        	else
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

     
     public static void main(String args[]){
    	 String s = "1. The client selects the option 'Log in' 2. The system asks for the username and password "+
"3. The client enters data "+
"4. The system checks the \"log in\" username existance"+
"5. The system validates password"+
"6. The system shows the site homepage"+
"7. The client selects a product"+
"8. The system displays the available payment options"+
"9. The client selects the payment mode"+
"10. The system informs a valid credit card is required"+
"11. The client provides the card number "+
"12. The system validates card information"+
"13. The system saves the sale and informs success"+
"14. The system displays the product news"+
"15. The client chooses the product news to follow"+
"16. The system registers the subscription";  
    	 StopwordRemover sr = new StopwordRemover();
    	 for (String stopword : sr.removeStopwords(s)){
        	 System.out.println(stopword);
    	 }
     }
     

}
