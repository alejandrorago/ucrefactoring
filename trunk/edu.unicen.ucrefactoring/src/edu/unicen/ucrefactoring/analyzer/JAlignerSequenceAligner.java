package edu.unicen.ucrefactoring.analyzer;

import neobio.alignment.PairwiseAlignment;
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
	public AlignmentX2Result performAlignment(String s1, String s2, String matrix) {
		//String result ="";
		AlignmentX2Result result = null;
		try {
			Sequence seq1 = SequenceParser.parse(s1);
			Sequence seq2 = SequenceParser.parse(s2);			
			Alignment alignment = SmithWatermanGotoh.align(seq1, seq2, MatrixLoader.load(matrix), 5f, 2f);
			result = new AlignmentX2Result(new String (alignment.getSequence1()), new String (alignment.getSequence2()), alignment.getStart1(), alignment.getStart2(), alignment.getScore());
	        String s =("AAAA:"+ alignment.getSummary() );
	        s+= ( new Pair().format(alignment) );
	        System.out.println(s);
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
