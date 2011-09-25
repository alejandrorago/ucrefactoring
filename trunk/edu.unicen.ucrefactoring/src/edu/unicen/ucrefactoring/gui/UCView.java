package edu.unicen.ucrefactoring.gui;


import java.util.HashMap;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import edu.unicen.ucrefactoring.core.UCRefactoringDetection;
import edu.unicen.ucrefactoring.model.Condition;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;

/**
 * Clase que define la vista del plugin, sus widgets y acciones.
 * @author migue y pau
 *
 */
public class UCView extends ViewPart {

	//Constantes propias
	private static final String RED_COLOR = "RED";
	private static final String BLUE_COLOR = "BLUE";
	private static final String GREEN_COLOR = "GREEN";
	
	//Widgets
	private ListViewer uCList;
	private TextViewer leftViewer;
	private TextViewer rightViewer;
	
	//UseCases
	private UseCase useCaseA;
	private UseCase useCaseB;
	
	// Actions
	private Action compareAction;
	
	//Colors
	private HashMap<String,Color> colors;
	
	IStructuredSelection selectiona;
	//===============================
	
	public UCView() {
		// TODO Auto-generated constructor stub
		Display display = Display.getCurrent();
		colors = new HashMap<String,Color>();
		colors.put(RED_COLOR,new Color(display,255,0,0));
		colors.put(BLUE_COLOR,new Color(display,0,0,255));
		colors.put(GREEN_COLOR,new Color(display,0,255,0));
	}
	
	//===============================

	/**
	 * Método que inicia el plugin
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		//Providers
		UCRefactoringDetection ucref = new UCRefactoringDetection();
		UseCaseLabelProvider ucLabel = new UseCaseLabelProvider();
		
		// Creamos layout
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.spacing=2;
		parent.setLayout(fillLayout);
		
		//Creamos los widgets
		uCList = new ListViewer(parent, SWT.MULTI | SWT.BORDER);
		uCList.setContentProvider(ucref);
		uCList.setLabelProvider(ucLabel);
		uCList.setInput(ucref);
		leftViewer = new TextViewer(parent, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
		rightViewer = new TextViewer(parent, SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);


		
        //Listener de cambio de selección en la lista(Double click)
		uCList.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) uCList.getSelection();
				
				//Creo el primer y segundo elemento a comparar. utilizado para conservar orden.
				if (useCaseA == null) useCaseA=(UseCase)selection.getFirstElement();
				else if (useCaseB == null) useCaseB=(UseCase)selection.getFirstElement();
				else{
					useCaseA=(UseCase)selection.getFirstElement();
					useCaseB=null;
				}
				
				//Habilito el boton de comparar si hay 2 y solo 2 elementos seleccionados
				if (selection != null && selection.size()==2){	
					compareAction.setEnabled(true);
				}
				else compareAction.setEnabled(false);
				
			}
		});
		
		//Defino las acciones que puedo realizar
		defineActions();
		//Creo el menú contextual de la lista de UCs
		initListContextMenu();
	}
	
	//List Context Menu
	private void initListContextMenu()	{
		MenuManager menuManager = new MenuManager("#PopupMenu");
		menuManager.setRemoveAllWhenShown(true);
		
		menuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				manager.add(compareAction);	
			}			

		});

		Menu menu = menuManager.createContextMenu(uCList.getList());
		uCList.getList().setMenu(menu);
		getSite().registerContextMenu(menuManager, uCList);
		
	}
	
	//actions
	/**
	 * Define las acciones que pueden realizarse
	 */
	private void defineActions() {
		
		//Adding
		compareAction = new Action() {			
			public void run(){
				
				if (useCaseA != null && useCaseB != null){
					setUseCaseContentToTextViewer(leftViewer,useCaseA);
					setUseCaseContentToTextViewer(rightViewer,useCaseB);
				}


								
			}
		};
	
		compareAction.setText("Comparar");
		compareAction.setToolTipText("Compara los elementos seleccionados");
	}	

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	/**
	 * Método que presenta los casos de uso en las pantallas para comparación
	 * @param textViewer
	 * @param useCase
	 */
	public void setUseCaseContentToTextViewer(TextViewer textViewer , UseCase useCase){
		

		StyleRange style1 = new StyleRange();
		style1.start = 10;
		style1.length = 100;
		style1.fontStyle = SWT.BOLD;
		style1.foreground = colors.get(RED_COLOR);
	

		
		String result = "";
		result+= "======= " + useCase.getName()+" ========\n";
		if (useCase.getContext().getPreconditions().size()>0)result+=("\nPRECONDITIONS: ");
		for (Condition c : useCase.getContext().getPreconditions()){
			result+=(" - "+c.getDescription());
		}
		if (useCase.getContext().getPostconditions().size()>0)result+=("\nPOSTCONDITIONS: ");
		for (Condition c : useCase.getContext().getPostconditions()){
			result+=(" - "+c.getDescription());
		}	
		for (Flow flow : useCase.getFlows()){
			result+=("\nFlow: "+ flow.getName());
			result+=("\nEvents: ");
			for(Event e : flow.getEvents())
				result+=("\n#"+e.getNumber() + " - " + "ID: "+e.getEventId() + " - "+e.getDetail() + "");			
		}	
		textViewer.getTextWidget().setText(result);
		textViewer.getTextWidget().setStyleRange(style1);
		textViewer.refresh();
	}
	
//====================Snipets======================
//	InputDialog dlg = new InputDialog(null,"", "Requirement title", "Insert new value", null);
//	if (dlg.open() == Window.OK) {									
//	   useCase = (UseCase)selection.getFirstElement();
//	   useCase.setName((dlg.getValue()));
//	   uCList.refresh(useCase);					   
//    }	
}
