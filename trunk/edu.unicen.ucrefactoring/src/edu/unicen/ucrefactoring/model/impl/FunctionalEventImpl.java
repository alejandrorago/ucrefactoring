/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.Actor;
import edu.unicen.ucrefactoring.model.EventTypeEnum;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.FunctionalEvent;
import edu.unicen.ucrefactoring.model.JointPoint;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functional Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl#getJointPoints <em>Joint Points</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl#getType <em>Type</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl#getOtherActors <em>Other Actors</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionalEventImpl extends EventImpl implements FunctionalEvent {
	/**
	 * The cached value of the '{@link #getJointPoints() <em>Joint Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJointPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<JointPoint> jointPoints;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final EventTypeEnum TYPE_EDEFAULT = EventTypeEnum.STIMULUS;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EventTypeEnum type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubject() <em>Subject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject()
	 * @generated
	 * @ordered
	 */
	protected Actor subject;

	/**
	 * The cached value of the '{@link #getOtherActors() <em>Other Actors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOtherActors()
	 * @generated
	 * @ordered
	 */
	protected EList<Actor> otherActors;

	/**
	 * The cached value of the '{@link #getExceptions() <em>Exceptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Flow> exceptions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionalEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.FUNCTIONAL_EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JointPoint> getJointPoints() {
		if (jointPoints == null) {
			jointPoints = new EObjectContainmentEList<JointPoint>(JointPoint.class, this, UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS);
		}
		return jointPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventTypeEnum getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EventTypeEnum newType) {
		EventTypeEnum oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FUNCTIONAL_EVENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getSubject() {
		if (subject != null && subject.eIsProxy()) {
			InternalEObject oldSubject = (InternalEObject)subject;
			subject = (Actor)eResolveProxy(oldSubject);
			if (subject != oldSubject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT, oldSubject, subject));
			}
		}
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetSubject() {
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject(Actor newSubject) {
		Actor oldSubject = subject;
		subject = newSubject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT, oldSubject, subject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Actor> getOtherActors() {
		if (otherActors == null) {
			otherActors = new EObjectContainmentEList<Actor>(Actor.class, this, UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS);
		}
		return otherActors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Flow> getExceptions() {
		if (exceptions == null) {
			exceptions = new EObjectContainmentEList<Flow>(Flow.class, this, UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS);
		}
		return exceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS:
				return ((InternalEList<?>)getJointPoints()).basicRemove(otherEnd, msgs);
			case UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS:
				return ((InternalEList<?>)getOtherActors()).basicRemove(otherEnd, msgs);
			case UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS:
				return ((InternalEList<?>)getExceptions()).basicRemove(otherEnd, msgs);
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
			case UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS:
				return getJointPoints();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__TYPE:
				return getType();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT:
				if (resolve) return getSubject();
				return basicGetSubject();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS:
				return getOtherActors();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS:
				return getExceptions();
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
			case UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS:
				getJointPoints().clear();
				getJointPoints().addAll((Collection<? extends JointPoint>)newValue);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__TYPE:
				setType((EventTypeEnum)newValue);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT:
				setSubject((Actor)newValue);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS:
				getOtherActors().clear();
				getOtherActors().addAll((Collection<? extends Actor>)newValue);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS:
				getExceptions().clear();
				getExceptions().addAll((Collection<? extends Flow>)newValue);
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
			case UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS:
				getJointPoints().clear();
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT:
				setSubject((Actor)null);
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS:
				getOtherActors().clear();
				return;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS:
				getExceptions().clear();
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
			case UCRefactoringPackage.FUNCTIONAL_EVENT__JOINT_POINTS:
				return jointPoints != null && !jointPoints.isEmpty();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__TYPE:
				return type != TYPE_EDEFAULT;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__SUBJECT:
				return subject != null;
			case UCRefactoringPackage.FUNCTIONAL_EVENT__OTHER_ACTORS:
				return otherActors != null && !otherActors.isEmpty();
			case UCRefactoringPackage.FUNCTIONAL_EVENT__EXCEPTIONS:
				return exceptions != null && !exceptions.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //FunctionalEventImpl
