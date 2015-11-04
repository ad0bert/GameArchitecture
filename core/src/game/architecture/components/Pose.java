package game.architecture.components;

public interface Pose {
	public float GetXPos();
	public float GetZPos();
	public float GetYPos();
	public float GetAngle();
	
	public void SetAngle(float angle);
	public void SetXPos(float x);
	public void SetZPos(float z);
	public void SetYPos(float y);
	
}
