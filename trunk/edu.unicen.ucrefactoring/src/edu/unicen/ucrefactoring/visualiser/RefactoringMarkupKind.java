package edu.unicen.ucrefactoring.visualiser;

import org.eclipse.contribution.visualiser.simpleImpl.SimpleMarkupKind;

import edu.unicen.ucrefactoring.refactorings.Refactoring;

public class RefactoringMarkupKind extends SimpleMarkupKind {
	private Refactoring refactoring;
	
	public RefactoringMarkupKind(Refactoring refactoring) {
		super("\nID: " + ((refactoring.getID()<9)?"0"+refactoring.getID():""+refactoring.getID()) + " - " + refactoring.getRefactoringName() + "\n" + refactoring.getDetail() );
		this.refactoring = refactoring;
	}
	
	public Refactoring getRefactoring() {
		return refactoring;
	}
}
