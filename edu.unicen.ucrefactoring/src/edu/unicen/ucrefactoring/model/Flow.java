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
 * A representation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.Flow#getName <em>Name</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Flow#getEvents <em>Events</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Flow#getUseCase <em>Use Case</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Flow#getParentFlow <em>Parent Flow</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.Flow#getReturnEvent <em>Return Event</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow()
 * @model
 * @generated
 */
public interface Flow extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Flow#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow_Events()
	 * @model containment="true"
	 *        extendedMetaData="name='events'"
	 * @generated
	 */
	EList<Event> getEvents();

	/**
	 * Returns the value of the '<em><b>Use Case</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Case</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Case</em>' reference.
	 * @see #setUseCase(UseCase)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow_UseCase()
	 * @model required="true"
	 * @generated
	 */
	UseCase getUseCase();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Flow#getUseCase <em>Use Case</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Case</em>' reference.
	 * @see #getUseCase()
	 * @generated
	 */
	void setUseCase(UseCase value);

	/**
	 * Returns the value of the '<em><b>Parent Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Flow</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Flow</em>' reference.
	 * @see #setParentFlow(Flow)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow_ParentFlow()
	 * @model
	 * @generated
	 */
	Flow getParentFlow();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Flow#getParentFlow <em>Parent Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Flow</em>' reference.
	 * @see #getParentFlow()
	 * @generated
	 */
	void setParentFlow(Flow value);

	/**
	 * Returns the value of the '<em><b>Return Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Event</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Event</em>' reference.
	 * @see #setReturnEvent(Event)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getFlow_ReturnEvent()
	 * @model
	 * @generated
	 */
	Event getReturnEvent();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.Flow#getReturnEvent <em>Return Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Event</em>' reference.
	 * @see #getReturnEvent()
	 * @generated
	 */
	void setReturnEvent(Event value);

} // Flow
