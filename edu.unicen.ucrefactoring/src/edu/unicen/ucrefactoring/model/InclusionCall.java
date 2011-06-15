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
 * A representation of the model object '<em><b>Inclusion Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.InclusionCall#getIncludedUseCases <em>Included Use Cases</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getInclusionCall()
 * @model
 * @generated
 */
public interface InclusionCall extends Event {
	/**
	 * Returns the value of the '<em><b>Included Use Cases</b></em>' reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.UseCase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Use Cases</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Use Cases</em>' reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getInclusionCall_IncludedUseCases()
	 * @model
	 * @generated
	 */
	EList<UseCase> getIncludedUseCases();

} // InclusionCall
