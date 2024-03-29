package edu.unicen.ucrefactoring.analyzer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;

import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.ActionCodeEnum;
import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;

public class SimilarityAnalyzer {

	// Model
	private UseCaseModel useCaseModel;

	// Sequence Alignement
	private HashMap<String, SequenceAligner> sequenceAligners;
	private HashMap<String, String> sequences;
	private ArrayList<String> matrixes;
	private HashMap<String, AlignmentX2Result> alignmentResult;

	// Text Analysis
	private StopwordRemover stopwordRemover;
	private Stemmer stemmer;
	private static HashMap<String, List<String>> useCaseKeywords;

	// ========Getters And Setters==================
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

	public void setStopwordRemover(StopwordRemover loadSequencesstopwordRemover) {
		this.stopwordRemover = stopwordRemover;
	}

	// =========Constructor========================

	public SimilarityAnalyzer(UseCaseModel useCaseModel) {
		this.useCaseModel = useCaseModel;
		this.alignmentResult = new HashMap<String, AlignmentX2Result>();
		this.useCaseKeywords = new HashMap<String, List<String>>();
		this.stopwordRemover = StopwordRemover.getInstance();
		this.stemmer = new LovinsStemmer();
		this.setAllUseCaseKeywords();
		loadAligners();
		loadMatrixes();
		loadSequences();
	}

	// =========Services===========================

	public static String getEventKey(UseCase useCase, Flow flow, Event event) {
		return useCase.getName() + ":" + flow.getName() + ":"
				+ event.getEventId();
	}

	public static String getSequenceKey(UseCase useCase, Flow flow) {
		return useCase.getName() + ":" + flow.getName();
	}

	public static String getSequenceKey(UseCase useCase, String flowName) {
		return useCase.getName() + ":" + flowName;
	}

	public static String getAlignmentKey(UseCase useCaseA, Flow flowA,
			UseCase useCaseB, Flow flowB) {
		if (useCaseA.getName().compareTo(useCaseB.getName()) > 0) {
			return useCaseA.getName() + ":" + flowA.getName() + "&"
					+ useCaseB.getName() + ":" + flowB.getName();
		} else {
			return useCaseB.getName() + ":" + flowB.getName() + "&"
					+ useCaseA.getName() + ":" + flowA.getName();
		}
	}

	private void setAllUseCaseKeywords() {
		for (UseCase useCase : useCaseModel.getUseCases()) {
			for (Flow flow : useCase.getFlows()) {
				for (Event event : flow.getEvents()) {
					String key = getEventKey(useCase, flow, event);

					// StopWord Remover
					List<String> value = this.stopwordRemover
							.removeStopwords(event.getDetail());

					// Stemming Process
					for (int i = 0; i < value.size(); i++) {
						value.set(i, stemmer.stem(value.get(i)));
					}

					// Busco las claves
					// value = this.keyFinder.findKeys(value);

					this.useCaseKeywords.put(key, value);
				}
			}
		}
	}

	private void loadMatrixes() {
		this.matrixes = new ArrayList<String>();
		matrixes.add(SequenceAligner.UCMATRIX);
	}

	/**
	 * Carga los algoritmos de alineamiento de sequencias disponibles
	 */
	private void loadAligners() {
		this.sequenceAligners = new HashMap<String, SequenceAligner>();
		sequenceAligners.put(SequenceAligner.SMITH_WATERMAN_SA,
				new SmithWatermanSequenceAligner());
		sequenceAligners.put(SequenceAligner.NEEDLEMAN_WUNSCH_SA,
				new NeedlemanWunschSequenceAligner());
		sequenceAligners.put(SequenceAligner.JALIGNER_SW_SA,
				new JAlignerSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_LOCAL_SA,
				new CrochemoreLocalSequenceAligner());
		sequenceAligners.put(SequenceAligner.CROCHEMORE_GLOBAL_SA,
				new CrochemoreGlobalSequenceAligner());
	}

	/**
	 * Ejecuta una prueba de alineamiento de sequencias con el algoritmo pasado
	 * como parámetro
	 * 
	 * @param s1
	 * @param s2
	 */
	public void testSequenceAlignment(String analyzer, String s1, String s2,
			String matrix) {
		System.out.println("TEST " + analyzer + " SA :");
		SequenceAligner sa = sequenceAligners.get(analyzer);
		AlignmentX2Result al = sa.performAlignment(s1, s2, matrix);
		System.out.println(al);
	}

	/**
	 * Compara las sequencias del UseCaseModel, todas contra todas, utilizando
	 * el Analizador pasado como parámetro TODO: STRATEGY ANALIZADORES
	 * 
	 * @param analyzerName
	 *            - El nombre del analizador a utilizar
	 */
	public void compareUCSequences(String analyzer, String matrix) {
		if (useCaseModel != null && sequences != null
				&& sequenceAligners != null) {
			SequenceAligner sa = sequenceAligners.get(analyzer);
			if (sa != null) {
				EList<UseCase> uc_list = useCaseModel.getUseCases();
				for (int i = 0; i < useCaseModel.getUseCases().size(); i++) {
					UseCase uc1 = uc_list.get(i);
					for (Flow f1 : uc1.getFlows()) {
						String seq1 = sequences.get(getSequenceKey(uc1, f1));
						for (int j = i+1; j < useCaseModel.getUseCases().size(); j++) {
								UseCase uc2 = uc_list.get(j);
								for (Flow f2 : uc2.getFlows()) {
									String seq2 = sequences.get(getSequenceKey(
											uc2, f2));
									System.out.println("-------------------------------");
									System.out.println(uc1.getName() + "/"
											+ f1.getName() + " - "
											+ uc2.getName() + "/"
											+ f2.getName());
									AlignmentX2Result result = sa
											.performAlignment(seq1, seq2,
													matrix);
									result.setUseCases(uc1, f1, uc2, f2);
									if (areSimilar(result)) {
										this.alignmentResult.put(
												getAlignmentKey(uc1, f1, uc2,
														f2), result);
									} else
										System.out.println("DISTINTOS");
									;
								}
						}
					}
				}
			}
		}
	}
	
	public AlignmentX2Result compareThisSequences(Flow fA, Flow fB, String analyzer, String matrix) {
		SequenceAligner sa = sequenceAligners.get(analyzer);
		String seqA= sequences.get(getSequenceKey(fA.getUseCase(), fA));
		String seqB= sequences.get(getSequenceKey(fB.getUseCase(), fB));
		AlignmentX2Result result = sa.performAlignment(seqA, seqB, matrix);
		result.setUseCases(fA.getUseCase(), fA, fB.getUseCase(), fB);
		return result;
	}

	/**
	 * Método que obtiene las sequencias del UseCaseModel
	 */
	private void loadSequences() {
		sequences = new HashMap<String, String>();
		for (UseCase uc : useCaseModel.getUseCases()) {
			EList<Flow> fs = uc.getFlows();
			for (Flow f : fs) {
				String s = "";
				for (Event e : f.getEvents()) {
					if (e instanceof FunctionalEvent) {
						if(((FunctionalEvent) e).getActionClasses().size() > 0){
							ActionClass acElegida = null;
							for (ActionClass ac : ((FunctionalEvent) e)
									.getActionClasses()) {
								// SI ES EL PRIMERO O ES EL DE MAYOR CONF
								if(acElegida == null || acElegida.getConfidence() < ac.getConfidence()){
									acElegida = ac;
								}
							}	
							ActionCodeEnum ace = null;
							try{
								ace = ActionCodeEnum.getByName(acElegida.getName());
							} catch (Exception ex) {
								if (ace == null){
									ace = ActionCodeEnum.UNKNOWN;
								}	
							}
							if (ace == null){
								ace = ActionCodeEnum.UNKNOWN;
							}
							s = s + ace.getLiteral();
						} else{ // SI NO TIENE ACTION CLASSES LE PONGO 'NI IDEA'
							s = s + ActionCodeEnum.UNKNOWN.getLiteral();
						}
					}
					else{
						s = s + ActionCodeEnum.getByName(ActionCodeEnum.FLOW.getName());
					}
				}
				sequences.put(getSequenceKey(uc, f), s);
			}
		}
		System.out.println(sequences.toString());
	}

	public boolean areSimilar(AlignmentX2Result alignment) {
		List<SimilarBlock> similarBlocksA = alignment.getSimilarBlocksFromA();
		List<SimilarBlock> similarBlocksB = alignment.getSimilarBlocksFromB();
		SimilarBlock similarA;
		SimilarBlock similarB;
		String key;

		List<String> aKeys = new ArrayList<String>();
		List<String> bKeys = new ArrayList<String>();
		for (int i = 0; i < similarBlocksA.size() && i < similarBlocksB.size(); i++) {
			similarA = similarBlocksA.get(i);
			similarB = similarBlocksB.get(i);
			for (Event eventA : similarA.getSimilarEvents()) {
				key = getEventKey(alignment.getUseCaseA(),
						alignment.getFlowA(), eventA);
				for (String newWord : this.useCaseKeywords.get(key)) {
					if (!aKeys.contains(newWord))
						aKeys.add(newWord);
				}
			}
			for (Event eventB : similarB.getSimilarEvents()) {
				key = getEventKey(alignment.getUseCaseB(),
						alignment.getFlowB(), eventB);
				for (String newWord : this.useCaseKeywords.get(key)) {
					if (!bKeys.contains(newWord))
						bKeys.add(newWord);
				}
			}
		}

		double threshold = 0.5;
		double total = (aKeys.size() + bKeys.size());
		double similars = 0;
		for (String aKey : aKeys) {
			for (String bKey : bKeys) {
				if (aKey.equals(bKey)) {
					similars = similars + 2;
				}
			}
		}
		double ratio = similars / total;

		// save the ratio in the align object for metric collection
		alignment.setTextSimilarityScore(ratio);

		if (ratio >= threshold) {
			return true;
		}
		return false;
	}
	
	public boolean areSimilar(Flow fA, Flow fB) {
		String key;
		List<String> aKeys = new ArrayList<String>();
		List<String> bKeys = new ArrayList<String>();
		for (Event eventA : fA.getEvents()) {
			key = getEventKey(fA.getUseCase(),
					fA, eventA);
			for (String newWord : this.useCaseKeywords.get(key)) {
				if (!aKeys.contains(newWord))
					aKeys.add(newWord);
			}
		}
		for (Event eventB : fB.getEvents()) {
			key = getEventKey(fB.getUseCase(),fB, eventB);
			for (String newWord : this.useCaseKeywords.get(key)) {
				if (!bKeys.contains(newWord))
					bKeys.add(newWord);
			}
		}
		double threshold = 0.5;
		double total = (aKeys.size() + bKeys.size());
		double similars = 0;
		for (String aKey : aKeys) {
			for (String bKey : bKeys) {
				if (aKey.equals(bKey)) {
					similars = similars + 2;
				}
			}
		}
		double ratio = similars / total;
		if (ratio >= threshold) {
			return true;
		}
		return false;
	}

	public static boolean isPrefix(String a, String b) {
		int size = (a.length() < b.length()) ? a.length() : b.length();
		for (int i = 0; i < size; i++) {
			char aChar = a.toCharArray()[i];
			char bChar = b.toCharArray()[i];
			if (aChar != bChar)
				return false;
		}
		return true;
	}

	public static Float getTextSimilarity(UseCase useCase1, UseCase useCase2) {
		String key;
		List<String> aKeys = new ArrayList<String>();
		List<String> bKeys = new ArrayList<String>();
		// get keys from usecase1
		for (Flow flow1 : useCase1.getFlows()) {
			for (Event event1 : flow1.getEvents()) {
				key = getEventKey(useCase1, flow1, event1);
				for (String newWord : SimilarityAnalyzer.useCaseKeywords
						.get(key)) {
					if (!aKeys.contains(newWord))
						aKeys.add(newWord);
				}
			}
		}
		// get keys from usecase2
		for (Flow flow2 : useCase2.getFlows()) {
			for (Event event2 : flow2.getEvents()) {
				key = getEventKey(useCase2, flow2, event2);
				for (String newWord : SimilarityAnalyzer.useCaseKeywords
						.get(key)) {
					if (!bKeys.contains(newWord))
						bKeys.add(newWord);
				}
			}
		}

		Float total = (float) (aKeys.size() + bKeys.size());
		Float similars = 0f;
		for (String aKey : aKeys) {
			for (String bKey : bKeys) {
				if (aKey.equals(bKey)
						|| SimilarityAnalyzer.isPrefix(aKey, bKey)) {
					similars = similars + 2;
				}
			}
		}
		Float textSimilarity = similars / total;

		return textSimilarity;
	}

	public static Float getActorSimilarity(UseCase useCase1, UseCase useCase2) {
		List<Actor> aActors = new ArrayList<Actor>();
		List<Actor> bActors = new ArrayList<Actor>();
		// get actors from usecase1
		aActors.add(useCase1.getPrimaryActor());
		aActors.addAll(useCase1.getSecondaryActors());
		// get actors from usecase2
		bActors.add(useCase2.getPrimaryActor());
		bActors.addAll(useCase2.getSecondaryActors());

		Float total = (float) (aActors.size() + bActors.size());
		Float similars = 0f;
		if (aActors.size() > 0 && bActors.size() > 0 && aActors.get(0) != null
				&& bActors.get(0) != null)
			for (Actor aActor : aActors) {
				for (Actor bActor : bActors) {
					if (aActor.equals(bActor)) {
						similars = similars + 2;
					}
				}
			}
		Float actorSimilarity = similars / total;

		return actorSimilarity;
	}

}
