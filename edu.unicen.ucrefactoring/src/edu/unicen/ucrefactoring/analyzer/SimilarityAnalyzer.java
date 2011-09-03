package edu.unicen.ucrefactoring.analyzer;

import java.util.HashMap;

import org.eclipse.emf.common.util.EList;

import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.ActionCodeEnum;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;

public class SimilarityAnalyzer {
	
	private HashMap<String,SequenceAligner> sequenceAligners;
	private HashMap<String,String> sequences;
	private UseCaseModel useCaseModel;


	//========Getters And Setters==================
	public HashMap<String, String> getSequences() {
		return sequences;
	}

	public void setSequences(HashMap<String, String> sequences) {
		this.sequences = sequences;
	}

	public UseCaseModel getUseCaseModel() {
		return useCaseModel;
	}

	public void setUseCaseModel(UseCaseModel useCaseModel) {
		this.useCaseModel = useCaseModel;
	}

	//=========Constructor========================
	
	public SimilarityAnalyzer(UseCaseModel useCaseModel){
		this.useCaseModel=useCaseModel;
		this.sequenceAligners=new HashMap<String,SequenceAligner>();
		loadAligners();
		loadSequences();
	}
	
	//=========Services===========================
	
	/**
	 * Carga los algoritmos de alineamiento de sequencias disponibles
	 */
	private void loadAligners(){
		sequenceAligners.put(SequenceAligner.SMITH_WATERMAN_SA, new SmithWatermanSequenceAligner());
		sequenceAligners.put(SequenceAligner.NEEDLEMAN_WUNSCH_SA, new NeedlemanWunschSequenceAligner());
		sequenceAligners.put(SequenceAligner.JALIGNER_SW_SA, new JAlignerSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_LOCAL_SA, new CrochemoreLocalSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_GLOBAL_SA, new CrochemoreGlobalSequenceAligner());
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo JAligner (Smith-Waterman)
	 * @param s1
	 * @param s2
	 */
	public void testJalignerSA(String s1, String s2){
		System.out.println("TEST JALIGNER (Smith-Waterman) SA :");
		SequenceAligner sa = sequenceAligners.get(SequenceAligner.JALIGNER_SW_SA);
		System.out.println(sa.performAlignment(s1, s2));
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo Smith-Waterman
	 * @param s1
	 * @param s2
	 */
	public void testSmithWatermanSA(String s1, String s2){
		System.out.println("TEST SMITH-WATERMAN SA :");
		SequenceAligner sa = sequenceAligners.get(SequenceAligner.SMITH_WATERMAN_SA);
		System.out.println(sa.performAlignment(s1, s2));
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo Crochemore Global
	 * @param s1
	 * @param s2
	 */
	public void testCrochemoreGlobalSA(String s1, String s2){
		System.out.println("TEST CROCHEMORE GLOBAL SA :");
		SequenceAligner sa = sequenceAligners.get(SequenceAligner.SMITH_WATERMAN_SA);
		System.out.println(sa.performAlignment(s1, s2));
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo Crochemore Local
	 * @param s1
	 * @param s2
	 */
	public void testCrochemoreLocalSA(String s1, String s2){
		System.out.println("TEST CROCHEMORE LOCAL SA :");
		SequenceAligner sa = sequenceAligners.get(SequenceAligner.CROCHEMORE_LOCAL_SA);
		System.out.println(sa.performAlignment(s1, s2));
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo Needleman-Wunsch
	 * @param s1
	 * @param s2
	 */
	public void testNeedlemanWunschSA(String s1,String s2){
		System.out.println("TEST NEDDLEMAN-WUNSCH SA :");
		SequenceAligner sa = sequenceAligners.get(SequenceAligner.NEEDLEMAN_WUNSCH_SA);
		System.out.println(sa.performAlignment(s1, s2));
	}
	
	/**
	 * Compara las sequencias del UseCaseModel, todas contra todas, utilizando el 
	 * Analizador pasado como parámetro TODO: STRATEGY ANALIZADORES
	 * @param analyzerName - El nombre del analizador a utilizar
	 */
	public void compareUCSequences(String analyzer){
		if (useCaseModel!=null && sequences!=null && sequenceAligners!=null){	
			SequenceAligner sa = sequenceAligners.get(analyzer);
			if (sa!=null){
				for (UseCase uc1 : useCaseModel.getUseCases()){
					String seq1 = sequences.get(uc1.getName());
					for (UseCase uc2 : useCaseModel.getUseCases()){
						String seq2 = sequences.get(uc2.getName());
						System.out.println(uc1.getName() +" - "+uc2.getName());
						System.out.println(sa.performAlignment(seq1, seq2));
					}
				}
			}
		}
	}

	/**
	 * Método que obtiene las sequencias del UseCaseModel
	 */
	private void loadSequences(){
		sequences = new HashMap<String,String>();
		for (UseCase uc : useCaseModel.getUseCases()){	
			String s = "";
			EList<Flow> fs = uc.getFlows();
			for(Flow f : fs){
				for(Event e : f.getEvents()){				
					for (ActionClass ac : ((FunctionalEvent)e).getActionClasses()){
						if (!ac.getName().equals("Noise"))s=s+((ActionCodeEnum.getByName(ac.getName())).getLiteral().equals("o")||(ActionCodeEnum.getByName(ac.getName())).getLiteral().equals("j")||(ac.getRanking().intValue()>1)?"":(ActionCodeEnum.getByName(ac.getName())).getLiteral());
						else s=s+"y";
					}
				}
			}
			sequences.put(uc.getName(), s);
		}
		System.out.println(sequences.toString());
	}
		
}
