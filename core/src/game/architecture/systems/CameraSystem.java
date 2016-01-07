package game.architecture.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import game.architecture.components.Component;
import game.architecture.engine.ServiceLocator;

public class CameraSystem extends SystemTemplate {

	private OrthographicCamera camera;
	
	public CameraSystem(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, ServiceLocator.V_WIDTH, ServiceLocator.V_HEIGHT);
	}
	
	public Camera GetCamera(){
		return camera;
	}
	
	@Override
	public void Update(float delta) {
		camera.update();

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
