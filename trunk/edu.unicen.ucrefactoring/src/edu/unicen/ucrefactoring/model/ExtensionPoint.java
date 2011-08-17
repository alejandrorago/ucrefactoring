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
 * A representation of the model object '<em><b>Extension Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.ExtensionPoint#getCondition <em>Condition</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.ExtensionPoint#getExtendedUseCases <em>Extended Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getExtensionPoint()
 * @model
 * @generated
 */
public interface ExtensionPoint extends Event {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' attribute.
	 * @see #setCondition(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getExtensionPoint_Condition()
	 * @model
	 * @generated
	 */
	String getCondition();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.ExtensionPoint#getCondition <em>Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' attribute.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(String value);

	/**
	 * Returns the value of the '<em><b>Extended Use Cases</b></em>' reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Use Cases</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Use Cases</em>' reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getExtensionPoint_ExtendedUseCases()
	 * @model
	 * @generated
	 */
	EList<UseCase> getExtendedUseCases();

} // ExtensionPoint
