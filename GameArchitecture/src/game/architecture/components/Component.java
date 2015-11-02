package game.architecture.components;

public abstract class Component {
	protected boolean IsActive = true;
	
	public abstract void Update();
	
	public void Activate(){
		IsActive = true;
	}
	// it says deactivate
	public void Deactivate(){
		IsActive = false;
	}
}
