package edu.unicen.ucrefactoring.gui;


import java.awt.Cursor;
import java.awt.Window;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

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
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.progress.WorkbenchSiteProgressService.SiteUpdateJob;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.part.ViewPart;
import java.util.ArrayList;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.KeyFinder;
import edu.unicen.ucrefactoring.analyzer.SequenceAligner;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.analyzer.SimilarityAnalyzer;
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
	private static final String COLOR_PROPIO = "COLOR_PROPIO";

	
	//Text Styles
	private StyleRange refCandidateStyle;
	private StyleRange blueStyle;
	
	//Widgets
	private ListViewer uCList;
	private TextViewer leftViewer;
	private TextViewer rightViewer;
	
	//UseCasesdetectar salto de linea en string java
	private UseCase useCaseA;
	private UseCase useCaseB;
	
	// Actions
	private Action compareAction;
	
	//vars
	private UCRefactoringDetection ucref;
	private UseCaseLabelProvider ucLabel;
	private KeyFinder keyFinder;
	
	
	
	//=========CONSTRUCTOR======================

	public UCView() {		
	}


	//===============G&S===========================

	public ListViewer getuCList() {
		return uCList;
	}


	public void setuCList(ListViewer uCList) {
		this.uCList = uCList;
	}


	public UCRefactoringDetection getUcref() {
		return ucref;
	}


	public void setUcref(UCRefactoringDetection ucref) {
		this.ucref = ucref;
	}


	public KeyFinder getKeyFinder() {
		return keyFinder;
	}


	public void setKeyFinder(KeyFinder keyFinder) {
		this.keyFinder = keyFinder;
	}

	//==============Servicios==========================
	
	
	/**
	 * Método que inicia el plugin
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		//Providers
		ucref = new UCRefactoringDetection(false);
		ucLabel = new UseCaseLabelProvider();
		
		ucref.compareUseCases();
		
		// Creamos layout
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		fillLayout.spacing=2;
		parent.setLayout(fillLayout);
		
		//Creamos los widgets
		uCList = new ListViewer(parent, SWT.MULTI | SWT.BORDER );
		uCList.setContentProvider(ucref);
		uCList.setLabelProvider(ucLabel);
		uCList.setInput(ucref);
		leftViewer = new TextViewer(parent , SWT.V_SCROLL | SWT.BORDER );
		rightViewer = new TextViewer(parent , SWT.V_SCROLL | SWT.BORDER);
		leftViewer.getTextWidget().setEditable(false);
		rightViewer.getTextWidget().setEditable(false);
		
        //Listener de cambio de selección en la lista(Double click)
		uCList.addSelectionChangedListener( new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) uCList.getSelection();
				
				//Si elegi uno solo, es el A
				if (selection.size()==1) useCaseA = (UseCase)selection.toList().get(0);
				//cuando tengo 2, seteo el B
				else if (selection.size()==2){
					if (!(((UseCase)(selection.toList().get(0))).getName().equals(useCaseA.getName())) && !(((UseCase)(selection.toList().get(1))).getName().equals(useCaseA.getName())))
						//deseleccione el A, lo cambio
						useCaseA = (UseCase)(selection.toList().get(0));
					//Como están ordenados alfabeticamente, pregunto cual de los dos no es el que ya utilice
					if ((((UseCase)(selection.toList().get(0))).getName().equals(useCaseA.getName()))) 
						//si el primero es el que utilice en A, uso el segundo
						useCaseB =  (UseCase)(selection.toList().get(1));
					else 
						//si no es el primero, uso ese
						useCaseB =  (UseCase)(selection.toList().get(0));
				}
				
				//TODO: ARREGLAR ORDEN DE USE CASES, SE ROMPE EN CIERTOS CASOS
				//Creo el primer y segundo elemento a comparar. utilizado para conservar orden.
//				if (useCaseA == null) useCaseA=(UseCase)selection.getFirstElement();
//				else if (useCaseB == null) useCaseB=(selection.getFirstElement().equals(useCaseA)&&selection.size()==2)?(UseCase)selection.toList().get(1):(UseCase)selection.getFirstElement();
//				else{
//					useCaseA=(UseCase)selection.getFirstElement();
//					useCaseB=null;
//				}
//				
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
				
				HashMap<String,AlignmentX2Result> alignResults = ucref.getSimilarityAnalizer().getAlignmentResult();			
				String key;
				if(useCaseA.getName().compareTo(useCaseB.getName())>0){
					key = useCaseA.getName()+":"+"Basic Flow" + "&" + useCaseB.getName()+":"+"Basic Flow";
				}
				else{
					key = useCaseB.getName()+":"+"Basic Flow" + "&" + useCaseA.getName()+":"+"Basic Flow";
				}
				AlignmentX2Result alignResult = alignResults.get(key);
				
				if (useCaseA != null && useCaseB != null){
					setUseCaseContentToTextViewer(leftViewer,useCaseA,alignResult.getSimilarBlocksFromA());
					setUseCaseContentToTextViewer(rightViewer,useCaseB,alignResult.getSimilarBlocksFromB());
				}
				
				//===Prueba del Key Finder===TODO: DECIDIR SI LO SACAMOS!!!
//				keyFinder = new KeyFinder();
//				List<String> test = new ArrayList<String>();
//				test.add("client");test.add("person");test.add("system");
//				keyFinder.method(test);
				//===========================
								
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
	public void setUseCaseContentToTextViewer(TextViewer textViewer , UseCase useCase, List<SimilarBlock> similarBlocks){

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
		
		int[] colors = {SWT.COLOR_RED,SWT.COLOR_BLUE,SWT.COLOR_GREEN,SWT.COLOR_YELLOW,SWT.COLOR_GRAY};
		int s = 0;
		for (SimilarBlock sb : similarBlocks){
			int startEvent=sb.getBeginIndex()+1;
			int endEvent=sb.getEndIndex()+1;
			int shift = 0;
			for (int i= (result.indexOf("#"+endEvent));i<result.length()&&!result.substring(i, i+1).equals("\n");i++){
				shift++;
			}
			
			//==Aplico un estilo al texto similar==
			StyleRange aStyle = new StyleRange();
			aStyle.fontStyle = SWT.BOLD;
			aStyle.foreground=Display.getCurrent().getSystemColor(s<5?colors[s]:SWT.COLOR_MAGENTA);
			
			aStyle.start=result.indexOf("#"+startEvent);
			aStyle.length=(result.indexOf("#"+endEvent))-aStyle.start+shift;
			
			textViewer.getTextWidget().setStyleRange(aStyle);
			s++;
			//====================================
		}
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
