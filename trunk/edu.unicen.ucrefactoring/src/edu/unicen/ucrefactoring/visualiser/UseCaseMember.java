package edu.unicen.ucrefactoring.visualiser;

import org.eclipse.contribution.visualiser.simpleImpl.SimpleMember;
import org.eclipse.emf.common.util.EList;

import edu.isistan.uima.unified.typesystems.nlp.Sentence;
import edu.isistan.uima.unified.typesystems.srs.Document;
import edu.unicen.ucrefactoring.model.UseCase;

/**
 * Represents a use case in the visualiser plugin
 * @author migue
 *
 */
@SuppressWarnings("unused")
public class UseCaseMember extends SimpleMember {
	private UseCase useCase;
	
	public UseCaseMember(UseCase useCase, int size) {
		super(useCase.getName());
		this.useCase = useCase;
		setFullName(useCase.getName());
		//setSize(transformSize());
		setSize(size);
	}
	
	public UseCase getUseCase() {
		return useCase;
	}
	
//	public int transformSize() {
//		return document.getEnd() - document.getBegin();
//	}
//	
//	public int transformStart(Sentence sentence) {
//		return sentence.getBegin() - document.getBegin();
//	}
//	
//	public int transformOffset(Sentence sentence) {
//		return sentence.getEnd() - sentence.getBegin();
//	}
}
