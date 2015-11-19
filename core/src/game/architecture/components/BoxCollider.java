package game.architecture.components;

import com.badlogic.gdx.math.collision.Ray;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CameraSystem;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
		Pose pos1 = (Pose)this.entity.getComponent(Pose.class);
		Pose pos2 = (Pose)c.entity.getComponent(Pose.class);
		
		float angle = pos1.GetAngle();
		
		float dx = pos1.GetXPos() - pos2.GetXPos();
		float dy = pos1.GetYPos() - pos2.GetYPos();
		
		float distance = (float)Math.sqrt(dx*dx + dy*dy);
		float xC = pos2.GetXPos() + (distance / (float)Math.sin(90)) /* 1 */ * (float)Math.sin(angle);
		float yC = pos2.GetYPos() + (distance / (float)Math.sin(180-90-angle)) * (float)Math.sin(angle);
		
		Visual visual1 = (Visual)this.entity.getComponent(Visual.class);
		Visual visual2 = (Visual)c.entity.getComponent(Visual.class);
		
		return false;
	}

	@Override
	public boolean IsHit(BoxCollider c) {
		throw new NotImplementedException();
	}

	@Override
	public boolean IsHit(Collideable c) {
		return c.IsHit(this);
	}

}
