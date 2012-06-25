/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.unicen.ucrefactoring.model.UCRefactoringFactory
 * @model kind="package"
 * @generated
 */
public interface UCRefactoringPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://ucrefactoring/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edu.unicen.ucrefactoring";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UCRefactoringPackage eINSTANCE = edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.UseCaseImpl <em>Use Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.UseCaseImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getUseCase()
	 * @generated
	 */
	int USE_CASE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__CONTEXT = 2;

	/**
	 * The feature id for the '<em><b>Secondary Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__SECONDARY_ACTORS = 3;

	/**
	 * The feature id for the '<em><b>Primary Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__PRIMARY_ACTOR = 4;

	/**
	 * The feature id for the '<em><b>Flows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__FLOWS = 5;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE__PARENT = 6;

	/**
	 * The number of structural features of the '<em>Use Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.ContextImpl <em>Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.ContextImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getContext()
	 * @generated
	 */
	int CONTEXT = 1;

	/**
	 * The feature id for the '<em><b>Preconditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__PRECONDITIONS = 0;

	/**
	 * The feature id for the '<em><b>Postconditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__POSTCONDITIONS = 1;

	/**
	 * The number of structural features of the '<em>Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.UseCaseModelImpl <em>Use Case Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.UseCaseModelImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getUseCaseModel()
	 * @generated
	 */
	int USE_CASE_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL__NAME = 1;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL__ACTORS = 2;

	/**
	 * The feature id for the '<em><b>Use Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL__USE_CASES = 3;

	/**
	 * The feature id for the '<em><b>Aspects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL__ASPECTS = 4;

	/**
	 * The number of structural features of the '<em>Use Case Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_MODEL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.ActorImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.FlowImpl <em>Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.FlowImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getFlow()
	 * @generated
	 */
	int FLOW = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__NAME = 0;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EVENTS = 1;

	/**
	 * The feature id for the '<em><b>Use Case</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__USE_CASE = 2;

	/**
	 * The feature id for the '<em><b>Parent Flow</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PARENT_FLOW = 3;

	/**
	 * The feature id for the '<em><b>Return Event</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RETURN_EVENT = 4;

	/**
	 * The number of structural features of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.EventImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 5;

	/**
	 * The feature id for the '<em><b>Detail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__DETAIL = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>Event Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__EVENT_ID = 2;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl <em>Functional Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getFunctionalEvent()
	 * @generated
	 */
	int FUNCTIONAL_EVENT = 6;

	/**
	 * The feature id for the '<em><b>Detail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__DETAIL = EVENT__DETAIL;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__NUMBER = EVENT__NUMBER;

	/**
	 * The feature id for the '<em><b>Event Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__EVENT_ID = EVENT__EVENT_ID;

	/**
	 * The feature id for the '<em><b>Joint Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__JOINT_POINTS = EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__TYPE = EVENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__SUBJECT = EVENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Other Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__OTHER_ACTORS = EVENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__EXCEPTIONS = EVENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Action Classes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT__ACTION_CLASSES = EVENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Functional Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTIONAL_EVENT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl <em>Extension Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getExtensionPoint()
	 * @generated
	 */
	int EXTENSION_POINT = 7;

	/**
	 * The feature id for the '<em><b>Detail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT__DETAIL = EVENT__DETAIL;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT__NUMBER = EVENT__NUMBER;

	/**
	 * The feature id for the '<em><b>Event Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT__EVENT_ID = EVENT__EVENT_ID;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT__CONDITION = EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extended Use Cases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT__EXTENDED_USE_CASES = EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Extension Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSION_POINT_FEATURE_COUNT = EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.InclusionCallImpl <em>Inclusion Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.InclusionCallImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getInclusionCall()
	 * @generated
	 */
	int INCLUSION_CALL = 8;

	/**
	 * The feature id for the '<em><b>Detail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUSION_CALL__DETAIL = EVENT__DETAIL;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUSION_CALL__NUMBER = EVENT__NUMBER;

	/**
	 * The feature id for the '<em><b>Event Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUSION_CALL__EVENT_ID = EVENT__EVENT_ID;

	/**
	 * The feature id for the '<em><b>Included Use Cases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUSION_CALL__INCLUDED_USE_CASES = EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Inclusion Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INCLUSION_CALL_FEATURE_COUNT = EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.JointPointImpl <em>Joint Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.JointPointImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getJointPoint()
	 * @generated
	 */
	int JOINT_POINT = 9;

	/**
	 * The feature id for the '<em><b>Impact Words</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOINT_POINT__IMPACT_WORDS = 0;

	/**
	 * The feature id for the '<em><b>Impact Aspect</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOINT_POINT__IMPACT_ASPECT = 1;

	/**
	 * The number of structural features of the '<em>Joint Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOINT_POINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl <em>Action Class</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.ActionClassImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActionClass()
	 * @generated
	 */
	int ACTION_CLASS = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__NAME = 0;

	/**
	 * The feature id for the '<em><b>Confidence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__CONFIDENCE = 1;

	/**
	 * The feature id for the '<em><b>Ranking</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__RANKING = 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__PARENT = 3;

	/**
	 * The feature id for the '<em><b>Childs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__CHILDS = 4;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS__PREDICATE = 5;

	/**
	 * The number of structural features of the '<em>Action Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_CLASS_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.AspectImpl <em>Aspect</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.AspectImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getAspect()
	 * @generated
	 */
	int ASPECT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Aspect</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASPECT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.impl.ConditionImpl
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__EREFERENCE0 = 2;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.ActorTypeEnum <em>Actor Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.ActorTypeEnum
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActorTypeEnum()
	 * @generated
	 */
	int ACTOR_TYPE_ENUM = 13;

	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.EventTypeEnum <em>Event Type Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.EventTypeEnum
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getEventTypeEnum()
	 * @generated
	 */
	int EVENT_TYPE_ENUM = 14;


	/**
	 * The meta object id for the '{@link edu.unicen.ucrefactoring.model.ActionCodeEnum <em>Action Code Enum</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.unicen.ucrefactoring.model.ActionCodeEnum
	 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActionCodeEnum()
	 * @generated
	 */
	int ACTION_CODE_ENUM = 15;


	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.UseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase
	 * @generated
	 */
	EClass getUseCase();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.UseCase#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getName()
	 * @see #getUseCase()
	 * @generated
	 */
	EAttribute getUseCase_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.UseCase#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getDescription()
	 * @see #getUseCase()
	 * @generated
	 */
	EAttribute getUseCase_Description();

	/**
	 * Returns the meta object for the containment reference '{@link edu.unicen.ucrefactoring.model.UseCase#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getContext()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_Context();

	/**
	 * Returns the meta object for the reference list '{@link edu.unicen.ucrefactoring.model.UseCase#getSecondaryActors <em>Secondary Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Secondary Actors</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getSecondaryActors()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_SecondaryActors();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.UseCase#getPrimaryActor <em>Primary Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Primary Actor</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getPrimaryActor()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_PrimaryActor();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.UseCase#getFlows <em>Flows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Flows</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getFlows()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_Flows();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.UseCase#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCase#getParent()
	 * @see #getUseCase()
	 * @generated
	 */
	EReference getUseCase_Parent();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Context <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context</em>'.
	 * @see edu.unicen.ucrefactoring.model.Context
	 * @generated
	 */
	EClass getContext();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.Context#getPreconditions <em>Preconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Preconditions</em>'.
	 * @see edu.unicen.ucrefactoring.model.Context#getPreconditions()
	 * @see #getContext()
	 * @generated
	 */
	EReference getContext_Preconditions();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.Context#getPostconditions <em>Postconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Postconditions</em>'.
	 * @see edu.unicen.ucrefactoring.model.Context#getPostconditions()
	 * @see #getContext()
	 * @generated
	 */
	EReference getContext_Postconditions();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.UseCaseModel <em>Use Case Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Model</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel
	 * @generated
	 */
	EClass getUseCaseModel();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.UseCaseModel#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel#getDescription()
	 * @see #getUseCaseModel()
	 * @generated
	 */
	EAttribute getUseCaseModel_Description();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.UseCaseModel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel#getName()
	 * @see #getUseCaseModel()
	 * @generated
	 */
	EAttribute getUseCaseModel_Name();

	/**
	 * Returns the meta object for the reference list '{@link edu.unicen.ucrefactoring.model.UseCaseModel#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actors</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel#getActors()
	 * @see #getUseCaseModel()
	 * @generated
	 */
	EReference getUseCaseModel_Actors();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.UseCaseModel#getUseCases <em>Use Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Use Cases</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel#getUseCases()
	 * @see #getUseCaseModel()
	 * @generated
	 */
	EReference getUseCaseModel_UseCases();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.UseCaseModel#getAspects <em>Aspects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aspects</em>'.
	 * @see edu.unicen.ucrefactoring.model.UseCaseModel#getAspects()
	 * @see #getUseCaseModel()
	 * @generated
	 */
	EReference getUseCaseModel_Aspects();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see edu.unicen.ucrefactoring.model.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Actor#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.Actor#getName()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Actor#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.unicen.ucrefactoring.model.Actor#getDescription()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Description();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Actor#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see edu.unicen.ucrefactoring.model.Actor#getType()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Type();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow
	 * @generated
	 */
	EClass getFlow();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Flow#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow#getName()
	 * @see #getFlow()
	 * @generated
	 */
	EAttribute getFlow_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.Flow#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow#getEvents()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Events();

	/**
	 * Returns the meta object for the container reference '{@link edu.unicen.ucrefactoring.model.Flow#getUseCase <em>Use Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Use Case</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow#getUseCase()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_UseCase();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.Flow#getParentFlow <em>Parent Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Flow</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow#getParentFlow()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_ParentFlow();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.Flow#getReturnEvent <em>Return Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Return Event</em>'.
	 * @see edu.unicen.ucrefactoring.model.Flow#getReturnEvent()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_ReturnEvent();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see edu.unicen.ucrefactoring.model.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Event#getDetail <em>Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Detail</em>'.
	 * @see edu.unicen.ucrefactoring.model.Event#getDetail()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Detail();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Event#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see edu.unicen.ucrefactoring.model.Event#getNumber()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Number();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Event#getEventId <em>Event Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Id</em>'.
	 * @see edu.unicen.ucrefactoring.model.Event#getEventId()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_EventId();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.FunctionalEvent <em>Functional Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Functional Event</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent
	 * @generated
	 */
	EClass getFunctionalEvent();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getJointPoints <em>Joint Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Joint Points</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getJointPoints()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EReference getFunctionalEvent_JointPoints();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getType()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EAttribute getFunctionalEvent_Type();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getSubject()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EReference getFunctionalEvent_Subject();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getOtherActors <em>Other Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Other Actors</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getOtherActors()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EReference getFunctionalEvent_OtherActors();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getExceptions <em>Exceptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exceptions</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getExceptions()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EReference getFunctionalEvent_Exceptions();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.FunctionalEvent#getActionClasses <em>Action Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action Classes</em>'.
	 * @see edu.unicen.ucrefactoring.model.FunctionalEvent#getActionClasses()
	 * @see #getFunctionalEvent()
	 * @generated
	 */
	EReference getFunctionalEvent_ActionClasses();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.ExtensionPoint <em>Extension Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extension Point</em>'.
	 * @see edu.unicen.ucrefactoring.model.ExtensionPoint
	 * @generated
	 */
	EClass getExtensionPoint();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.ExtensionPoint#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see edu.unicen.ucrefactoring.model.ExtensionPoint#getCondition()
	 * @see #getExtensionPoint()
	 * @generated
	 */
	EAttribute getExtensionPoint_Condition();

	/**
	 * Returns the meta object for the reference list '{@link edu.unicen.ucrefactoring.model.ExtensionPoint#getExtendedUseCases <em>Extended Use Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extended Use Cases</em>'.
	 * @see edu.unicen.ucrefactoring.model.ExtensionPoint#getExtendedUseCases()
	 * @see #getExtensionPoint()
	 * @generated
	 */
	EReference getExtensionPoint_ExtendedUseCases();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.InclusionCall <em>Inclusion Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inclusion Call</em>'.
	 * @see edu.unicen.ucrefactoring.model.InclusionCall
	 * @generated
	 */
	EClass getInclusionCall();

	/**
	 * Returns the meta object for the reference list '{@link edu.unicen.ucrefactoring.model.InclusionCall#getIncludedUseCases <em>Included Use Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Use Cases</em>'.
	 * @see edu.unicen.ucrefactoring.model.InclusionCall#getIncludedUseCases()
	 * @see #getInclusionCall()
	 * @generated
	 */
	EReference getInclusionCall_IncludedUseCases();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.JointPoint <em>Joint Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Joint Point</em>'.
	 * @see edu.unicen.ucrefactoring.model.JointPoint
	 * @generated
	 */
	EClass getJointPoint();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactWords <em>Impact Words</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Impact Words</em>'.
	 * @see edu.unicen.ucrefactoring.model.JointPoint#getImpactWords()
	 * @see #getJointPoint()
	 * @generated
	 */
	EAttribute getJointPoint_ImpactWords();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.JointPoint#getImpactAspect <em>Impact Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Impact Aspect</em>'.
	 * @see edu.unicen.ucrefactoring.model.JointPoint#getImpactAspect()
	 * @see #getJointPoint()
	 * @generated
	 */
	EReference getJointPoint_ImpactAspect();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.ActionClass <em>Action Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Class</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass
	 * @generated
	 */
	EClass getActionClass();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.ActionClass#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getName()
	 * @see #getActionClass()
	 * @generated
	 */
	EAttribute getActionClass_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.ActionClass#getConfidence <em>Confidence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confidence</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getConfidence()
	 * @see #getActionClass()
	 * @generated
	 */
	EAttribute getActionClass_Confidence();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.ActionClass#getRanking <em>Ranking</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ranking</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getRanking()
	 * @see #getActionClass()
	 * @generated
	 */
	EAttribute getActionClass_Ranking();

	/**
	 * Returns the meta object for the containment reference '{@link edu.unicen.ucrefactoring.model.ActionClass#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parent</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getParent()
	 * @see #getActionClass()
	 * @generated
	 */
	EReference getActionClass_Parent();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.unicen.ucrefactoring.model.ActionClass#getChilds <em>Childs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Childs</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getChilds()
	 * @see #getActionClass()
	 * @generated
	 */
	EReference getActionClass_Childs();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.ActionClass#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Predicate</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionClass#getPredicate()
	 * @see #getActionClass()
	 * @generated
	 */
	EAttribute getActionClass_Predicate();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Aspect <em>Aspect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Aspect</em>'.
	 * @see edu.unicen.ucrefactoring.model.Aspect
	 * @generated
	 */
	EClass getAspect();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Aspect#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.Aspect#getName()
	 * @see #getAspect()
	 * @generated
	 */
	EAttribute getAspect_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Aspect#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.unicen.ucrefactoring.model.Aspect#getDescription()
	 * @see #getAspect()
	 * @generated
	 */
	EAttribute getAspect_Description();

	/**
	 * Returns the meta object for class '{@link edu.unicen.ucrefactoring.model.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see edu.unicen.ucrefactoring.model.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Condition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.unicen.ucrefactoring.model.Condition#getName()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.unicen.ucrefactoring.model.Condition#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.unicen.ucrefactoring.model.Condition#getDescription()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Description();

	/**
	 * Returns the meta object for the reference '{@link edu.unicen.ucrefactoring.model.Condition#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see edu.unicen.ucrefactoring.model.Condition#getEReference0()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_EReference0();

	/**
	 * Returns the meta object for enum '{@link edu.unicen.ucrefactoring.model.ActorTypeEnum <em>Actor Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Actor Type Enum</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActorTypeEnum
	 * @generated
	 */
	EEnum getActorTypeEnum();

	/**
	 * Returns the meta object for enum '{@link edu.unicen.ucrefactoring.model.EventTypeEnum <em>Event Type Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Event Type Enum</em>'.
	 * @see edu.unicen.ucrefactoring.model.EventTypeEnum
	 * @generated
	 */
	EEnum getEventTypeEnum();

	/**
	 * Returns the meta object for enum '{@link edu.unicen.ucrefactoring.model.ActionCodeEnum <em>Action Code Enum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Code Enum</em>'.
	 * @see edu.unicen.ucrefactoring.model.ActionCodeEnum
	 * @generated
	 */
	EEnum getActionCodeEnum();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UCRefactoringFactory getUCRefactoringFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.UseCaseImpl <em>Use Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.UseCaseImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getUseCase()
		 * @generated
		 */
		EClass USE_CASE = eINSTANCE.getUseCase();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE__NAME = eINSTANCE.getUseCase_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE__DESCRIPTION = eINSTANCE.getUseCase_Description();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__CONTEXT = eINSTANCE.getUseCase_Context();

		/**
		 * The meta object literal for the '<em><b>Secondary Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__SECONDARY_ACTORS = eINSTANCE.getUseCase_SecondaryActors();

		/**
		 * The meta object literal for the '<em><b>Primary Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__PRIMARY_ACTOR = eINSTANCE.getUseCase_PrimaryActor();

		/**
		 * The meta object literal for the '<em><b>Flows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__FLOWS = eINSTANCE.getUseCase_Flows();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE__PARENT = eINSTANCE.getUseCase_Parent();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.ContextImpl <em>Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.ContextImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getContext()
		 * @generated
		 */
		EClass CONTEXT = eINSTANCE.getContext();

		/**
		 * The meta object literal for the '<em><b>Preconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT__PRECONDITIONS = eINSTANCE.getContext_Preconditions();

		/**
		 * The meta object literal for the '<em><b>Postconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT__POSTCONDITIONS = eINSTANCE.getContext_Postconditions();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.UseCaseModelImpl <em>Use Case Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.UseCaseModelImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getUseCaseModel()
		 * @generated
		 */
		EClass USE_CASE_MODEL = eINSTANCE.getUseCaseModel();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_MODEL__DESCRIPTION = eINSTANCE.getUseCaseModel_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_MODEL__NAME = eINSTANCE.getUseCaseModel_Name();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_MODEL__ACTORS = eINSTANCE.getUseCaseModel_Actors();

		/**
		 * The meta object literal for the '<em><b>Use Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_MODEL__USE_CASES = eINSTANCE.getUseCaseModel_UseCases();

		/**
		 * The meta object literal for the '<em><b>Aspects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_MODEL__ASPECTS = eINSTANCE.getUseCaseModel_Aspects();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.ActorImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__NAME = eINSTANCE.getActor_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__DESCRIPTION = eINSTANCE.getActor_Description();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__TYPE = eINSTANCE.getActor_Type();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.FlowImpl <em>Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.FlowImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getFlow()
		 * @generated
		 */
		EClass FLOW = eINSTANCE.getFlow();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW__NAME = eINSTANCE.getFlow_Name();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__EVENTS = eINSTANCE.getFlow_Events();

		/**
		 * The meta object literal for the '<em><b>Use Case</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__USE_CASE = eINSTANCE.getFlow_UseCase();

		/**
		 * The meta object literal for the '<em><b>Parent Flow</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__PARENT_FLOW = eINSTANCE.getFlow_ParentFlow();

		/**
		 * The meta object literal for the '<em><b>Return Event</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__RETURN_EVENT = eINSTANCE.getFlow_ReturnEvent();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.EventImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Detail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__DETAIL = eINSTANCE.getEvent_Detail();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__NUMBER = eINSTANCE.getEvent_Number();

		/**
		 * The meta object literal for the '<em><b>Event Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__EVENT_ID = eINSTANCE.getEvent_EventId();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl <em>Functional Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.FunctionalEventImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getFunctionalEvent()
		 * @generated
		 */
		EClass FUNCTIONAL_EVENT = eINSTANCE.getFunctionalEvent();

		/**
		 * The meta object literal for the '<em><b>Joint Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTIONAL_EVENT__JOINT_POINTS = eINSTANCE.getFunctionalEvent_JointPoints();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTIONAL_EVENT__TYPE = eINSTANCE.getFunctionalEvent_Type();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTIONAL_EVENT__SUBJECT = eINSTANCE.getFunctionalEvent_Subject();

		/**
		 * The meta object literal for the '<em><b>Other Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTIONAL_EVENT__OTHER_ACTORS = eINSTANCE.getFunctionalEvent_OtherActors();

		/**
		 * The meta object literal for the '<em><b>Exceptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTIONAL_EVENT__EXCEPTIONS = eINSTANCE.getFunctionalEvent_Exceptions();

		/**
		 * The meta object literal for the '<em><b>Action Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTIONAL_EVENT__ACTION_CLASSES = eINSTANCE.getFunctionalEvent_ActionClasses();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl <em>Extension Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.ExtensionPointImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getExtensionPoint()
		 * @generated
		 */
		EClass EXTENSION_POINT = eINSTANCE.getExtensionPoint();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSION_POINT__CONDITION = eINSTANCE.getExtensionPoint_Condition();

		/**
		 * The meta object literal for the '<em><b>Extended Use Cases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTENSION_POINT__EXTENDED_USE_CASES = eINSTANCE.getExtensionPoint_ExtendedUseCases();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.InclusionCallImpl <em>Inclusion Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.InclusionCallImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getInclusionCall()
		 * @generated
		 */
		EClass INCLUSION_CALL = eINSTANCE.getInclusionCall();

		/**
		 * The meta object literal for the '<em><b>Included Use Cases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INCLUSION_CALL__INCLUDED_USE_CASES = eINSTANCE.getInclusionCall_IncludedUseCases();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.JointPointImpl <em>Joint Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.JointPointImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getJointPoint()
		 * @generated
		 */
		EClass JOINT_POINT = eINSTANCE.getJointPoint();

		/**
		 * The meta object literal for the '<em><b>Impact Words</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOINT_POINT__IMPACT_WORDS = eINSTANCE.getJointPoint_ImpactWords();

		/**
		 * The meta object literal for the '<em><b>Impact Aspect</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOINT_POINT__IMPACT_ASPECT = eINSTANCE.getJointPoint_ImpactAspect();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.ActionClassImpl <em>Action Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.ActionClassImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActionClass()
		 * @generated
		 */
		EClass ACTION_CLASS = eINSTANCE.getActionClass();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_CLASS__NAME = eINSTANCE.getActionClass_Name();

		/**
		 * The meta object literal for the '<em><b>Confidence</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_CLASS__CONFIDENCE = eINSTANCE.getActionClass_Confidence();

		/**
		 * The meta object literal for the '<em><b>Ranking</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_CLASS__RANKING = eINSTANCE.getActionClass_Ranking();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_CLASS__PARENT = eINSTANCE.getActionClass_Parent();

		/**
		 * The meta object literal for the '<em><b>Childs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_CLASS__CHILDS = eINSTANCE.getActionClass_Childs();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_CLASS__PREDICATE = eINSTANCE.getActionClass_Predicate();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.AspectImpl <em>Aspect</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.AspectImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getAspect()
		 * @generated
		 */
		EClass ASPECT = eINSTANCE.getAspect();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASPECT__NAME = eINSTANCE.getAspect_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASPECT__DESCRIPTION = eINSTANCE.getAspect_Description();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.impl.ConditionImpl
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__NAME = eINSTANCE.getCondition_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__DESCRIPTION = eINSTANCE.getCondition_Description();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__EREFERENCE0 = eINSTANCE.getCondition_EReference0();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.ActorTypeEnum <em>Actor Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.ActorTypeEnum
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActorTypeEnum()
		 * @generated
		 */
		EEnum ACTOR_TYPE_ENUM = eINSTANCE.getActorTypeEnum();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.EventTypeEnum <em>Event Type Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.EventTypeEnum
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getEventTypeEnum()
		 * @generated
		 */
		EEnum EVENT_TYPE_ENUM = eINSTANCE.getEventTypeEnum();

		/**
		 * The meta object literal for the '{@link edu.unicen.ucrefactoring.model.ActionCodeEnum <em>Action Code Enum</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.unicen.ucrefactoring.model.ActionCodeEnum
		 * @see edu.unicen.ucrefactoring.model.impl.UCRefactoringPackageImpl#getActionCodeEnum()
		 * @generated
		 */
		EEnum ACTION_CODE_ENUM = eINSTANCE.getActionCodeEnum();

	}

} //UCRefactoringPackage
