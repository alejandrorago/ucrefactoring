/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Joint Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactWords <em>Impact Words</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactAspect <em>Impact Aspect</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getJointPoint()
 * @model
 * @generated
 */
public interface JointPoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Impact Words</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impact Words</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Impact Words</em>' attribute.
	 * @see #setImpactWords(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getJointPoint_ImpactWords()
	 * @model
	 * @generated
	 */
	String getImpactWords();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactWords <em>Impact Words</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Impact Words</em>' attribute.
	 * @see #getImpactWords()
	 * @generated
	 */
	void setImpactWords(String value);

	/**
	 * Returns the value of the '<em><b>Impact Aspect</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Impact Aspect</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Impact Aspect</em>' reference.
	 * @see #setImpactAspect(Aspect)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getJointPoint_ImpactAspect()
	 * @model
	 * @generated
	 */
	Aspect getImpactAspect();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactAspect <em>Impact Aspect</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Impact Aspect</em>' reference.
	 * @see #getImpactAspect()
	 * @generated
	 */
	void setImpactAspect(Aspect value);

} // JointPoint
