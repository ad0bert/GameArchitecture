package game.architecture.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.collision.Ray;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CameraSystem;

public class Collideable extends Component {

	public Collideable(GameEntity e) {
		super(e);
	}

	public boolean IsHit(float x, float y){
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		Ray r = ((CameraSystem)ServiceLocator
					.GetService(CameraSystem.class))
					.GetCamera()
					.getPickRay(x, y);
		
		Visual visual = (Visual)this.entity.getComponent(Visual.class);
		
		float rad = visual.GetTexture().getRegionHeight() / 2;
		
		float mouseX = r.origin.x * 100;
		float mouseY = r.origin.y * 100;
		
		float circleMidX = pos.GetXPos() + rad;
		float circleMidY = pos.GetYPos() + rad;
		
		float distancesquared = (mouseX - circleMidX) * (mouseX - circleMidX) + (mouseY - circleMidY) * (mouseY - circleMidY);
		
		boolean collison = distancesquared <= rad * rad;
		return collison;
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
