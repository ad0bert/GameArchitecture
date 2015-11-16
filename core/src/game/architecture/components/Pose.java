package game.architecture.components;

public interface Pose {
	public float GetXPos();
	public float GetZPos();
	public float GetYPos();
	public float GetAngle();
	public float GetXScale();
	public float GetYScale();
	
	public void SetAngle(float angle);
	public void SetXPos(float x);
	public void SetZPos(float z);
	public void SetYPos(float y);
	public void SetXScale(float x);
	public void SetYScale(float y);
	
	public void Update();
	
}
