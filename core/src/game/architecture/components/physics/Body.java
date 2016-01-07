package game.architecture.components.physics;

import game.architecture.components.Component;
import game.architecture.components.Pose;
import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.PhysicsSystem;

public class Body extends Component implements Pose {

	float pos[] = new float[3];

	float xScale;
	float yScale;
	
	/** The velocity of this body, including angular velocity. */
	float vel[] = new float[3];

	/** The acceleration of this body, including angular acceleration. */
	public float acc[] = new float[3];

	/** The damping factors of this body, including angular damping. */
	float dmp[] = new float[3];

	/** The inverse mass of this body (1.0 / mass). */
	private float invMass = 1;

	/** The inverse moment of inertia of this body (1.0 / moi). */
	private float invMoi = 1;

	public Body(GameEntity e){
		this.entity = e;
		((PhysicsSystem)ServiceLocator.GetService(PhysicsSystem.class)).addBody(this);
	}
	
	public Body(Body c, GameEntity gameEntity) {
		this.entity = gameEntity;
		//this = c.clone();
		((PhysicsSystem)ServiceLocator.GetService(PhysicsSystem.class)).addBody(this);
	}

	public Body mass(float mass) {
		if (mass <= 0) {
			invMass = 0;
		} else {
			invMass = 1.0f / mass;
		}
		return this;
	}

	public float getInvMass() {
		return invMass;
	}

	public Body momentOfInertia(float moi) {
		if (moi <= 0) {
			invMoi = 0;
		} else {
			invMoi = 1.0f / moi;
		}
		return this;
	}

	public Body linearDamping(float ld) {
		dmp[0] = dmp[1] = ld;
		return this;
	}

	public Body angularDamping(float ad) {
		dmp[2] = ad;
		return this;
	}

	public Body velocity(float vx, float vy, float av) {
		vel[0] = vx;
		vel[1] = vy;
		vel[2] = av;

		return this;
	}

	public Body linearVelocity(float vx, float vy) {
		vel[0] = vx;
		vel[1] = vy;

		return this;
	}

	public Body angularVelociry(float av) {
		vel[2] = av;
		return this;
	}

	public float getLinearVelocityX() {
		return vel[0];
	}

	public float getLinearVelocityY() {
		return vel[1];
	}

	public float getAngularVelocity() {
		return vel[2];
	}

	public void applyForce(float fx, float fy) {
		acc[0] += fx * invMass;
		acc[1] += fy * invMass;
	}

	@Override
	public float GetXPos() {
		return pos[0];
	}

	@Override
	public float GetZPos() {
		return 0;
	}

	@Override
	public float GetYPos() {
		return pos[1];
	}

	@Override
	public void SetXPos(float x) {
		pos[0] = x;
	}

	@Override
	public void SetZPos(float z) {
		//zPos = z;
	}

	@Override
	public void SetYPos(float y) {
		pos[1] = y;
	}

	@Override
	public float GetAngle() {
		return pos[2];
	}
	@Override
	public void SetAngle(float angle) {
		pos[2] = angle;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float GetXScale() {
		return xScale;
	}

	@Override
	public float GetYScale() {
		return yScale;
	}

	@Override
	public void SetXScale(float x) {
		xScale = x;
	}

	@Override
	public void SetYScale(float y) {
		yScale = y;
	}
}
