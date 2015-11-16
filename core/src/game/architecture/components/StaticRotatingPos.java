package game.architecture.components;

import game.architecture.entity.GameEntity;
public class StaticRotatingPos extends Component implements Pose{

	public StaticRotatingPos(GameEntity e) {
		super(e);
	}

	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float angle;
	private float angularSpeed;

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

	public float getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(float angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	
	@Override
	public void Update(){
		angle += angularSpeed;
	}

}
