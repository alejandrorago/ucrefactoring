package edu.unicen.ucrefactoring.visualiser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.contribution.visualiser.core.Stripe;
import org.eclipse.contribution.visualiser.interfaces.IGroup;
import org.eclipse.contribution.visualiser.interfaces.IMember;
import org.eclipse.contribution.visualiser.simpleImpl.SimpleMarkupProvider;

import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.analyzer.SimilarBlock;
import edu.unicen.ucrefactoring.gui.UCRDataView;
import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UseCase;
import edu.unicen.ucrefactoring.refactorings.ExtractAspectRefactoring;
import edu.unicen.ucrefactoring.refactorings.MergeUseCasesRefactoring;
import edu.unicen.ucrefactoring.refactorings.Refactoring;

/**
 * Create and add stripes to use cases according refactorings
 * @author migue
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UCRVisualMarkupProvider2 extends SimpleMarkupProvider  {
	public static Map<UseCaseMember, List<Stripe>> markups = new HashMap<UseCaseMember, List<Stripe>>();
	public static Map<Refactoring, RefactoringMarkupKind> kindMap = new HashMap<Refactoring, RefactoringMarkupKind>();

	
	public  void addResult(UseCaseMember member) {
		for (Refactoring refactoring : UCRDataView.refactorings.values()) {
			//1
			if (refactoring.getAlignment()!= null){
				if (refactoring.affectsUseCase(member.getUseCase())) {
					List<Stripe> stripes = markups.get(member);
					if (stripes == null) {
						stripes = new ArrayList<Stripe>();
						markups.put(member, stripes);
					}
					RefactoringMarkupKind markupKind = kindMap.get(refactoring);
					if (markupKind == null) {
						markupKind = new RefactoringMarkupKind(refactoring);
						//Color kindColor = new Color(Display.getCurrent(), ((Double)(refactoring.getScore()*2.5)).intValue(), 0, 0);
						//setColorFor(markupKind, kindColor);
						kindMap.put(refactoring, markupKind);
					}
					AlignmentX2Result align = refactoring.getAlignment();
						
					Flow flow = null;
					
					// get events & flow
					List<Event> similarEvents = new ArrayList<Event>();
					if (align.getUseCaseA().getName().equals(member.getUseCase().getName())) {
						for (SimilarBlock similarBlock : align
								.getSimilarBlocksFromA())
							similarEvents.addAll(similarBlock
									.getSimilarEvents());
							flow = align.getFlowA();
					} else {
						for (SimilarBlock similarBlock : align
								.getSimilarBlocksFromB())
							similarEvents.addAll(similarBlock
									.getSimilarEvents());
							flow = align.getFlowB();
					}

					// add strip for each event
					for (Event event : similarEvents) {
						// Stripe stripe = new Stripe(markupKind,
						// member.transformStart(sentence),
						// member.transformOffset(sentence));
						int offset = 0;
						if (!flow.getName().equals("Basic Flow")) {
							int startPosition = member.getUseCase().getBasicFlow().getEvents().size() -1;
							startPosition += event.getNumber();
							offset = (startPosition) * 10;
						} else {
							offset = (event.getNumber() - 1) * 10;
						}

						int size = 10;
						Stripe stripe = null;
						Iterator<Stripe> iterator = stripes.iterator();
						while (stripe == null && iterator.hasNext()) {
							Stripe s = iterator.next();
							if (s.getOffset() == offset)
								stripe = s;
						}
						if (stripe == null) {
							stripe = new Stripe(markupKind, offset, size);
							stripes.add(stripe);
						} else if (!stripe.getKinds().contains(markupKind))
							stripe.getKinds().add(markupKind);
					}
					
					
				}
				
			}
			//2
			//Refactoring with two UCs
			else if (refactoring.getType().equals(Refactoring.REF_MERGE)){
				if (refactoring.affectsUseCase(member.getUseCase())) {
					List<Stripe> stripes = markups.get(member);
					if (stripes == null) {
						stripes = new ArrayList<Stripe>();
						markups.put(member, stripes);
					}
					RefactoringMarkupKind markupKind = kindMap.get(refactoring);
					if (markupKind == null) {
						markupKind = new RefactoringMarkupKind(refactoring);
						//Color kindColor = new Color(Display.getCurrent(), ((Double)(refactoring.getScore()*2.5)).intValue(), 0, 0);
						//setColorFor(markupKind, kindColor);
						kindMap.put(refactoring, markupKind);
					}	
					UseCase useCaseA = ((MergeUseCasesRefactoring) refactoring).getUseCaseA();
					UseCase useCaseB = ((MergeUseCasesRefactoring) refactoring).getUseCaseB();
					
					UseCase uc;
					
					if (useCaseA.getName().equals(member.getUseCase().getName())) {
						uc = useCaseA;
					} else {
						uc = useCaseB;
					}
	
					// add strip for each event
					for (Flow flow : uc.getFlows()) {
						for (Event event : flow.getEvents()) {
							// Stripe stripe = new Stripe(markupKind,
							// member.transformStart(sentence),
							// member.transformOffset(sentence));
							int offset = 0;
							if (!flow.getName().equals("Basic Flow")) {
								int startPosition = member.getUseCase().getBasicFlow().getEvents().size() -1;
								startPosition += event.getNumber();
								offset = (startPosition) * 10;
							} else {
								offset = (event.getNumber() - 1) * 10;
							}
	
							int size = 10;
							Stripe stripe = null;
							Iterator<Stripe> iterator = stripes.iterator();
							while (stripe == null && iterator.hasNext()) {
								Stripe s = iterator.next();
								if (s.getOffset() == offset)
									stripe = s;
							}
							if (stripe == null) {
								stripe = new Stripe(markupKind, offset, size);
								stripes.add(stripe);
							} else if (!stripe.getKinds().contains(markupKind))
								stripe.getKinds().add(markupKind);
						}
					}
				}
			}
			//3
			//Refactorings with only 1 UC
			else if (refactoring.getType().equals(Refactoring.REF_DELETE_UC) || refactoring.getType().equals(Refactoring.REF_EXTRACT_UC) ){
				if (refactoring.affectsUseCase(member.getUseCase())) {
					List<Stripe> stripes = markups.get(member);
					if (stripes == null) {
						stripes = new ArrayList<Stripe>();
						markups.put(member, stripes);
					}
					RefactoringMarkupKind markupKind = kindMap.get(refactoring);
					if (markupKind == null) {
						markupKind = new RefactoringMarkupKind(refactoring);
						//Color kindColor = new Color(Display.getCurrent(), ((Double)(refactoring.getScore()*2.5)).intValue(), 0, 0);
						//setColorFor(markupKind, kindColor);
						kindMap.put(refactoring, markupKind);
					}	
					UseCase uc = refactoring.getUseCase();
					// add strip for each event
					for (Flow flow : uc.getFlows()) {
						for (Event event : flow.getEvents()) {
							// Stripe stripe = new Stripe(markupKind,
							// member.transformStart(sentence),
							// member.transformOffset(sentence));
							int offset = 0;
							if (!flow.getName().equals("Basic Flow")) {
								int startPosition = member.getUseCase().getBasicFlow().getEvents().size() -1;
								startPosition += event.getNumber();
								offset = (startPosition) * 10;
							} else {
								offset = (event.getNumber() - 1) * 10;
							}
	
							int size = 10;
							Stripe stripe = null;
							Iterator<Stripe> iterator = stripes.iterator();
							while (stripe == null && iterator.hasNext()) {
								Stripe s = iterator.next();
								if (s.getOffset() == offset)
									stripe = s;
							}
							if (stripe == null) {
								stripe = new Stripe(markupKind, offset, size);
								stripes.add(stripe);
							} else if (!stripe.getKinds().contains(markupKind))
								stripe.getKinds().add(markupKind);
						}
					}
				}
			}
			//4
			//Aspect Refactorings
			else if (refactoring.getType().equals(Refactoring.REF_EXTRACT_ASPECT) ){
				if (refactoring.affectsUseCase(member.getUseCase())) {
					List<Stripe> stripes = markups.get(member);
					if (stripes == null) {
						stripes = new ArrayList<Stripe>();
						markups.put(member, stripes);
					}
					RefactoringMarkupKind markupKind = kindMap.get(refactoring);
					if (markupKind == null) {
						markupKind = new RefactoringMarkupKind(refactoring);
						//Color kindColor = new Color(Display.getCurrent(), ((Double)(refactoring.getScore()*2.5)).intValue(), 0, 0);
						//setColorFor(markupKind, kindColor);
						kindMap.put(refactoring, markupKind);
					}
					
					String ccString = ((ExtractAspectRefactoring)refactoring).getCcName();
					//TODO: GET USE CASES AFFECTED LINES TO SHOW THEM
				}
			}
		}
	}

	public  void resetMarkups() {
		markups = new HashMap<UseCaseMember, List<Stripe>>();
		kindMap = new HashMap<Refactoring, RefactoringMarkupKind>();
	}

	@Override
	public SortedSet getAllMarkupKinds() {
		return new TreeSet(kindMap.values());
	}

	@Override
	public List getGroupMarkups(IGroup group) {
		return null;
	}

	@Override
	public List getMemberMarkups(IMember member) {
		return (List) markups.get(member);
	}
}