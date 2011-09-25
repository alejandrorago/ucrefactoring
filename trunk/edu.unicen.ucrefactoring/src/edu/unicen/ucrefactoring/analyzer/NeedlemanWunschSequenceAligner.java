package edu.unicen.ucrefactoring.analyzer;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import neobio.alignment.IncompatibleScoringSchemeException;
import neobio.alignment.InvalidScoringMatrixException;
import neobio.alignment.InvalidSequenceException;
import neobio.alignment.NeedlemanWunsch;
import neobio.alignment.ScoringMatrix;
import edu.unicen.ucrefactoring.util.Constants;

public class NeedlemanWunschSequenceAligner implements SequenceAligner{

	@Override
	public AlignmentX2Result performAlignment(String s1, String s2, String matrix) {
		String result ="";
		NeedlemanWunsch n = new NeedlemanWunsch();
		try {
			n.loadSequences(new StringReader(s1), new StringReader(s2));
			System.out.println( );
			ScoringMatrix sm = new ScoringMatrix(new FileReader(Constants.RESOURCE_PATH+matrix));
			n.setScoringScheme(sm);
			result+= n.getPairwiseAlignment();
		} catch (IncompatibleScoringSchemeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidScoringMatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return result;
		return new AlignmentX2Result("", "", 0, 0, 0f);
		
	}

}
