package edu.unicen.ucrefactoring.analyzer;

public interface SequenceAligner {
	
	public static final String SMITH_WATERMAN_SA = "SW";
	public static final String NEEDLEMAN_WUNSCH_SA = "NW";
	public static final String JALIGNER_SW_SA = "JASW";
	public static final String CROCHEMORE_GLOBAL_SA = "CG";
	public static final String CROCHEMORE_LOCAL_SA = "CC";
	
	/**
	 * Realiza el alinemiento de sequencias de las dos cadenas pasadas como parámetros
	 * @param s1
	 * @param s2
	 * @return result
	 */
	public String performAlignment(String s1,String s2);

}
