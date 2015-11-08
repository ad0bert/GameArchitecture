package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.systems.PhysicsSystem;

public class Physics extends Component implements Pose {
	
	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float angle;
	
	public Physics(){
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

}
