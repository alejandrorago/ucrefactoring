/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.Condition;
import edu.unicen.ucrefactoring.model.Context;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ContextImpl#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ContextImpl#getPostconditions <em>Postconditions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContextImpl extends EObjectImpl implements Context {
	/**
	 * The cached value of the '{@link #getPreconditions() <em>Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Condition> preconditions;

	/**
	 * The cached value of the '{@link #getPostconditions() <em>Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Condition> postconditions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Condition> getPreconditions() {
		if (preconditions == null) {
			preconditions = new EObjectContainmentEList<Condition>(Condition.class, this, UCRefactoringPackage.CONTEXT__PRECONDITIONS);
		}
		return preconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Condition> getPostconditions() {
		if (postconditions == null) {
			postconditions = new EObjectContainmentEList<Condition>(Condition.class, this, UCRefactoringPackage.CONTEXT__POSTCONDITIONS);
		}
		return postconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UCRefactoringPackage.CONTEXT__PRECONDITIONS:
				return ((InternalEList<?>)getPreconditions()).basicRemove(otherEnd, msgs);
			case UCRefactoringPackage.CONTEXT__POSTCONDITIONS:
				return ((InternalEList<?>)getPostconditions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.CONTEXT__PRECONDITIONS:
				return getPreconditions();
			case UCRefactoringPackage.CONTEXT__POSTCONDITIONS:
				return getPostconditions();
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
			case UCRefactoringPackage.CONTEXT__PRECONDITIONS:
				getPreconditions().clear();
				getPreconditions().addAll((Collection<? extends Condition>)newValue);
				return;
			case UCRefactoringPackage.CONTEXT__POSTCONDITIONS:
				getPostconditions().clear();
				getPostconditions().addAll((Collection<? extends Condition>)newValue);
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
			case UCRefactoringPackage.CONTEXT__PRECONDITIONS:
				getPreconditions().clear();
				return;
			case UCRefactoringPackage.CONTEXT__POSTCONDITIONS:
				getPostconditions().clear();
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
			case UCRefactoringPackage.CONTEXT__PRECONDITIONS:
				return preconditions != null && !preconditions.isEmpty();
			case UCRefactoringPackage.CONTEXT__POSTCONDITIONS:
				return postconditions != null && !postconditions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContextImpl
