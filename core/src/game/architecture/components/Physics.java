package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CollisionSystem;
import game.architecture.systems.PhysicsSystem;

public class Physics extends Component implements Pose {

	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float yScale;
	private float xScale;
	private float angle;
	private float dT;
	private float velocityX;
	private float velocityY;

	public Physics(GameEntity e) {
		super(e);
		ServiceLocator.GetService(PhysicsSystem.class).Add(this);
	}

	public Physics(Physics p, GameEntity gameEntity) {
		super(p, gameEntity);
		xPos = p.GetXPos();
		yPos = p.GetYPos();
		zPos = p.GetZPos();
		yScale = p.GetYScale();
		xScale = p.GetXScale();
		angle = p.GetAngle();
		velocityX = p.getVelocityX();
		velocityY = p.getVelocityY();
		dT = p.dT;
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

	@Override
	public void Update() {
		if (!this.isActive || ((CollisionSystem) ServiceLocator.GetService(CollisionSystem.class)).CheckHit(this.getEntity())) {
			dT = 0;
		}
		if (xPos > 0 && yPos > 0) {
			SetXPos(xPos + velocityX * dT);
			SetYPos(yPos - velocityY * dT);
			dT++;
		}
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

}
