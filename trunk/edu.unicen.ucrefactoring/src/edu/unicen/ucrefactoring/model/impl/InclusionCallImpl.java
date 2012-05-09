/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.InclusionCall;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;
import edu.unicen.ucrefactoring.model.UseCase;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Inclusion Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.InclusionCallImpl#getIncludedUseCases <em>Included Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InclusionCallImpl extends EventImpl implements InclusionCall {
	/**
	 * The cached value of the '{@link #getIncludedUseCases() <em>Included Use Cases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedUseCases()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCase> includedUseCases;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InclusionCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.INCLUSION_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCase> getIncludedUseCases() {
		if (includedUseCases == null) {
			includedUseCases = new EObjectResolvingEList<UseCase>(UseCase.class, this, UCRefactoringPackage.INCLUSION_CALL__INCLUDED_USE_CASES);
		}
		return includedUseCases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.INCLUSION_CALL__INCLUDED_USE_CASES:
				return getIncludedUseCases();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UCRefactoringPackage.INCLUSION_CALL__INCLUDED_USE_CASES:
				getIncludedUseCases().clear();
				getIncludedUseCases().addAll((Collection<? extends UseCase>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UCRefactoringPackage.INCLUSION_CALL__INCLUDED_USE_CASES:
				getIncludedUseCases().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UCRefactoringPackage.INCLUSION_CALL__INCLUDED_USE_CASES:
				return includedUseCases != null && !includedUseCases.isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
	// PAU: adhoc
	public Event cloneEvent(){
		return null;
	}

} //InclusionCallImpl
