/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.EventImpl#getDetail <em>Detail</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.EventImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.EventImpl#getEventId <em>Event Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EventImpl extends EObjectImpl implements Event {
	/**
	 * The default value of the '{@link #getDetail() <em>Detail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetail()
	 * @generated
	 * @ordered
	 */
	protected static final String DETAIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDetail() <em>Detail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDetail()
	 * @generated
	 * @ordered
	 */
	protected String detail = DETAIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected static final Integer NUMBER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumber()
	 * @generated
	 * @ordered
	 */
	protected Integer number = NUMBER_EDEFAULT;

	/**
	 * The default value of the '{@link #getEventId() <em>Event Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventId()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEventId() <em>Event Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventId()
	 * @generated
	 * @ordered
	 */
	protected String eventId = EVENT_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.EVENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDetail(String newDetail) {
		String oldDetail = detail;
		detail = newDetail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.EVENT__DETAIL, oldDetail, detail));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumber(Integer newNumber) {
		Integer oldNumber = number;
		number = newNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.EVENT__NUMBER, oldNumber, number));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventId(String newEventId) {
		String oldEventId = eventId;
		eventId = newEventId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.EVENT__EVENT_ID, oldEventId, eventId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.EVENT__DETAIL:
				return getDetail();
			case UCRefactoringPackage.EVENT__NUMBER:
				return getNumber();
			case UCRefactoringPackage.EVENT__EVENT_ID:
				return getEventId();
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
			case UCRefactoringPackage.EVENT__DETAIL:
				setDetail((String)newValue);
				return;
			case UCRefactoringPackage.EVENT__NUMBER:
				setNumber((Integer)newValue);
				return;
			case UCRefactoringPackage.EVENT__EVENT_ID:
				setEventId((String)newValue);
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
			case UCRefactoringPackage.EVENT__DETAIL:
				setDetail(DETAIL_EDEFAULT);
				return;
			case UCRefactoringPackage.EVENT__NUMBER:
				setNumber(NUMBER_EDEFAULT);
				return;
			case UCRefactoringPackage.EVENT__EVENT_ID:
				setEventId(EVENT_ID_EDEFAULT);
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
			case UCRefactoringPackage.EVENT__DETAIL:
				return DETAIL_EDEFAULT == null ? detail != null : !DETAIL_EDEFAULT.equals(detail);
			case UCRefactoringPackage.EVENT__NUMBER:
				return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
			case UCRefactoringPackage.EVENT__EVENT_ID:
				return EVENT_ID_EDEFAULT == null ? eventId != null : !EVENT_ID_EDEFAULT.equals(eventId);
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
		result.append(" (detail: ");
		result.append(detail);
		result.append(", number: ");
		result.append(number);
		result.append(", eventId: ");
		result.append(eventId);
		result.append(')');
		return result.toString();
	}

} //EventImpl
