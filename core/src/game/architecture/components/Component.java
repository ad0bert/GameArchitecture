package game.architecture.components;

public abstract class Component {
	protected boolean IsActive = true;
	
	public void Activate(){
		IsActive = true;
	}

	public void Deactivate(){
		IsActive = false;
	}
	
	public abstract void Update();
}
