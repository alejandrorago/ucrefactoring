package edu.unicen.ucrefactoring.gui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog to show a custom message
 * @author migue
 *
 */
public class UCRMessageDialog extends Dialog {

	private String question = "";
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public UCRMessageDialog(Shell parentShell, String question) {
		super(parentShell);
		setShellStyle(SWT.APPLICATION_MODAL);
		this.question = question;
	}
	
	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setText(this.question);
		
		
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
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(620, 170);
	}
	
	public String getQuestion() {
		return question;
	}

}
