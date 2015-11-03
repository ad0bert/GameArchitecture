package game.architecture.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Component {
	protected boolean IsActive = true;
	
	public void Activate(){
		IsActive = true;
	}
	// it says deactivate
	public void Deactivate(){
		IsActive = false;
	}
}
