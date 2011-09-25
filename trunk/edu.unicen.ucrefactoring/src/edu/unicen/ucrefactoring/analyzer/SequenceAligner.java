package edu.unicen.ucrefactoring.analyzer;

public interface SequenceAligner {
	
	public static final String SMITH_WATERMAN_SA = "SMITH-WATERMAN";
	public static final String NEEDLEMAN_WUNSCH_SA = "NEEDLEMAN-WUNSCH";
	public static final String JALIGNER_SW_SA = "JALIGNER-SW";
	public static final String CROCHEMORE_GLOBAL_SA = "CROCHEMORE-GLOBAL";
	public static final String CROCHEMORE_LOCAL_SA = "CROCHEMORE-LOCAL";
	
	public static final String UCMATRIX = "UCMatrix";
	public static final String UCMATRIX2 = "UCMatrix2";
	public static final String UCMATRIX3 = "UCMatrix3";
	
	/**
	 * Realiza el alinemiento de sequencias de las dos cadenas pasadas como parámetros
	 * @param s1
	 * @param s2
	 * @return result
	 */
	public AlignmentX2Result performAlignment(String s1,String s2, String matrix);

}
