package edu.unicen.ucrefactoring.visualiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.contribution.visualiser.VisualiserPlugin;
import org.eclipse.contribution.visualiser.core.ProviderManager;
import org.eclipse.contribution.visualiser.interfaces.IGroup;
import org.eclipse.contribution.visualiser.interfaces.IMember;
import org.eclipse.contribution.visualiser.simpleImpl.SimpleContentProvider;
import org.eclipse.contribution.visualiser.simpleImpl.SimpleGroup;
import org.eclipse.contribution.visualiser.simpleImpl.SimpleMember;
import org.eclipse.emf.common.command.Command;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.unicen.ucrefactoring.gui.UCRDataView;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.model.UseCaseModel;
import edu.unicen.ucrefactoring.refactorings.DeleteActorRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;
/**
 * Draws content in the visualiser plugin
 * @author migue
 *
 */
@SuppressWarnings("rawtypes")
public class UCRVisualContentProvider extends SimpleContentProvider implements IPartListener2 {
	static  UseCaseModel useCaseModel;
	
	private Command mostRecentCommand;
	private boolean updateNeeded;
	
	public static List<SimpleGroup> groups;
	public static List<SimpleMember> members;
	
	protected static UCRVisualMarkupProvider2 ucrmarkup;

	
	@Override
	public void initialise() {
		if (VisualiserPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow() != null) {
			VisualiserPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
				.getPartService().addPartListener(this);
		}
		//useCaseModel = UCRUseCasesView.ucref.getUseCaseModel();
		ucrmarkup = new UCRVisualMarkupProvider2();

	}

	@Override
	public List getAllGroups() {
		if(updateNeeded)
			updateData();
		return groups;
	}

	@Override
	public List getAllMembers() {
		if(updateNeeded)
			updateData();
		return members;
	}

	@Override
	public List getAllMembers(IGroup group) {
		if(updateNeeded)
			updateData();
		return null;
	}

	@Override
	public boolean processMouseclick(IMember member, boolean markupWasClicked, int buttonClicked) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRCompareView");
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("edu.unicen.ucrefactoring.gui.UCRUseCasesView");
			UseCaseMember ucm = (UseCaseMember) member;
			Refactoring selectedRefactoring = null;
			for (RefactoringMarkupKind r : UCRVisualMarkupProvider2.kindMap.values()){
				if (r.getRefactoring().getAlignment()!=null){
					if (r.getRefactoring().getAlignment().getUseCaseA().getName().equals(ucm.getUseCase().getName()) ||
							r.getRefactoring().getAlignment().getUseCaseB().getName().equals(ucm.getUseCase().getName())){
						if (selectedRefactoring == null || (selectedRefactoring!=null && selectedRefactoring.getScore()<r.getRefactoring().getScore())){
							selectedRefactoring = r.getRefactoring();
						}
					}
				}
				else if (r.getRefactoring().getUseCase()!=null && r.getRefactoring().getUseCase().getName().equals(ucm.getUseCase().getName())){
					if (selectedRefactoring == null || (selectedRefactoring!=null && selectedRefactoring.getScore()<r.getRefactoring().getScore())){
						selectedRefactoring = r.getRefactoring();
					}
				}
			}
			if (selectedRefactoring!=null){
				UCRDataView.showRefactoring(selectedRefactoring, true);		
			}
			return true;
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void resetProvider() {
		groups = new ArrayList<SimpleGroup>();
		members = new ArrayList<SimpleMember>();
	}
	@Override
	public void partActivated(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partDeactivated(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partOpened(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partClosed(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partBroughtToTop(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partVisible(IWorkbenchPartReference part) {
		evaluateUpdate(part);	
	}
	
	@Override
	public void partHidden(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	@Override
	public void partInputChanged(IWorkbenchPartReference part) {
		evaluateUpdate(part);
	}
	
	public void evaluateUpdate(IWorkbenchPartReference part) {
		if(!(ProviderManager.getContentProvider().equals(this)))
			return;
			
		updateNeeded = true;			
		VisualiserPlugin.refresh();
		
	}
	

	/**
	 * Update the data
	 */
	private void updateData() {
		setResults(useCaseModel);
		updateNeeded = false;
	}

	public static void setResults(UseCaseModel useCaseModel) {
		if (useCaseModel!=null){
			UCRVisualContentProvider.resetProvider();
			if (ucrmarkup!=null){
				ucrmarkup.resetMarkups();
				for(UseCase useCase : useCaseModel.getUseCases()) {
					
					int size = 0;
					for (Flow flow : useCase.getFlows()){
						size+= flow.getEvents().size();
					}
					size = size * 10;
				
					UseCaseMember member = new UseCaseMember(useCase, size);
					members.add(member);
					ucrmarkup.addResult(member);
				}
			}
		}
	}
	
	public static void setUseCaseModel(UseCaseModel useCaseModel){
		UCRVisualContentProvider.useCaseModel = useCaseModel;
		setResults(useCaseModel);
		VisualiserPlugin.refresh();
	}
	
	

}
