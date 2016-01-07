package game.architecture.components.physics;

import java.util.List;

public class SemiImplicitEulerIntegrator extends AbstractIntegrator {

	/**
	 * Creates a new instance of this type of numerical integrator.
	 * 
	 * @param bodies
	 *            the list of bodies managed by the physics engine.
	 * @param forces
	 *            the list of forces managed by the physics engine.
	 */
	public SemiImplicitEulerIntegrator(List<Body> bodies, List<Force> forces) {
		super(bodies, forces);
	}

	@Override
	public void update(float dt) {
		ensureSize();
		storeState();
		applyForces();
		
		for (int i = 0; i < bodies.size(); ++i) {
			updateBody(bodies.get(i), dt);
		}
	}

	/**
	 * Integrates the given body over the specified time step.
	 * 
	 * @param body
	 *            the body to update.
	 * @param dt
	 *            the delta time.
	 */
	private void updateBody(Body body, float dt) {
		for (int i = 0; i < 3; ++i) {
			body.vel[i] += (body.acc[i] - body.vel[i] * body.dmp[i]) * dt;
			body.pos[i] += body.vel[i] * dt;
		}
	}

}
