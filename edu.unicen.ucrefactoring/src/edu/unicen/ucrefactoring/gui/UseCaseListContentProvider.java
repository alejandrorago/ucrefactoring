package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCaseModel;

public class UseCaseListContentProvider implements IStructuredContentProvider {

	private Object list;
	private static Object[] EMPTY_ARRAY = new Object[0];

	
	public UseCaseListContentProvider(Object list){
		this.list = list;
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
		if (list instanceof UseCaseModel){
			List<Object> l = new ArrayList<Object>();
			l.addAll(((UseCaseModel)this.list).getUseCases());
			l.addAll(((UseCaseModel)this.list).getAspects());
			return l.toArray();	
		}
		else if (list instanceof Flow){
			return ((Flow)this.list).getEvents().toArray();	
		}
		return EMPTY_ARRAY;
	}
}
