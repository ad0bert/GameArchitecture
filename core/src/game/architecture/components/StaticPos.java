package game.architecture.components;

import game.architecture.entity.GameEntity;

public class StaticPos extends Component implements Pose{

	public StaticPos(GameEntity e) {
		super(e);
		// TODO Auto-generated constructor stub
	}

	public StaticPos(StaticPos p, GameEntity e){
		super(p, e);
		xPos = p.GetXPos();
		yPos = p.GetYPos();
		zPos = p.GetZPos();
		yScale = p.GetYScale();
		xScale = p.GetXScale();
		angle = p.GetAngle();
	}
	// TODO: make vector class
	private float xPos;
	private float yPos;
	private float zPos;
	private float yScale;
	private float xScale;
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
