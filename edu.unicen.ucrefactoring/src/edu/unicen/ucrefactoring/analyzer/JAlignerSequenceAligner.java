package edu.unicen.ucrefactoring.analyzer;

import jaligner.Alignment;
import jaligner.Sequence;
import jaligner.SmithWatermanGotoh;
import jaligner.formats.Pair;
import jaligner.matrix.MatrixLoader;
import jaligner.matrix.MatrixLoaderException;
import jaligner.util.SequenceParser;
import jaligner.util.SequenceParserException;

public class JAlignerSequenceAligner implements SequenceAligner {

	@Override
	public String performAlignment(String s1, String s2, String matrix) {
		String result ="";
		try {
			Sequence seq1 = SequenceParser.parse(s1);
			Sequence seq2 = SequenceParser.parse(s2);	
			
			Alignment alignment = SmithWatermanGotoh.align(seq1, seq2, MatrixLoader.load(matrix), 5f, 2f);
	        result+=("AAAA:"+ alignment.getSummary() );
	        result+= ( new Pair().format(alignment) );
		} catch (SequenceParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatrixLoaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
