/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.util;

import edu.unicen.ucrefactoring.model.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.unicen.ucrefactoring.model.UCRefactoringPackage
 * @generated
 */
public class UCRefactoringSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UCRefactoringPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UCRefactoringSwitch() {
		if (modelPackage == null) {
			modelPackage = UCRefactoringPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UCRefactoringPackage.USE_CASE: {
				UseCase useCase = (UseCase)theEObject;
				T result = caseUseCase(useCase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.CONTEXT: {
				Context context = (Context)theEObject;
				T result = caseContext(context);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.USE_CASE_MODEL: {
				UseCaseModel useCaseModel = (UseCaseModel)theEObject;
				T result = caseUseCaseModel(useCaseModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				T result = caseActor(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.FLOW: {
				Flow flow = (Flow)theEObject;
				T result = caseFlow(flow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.EVENT: {
				Event event = (Event)theEObject;
				T result = caseEvent(event);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.FUNCTIONAL_EVENT: {
				FunctionalEvent functionalEvent = (FunctionalEvent)theEObject;
				T result = caseFunctionalEvent(functionalEvent);
				if (result == null) result = caseEvent(functionalEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.EXTENSION_POINT: {
				ExtensionPoint extensionPoint = (ExtensionPoint)theEObject;
				T result = caseExtensionPoint(extensionPoint);
				if (result == null) result = caseEvent(extensionPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.INCLUSION_CALL: {
				InclusionCall inclusionCall = (InclusionCall)theEObject;
				T result = caseInclusionCall(inclusionCall);
				if (result == null) result = caseEvent(inclusionCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.JOINT_POINT: {
				JointPoint jointPoint = (JointPoint)theEObject;
				T result = caseJointPoint(jointPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.ACTION_CLASS: {
				ActionClass actionClass = (ActionClass)theEObject;
				T result = caseActionClass(actionClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.ASPECT: {
				Aspect aspect = (Aspect)theEObject;
				T result = caseAspect(aspect);
				if (result == null) result = caseUseCase(aspect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UCRefactoringPackage.CONDITION: {
				Condition condition = (Condition)theEObject;
				T result = caseCondition(condition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCase(UseCase object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContext(Context object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Use Case Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Use Case Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUseCaseModel(UseCaseModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActor(Actor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFlow(Flow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvent(Event object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Functional Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Functional Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionalEvent(FunctionalEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Extension Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Extension Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExtensionPoint(ExtensionPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Inclusion Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Inclusion Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInclusionCall(InclusionCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Joint Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Joint Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJointPoint(JointPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionClass(ActionClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Aspect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAspect(Aspect object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCondition(Condition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //UCRefactoringSwitch
