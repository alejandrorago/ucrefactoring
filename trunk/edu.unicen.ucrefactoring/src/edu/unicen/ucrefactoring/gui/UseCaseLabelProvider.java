package edu.unicen.ucrefactoring.gui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;

public class UseCaseLabelProvider extends LabelProvider {

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
		if (element instanceof UseCase){
			UseCase useCase = (UseCase)element;
			return useCase.getName();
		}
		else if (element instanceof Flow){
			Flow flow = (Flow) element;
			return flow.getName();
		}
		else if (element instanceof Event){
			Event event = (Event)element;
			return event.getEventId() +") "+ event.getDetail();
		}
		return element.toString();
	}
	

}
