package edu.unicen.ucrefactoring.visualiser;

import org.eclipse.contribution.visualiser.simpleImpl.SimpleMarkupKind;

import edu.unicen.ucrefactoring.refactorings.Refactoring;

public class RefactoringMarkupKind extends SimpleMarkupKind {
	private Refactoring refactoring;
	
	public RefactoringMarkupKind(Refactoring refactoring) {
		super(refactoring.getRefactoringName());
		this.refactoring = refactoring;
	}
	
	public Refactoring getRefactoring() {
		return refactoring;
	}
}
