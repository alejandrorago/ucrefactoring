package edu.unicen.ucrefactoring.gui;


import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class NewUseCaseDialog extends Dialog {
	
	 private String name;
	 
	 public NewUseCaseDialog(Shell parent) {
		    super(parent);
	 }
	 
	 public String openDialog() {
		    Shell parent = getParentShell();
		    final Shell shell =
		      new Shell(parent, SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL);
		    shell.setText("Creating use case for duplicated functionality...");

		    shell.setLayout(new GridLayout(2, true));

		    Label label = new Label(shell, SWT.NULL);
		    label.setText("New Use Case Name:");

		    final Text text = new Text(shell, SWT.SINGLE | SWT.BORDER);

		    final Button buttonOK = new Button(shell, SWT.PUSH);
		    buttonOK.setText("Ok");
		    buttonOK.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		    Button buttonCancel = new Button(shell, SWT.PUSH);
		    buttonCancel.setText("Cancel");

		    text.addListener(SWT.Modify, new Listener() {
				
		    	public void handleEvent(Event event) {
			        try {
			          name = new String(text.getText());
			          buttonOK.setEnabled(true);
			        } catch (Exception e) {
			          buttonOK.setEnabled(false);
			        }
		    	}; 
		    });

		    buttonOK.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		        shell.dispose();
		      }
		    });

		    buttonCancel.addListener(SWT.Selection, new Listener() {
		      public void handleEvent(Event event) {
		        name = null;
		        shell.dispose();
		      }
		    });
		    
		    shell.addListener(SWT.Traverse, new Listener() {
		      public void handleEvent(Event event) {
		        if(event.detail == SWT.TRAVERSE_ESCAPE)
		          event.doit = false;
		      }
		    });

		    text.setText("");
		    shell.pack();
		    shell.open();

		    Display display = parent.getDisplay();
		    while (!shell.isDisposed()) {
		      if (!display.readAndDispatch())
		        display.sleep();
		    }

		    return name;
		  }

}
