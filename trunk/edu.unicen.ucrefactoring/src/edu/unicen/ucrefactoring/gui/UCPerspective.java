package edu.unicen.ucrefactoring.gui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class UCPerspective implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.addView("edu.unicen.ucrefactoring.UCView", IPageLayout.RIGHT, IPageLayout.RATIO_MAX, IPageLayout.ID_OUTLINE);

	}

}
