package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.refactorings.ExtensionRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;

public class RefactoringTableContentProvider implements IStructuredContentProvider {

	private Object list;
	private static Object[] EMPTY_ARRAY = new Object[0];

	
	public RefactoringTableContentProvider(Object list){
		//this.list = list;
		List<Refactoring> refactorings = new ArrayList<Refactoring>();
		if (list instanceof Collection<?>){
			refactorings = new ArrayList(((Collection<Refactoring>)list));
			Collections.sort(refactorings, new Comparator<Refactoring>() {
				@Override
				public int compare(Refactoring o1, Refactoring o2) {
					return (o2.getScore().compareTo(o1.getScore()));
				}
			});
		}
		this.list = refactorings; 
	}
	
	public RefactoringTableContentProvider(Object list, String property){
		List<Refactoring> refactorings = new ArrayList<Refactoring>();
		if (list instanceof Collection<?>){
			refactorings = new ArrayList(((Collection<Refactoring>)list));
			if (property.equals(UCRDataView.PROBLEM_HEADER)){
				Collections.sort(refactorings, new Comparator<Refactoring>() {
					@Override
					public int compare(Refactoring o1, Refactoring o2) {
						return (o1.getProblem().compareTo(o2.getProblem()));
					}
				});
			}
			else if (property.equals(UCRDataView.REFACTORING_HEADER)){
				Collections.sort(refactorings, new Comparator<Refactoring>() {
					@Override
					public int compare(Refactoring o1, Refactoring o2) {
						return (o1.getRefactoringName().compareTo(o2.getRefactoringName()));
					}
				});
			}
			else if (property.equals(UCRDataView.ARTIFACT_HEADER)){
				Collections.sort(refactorings, new Comparator<Refactoring>() {
					@Override
					public int compare(Refactoring o1, Refactoring o2) {
						return (o1.getArtifacts().get(0).compareTo(o2.getArtifacts().get(0)));
					}
				});		
			}
			else if (property.equals(UCRDataView.SCORE_HEADER)){
				Collections.sort(refactorings, new Comparator<Refactoring>() {
					@Override
					public int compare(Refactoring o1, Refactoring o2) {
						return (o2.getScore().compareTo(o1.getScore()));
					}
				});
			}
			else if (property.equals(UCRDataView.PRIORITY_HEADER)){
				Collections.sort(refactorings, new Comparator<Refactoring>() {
					@Override
					public int compare(Refactoring o1, Refactoring o2) {
						return (o1.getPriorityText().compareTo(o2.getPriorityText()));
					}
				});
			}
		}
		this.list = refactorings; 

	}
	//=========Implementacion del content provider==============================

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (list instanceof List<?>){
			return ((List<Refactoring>)this.list).toArray();	
		}
		return EMPTY_ARRAY;
	}
}
