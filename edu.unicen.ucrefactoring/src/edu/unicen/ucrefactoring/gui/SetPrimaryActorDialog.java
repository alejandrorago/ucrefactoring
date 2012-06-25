package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

import edu.unicen.ucrefactoring.model.Actor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;

public class SetPrimaryActorDialog extends Dialog {

	private String actorName = "";
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SetPrimaryActorDialog(Shell parentShell) {
		super(parentShell);
	}

	private Combo combo;
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.marginLeft = 5;
		container.setLayout(new FillLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Select Existent Actor or Type a New One:");
		
		combo = new Combo(container, SWT.NONE);
		List<String> items = new ArrayList<String>();
		for(Actor a : UCRUseCasesView.ucref.getUseCaseModel().getActors()){
			if(!a.getName().equalsIgnoreCase("system")){
				items.add(a.getName());
			}
		}
 		combo.setItems(items.toArray(new String[]{}));
 		combo.addListener(SWT.Selection, new Listener(){
 			public void handleEvent(Event e){
 				if(combo.getSelectionIndex() > 0){
 					getButton(OK).setEnabled(true);
 					actorName = combo.getItem(combo.getSelectionIndex());
 				}
 				else if(combo.getText() != ""){
 					actorName = combo.getText();
 				}
 			}
 		});
 		combo.addListener(SWT.Modify, new Listener(){
 			public void handleEvent(Event e){
 				if(combo.getSelectionIndex() > 0){
 					getButton(OK).setEnabled(true);
 					actorName = combo.getItem(combo.getSelectionIndex());
 				}
 				else if(combo.getText() != ""){
 					getButton(OK).setEnabled(true);
 					actorName = combo.getText();
 				}
 			}
 		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		getButton(OK).setEnabled(false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(302, 138);
	}
	
	public String getActorName() {
		return actorName;
	}

}
