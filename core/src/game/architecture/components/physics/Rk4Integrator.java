package game.architecture.components.physics;

import java.util.ArrayList;
import java.util.List;

public class Rk4Integrator extends AbstractIntegrator{

	/**
	 * Inner class that represents a derivative state of a physics body.
	 */
	private static class Derivative {
		
		/** The derivative of the position (velocity). */
		public float[] vel = new float[3];
		
		/** The derivative of the velocity (acceleration). */
		public float[] acc = new float[3];
		
	}
	
	/** Interim step of the RK4 method. */
	private List<Derivative> a = new ArrayList<Derivative>();

	/** Interim step of the RK4 method. */
	private List<Derivative> b = new ArrayList<Derivative>();
	
	/** Interim step of the RK4 method. */
	private List<Derivative> c = new ArrayList<Derivative>();
	
	/** Interim step of the RK4 method. */
	private List<Derivative> d = new ArrayList<Derivative>();
	
	/** Interim step of the RK4 method. */
	private List<Derivative> e = new ArrayList<Derivative>();
	
	/** Float array used to calculate the final RK4 step. */
	private float vel[] = new float[3];
	
	/** Float array used to calculate the final RK4 step. */
	private float acc[] = new float[3];
		

	/**
	 * Create a new instance of this integrator.
	 * 
	 * @param bodies
	 *            the list of bodies managed by the physics engine.
	 * @param forces
	 *            the list of forces managed by the physics engine.
	 */
	public Rk4Integrator(List<Body> bodies, List<Force> forces) {
		super(bodies, forces);
	}

	@Override
	public void update(float dt) {
		ensureSize();
		
		storeState();
		derive(a);
		restoreState();
		
		derive(a, b, 0.5f * dt);
		restoreState();
		
		derive(b, c, 0.5f * dt);
		restoreState();
		
		derive(c, d, dt);
		restoreState();
		
		finalStep(dt);
	}
	
	/**
	 * Carries out the final step of the RK5 method for all bodies.
	 * 
	 * @param dt
	 *            delta time in seconds.
	 */
	private void finalStep(float dt) {
		assert(a.size() == bodies.size());
		assert(b.size() == bodies.size());
		assert(c.size() == bodies.size());
		assert(d.size() == bodies.size());

		for (int i = 0; i < bodies.size(); ++i) {
			finalStep(bodies.get(i), a.get(i), b.get(i), c.get(i), d.get(i), dt);
		}
	}
	
	/**
	 * Calculates the final state of the given body using the previously
	 * calculate derivatives.
	 * 
	 * @param body
	 *            the physics body which's final step should be calculated.
	 * @param a
	 *            interim step a of the given body.
	 * @param b
	 *            interim step b of the given body.
	 * @param c
	 *            interim step c of the given body.
	 * @param d
	 *            interim step d of the given body.
	 * @param dt
	 *            delta time in seconds.
	 */
	private void finalStep(Body body, Derivative a, Derivative b, Derivative c,
			Derivative d, float dt) {
		
		for (int i = 0; i < 3; ++i) {
			vel[i] = 1f/6f * (a.vel[i] + 2 * (b.vel[i] + c.vel[i]) + d.vel[i]); 
			acc[i] = 1f/6f * (a.acc[i] + 2 * (b.acc[i] + c.acc[i]) + d.acc[i]);
			body.pos[i] += vel[i] * dt;
			body.vel[i] += acc[i] * dt;
		}
	}
	
	/**
	 * Takes care that all lists are big enough to handle the current number of
	 * bodies.
	 */
	protected void ensureSize() {
		super.ensureSize();
		
		ensureSize(a);
		ensureSize(b);
		ensureSize(c);
		ensureSize(d);
		ensureSize(e);
	}

	/**
	 * Takes care that the given list of derivatives is big enough.
	 * 
	 * @param d
	 *            list of derivatives to enlarge if necessary
	 */
	private void ensureSize(List<Derivative> d) {
		for (int i = d.size(); i < bodies.size(); ++i) {
			d.add(new Derivative());
		}
	}

	/**
	 * Calculates the derivatives of all bodies based on given derivative. This
	 * method carries out an Euler step for all bodies based on the given
	 * derivative and calculates derivative state on the new position.
	 *
	 * <p>This method <strong>does modify</strong> the bodies' state.
	 * 
	 * @param use
	 *            the derivative state used to do an Euler step.
	 * @param result
	 *            the resulting derivative state of all bodies.
	 * @param dt
	 *            the delta time in seconds.
	 */
	private void derive(List<Derivative> use, List<Derivative> result, float dt) {
		assert(use.size() >= bodies.size());
		doStep(use, dt);
		derive(result);
	}
	
	/**
	 * Calculates the derivatives of all bodies. 
	 * 
	 * <p>This method <strong>does modify</strong> the bodies' state.
	 * 
	 * @param result
	 *            the resulting derivative state of all bodies.
	 */
	private void derive(List<Derivative> result) {
		assert(result.size() >= bodies.size());
		
		applyForces();
		for (int i = 0; i < bodies.size(); ++i) {
			derive(bodies.get(i), result.get(i));
		}		
	}

	/**
	 * Calculates the derivative state of the given body. The given body will
	 * not be modified.
	 * 
	 * <p>This method does not the body's state.</p>
	 * 
	 * @param body
	 *            the body which derivative state should be determined.
	 * @param result
	 *            where the resulting derivative state should be stored.
	 */
	private void derive(Body body, Derivative result) {
		for (int i = 0; i < 3; ++i) {
			result.vel[i] = body.vel[i];
			result.acc[i] = body.acc[i] - body.dmp[i] * body.vel[i];
		}
	}	
	
	/**
	 * Carries out an Euler step for all bodies.
	 * 
	 * <p>
	 * This method does not modify the acceleration value stored within the
	 * bodies.
	 * </p>
	 * 
	 * @param derivatives
	 *            the list of derivatives of the bodies.
	 * @param dt
	 *            the delta time in seconds.
	 */
	private void doStep(List<Derivative> derivatives, float dt) {
		assert(derivatives.size() >= bodies.size());
		
		for (int i = 0; i < bodies.size(); ++i) {
			doStep(bodies.get(i), derivatives.get(i), dt);
		}
	}
	
	/**
	 * Carries out one Euler step for the given Body. The Euler step is based on
	 * the position of the body and the given derivative.
	 * 
	 * <p>
	 * This method does not modify the acceleration value stored in the given
	 * body.
	 * </p>
	 * 
	 * @param body
	 *            the body that should be updated.
	 * @param d
	 *            the derivative state of the body.
	 * @param dt
	 *            the delta time in seconds.
	 */
	private void doStep(Body body, Derivative d, float dt) {
		for (int i = 0; i < 3; ++i) {
			body.pos[i] += d.vel[i] * dt;
			body.vel[i] += d.acc[i] * dt;
		}
	}
}
