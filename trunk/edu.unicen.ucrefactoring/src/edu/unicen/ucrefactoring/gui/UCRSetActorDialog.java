package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.Combo;

import edu.unicen.ucrefactoring.model.Actor;

/**
 * Dialog to set an Autor to a Use Case
 * @author migue
 *
 */
public class UCRSetActorDialog extends Dialog {

	 private String actorName = "";

	 private Button buttonOK;
	 private Button buttonCancel;
	 private Combo combo;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UCRSetActorDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.APPLICATION_MODAL);
		//shell = this.getShell();
		
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setText("Actor :");
		
		Label lblPleaseInsertNew = new Label(container, SWT.NONE);
		lblPleaseInsertNew.setText("Please select existing actor, or type a new one");
		
		combo = new Combo(container, SWT.NONE);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(69)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(lblPleaseInsertNew)
						.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
							.add(53)
							.add(lblName)
							.add(18)
							.add(combo)))
					.add(74))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
					.add(31)
					.add(lblPleaseInsertNew)
					.add(35)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblName))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);

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
		buttonOK = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		buttonOK.setEnabled(false);
		buttonCancel = createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 239);
	}
	
//	 public String[] openDialog() {
//	    // Create the dialog window
//	    Shell shell = new Shell(getShell(), getShellStyle());
//	    shell.setText("New Use Case");
//	    createContents(shell);
//	    shell.pack();
//	    shell.open();
//	    Display display = getParentShell().getDisplay();
//	    while (!shell.isDisposed()) {
//	      if (!display.readAndDispatch()) {
//	        display.sleep();
//	      }
//	    }
//	    // Return the entered value, or null
//	    return new String[]{this.nameText.getText(),this.descText.getText()};
//	  }
	
	
	public String getActorName() {
		return actorName;
	}
}
