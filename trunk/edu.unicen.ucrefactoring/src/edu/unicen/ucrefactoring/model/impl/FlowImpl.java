/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.unicen.ucrefactoring.model.impl;

import edu.unicen.ucrefactoring.model.Event;
import edu.unicen.ucrefactoring.model.Flow;
import edu.unicen.ucrefactoring.model.UCRefactoringPackage;

import edu.unicen.ucrefactoring.model.UseCase;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FlowImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FlowImpl#getEvents <em>Events</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FlowImpl#getUseCase <em>Use Case</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FlowImpl#getParentFlow <em>Parent Flow</em>}</li>
 *   <li>{@link edu.unicen.ucrefactoring.model.impl.FlowImpl#getReturnEvent <em>Return Event</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FlowImpl extends EObjectImpl implements Flow {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEvents() <em>Events</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvents()
	 * @generated
	 * @ordered
	 */
	protected EList<Event> events;

	/**
	 * The cached value of the '{@link #getUseCase() <em>Use Case</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUseCase()
	 * @generated
	 * @ordered
	 */
	protected UseCase useCase;

	/**
	 * The cached value of the '{@link #getParentFlow() <em>Parent Flow</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentFlow()
	 * @generated
	 * @ordered
	 */
	protected Flow parentFlow;

	/**
	 * The cached value of the '{@link #getReturnEvent() <em>Return Event</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReturnEvent()
	 * @generated
	 * @ordered
	 */
	protected Event returnEvent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UCRefactoringPackage.Literals.FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FLOW__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Event> getEvents() {
		if (events == null) {
			events = new EObjectContainmentEList<Event>(Event.class, this, UCRefactoringPackage.FLOW__EVENTS);
		}
		return events;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCase getUseCase() {
		if (useCase != null && useCase.eIsProxy()) {
			InternalEObject oldUseCase = (InternalEObject)useCase;
			useCase = (UseCase)eResolveProxy(oldUseCase);
			if (useCase != oldUseCase) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.FLOW__USE_CASE, oldUseCase, useCase));
			}
		}
		return useCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UseCase basicGetUseCase() {
		return useCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUseCase(UseCase newUseCase) {
		UseCase oldUseCase = useCase;
		useCase = newUseCase;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FLOW__USE_CASE, oldUseCase, useCase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Flow getParentFlow() {
		if (parentFlow != null && parentFlow.eIsProxy()) {
			InternalEObject oldParentFlow = (InternalEObject)parentFlow;
			parentFlow = (Flow)eResolveProxy(oldParentFlow);
			if (parentFlow != oldParentFlow) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.FLOW__PARENT_FLOW, oldParentFlow, parentFlow));
			}
		}
		return parentFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Flow basicGetParentFlow() {
		return parentFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentFlow(Flow newParentFlow) {
		Flow oldParentFlow = parentFlow;
		parentFlow = newParentFlow;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FLOW__PARENT_FLOW, oldParentFlow, parentFlow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event getReturnEvent() {
		if (returnEvent != null && returnEvent.eIsProxy()) {
			InternalEObject oldReturnEvent = (InternalEObject)returnEvent;
			returnEvent = (Event)eResolveProxy(oldReturnEvent);
			if (returnEvent != oldReturnEvent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UCRefactoringPackage.FLOW__RETURN_EVENT, oldReturnEvent, returnEvent));
			}
		}
		return returnEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event basicGetReturnEvent() {
		return returnEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReturnEvent(Event newReturnEvent) {
		Event oldReturnEvent = returnEvent;
		returnEvent = newReturnEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UCRefactoringPackage.FLOW__RETURN_EVENT, oldReturnEvent, returnEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UCRefactoringPackage.FLOW__EVENTS:
				return ((InternalEList<?>)getEvents()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UCRefactoringPackage.FLOW__NAME:
				return getName();
			case UCRefactoringPackage.FLOW__EVENTS:
				return getEvents();
			case UCRefactoringPackage.FLOW__USE_CASE:
				if (resolve) return getUseCase();
				return basicGetUseCase();
			case UCRefactoringPackage.FLOW__PARENT_FLOW:
				if (resolve) return getParentFlow();
				return basicGetParentFlow();
			case UCRefactoringPackage.FLOW__RETURN_EVENT:
				if (resolve) return getReturnEvent();
				return basicGetReturnEvent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UCRefactoringPackage.FLOW__NAME:
				setName((String)newValue);
				return;
			case UCRefactoringPackage.FLOW__EVENTS:
				getEvents().clear();
				getEvents().addAll((Collection<? extends Event>)newValue);
				return;
			case UCRefactoringPackage.FLOW__USE_CASE:
				setUseCase((UseCase)newValue);
				return;
			case UCRefactoringPackage.FLOW__PARENT_FLOW:
				setParentFlow((Flow)newValue);
				return;
			case UCRefactoringPackage.FLOW__RETURN_EVENT:
				setReturnEvent((Event)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UCRefactoringPackage.FLOW__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UCRefactoringPackage.FLOW__EVENTS:
				getEvents().clear();
				return;
			case UCRefactoringPackage.FLOW__USE_CASE:
				setUseCase((UseCase)null);
				return;
			case UCRefactoringPackage.FLOW__PARENT_FLOW:
				setParentFlow((Flow)null);
				return;
			case UCRefactoringPackage.FLOW__RETURN_EVENT:
				setReturnEvent((Event)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UCRefactoringPackage.FLOW__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UCRefactoringPackage.FLOW__EVENTS:
				return events != null && !events.isEmpty();
			case UCRefactoringPackage.FLOW__USE_CASE:
				return useCase != null;
			case UCRefactoringPackage.FLOW__PARENT_FLOW:
				return parentFlow != null;
			case UCRefactoringPackage.FLOW__RETURN_EVENT:
				return returnEvent != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //FlowImpl
