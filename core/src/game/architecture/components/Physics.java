package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.PhysicsSystem;

public class Physics extends Component implements Pose {

	private float xPos;
	private float yPos;
	private float zPos;
	private float yScale;
	private float xScale;
	private float angle;
	private float dT;
	private float velocityX;
	private float velocityY;
	private float forceX;
	private float forceY;
	private float mass;

	private class Derivative {
		float dx; // dx/dt = velocity
		float dv; // dv/dt = acceleration
	};

	private class State {
		float x; // position
		float v; // velocity
	};

	public Physics(GameEntity e) {
		this.entity = e;
		dT = 0.1f;
		ServiceLocator.GetService(PhysicsSystem.class).Add(this);
	}

	public Physics(Physics p, GameEntity gameEntity) {
		this.entity = gameEntity;
		xPos = p.GetXPos();
		yPos = p.GetYPos();
		zPos = p.GetZPos();
		yScale = p.GetYScale();
		xScale = p.GetXScale();
		angle = p.GetAngle();
		velocityX = p.getVelocityX();
		velocityY = p.getVelocityY();
		forceX = p.getForceX();
		forceY = p.getForceY();
		mass = p.getMass();
		dT = p.dT;
		ServiceLocator.GetService(PhysicsSystem.class).Add(this);
	}

	@Override
	public float GetXPos() {
		return xPos;
	}

	@Override
	public float GetZPos() {
		return zPos;
	}

	@Override
	public float GetYPos() {
		return yPos;
	}

	@Override
	public void SetXPos(float x) {
		xPos = x;
	}

	@Override
	public void SetZPos(float z) {
		zPos = z;
	}

	@Override
	public void SetYPos(float y) {
		yPos = y;
	}

	@Override
	public float GetAngle() {
		return angle;
	}

	@Override
	public void SetAngle(float angle) {
		this.angle = angle;
	}

	private float acceleration(State state)
	{
	    float k = 10;
	    float b = 1;
	    return -k * state.x - b*state.v;
	}
	
	private Derivative evaluate(float t, Derivative d) {
		State state = new State();
		state.x = xPos + d.dx * t;
		state.v = velocityX + d.dv * t;

		Derivative output = new Derivative();
		output.dx = state.v;
		output.dv = acceleration(state);
		return output;
	}

	private void RK4() {
		Derivative a, b, c, d;

		a = evaluate(0.0f, new Derivative());
		b = evaluate(dT * 0.5f, a);
		c = evaluate(dT * 0.5f, b);
		d = evaluate(dT, c);

		float dxdt = 1.0f / 6.0f * (a.dx + 2.0f * (b.dx + c.dx) + d.dx);

		float dvdt = 1.0f / 6.0f * (a.dv + 2.0f * (b.dv + c.dv) + d.dv);

		
		yPos = yPos + dxdt * dT;
		velocityX = velocityX  + dvdt * dT;
		
		/*TODO: 
		 * Switch from integrating velocity directly from acceleration to integrating momentum from force instead 
		 * (the derivative of momentum is force). You will need to add “mass” and “inverseMass” to the State struct and 
		 * I recommend adding a method called “recalculate” which updates velocity = momentum * inverseMass whenever it is called.
		 * Every time you modify the momentum value you need to recalculate the velocity. You should also rename the acceleration
		 * method to “force”. Why do this? Later on when working with rotational dynamics you will need to work with angular
		 * momentum directly instead of angular velocity, so you might as well start now.
		 */
		
		
		if(yPos < 0)
			yPos=0;
		
	}

	@Override
	public void Update() {
//		if (!this.isActive
//				|| ((CollisionSystem) ServiceLocator.GetService(CollisionSystem.class)).CheckHit(this.getEntity())) {
//			velocityX = 0;
//			velocityY = 0;
//			return;
//		}
//		RK4();
//		SetXPos(xPos + velocityX * dT);
//		SetYPos(yPos + velocityY * dT);
//
//		velocityX = velocityX + (forceX / mass) * dT;
//		velocityY = velocityY + (forceY / mass) * dT;
		// t += dT;
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

	public float getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public void setForceX(float force) {
		this.forceX = force;
	}

	public float getForceX() {
		return forceX;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getMass() {
		return mass;
	}

	public float getForceY() {
		return forceY;
	}

	public void setForceY(float forceY) {
		this.forceY = forceY;
	}

}
