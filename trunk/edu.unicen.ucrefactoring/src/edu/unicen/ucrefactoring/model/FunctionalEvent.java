/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functional Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getJointPoints <em>Joint Points</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getType <em>Type</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getSubject <em>Subject</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getOtherActors <em>Other Actors</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent()
 * @model
 * @generated
 */
public interface FunctionalEvent extends Event {
	/**
	 * Returns the value of the '<em><b>Joint Points</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.JointPoint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Joint Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Joint Points</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent_JointPoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<JointPoint> getJointPoints();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.unicen.ucrefactoring.model.EventTypeEnum}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see edu.unicen.ucrefactoring.model.EventTypeEnum
	 * @see #setType(EventTypeEnum)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent_Type()
	 * @model
	 * @generated
	 */
	EventTypeEnum getType();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see edu.unicen.ucrefactoring.model.EventTypeEnum
	 * @see #getType()
	 * @generated
	 */
	void setType(EventTypeEnum value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' reference.
	 * @see #setSubject(Actor)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent_Subject()
	 * @model
	 * @generated
	 */
	Actor getSubject();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getSubject <em>Subject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' reference.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(Actor value);

	/**
	 * Returns the value of the '<em><b>Other Actors</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other Actors</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent_OtherActors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getOtherActors();

	/**
	 * Returns the value of the '<em><b>Exceptions</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.Flow}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exceptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exceptions</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFunctionalEvent_Exceptions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Flow> getExceptions();

} // FunctionalEvent
