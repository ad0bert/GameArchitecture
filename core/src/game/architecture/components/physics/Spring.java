package game.architecture.components.physics;

import com.badlogic.gdx.math.Vector2;

import game.architecture.components.Component;
import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.PhysicsSystem;

public class Spring extends Component implements Force, Connection{

	/** The first entity this spring is connected to. */
	private GameEntity e1;
	
	/** The second entity this spring is connected to. **/
	private GameEntity e2;
	
	/** The first body this spring is connected to. */
	private Body b1;
	
	/** The second body this spring is connected to. */
	private Body b2;
	
	/** The standard length of this spring where no force is applied. */
	private float lng;
	
	/** The spring constant describing the stiffness of this spring. */
	private float k = 1;
	
	/** The damping coefficient of this spring. */
	private float c = 0.1f;
	
	/** An auxiliary vector used to avoid repetitive memory allocation .*/
	private Vector2 aux = new Vector2();
	
	/**
	 * Creates a new instance of this spring. It is assumed that the given
	 * entities contain body components.
	 * 
	 * @param e1
	 *            the first entity this spring is connected to.
	 * @param e2
	 *            the second entity this spring is connected to.
	 * @throws IllegalArgumentException
	 *             in case at least one of the given entities does not contain a
	 *             physics body component.
	 */
	public Spring(GameEntity e1, GameEntity e2) throws IllegalArgumentException {		
		// store reference of entities to connect springs to bodies on
		// activation.
		this.e1 = e1;
		this.e2 = e2;
	}
	
	/**
	 * Sets the spring constant of this spring. The spring constant describes
	 * the stiffness of a spring.
	 * 
	 * @param value
	 *            the new value of the spring constant.
	 * @return a reference of this spring used for method chaining.
	 * @throws IllegalArgumentException
	 *             of the specified spring constant is smaller than zero.
	 */
	public Spring springConstant(float value) {
		if (value < 0) throw new IllegalArgumentException();
		k = value;
		return this;
	}
	
	/**
	 * Sets the camping coefficient of this spring. The damping coefficient must
	 * be positive or zero.
	 * 
	 * @param value the new value for the damping coefficient.
	 * @return a reference of this spring used for method chaining.
	 * @throws IllegalArgumentException
	 *             of the specified spring constant is smaller than zero.
	 */
	public Spring dampingCoefficient(float value)
			throws IllegalArgumentException {
		
		if (value < 0) {
			throw new IllegalArgumentException(
					"damping coefficient must be equal or greater zero");
		}
		c = value;
		return this;
	}
	
	//@Override
	public void entityActivated() {
		//super.entityActivated();
		((PhysicsSystem)ServiceLocator.GetService(PhysicsSystem.class)).addForce(this);
		//getEntity().getBatch().addListener(this);
		
		b1 = (Body)e1.getComponent(Body.class);
		if (b1 == null) {
			throw new IllegalArgumentException("Entity does not contain a physics body");
		}
		b2 = (Body)e2.getComponent(Body.class);
		if (b2 == null) {
			throw new IllegalArgumentException("Entity does not contain a physics body");
		}
		
		// determine standard length of this spring
		lng = Vector2.dst(b1.GetXPos(),  b1.GetYPos(), 
				          b2.GetXPos(), b2.GetYPos());
	}

	//@Override
	public void entityDeactivated() {
		//getEntity().getBatch().removeListener(this);
		((PhysicsSystem)ServiceLocator.GetService(PhysicsSystem.class)).removeForce(this);
		//super.entityDeactivated();
	}

	/**
	 * Calculates the magnitude of the force produced by this spring.
	 * 
	 * @param currentLength
	 *            the current distance of the two connected bodies.
	 * @return the magnitude of the force.
	 */
	private float calcSpringForce(float currentLength) {
		return (lng - currentLength) * k;
	}
	
	/**
	 * Calculates the relative velocity of the two bodies in the given
	 * direction.
	 * 
	 * <p>
	 * The velocity is determined by projecting the velocity vectors of the two
	 * bodies to the normalized direction vector from b2 to b1 and subtracting
	 * the result. The projection is done using the dot product.
	 * </p>
	 * 
	 * @param direction
	 *            normalized vector describing the direction to be considered.
	 * @return the magnitude of the relative velocity.
	 */
	private float calcDampingForce(Vector2 direction) {
		// velocity of first body in given direction using dot product
		float v1 = direction.dot(b1.getLinearVelocityX(),
				b1.getLinearVelocityY());

		// velocity of second body in given direction using dot product
		float v2 = direction.dot(b2.getLinearVelocityX(),
				b2.getLinearVelocityY());

		// calculate relative velocity
		float rv = v1 - v2;
		
		// return damping force
		return -rv * c;
	}

	@Override
	public Body getBody(int idx) throws IndexOutOfBoundsException {
		if (idx < 0 || idx > 1) {
			throw new IndexOutOfBoundsException();
		}
		
		return idx == 0 ? b1 : b2;
	}

	/////////////////////////////////////////////////
	/////// Interface Force
	/////////////////////////////////////////////////
	
	@Override
	public void apply() {
		// vector from b2 to b1
		Vector2 v = aux;
		v.set(b1.GetXPos(), b1.GetYPos()).sub(b2.GetXPos(), b2.GetYPos());
		
		// get current distance between our two bodies
		float dst = v.len();

		// scale vector to the magnitude of our force
		v.scl((calcSpringForce(dst) + calcDampingForce(v)) / dst);
		
		// apply force to both bodies
		b2.applyForce(-v.x, -v.y);
		b1.applyForce(v.x, v.y);	
	}



}
