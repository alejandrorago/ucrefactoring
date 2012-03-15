package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.List;

import edu.smu.tspell.wordnet.*;

public class KeyFinder {
    private static KeyFinder self;

	
	private KeyFinder(){
		//Seteo el path del diccionario para extraer claves
		System.setProperty("wordnet.database.dir","/home/migue/workspace/REAssistant/edu.unicen.ucrefactoring/src/edu/unicen/ucrefactoring/resources/dict");
	}
	
    /**
     * Devuelve una instancia de StopwordRemover
     * (Singleton, para utilizar solo una instancia de esta clase.)
     * @return stopwordRemover
     */
    public static KeyFinder getInstance(){
    	if (self==null){
    		self = new KeyFinder();
    	}
    	return self;
    }
	
	public List<String> findKeys(List<String> candidateKeys){
		
		List<String> keys = new ArrayList<String>();
		
		NounSynset nounSynset;
		NounSynset[] hyponyms;
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		
		if (candidateKeys.size()>0){
			for (String candidate : candidateKeys){
				Synset[] synsets = database.getSynsets(candidate, SynsetType.NOUN);
				for (int i = 0; i < synsets.length; i++) {
				    nounSynset = (NounSynset)(synsets[i]);
				    hyponyms = nounSynset.getHyponyms();
				    System.out.println("AAA"+nounSynset.getWordForms()[0] +
				            ": " + nounSynset.getDefinition() + ") has " + hyponyms.length + " hyponyms"); 
				}
			}
		}
		
		return keys;
	}
	
	
	
}
