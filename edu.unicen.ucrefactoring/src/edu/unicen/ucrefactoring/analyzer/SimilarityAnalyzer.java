package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
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
	private ArrayList<String> matrixes;


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
		loadAligners();
		loadMatrixes();
		loadSequences();
	}
	
	//=========Services===========================
	
	private void loadMatrixes(){
		this.matrixes=new ArrayList<String>();
		matrixes.add(SequenceAligner.UCMATRIX);
	}
	
	/**
	 * Carga los algoritmos de alineamiento de sequencias disponibles
	 */
	private void loadAligners(){
		this.sequenceAligners=new HashMap<String,SequenceAligner>();
		sequenceAligners.put(SequenceAligner.SMITH_WATERMAN_SA, new SmithWatermanSequenceAligner());
		sequenceAligners.put(SequenceAligner.NEEDLEMAN_WUNSCH_SA, new NeedlemanWunschSequenceAligner());
		sequenceAligners.put(SequenceAligner.JALIGNER_SW_SA, new JAlignerSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_LOCAL_SA, new CrochemoreLocalSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_GLOBAL_SA, new CrochemoreGlobalSequenceAligner());
	}
	
	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo  pasado como parámetro
	 * @param s1
	 * @param s2
	 */
	public void testSequenceAlignment(String analyzer,String s1, String s2, String matrix){
		System.out.println("TEST "+analyzer+" SA :");
		SequenceAligner sa = sequenceAligners.get(analyzer);
		System.out.println(sa.performAlignment(s1, s2, matrix));
	}
	
	
	/**
	 * Compara las sequencias del UseCaseModel, todas contra todas, utilizando el 
	 * Analizador pasado como parámetro TODO: STRATEGY ANALIZADORES
	 * @param analyzerName - El nombre del analizador a utilizar
	 */
	public void compareUCSequences(String analyzer, String matrix){
		if (useCaseModel!=null && sequences!=null && sequenceAligners!=null){	
			SequenceAligner sa = sequenceAligners.get(analyzer);
			if (sa!=null){
				for (UseCase uc1 : useCaseModel.getUseCases()){
					String seq1 = sequences.get(uc1.getName());
					for (UseCase uc2 : useCaseModel.getUseCases()){
						String seq2 = sequences.get(uc2.getName());
						System.out.println(uc1.getName() +" - "+uc2.getName());
						System.out.println(sa.performAlignment(seq1, seq2, matrix));
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
