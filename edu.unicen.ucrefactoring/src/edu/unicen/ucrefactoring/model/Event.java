/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.Event#getDetail <em>Detail</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Event#getNumber <em>Number</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Event#getEventId <em>Event Id</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Event#getAffectedByJoinPoint <em>Affected By Join Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getEvent()
 * @model abstract="true"
 * @generated
 */
public interface Event extends EObject {
	/**
	 * Returns the value of the '<em><b>Detail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detail</em>' attribute.
	 * @see #setDetail(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getEvent_Detail()
	 * @model
	 * @generated
	 */
	String getDetail();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Event#getDetail <em>Detail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Detail</em>' attribute.
	 * @see #getDetail()
	 * @generated
	 */
	void setDetail(String value);

	/**
	 * Returns the value of the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number</em>' attribute.
	 * @see #setNumber(Integer)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getEvent_Number()
	 * @model
	 * @generated
	 */
	Integer getNumber();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Event#getNumber <em>Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number</em>' attribute.
	 * @see #getNumber()
	 * @generated
	 */
	void setNumber(Integer value);

	/**
	 * Returns the value of the '<em><b>Event Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Id</em>' attribute.
	 * @see #setEventId(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getEvent_EventId()
	 * @model
	 * @generated
	 */
	String getEventId();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Event#getEventId <em>Event Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Id</em>' attribute.
	 * @see #getEventId()
	 * @generated
	 */
	void setEventId(String value);
	
	/**
	 * Returns the value of the '<em><b>Affected By Join Point</b></em>' reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.JointPoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affected By Join Point</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affected By Join Point</em>' reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getEvent_AffectedByJoinPoint()
	 * @model
	 * @generated
	 */
	EList<JointPoint> getAffectedByJoinPoint();

	// PAU: ad-hoc
	
	public Event cloneEvent();

} // Event
