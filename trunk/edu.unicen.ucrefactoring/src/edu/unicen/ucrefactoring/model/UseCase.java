/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Use Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getName <em>Name</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getDescription <em>Description</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getContext <em>Context</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getSecondaryActors <em>Secondary Actors</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getPrimaryActor <em>Primary Actor</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getFlows <em>Flows</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.UseCase#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase()
 * @model
 * @generated
 */
public interface UseCase extends EObject {
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
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.UseCase#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.UseCase#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference.
	 * @see #setContext(Context)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_Context()
	 * @model containment="true"
	 * @generated
	 */
	Context getContext();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.UseCase#getContext <em>Context</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' containment reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(Context value);

	/**
	 * Returns the value of the '<em><b>Secondary Actors</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secondary Actors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secondary Actors</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_SecondaryActors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getSecondaryActors();

	/**
	 * Returns the value of the '<em><b>Primary Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primary Actor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primary Actor</em>' reference.
	 * @see #setPrimaryActor(Actor)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_PrimaryActor()
	 * @model
	 * @generated
	 */
	Actor getPrimaryActor();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.UseCase#getPrimaryActor <em>Primary Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primary Actor</em>' reference.
	 * @see #getPrimaryActor()
	 * @generated
	 */
	void setPrimaryActor(Actor value);

	/**
	 * Returns the value of the '<em><b>Flows</b></em>' containment reference list.
	 * The list contents are of type {@link edu.unicen.ucrefactoring.model.Flow}.
	 * It is bidirectional and its opposite is '{@link edu.unicen.ucrefactoring.model.Flow#getUseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flows</em>' containment reference list.
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_Flows()
	 * @see edu.unicen.ucrefactoring.model.Flow#getUseCase
	 * @model opposite="useCase" containment="true"
	 * @generated
	 */
	EList<Flow> getFlows();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(UseCase)
	 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage#getUseCase_Parent()
	 * @model
	 * @generated
	 */
	UseCase getParent();

	/**
	 * Sets the value of the '{@link edu.unicen.ucrefactoring.model.UseCase#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(UseCase value);
	
	/**
	 * Returns the basic flow
	 * @return
	 */
	Flow getBasicFlow();
	
	String getFullDescription();
	
	List<UseCase> getIncludedUseCases();
	
	List<UseCase> getExtendedUseCases();
	
	void addExtendedUC(UseCase uc);

} // UseCase
