package edu.unicen.ucrefactoring.gui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.Aspect;

import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;
import org.eclipse.swt.custom.StyledText;

public class AssignToAspectDialog extends Dialog {

	private String aspectName = "";
	private String aspectDescription = "";
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AssignToAspectDialog(Shell parentShell) {
		super(parentShell);
	}

	private Combo combo;
	private StyledText text;
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Select Existent Aspect or Type a New One:");
		
		combo = new Combo(container, SWT.NONE);
		List<String> items = new ArrayList<String>();
		for(Aspect a : UCRUseCasesView.ucref.getUseCaseModel().getAspects()){
			items.add(a.getName());
		}
 		combo.setItems(items.toArray(new String[]{}));
 		
 		Label lblDescription = new Label(container, SWT.NONE);
 		lblDescription.setText("Description:");
 		
 		text = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP | SWT.MULTI);
 		GroupLayout gl_container = new GroupLayout(container);
 		gl_container.setHorizontalGroup(
 			gl_container.createParallelGroup(GroupLayout.TRAILING)
 				.add(gl_container.createSequentialGroup()
 					.add(17)
 					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
 						.add(GroupLayout.LEADING, text, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
 						.add(GroupLayout.LEADING, lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 						.add(GroupLayout.LEADING, combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 						.add(GroupLayout.LEADING, lblDescription))
 					.add(46))
 		);
 		gl_container.setVerticalGroup(
 			gl_container.createParallelGroup(GroupLayout.LEADING)
 				.add(gl_container.createSequentialGroup()
 					.add(15)
 					.add(lblNewLabel)
 					.add(9)
 					.add(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 					.add(9)
 					.add(lblDescription)
 					.addPreferredGap(LayoutStyle.UNRELATED)
 					.add(text, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
 					.addContainerGap())
 		);
 		container.setLayout(gl_container);
 		combo.addListener(SWT.Selection, new Listener(){
 			public void handleEvent(Event e){
 				if(combo.getSelectionIndex() > 0){
 					getButton(OK).setEnabled(true);
 					aspectName = combo.getItem(combo.getSelectionIndex());
 					Aspect selected = null;
 					for(Aspect a: UCRUseCasesView.ucref.getUseCaseModel().getAspects()){
 						if (a.getName().equals(aspectName)){
 							selected =a;
 							break;
 						}
 					}
 					text.setText(selected.getDescription());
 					text.setEnabled(false);
 				}
 				else if(combo.getText() != ""){
 					aspectName = combo.getText();
 					text.setEnabled(true);
 				}
 			}
 		});
 		combo.addListener(SWT.Modify, new Listener(){
 			public void handleEvent(Event e){
 				if(combo.getSelectionIndex() > 0){
 					getButton(OK).setEnabled(true);
 					aspectName = combo.getItem(combo.getSelectionIndex());
 					Aspect selected = null;
 					for(Aspect a: UCRUseCasesView.ucref.getUseCaseModel().getAspects()){
 						if (a.getName().equals(aspectName)){
 							selected =a;
 							break;
 						}
 					}
 					text.setText(selected.getDescription());
 					text.setEnabled(false);
 				}
 				else if(combo.getText() != ""){
 					getButton(OK).setEnabled(true);
 					aspectName = combo.getText();
 					text.setEnabled(true);
 				}
 			}
 		});
 		text.addListener(SWT.Modify, new Listener(){
	 		public void handleEvent(Event e){
	 			aspectDescription = text.getText();
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
		return new Point(319, 302);
	}
	
	public String getAspectName() {
		return aspectName;
	}
	public String getAspectDescription() {
		return aspectDescription;
	}
}
