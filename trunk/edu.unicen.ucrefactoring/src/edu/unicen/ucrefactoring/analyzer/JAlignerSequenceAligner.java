package edu.unicen.ucrefactoring.analyzer;

import jaligner.Alignment;
import jaligner.Sequence;
import jaligner.SmithWatermanGotoh;
import jaligner.formats.Pair;
import jaligner.matrix.Matrix;
import jaligner.matrix.MatrixLoader;
import jaligner.matrix.MatrixLoaderException;
import jaligner.ui.filechooser.NamedInputStream;
import jaligner.util.SequenceParser;
import jaligner.util.SequenceParserException;

import java.io.InputStream;

public class JAlignerSequenceAligner implements SequenceAligner {
	
	private final float GAP_OPEN_PENALTY = 5f;
	private final float GAP_EXT_PENALTY = .5f;

	@Override
	public AlignmentX2Result performAlignment(String s1, String s2, String matrix) {
		//String result ="";
		AlignmentX2Result result = null;
		try {
			InputStream IS = Thread.currentThread().getContextClassLoader().getResourceAsStream("/src/edu/unicen/ucrefactoring/resources/"+matrix);
			Sequence seq1 = SequenceParser.parse(s1);
			Sequence seq2 = SequenceParser.parse(s2);	
			NamedInputStream nis = new NamedInputStream(matrix, IS);
			Matrix m = MatrixLoader.load(nis);
			Alignment alignment = SmithWatermanGotoh.align(seq1, seq2, m, GAP_OPEN_PENALTY, GAP_EXT_PENALTY);
			result = new AlignmentX2Result(new String (alignment.getSequence1()), new String (alignment.getSequence2()), alignment.getStart1(), alignment.getStart2(), alignment.getScore());
	        String s =(alignment.getSummary() );
	        s+= ( new Pair().format(alignment) );
	        System.out.println(s);
			System.out.println(alignment.calculateScore());
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
