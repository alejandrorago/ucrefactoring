package edu.unicen.ucrefactoring.gui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.Page;

public class UCRPerspective implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		//Defaults functions
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		

		IFolderLayout folderLayout3 = layout.createFolder("folder", IPageLayout.LEFT, 0.22f, "edu.unicen.ucrefactoring.gui.UCRCompareView");
		folderLayout3.addView("edu.unicen.ucrefactoring.gui.UCRUseCasesView");
		IFolderLayout folderLayout1 = layout.createFolder("compareFolder", IPageLayout.LEFT, 0.69f, IPageLayout.ID_EDITOR_AREA);
		folderLayout1.addView("edu.unicen.ucrefactoring.gui.UCRCompareView");	
		IFolderLayout folderLayout2 = layout.createFolder("refactoringFolder", IPageLayout.BOTTOM, 0.64f, "edu.unicen.ucrefactoring.gui.UCRCompareView");
		folderLayout2.addView("edu.unicen.ucrefactoring.gui.UCRDataView");
		
		folderLayout1.addView("org.eclipse.contribution.visualiser.views.Visualiser");
		folderLayout3.addView("org.eclipse.contribution.visualiser.views.Menu");
			
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRUseCasesView");
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRCompareView");
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRDataView");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
