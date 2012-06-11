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

import edu.isistan.uima.unified.typesystems.nlp.Sentence;
import edu.unicen.ucrefactoring.analyzer.AlignmentX2Result;
import edu.unicen.ucrefactoring.gui.UCRDataView;
import edu.unicen.ucrefactoring.refactorings.Refactoring;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class UCRVisualMarkupProvider extends SimpleMarkupProvider {
	public static Map<UseCaseMember, List<Stripe>> markups = new HashMap<UseCaseMember, List<Stripe>>();
	public static Map<Refactoring, RefactoringMarkupKind> kindMap = new HashMap<Refactoring, RefactoringMarkupKind>();
	
	public static void addResult(UseCaseMember member) {
		for(Refactoring refactoring : UCRDataView.refactorings.values()) {
			if (refactoring.affectsUseCase(member.getUseCase())){
				List<Stripe> stripes = markups.get(member);
				if(stripes == null) {
					stripes = new ArrayList<Stripe>();
					markups.put(member, stripes);
				}
				RefactoringMarkupKind markupKind = kindMap.get(refactoring);
				if(markupKind == null) {
					markupKind = new RefactoringMarkupKind(refactoring);
					kindMap.put(refactoring, markupKind);
				}
			
			
				AlignmentX2Result align = refactoring.getAlignment();
				if (align != null){

					//Stripe stripe = new Stripe(markupKind, member.transformStart(sentence), member.transformOffset(sentence));
					int offset = align.getSimilarBlocksFromA().get(0).getSimilarEvents().get(0).getNumber() * 10;
					int size = align.getSimilarBlocksFromA().get(0).getSimilarEvents().size() * 10;
					Stripe stripe = null;
					Iterator<Stripe> iterator = stripes.iterator();
					while(stripe == null && iterator.hasNext()) {
						Stripe s = iterator.next();
						if(s.getOffset() == offset)
							stripe = s;
					}
					if(stripe == null) {
						stripe = new Stripe(markupKind, offset, size);
						stripes.add(stripe);
					}
					else
						if(!stripe.getKinds().contains(markupKind))
							stripe.getKinds().add(markupKind);	
				}
			}
		}
	}
	
	public static void resetMarkups() {
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