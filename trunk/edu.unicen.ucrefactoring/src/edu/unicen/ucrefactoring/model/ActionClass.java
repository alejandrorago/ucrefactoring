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
 * A representation of the model object '<em><b>Action Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getName <em>Name</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getConfidence <em>Confidence</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getRanking <em>Ranking</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getParent <em>Parent</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getChilds <em>Childs</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ActionClass#getPredicate <em>Predicate</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass()
 * @model
 * @generated
 */
public interface ActionClass extends EObject {
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
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ActionClass#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Confidence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Confidence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Confidence</em>' attribute.
	 * @see #setConfidence(Double)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Confidence()
	 * @model
	 * @generated
	 */
	Double getConfidence();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ActionClass#getConfidence <em>Confidence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confidence</em>' attribute.
	 * @see #getConfidence()
	 * @generated
	 */
	void setConfidence(Double value);

	/**
	 * Returns the value of the '<em><b>Ranking</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ranking</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ranking</em>' attribute.
	 * @see #setRanking(Integer)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Ranking()
	 * @model
	 * @generated
	 */
	Integer getRanking();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ActionClass#getRanking <em>Ranking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ranking</em>' attribute.
	 * @see #getRanking()
	 * @generated
	 */
	void setRanking(Integer value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' containment reference.
	 * @see #setParent(ActionClass)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Parent()
	 * @model containment="true"
	 * @generated
	 */
	ActionClass getParent();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ActionClass#getParent <em>Parent</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' containment reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(ActionClass value);

	/**
	 * Returns the value of the '<em><b>Childs</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.ActionClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Childs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Childs</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Childs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ActionClass> getChilds();

	/**
	 * Returns the value of the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predicate</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predicate</em>' attribute.
	 * @see #setPredicate(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getActionClass_Predicate()
	 * @model
	 * @generated
	 */
	String getPredicate();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ActionClass#getPredicate <em>Predicate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Predicate</em>' attribute.
	 * @see #getPredicate()
	 * @generated
	 */
	void setPredicate(String value);

} // ActionClass
