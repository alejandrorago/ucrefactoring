package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.ActionCodeEnum;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.model.creation.ModelCreator;
import edu.unicen.ucrefactoring.util.Constants;

public class SimilarityAnalyzer {
	
	private HashMap<String,SequenceAligner> sequenceAligners;
	private StopwordRemover stopwordRemover;
	private HashMap<String,String> sequences;
	private UseCaseModel useCaseModel;
	private ArrayList<String> matrixes;
	private KeyFinder keyFinder;
	
	private HashMap<String, AlignmentX2Result> alignmentResult;
	private HashMap<String, List<String>> useCaseKeywords;

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
	
	public HashMap<String, AlignmentX2Result> getAlignmentResult() {
		return alignmentResult;
	}
	
	public void setAlignmentResult(
			HashMap<String, AlignmentX2Result> alignmentResult) {
		this.alignmentResult = alignmentResult;
	}
	
	public HashMap<String, List<String>> getUseCaseKeywords() {
		return useCaseKeywords;
	}
	
	public void setUseCaseKeywords(HashMap<String, List<String>> useCaseKeywords) {
		this.useCaseKeywords = useCaseKeywords;
	}
	
	public StopwordRemover getStopwordRemover() {
		return stopwordRemover;
	}
	
	public void setStopwordRemover(StopwordRemover stopwordRemover) {
		this.stopwordRemover = stopwordRemover;
	}

	//=========Constructor========================
	
	public SimilarityAnalyzer(UseCaseModel useCaseModel){
		this.useCaseModel=useCaseModel;
		this.alignmentResult = new HashMap<String, AlignmentX2Result>();
		this.useCaseKeywords = new HashMap<String, List<String>>();
		this.stopwordRemover = StopwordRemover.getInstance();
		this.keyFinder = KeyFinder.getInstance();
		this.setAllUseCaseKeywords();
		loadAligners();
		loadMatrixes();
		loadSequences();
	}
	
	//=========Services===========================
	
	public static String getEventKey(UseCase useCase, Flow flow, Event event){
		return useCase.getName() + ":" + flow.getName() + ":" + event.getEventId();
	}
	
	public static String getSequenceKey(UseCase useCase, Flow flow){
		return useCase.getName() + ":" + flow.getName();
	}
	
	public static String getSequenceKey(UseCase useCase, String flowName){
		return useCase.getName() + ":" + flowName;
	}
	
	public static String getAlignmentKey(UseCase useCaseA,Flow flowA, UseCase useCaseB, Flow flowB){
		if(useCaseA.getName().compareTo(useCaseB.getName())>0){
			return useCaseA.getName()+":"+ flowA.getName() + "&" + useCaseB.getName()+":"+flowB.getName();
		}
		else{
			return useCaseB.getName()+":"+ flowB.getName() + "&" + useCaseA.getName()+":"+ flowA.getName();
		}
	}

	
	private void setAllUseCaseKeywords(){
		for(UseCase useCase : useCaseModel.getUseCases()){
			for(Flow flow : useCase.getFlows()){
				for(Event event : flow.getEvents()){
					String key = getEventKey(useCase, flow, event);
					List<String> value = this.stopwordRemover.removeStopwords(event.getDetail());
					
					//Busco las claves 
					//value = this.keyFinder.findKeys(value);
					
//					System.out.println(key);
//					System.out.println(value);
					this.useCaseKeywords.put(key, value);
					
				}
			}
		}
	}
	
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
				EList<UseCase> uc_list = useCaseModel.getUseCases();
				for (int i=0; i <useCaseModel.getUseCases().size(); i++){
					UseCase uc1 = uc_list.get(i);
					for (Flow f1 : uc1.getFlows()){
						String seq1 = sequences.get(getSequenceKey(uc1, f1));
						for (int j=0; j < useCaseModel.getUseCases().size(); j++){							
							if (i!=j){								
								UseCase uc2 = uc_list.get(j);
								for (Flow f2 : uc2.getFlows()){
									String seq2 = sequences.get(getSequenceKey(uc2,f2));
									System.out.println(uc1.getName()+"/"+f1.getName() +" - "+uc2.getName()+"/"+f2.getName());
									AlignmentX2Result result = sa.performAlignment(seq1, seq2, matrix);
									result.setUseCases(uc1,f1,uc2,f2);
									if (areSimilar(result)){
										this.alignmentResult.put(getAlignmentKey(uc1,f1,uc2,f2), result);
									}
								}
							}
						}
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
			EList<Flow> fs = uc.getFlows();
			for(Flow f : fs){
				String s = "";
				for(Event e : f.getEvents()){				
					for (ActionClass ac : ((FunctionalEvent)e).getActionClasses()){
//						if (!ac.getName().equals("Noise"))
//							s=s+((ActionCodeEnum.getByName(ac.getName())).getLiteral().equals("o")||(ActionCodeEnum.getByName(ac.getName())).getLiteral().equals("j")||(ac.getRanking().intValue()>1)?"":(ActionCodeEnum.getByName(ac.getName())).getLiteral());
//						else s=s+"y";
						if (ActionCodeEnum.getByName(ac.getName())!=null)s = s + ActionCodeEnum.getByName(ac.getName());
					}
					sequences.put(getSequenceKey(uc, f), s);
				}
			}
		}
		System.out.println(sequences.toString());
	}
	
	public boolean areSimilar(AlignmentX2Result alignment){
		List<SimilarBlock> similarBlocksA = alignment.getSimilarBlocksFromA();
		List<SimilarBlock> similarBlocksB = alignment.getSimilarBlocksFromB();
		SimilarBlock similarA;
		SimilarBlock similarB;
		String key;
		
		int similarCount = 0;
		
		for (int i = 0; i<similarBlocksA.size() && i<similarBlocksB.size() ; i++){
			similarA = similarBlocksA.get(i);
			similarB = similarBlocksB.get(i);
			for (Event eventA : similarA.getSimilarEvents()){
				key = getEventKey(alignment.getUseCaseA(), alignment.getUseCaseA().getFlows().get(0), eventA);
				List<String> aKeys = this.useCaseKeywords.get(key);
				
				for (Event eventB : similarB.getSimilarEvents()){
					key = getEventKey(alignment.getUseCaseB(), alignment.getUseCaseB().getFlows().get(0), eventB);
					List<String> bKeys = this.useCaseKeywords.get(key);
					int count = 0;
					for (String s : aKeys){
						if (bKeys.contains(s)) count++;
					}
					if (count>=Math.ceil(aKeys.size()/4)) similarCount++;
				}

			}
			if (similarCount>=Math.ceil((similarA.getSimilarEvents().size()+similarB.getSimilarEvents().size())/2)){
				return true;
			}
			
		}

		return false;
	}
	

		
}
