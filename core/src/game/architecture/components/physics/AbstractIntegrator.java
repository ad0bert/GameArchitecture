package game.architecture.components.physics;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractIntegrator implements Integrator{
	/** Reference to the list of bodies managed by the physics engine. */
	protected List<Body> bodies;
	
	/** Reference to the list of forces managed by the physics engine. */
	protected List<Force> forces;
	
	/** Stored stated of all bodies, used to restore a previous state. */
	private List<State> states = new ArrayList<State>();
	
	
	/**
	 * Create a new instance of this integrator.
	 * 
	 * A integrator should and will not modify the list specified with this
	 * constructor. However it will modify the state of the physics bodies.
	 * 
	 * @param bodies
	 *            the list of bodies managed by the physics engine.
	 * @param forces
	 *            the list of forces managed by the physics engine.
	 */
	public AbstractIntegrator(List<Body> bodies, List<Force> forces) {
		this.bodies = bodies;
		this.forces = forces;
	}
	
	/**
	 * Takes care that the list of physics states for bodies is big
	 * enough. This method should be called once per update and before
	 * {@link #storeState()} is called.
	 */
	protected void ensureSize() {
		// ensure size of list to store/restore body states
		for (int i = states.size(); i < bodies.size(); ++i) {
			states.add(new State());
		}		
	}
	
	/**
	 * Applies the forces to all bodies. 
	 */
	protected void applyForces() {
		for (int i = 0; i < forces.size(); ++i) {
			forces.get(i).apply();
		}
	}		
	
	/**
	 * Stores the current physics state of all bodies. 
	 */
	protected void storeState() {
		for (int i = 0; i < bodies.size(); ++i) {
			storeState(bodies.get(i), states.get(i));
		}
	}

	/**
	 * Stores the physics state of the given body.
	 * 
	 * @param body
	 *            the body which's physics state should be stored.
	 * @param state
	 *            the state instance used to store the bodies physics state.
	 */
	private void storeState(Body body, State state) {
		for (int i = 0; i < 3; ++i) {
			state.pos[i] = body.pos[i];
			state.vel[i] = body.vel[i];
			state.acc[i] = body.acc[i];
		}
	}

	/**
	 * Restores the previously stored physics state of all bodies.
	 */
	protected void restoreState() {
		for (int i = 0; i < bodies.size(); ++i) {
			restoreState(bodies.get(i), states.get(i));
		}		
	}

	/**
	 * Restores the physics state of the given body.
	 * 
	 * @param body
	 *            the body which's physics state should be restored.
	 * @param state
	 *            the previously stored physics state.
	 */
	private void restoreState(Body body, State state) {
		for (int i = 0; i < 3; ++i) {
			body.pos[i] = state.pos[i];
			body.vel[i] = state.vel[i];
			body.acc[i] = state.acc[i];
		}
	}
	
	/**
	 * Internal class that stores the physics state of one single body.
	 */
	private static class State {
		public float[] pos = new float[3];
		public float[] vel = new float[3];
		public float[] acc = new float[3];		
	}

}
