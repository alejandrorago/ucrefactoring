package edu.unicen.ucrefactoring.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ListViewer;

import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.model.UseCaseModel;

public class UCView extends ViewPart {

	//Widgets
	private ListViewer uCList;
	private TextViewer leftViewer;
	private TextViewer rightViewer;
	
	//===============================
	
	public UCView() {
		// TODO Auto-generated constructor stub
	}
	
	//===============================

	@Override
	public void createPartControl(Composite parent) {
		
		//Providers
		UCRefactoringDetection ucref = new UCRefactoringDetection();
		UseCaseLabelProvider ucLabel = new UseCaseLabelProvider();
		
		// Creamos layout
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		parent.setLayout(fillLayout);
		
		//Creamos los widgets
		leftViewer = new TextViewer(parent, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		rightViewer = new TextViewer(parent, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		uCList = new ListViewer(parent,SWT.BORDER);
		uCList.setContentProvider(ucref);
		uCList.setLabelProvider(ucLabel);
		uCList.setInput(ucref);
		uCList.refresh();
		
		//

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
