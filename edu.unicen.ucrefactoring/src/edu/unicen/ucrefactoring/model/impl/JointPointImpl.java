/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.Aspect;
import edu.unicen.ucrefactoring.model.JointPoint;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Joint Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.JointPointImpl#getImpactWords <em>Impact Words</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.JointPointImpl#getImpactAspect <em>Impact Aspect</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JointPointImpl extends EObjectImpl implements JointPoint {
	/**
	 * The default value of the '{@link #getImpactWords() <em>Impact Words</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactWords()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPACT_WORDS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImpactWords() <em>Impact Words</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactWords()
	 * @generated
	 * @ordered
	 */
	protected String impactWords = IMPACT_WORDS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getImpactAspect() <em>Impact Aspect</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpactAspect()
	 * @generated
	 * @ordered
	 */
	protected Aspect impactAspect;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JointPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.JOINT_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImpactWords() {
		return impactWords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImpactWords(String newImpactWords) {
		String oldImpactWords = impactWords;
		impactWords = newImpactWords;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.JOINT_POINT__IMPACT_WORDS, oldImpactWords, impactWords));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Aspect getImpactAspect() {
		if (impactAspect != null && impactAspect.eIsProxy()) {
			InternalEObject oldImpactAspect = (InternalEObject)impactAspect;
			impactAspect = (Aspect)eResolveProxy(oldImpactAspect);
			if (impactAspect != oldImpactAspect) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT, oldImpactAspect, impactAspect));
			}
		}
		return impactAspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Aspect basicGetImpactAspect() {
		return impactAspect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImpactAspect(Aspect newImpactAspect) {
		Aspect oldImpactAspect = impactAspect;
		impactAspect = newImpactAspect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT, oldImpactAspect, impactAspect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.JOINT_POINT__IMPACT_WORDS:
				return getImpactWords();
			case UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT:
				if (resolve) return getImpactAspect();
				return basicGetImpactAspect();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UCRefactoringPackage.JOINT_POINT__IMPACT_WORDS:
				setImpactWords((String)newValue);
				return;
			case UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT:
				setImpactAspect((Aspect)newValue);
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
			case UCRefactoringPackage.JOINT_POINT__IMPACT_WORDS:
				setImpactWords(IMPACT_WORDS_EDEFAULT);
				return;
			case UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT:
				setImpactAspect((Aspect)null);
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
			case UCRefactoringPackage.JOINT_POINT__IMPACT_WORDS:
				return IMPACT_WORDS_EDEFAULT == null ? impactWords != null : !IMPACT_WORDS_EDEFAULT.equals(impactWords);
			case UCRefactoringPackage.JOINT_POINT__IMPACT_ASPECT:
				return impactAspect != null;
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
		result.append(" (impactWords: ");
		result.append(impactWords);
		result.append(')');
		return result.toString();
	}

} //JointPointImpl
