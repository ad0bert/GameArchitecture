package game.architecture.systems;

import java.util.ArrayList;
import java.util.List;

import game.architecture.components.Component;
import game.architecture.components.physics.Body;
import game.architecture.components.physics.Force;
import game.architecture.components.physics.Integrator;
import game.architecture.components.physics.Rk4Integrator;
import game.architecture.components.physics.SemiImplicitEulerIntegrator;

public class PhysicsSystem extends SystemTemplate {

	/** Enumerates the available types of integrators. */
	public enum Type {SEMI_IMPLICIT_EULER, RK4};
	
	/** Default time step size if nothing else is specified. */
	private static final float DEFAULT_TIME_STEP = 1f / 60f;
	
	/** List of bodies to be updated. */
	private List<Body> bodies = new ArrayList<Body>();

	/** List of force actors that apply forces to bodies. */
	private List<Force> forces = new ArrayList<Force>();
	
	/** Current step size for the integrator. */
	private float timeStep = DEFAULT_TIME_STEP;
	
	/** Used to ensure constant step size and update fixed frequency. */
	private float timeAccumulator;
	
	/** Global gravity vector acting on all bodies. */
	private float[] gravity = new float[] {0, 0};
		
	/** The numerical integrator used to calculate the next physics step. */
	private Integrator integrator = new Rk4Integrator(bodies, forces);
	
	/** The type of numerical integrator used by this physics engine. */
	private Type type;
	

	/**
	 * Creates a new instance of this physics system. The physics system will
	 * use the RK4 Integrator by default.
	 * 
	 * @see Rk4Integrator
	 */
	public PhysicsSystem() {
		type(Type.RK4);
	}
	
	/**
	 * Sets the type of numerical integrator used by this physics system.
	 * 
	 * @param value
	 *            enum type describing the type of integrator.
	 * @return reference to this system to enable method chaining.
	 * 
	 * @see Type
	 */
	public final PhysicsSystem type(Type value) {
		switch (value) {
		case RK4:
			this.integrator = new Rk4Integrator(bodies, forces);
			this.type = Type.RK4;
			break;
			
		case SEMI_IMPLICIT_EULER:
			this.integrator = new SemiImplicitEulerIntegrator(bodies, forces);
			this.type = Type.SEMI_IMPLICIT_EULER;
			break;		
		}
		
		return this;
	}
	
	/**
	 * Retrieves the type of currently used integrator of this physics system.
	 * 
	 * @return enum describing the type of integrator.
	 * 
	 * @see Type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Sets the fixed time step of this system. This also defines the update
	 * frequency.
	 * 
	 * @param value
	 *            value for the fixed delta time in seconds.
	 * @return reference to this system to enable method chaining.
	 */
	public PhysicsSystem timeStep(float value) {
		timeStep = value;
		return this;
	}
	
	/**
	 * Sets the global gravity vector acting on all physics bodies.
	 * 
	 * <p>
	 * <strong>Note:</strong> Gravity is defined as acceleration not as a force.
	 * </p>
	 * 
	 * @param gx
	 *            x-component of the gravity vector.
	 * @param gy
	 *            y-component of the gravity vector.
	 * @return reference to this system to enable method chaining.
	 */
	public PhysicsSystem gravity(float gx, float gy) {
		gravity[0] = gx;
		gravity[1] = gy;
		return this;
	}
		
	/**
	 * Adds a body to this physics system.
	 * 
	 * @param body the body instance to be added.
	 */
	public void addBody(Body body) {
		assert(!bodies.contains(body));
		bodies.add(body);
	}

	/**
	 * Removes a body from this physics system.
	 * 
	 * @param body
	 *            the body instance to be removed.
	 */
	public void removeBody(Body body) {
		bodies.remove(body);
	}

	/**
	 * Adds the given force to this system.
	 * 
	 * @param force
	 *            the force to be added.
	 */
	public void addForce(Force force) {
		assert(!forces.contains(force));
		forces.add(force);
	}
	
	/**
	 * Removes the given force from this system.
	 * 
	 * @param force the force to be removed.
	 */
	public void removeForce(Force force) {
		forces.remove(force);
	}
	
	@Override
	public void Update(float delta) {
		//if (!isRunning()) return;

		applyGravity();
		
		timeAccumulator += delta;
		while (timeAccumulator >= timeStep) {
			integrator.update(timeStep);
			timeAccumulator -= timeStep;
		}
		clearForces();
	}

	/**
	 * Clears the forces (acceleration) of all bodies. This should be the 
	 * final step after each update.
	 */
	private void clearForces() {
		for (int i = 0; i < bodies.size(); ++i) {
			clearForces(bodies.get(i));
		}
	}

	/**
	 * Clears the forces of the given body.
	 * 
	 * @param body
	 *            the body which's forces should be set to zero.
	 */
	private void clearForces(Body body) {
		body.acc[0] = 0;
		body.acc[1] = 0;
		body.acc[2] = 0;
	}

	/**
	 * Applies the global gravity acceleration to all bodies. 
	 */
	private void applyGravity() {
		for (int i = 0; i < bodies.size(); ++i) {
			applyGravity(bodies.get(i));
		}
	}
	
	/**
	 * Applies the global gravity to the given body.
	 * 
	 * @param body the body that should be updated by this method.
	 */
	private void applyGravity(Body body) {
		if (body.getInvMass() == 0) return;
		body.acc[0] += gravity[0];
		body.acc[1] += gravity[1];
	}


	@Override
	public void Add(Component c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Remove(Component c) {
		// TODO Auto-generated method stub
		
	}	

}
