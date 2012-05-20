package edu.unicen.ucrefactoring.gui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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

public class UCRNewUseCaseDialog extends Dialog {

	 private String name;
	 private String description;
	 private Button buttonOK;
	 private Button buttonCancel;
	 
	 //private Shell shell;
	 private Text nameText;
	 private StyledText descText;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UCRNewUseCaseDialog(Shell parentShell) {
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
		lblName.setText("Name :");
		
		Label lblDescription = new Label(container, SWT.NONE);
		lblDescription.setText("Description :");
		
		nameText = new Text(container, SWT.BORDER);
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (nameText != null && !nameText.getText().equals("")){
			        try {
			          name = new String(nameText.getText());
			          if (description!=null && !description.equals(""))buttonOK.setEnabled(true);
			        } catch (Exception ex) {
			          buttonOK.setEnabled(false);
			        }
	    		}
	    		else{
	    			buttonOK.setEnabled(false);
	    		}
			}
		});
		
		Label lblPleaseInsertNew = new Label(container, SWT.NONE);
		lblPleaseInsertNew.setText("Please Insert new Use Case name and description");
		
		TextViewer textViewer = new TextViewer(container, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		descText = textViewer.getTextWidget();
		descText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (descText != null && !descText.getText().equals("")){
			        try {
			          description = new String(descText.getText());
			          if (name!=null && !name.equals(""))buttonOK.setEnabled(true);
			        } catch (Exception ex) {
			          buttonOK.setEnabled(false);
			        }
	    		}
	    		else{
	    			buttonOK.setEnabled(false);
	    		}
			}
		});
		descText.setRightMargin(2);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(lblPleaseInsertNew))
						.add(gl_container.createSequentialGroup()
							.add(33)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(lblDescription)
									.addPreferredGap(LayoutStyle.UNRELATED)
									.add(descText, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
								.add(gl_container.createSequentialGroup()
									.add(38)
									.add(lblName)
									.addPreferredGap(LayoutStyle.UNRELATED)
									.add(nameText, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))))
					.add(57))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(24)
					.add(lblPleaseInsertNew)
					.addPreferredGap(LayoutStyle.RELATED, 31, Short.MAX_VALUE)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblName))
					.add(37)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblDescription)
						.add(descText, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.add(22))
		);
		container.setLayout(gl_container);

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
		return new Point(450, 300);
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
	
	
	public String getUseCaseName(){
		return this.name;
	}
	
	public String getUseCaseDescription(){
		return this.description;
	}
	
}
