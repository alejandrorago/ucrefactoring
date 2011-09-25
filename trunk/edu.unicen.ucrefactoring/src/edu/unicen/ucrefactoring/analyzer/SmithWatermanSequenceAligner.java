package edu.unicen.ucrefactoring.analyzer;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import neobio.alignment.IncompatibleScoringSchemeException;
import neobio.alignment.InvalidScoringMatrixException;
import neobio.alignment.InvalidSequenceException;
import neobio.alignment.PairwiseAlignment;
import neobio.alignment.ScoringMatrix;
import neobio.alignment.SmithWaterman;
import edu.unicen.ucrefactoring.util.Constants;

public class SmithWatermanSequenceAligner implements SequenceAligner{

	@Override
	public AlignmentX2Result performAlignment(String s1, String s2, String matrix) {
		//String result ="";
		AlignmentX2Result result = null;
		s1 = "CUDSEUDMSDU" ;//"CUDSEMGCUDM";
		s2 = "DUSMSDU";//"GCUSM";
		
		try {
			SmithWaterman s = new SmithWaterman();
			s.loadSequences(new StringReader(s1), new StringReader(s2));
			ScoringMatrix sm = new ScoringMatrix(new FileReader(Constants.RESOURCE_PATH+matrix));
			s.setScoringScheme(sm);
			PairwiseAlignment alignment = s.getPairwiseAlignment();
			System.out.println(alignment.toString());
			result = new AlignmentX2Result(alignment.getGappedSequence1(), alignment.getGappedSequence2(), 0, 0, new Float (alignment.getScore()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidSequenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidScoringMatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncompatibleScoringSchemeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
