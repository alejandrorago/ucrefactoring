package edu.unicen.ucrefactoring.gui;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import edu.unicen.ucrefactoring.model.UseCaseModel;

public class UseCaseContentProvider implements IStructuredContentProvider {

	private UseCaseModel useCaseModel;
	
	public UseCaseContentProvider(UseCaseModel useCaseModel){
		this.useCaseModel = useCaseModel;
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
		return this.useCaseModel.getUseCases().toArray();
	}
}
