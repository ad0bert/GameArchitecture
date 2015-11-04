package game.architecture.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.architecture.components.Component;

public class CameraSystem extends SystemTemplate {

	private Camera camera;
	private Viewport vp;
	
	public CameraSystem(float worldWidth, float worldHeight){
		
	}
	
	public Camera GetCamera(){
		return camera;
	}
	
	public Viewport GetViewPort(){
		return vp;
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Add(Component c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Remove(Component c) {
		// TODO Auto-generated method stub

	}

}
