package game.architecture.components;

public class StaticPos extends Component implements Pose{

	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float angle;
	
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

}
