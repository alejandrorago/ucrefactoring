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
 * A representation of the model object '<em><b>Aspect</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.Aspect#getCcNames <em>Cc Names</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getAspect()
 * @model
 * @generated
 */
public interface Aspect extends UseCase {

	/**
	 * Returns the value of the '<em><b>Cc Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cc Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cc Names</em>' attribute list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getAspect_CcNames()
	 * @model
	 * @generated
	 */
	EList<String> getCcNames();

} // Aspect
