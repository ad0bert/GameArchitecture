package game.architecture.components;

import com.badlogic.gdx.Gdx;

public class StaticRotatingPos extends Component implements Pose{

	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float angle;
	private float angularSpeed;
	
	@Override
	public float GetXPos() {
		return (Gdx.graphics.getWidth()/2)+xPos;
	}

	@Override
	public float GetZPos() {
		return (Gdx.graphics.getWidth()/2)+zPos;
	}

	@Override
	public float GetYPos() {
		return (Gdx.graphics.getHeight()/2)+yPos;
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
