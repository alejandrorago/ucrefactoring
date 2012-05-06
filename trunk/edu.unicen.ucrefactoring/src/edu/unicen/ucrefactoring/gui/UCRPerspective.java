package edu.unicen.ucrefactoring.gui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IWorkbenchPage;
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
		layout.addView("edu.unicen.ucrefactoring.gui.UCRUseCasesView", IPageLayout.LEFT, 0.32f, "edu.unicen.ucrefactoring.gui.UCRCompareView");
		layout.addView("edu.unicen.ucrefactoring.gui.UCRCompareView", IPageLayout.LEFT, 0.95f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("edu.unicen.ucrefactoring.gui.UCRDataView", IPageLayout.BOTTOM, 0.5f, "edu.unicen.ucrefactoring.gui.UCRCompareView");
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
