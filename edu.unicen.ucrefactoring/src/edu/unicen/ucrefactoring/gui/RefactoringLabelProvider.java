package edu.unicen.ucrefactoring.gui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import edu.unicen.ucrefactoring.metrics.LargeUseCaseMetric;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.refactorings.DeleteActorRefactoring;
import edu.unicen.ucrefactoring.refactorings.DeleteUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtensionRefactoring;
import edu.unicen.ucrefactoring.refactorings.ExtractUseCaseRefactoring;
import edu.unicen.ucrefactoring.refactorings.GeneralizationRefactoring;
import edu.unicen.ucrefactoring.refactorings.InclusionRefactoring;
import edu.unicen.ucrefactoring.refactorings.MergeUseCasesRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;

public class RefactoringLabelProvider extends LabelProvider {

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getImage(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof ExtensionRefactoring){
			ExtensionRefactoring ref = (ExtensionRefactoring)element;
			return "Generate Extension Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof InclusionRefactoring){
			InclusionRefactoring ref = (InclusionRefactoring)element;
			return "Generate Inclusion Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof GeneralizationRefactoring){
			GeneralizationRefactoring ref = (GeneralizationRefactoring)element;
			return "Generate Generalization Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof ExtractUseCaseRefactoring){
			ExtractUseCaseRefactoring ref = (ExtractUseCaseRefactoring)element;
			return "Exctract Use Case Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof DeleteUseCaseRefactoring){
			DeleteUseCaseRefactoring ref = (DeleteUseCaseRefactoring)element;
			return "Delete Use Case Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof DeleteActorRefactoring){
			DeleteActorRefactoring ref = (DeleteActorRefactoring)element;
			return "Delete Actor Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		else if (element instanceof MergeUseCasesRefactoring){
			MergeUseCasesRefactoring ref = (MergeUseCasesRefactoring)element;
			return "Merge Use Cases Refactoring - ["+ref.getName()+"] - SCORE: "+ref.getScore();
		}
		return element.toString();
	}
	

}
