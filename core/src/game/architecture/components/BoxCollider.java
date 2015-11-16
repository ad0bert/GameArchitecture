package game.architecture.components;

import com.badlogic.gdx.math.collision.Ray;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CameraSystem;

public class BoxCollider extends Collideable {

	public BoxCollider(GameEntity e) {
		super(e);
	}

	public BoxCollider(BoxCollider c, GameEntity e) {
		super(c, e);
	}

	@Override
	public boolean IsHit(float x, float y) {
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		Visual visual = (Visual)this.entity.getComponent(Visual.class);
		Ray r = ((CameraSystem)ServiceLocator.GetService(CameraSystem.class)).GetCamera().getPickRay(x, y);
		float H = visual.GetTexture().getRegionHeight() / pos.GetXScale();
		float W = visual.GetTexture().getRegionWidth() / pos.GetYScale();
		
		return r.origin.x >= pos.GetXPos() && 
			   r.origin.x < pos.GetXPos() + W && 
			   r.origin.y >= pos.GetYPos() && 
			   r.origin.y < pos.GetYPos() + H ? true : false;
	}

	@Override
	public boolean IsHitCircle(float x, float y, float rad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsHitBox(float x, float y, float height, float width) {
		// TODO Auto-generated method stub
		return false;
	}

}
