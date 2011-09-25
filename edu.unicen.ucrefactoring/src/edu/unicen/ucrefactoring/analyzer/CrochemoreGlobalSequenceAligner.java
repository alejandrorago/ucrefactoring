package edu.unicen.ucrefactoring.analyzer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import neobio.alignment.CrochemoreLandauZivUkelsonGlobalAlignment;
import neobio.alignment.IncompatibleScoringSchemeException;
import neobio.alignment.InvalidScoringMatrixException;
import neobio.alignment.InvalidSequenceException;
import neobio.alignment.ScoringMatrix;
import edu.unicen.ucrefactoring.util.Constants;

public class CrochemoreGlobalSequenceAligner implements SequenceAligner{

	@Override
	public AlignmentX2Result performAlignment(String s1, String s2, String matrix) {
		String result ="";
		CrochemoreLandauZivUkelsonGlobalAlignment c = new CrochemoreLandauZivUkelsonGlobalAlignment();
		try {
			ScoringMatrix sm = new ScoringMatrix(new FileReader(Constants.RESOURCE_PATH+matrix));
			c.loadSequences(new StringReader(s1), new StringReader(s2));
			c.setScoringScheme(sm);
			result+= c.getPairwiseAlignment();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidScoringMatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncompatibleScoringSchemeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return result;
		return new AlignmentX2Result("", "", 0, 0, 0f);
	}

}
