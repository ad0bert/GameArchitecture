package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.PhysicsSystem;

public class Physics extends Component implements Pose {
	
	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float yScale;
	private float xScale;
	private float angle;
	
	public Physics(GameEntity e){
		super(e);
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
