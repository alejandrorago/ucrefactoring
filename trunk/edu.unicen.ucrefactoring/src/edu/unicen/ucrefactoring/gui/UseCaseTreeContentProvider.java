package edu.unicen.ucrefactoring.gui;

import java.util.Arrays;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;

public class UseCaseTreeContentProvider implements ITreeContentProvider {

	private Object tree;
	private static Object[] EMPTY_ARRAY = new Object[0];
	
	public UseCaseTreeContentProvider(Object tree){
		this.tree = tree;
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
	public void dispose() {
	}
	
	
	
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
	
	
	
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof UseCase){
			UseCase useCase = (UseCase)parentElement;
			return useCase.getFlows().toArray();
		}
		else if (parentElement instanceof Flow){
			Flow flow = (Flow)parentElement;
			return flow.getEvents().toArray();
		}
		else if (parentElement instanceof Event){
			//Event event = (Event)parentElement;
			return EMPTY_ARRAY;
		}
		return getChildren(tree);
//		Object[] events = EMPTY_ARRAY;
//		for (Flow f : (Flow[])((UseCase)tree).getFlows().toArray()){
//			events = concatAll(events,f.getEvents().toArray());
//		}
//		return concatAll(events,((UseCase)tree).getFlows().toArray());
		//return	((UseCase)tree).getFlows().toArray();
	}
	
	public Object getParent(Object element) {
		return null;
	}
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}
	
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		  int totalLength = first.length;
		  for (T[] array : rest) {
		    totalLength += array.length;
		  }
		  T[] result = Arrays.copyOf(first, totalLength);
		  int offset = first.length;
		  for (T[] array : rest) {
		    System.arraycopy(array, 0, result, offset, array.length);
		    offset += array.length;
		  }
		  return result;
	}
	
}
