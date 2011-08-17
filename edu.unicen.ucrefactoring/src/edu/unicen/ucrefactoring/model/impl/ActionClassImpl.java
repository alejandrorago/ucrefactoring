/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.ActionClass;
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
 * An implementation of the model object '<em><b>Action Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getConfidence <em>Confidence</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getRanking <em>Ranking</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getChilds <em>Childs</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl#getPredicate <em>Predicate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionClassImpl extends EObjectImpl implements ActionClass {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfidence() <em>Confidence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfidence()
	 * @generated
	 * @ordered
	 */
	protected static final Double CONFIDENCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConfidence() <em>Confidence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfidence()
	 * @generated
	 * @ordered
	 */
	protected Double confidence = CONFIDENCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRanking() <em>Ranking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRanking()
	 * @generated
	 * @ordered
	 */
	protected static final Integer RANKING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRanking() <em>Ranking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRanking()
	 * @generated
	 * @ordered
	 */
	protected Integer ranking = RANKING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected ActionClass parent;

	/**
	 * The cached value of the '{@link #getChilds() <em>Childs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChilds()
	 * @generated
	 * @ordered
	 */
	protected EList<ActionClass> childs;

	/**
	 * The default value of the '{@link #getPredicate() <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected static final String PREDICATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPredicate() <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredicate()
	 * @generated
	 * @ordered
	 */
	protected String predicate = PREDICATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.ACTION_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Double getConfidence() {
		return confidence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfidence(Double newConfidence) {
		Double oldConfidence = confidence;
		confidence = newConfidence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__CONFIDENCE, oldConfidence, confidence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRanking() {
		return ranking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRanking(Integer newRanking) {
		Integer oldRanking = ranking;
		ranking = newRanking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__RANKING, oldRanking, ranking));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionClass getParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ActionClass newParent, NotificationChain msgs) {
		ActionClass oldParent = parent;
		parent = newParent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__PARENT, oldParent, newParent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(ActionClass newParent) {
		if (newParent != parent) {
			NotificationChain msgs = null;
			if (parent != null)
				msgs = ((InternalEObject)parent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UCRefactoringPackage.ACTION_CLASS__PARENT, null, msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UCRefactoringPackage.ACTION_CLASS__PARENT, null, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActionClass> getChilds() {
		if (childs == null) {
			childs = new EObjectContainmentEList<ActionClass>(ActionClass.class, this, UCRefactoringPackage.ACTION_CLASS__CHILDS);
		}
		return childs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPredicate() {
		return predicate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPredicate(String newPredicate) {
		String oldPredicate = predicate;
		predicate = newPredicate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.ACTION_CLASS__PREDICATE, oldPredicate, predicate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UCRefactoringPackage.ACTION_CLASS__PARENT:
				return basicSetParent(null, msgs);
			case UCRefactoringPackage.ACTION_CLASS__CHILDS:
				return ((InternalEList<?>)getChilds()).basicRemove(otherEnd, msgs);
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
			case UCRefactoringPackage.ACTION_CLASS__NAME:
				return getName();
			case UCRefactoringPackage.ACTION_CLASS__CONFIDENCE:
				return getConfidence();
			case UCRefactoringPackage.ACTION_CLASS__RANKING:
				return getRanking();
			case UCRefactoringPackage.ACTION_CLASS__PARENT:
				return getParent();
			case UCRefactoringPackage.ACTION_CLASS__CHILDS:
				return getChilds();
			case UCRefactoringPackage.ACTION_CLASS__PREDICATE:
				return getPredicate();
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
			case UCRefactoringPackage.ACTION_CLASS__NAME:
				setName((String)newValue);
				return;
			case UCRefactoringPackage.ACTION_CLASS__CONFIDENCE:
				setConfidence((Double)newValue);
				return;
			case UCRefactoringPackage.ACTION_CLASS__RANKING:
				setRanking((Integer)newValue);
				return;
			case UCRefactoringPackage.ACTION_CLASS__PARENT:
				setParent((ActionClass)newValue);
				return;
			case UCRefactoringPackage.ACTION_CLASS__CHILDS:
				getChilds().clear();
				getChilds().addAll((Collection<? extends ActionClass>)newValue);
				return;
			case UCRefactoringPackage.ACTION_CLASS__PREDICATE:
				setPredicate((String)newValue);
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
			case UCRefactoringPackage.ACTION_CLASS__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UCRefactoringPackage.ACTION_CLASS__CONFIDENCE:
				setConfidence(CONFIDENCE_EDEFAULT);
				return;
			case UCRefactoringPackage.ACTION_CLASS__RANKING:
				setRanking(RANKING_EDEFAULT);
				return;
			case UCRefactoringPackage.ACTION_CLASS__PARENT:
				setParent((ActionClass)null);
				return;
			case UCRefactoringPackage.ACTION_CLASS__CHILDS:
				getChilds().clear();
				return;
			case UCRefactoringPackage.ACTION_CLASS__PREDICATE:
				setPredicate(PREDICATE_EDEFAULT);
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
			case UCRefactoringPackage.ACTION_CLASS__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UCRefactoringPackage.ACTION_CLASS__CONFIDENCE:
				return CONFIDENCE_EDEFAULT == null ? confidence != null : !CONFIDENCE_EDEFAULT.equals(confidence);
			case UCRefactoringPackage.ACTION_CLASS__RANKING:
				return RANKING_EDEFAULT == null ? ranking != null : !RANKING_EDEFAULT.equals(ranking);
			case UCRefactoringPackage.ACTION_CLASS__PARENT:
				return parent != null;
			case UCRefactoringPackage.ACTION_CLASS__CHILDS:
				return childs != null && !childs.isEmpty();
			case UCRefactoringPackage.ACTION_CLASS__PREDICATE:
				return PREDICATE_EDEFAULT == null ? predicate != null : !PREDICATE_EDEFAULT.equals(predicate);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", confidence: ");
		result.append(confidence);
		result.append(", ranking: ");
		result.append(ranking);
		result.append(", predicate: ");
		result.append(predicate);
		result.append(')');
		return result.toString();
	}

} //ActionClassImpl
