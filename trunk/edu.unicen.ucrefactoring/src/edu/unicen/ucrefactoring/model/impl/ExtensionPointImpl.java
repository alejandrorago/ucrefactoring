/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.ActionClass;
import edu.unicen.ucrefactoring.model.ExtensionPoint;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;
import edu.unicen.ucrefactoring.model.UseCase;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extension Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl#getActionClass <em>Action Class</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl#getExcludedUseCases <em>Excluded Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtensionPointImpl extends EventImpl implements ExtensionPoint {
	/**
	 * The default value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected String condition = CONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getActionClass() <em>Action Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionClass()
	 * @generated
	 * @ordered
	 */
	protected ActionClass actionClass;

	/**
	 * The cached value of the '{@link #getExcludedUseCases() <em>Excluded Use Cases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExcludedUseCases()
	 * @generated
	 * @ordered
	 */
	protected EList<UseCase> excludedUseCases;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtensionPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.EXTENSION_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(String newCondition) {
		String oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.EXTENSION_POINT__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionClass getActionClass() {
		if (actionClass != null && actionClass.eIsProxy()) {
			InternalEObject oldActionClass = (InternalEObject)actionClass;
			actionClass = (ActionClass)eResolveProxy(oldActionClass);
			if (actionClass != oldActionClass) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS, oldActionClass, actionClass));
			}
		}
		return actionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionClass basicGetActionClass() {
		return actionClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActionClass(ActionClass newActionClass) {
		ActionClass oldActionClass = actionClass;
		actionClass = newActionClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS, oldActionClass, actionClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UseCase> getExcludedUseCases() {
		if (excludedUseCases == null) {
			excludedUseCases = new EObjectResolvingEList<UseCase>(UseCase.class, this, UCRefactoringPackage.EXTENSION_POINT__EXCLUDED_USE_CASES);
		}
		return excludedUseCases;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.EXTENSION_POINT__CONDITION:
				return getCondition();
			case UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS:
				if (resolve) return getActionClass();
				return basicGetActionClass();
			case UCRefactoringPackage.EXTENSION_POINT__EXCLUDED_USE_CASES:
				return getExcludedUseCases();
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
			case UCRefactoringPackage.EXTENSION_POINT__CONDITION:
				setCondition((String)newValue);
				return;
			case UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS:
				setActionClass((ActionClass)newValue);
				return;
			case UCRefactoringPackage.EXTENSION_POINT__EXCLUDED_USE_CASES:
				getExcludedUseCases().clear();
				getExcludedUseCases().addAll((Collection<? extends UseCase>)newValue);
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
			case UCRefactoringPackage.EXTENSION_POINT__CONDITION:
				setCondition(CONDITION_EDEFAULT);
				return;
			case UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS:
				setActionClass((ActionClass)null);
				return;
			case UCRefactoringPackage.EXTENSION_POINT__EXCLUDED_USE_CASES:
				getExcludedUseCases().clear();
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
			case UCRefactoringPackage.EXTENSION_POINT__CONDITION:
				return CONDITION_EDEFAULT == null ? condition != null : !CONDITION_EDEFAULT.equals(condition);
			case UCRefactoringPackage.EXTENSION_POINT__ACTION_CLASS:
				return actionClass != null;
			case UCRefactoringPackage.EXTENSION_POINT__EXCLUDED_USE_CASES:
				return excludedUseCases != null && !excludedUseCases.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (condition: ");
		result.append(condition);
		result.append(')');
		return result.toString();
	}

} //ExtensionPointImpl
