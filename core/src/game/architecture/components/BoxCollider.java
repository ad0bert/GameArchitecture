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
		float H = visual.GetTexture().getRegionHeight() * pos.GetYScale();
		float W = visual.GetTexture().getRegionWidth() * pos.GetXScale();
		
		return r.origin.x >= pos.GetXPos() + W/2 && 
			   r.origin.x < pos.GetXPos() + W + W/2 && 
			   r.origin.y >= pos.GetYPos() + H/2 && 
			   r.origin.y < pos.GetYPos() + H  + H/2 ? true : false;
	}

	@Override
	public boolean IsHit(CircleCollider c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsHit(BoxCollider c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsHit(Collideable c) {
		return c.IsHit(this);
	}

}
