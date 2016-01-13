package game.architecture.components.collider;

import com.badlogic.gdx.math.collision.Ray;

import game.architecture.components.Pose;
import game.architecture.components.Visual;
import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CameraSystem;
import game.architecture.systems.CollisionSystem;

public class BoxCollider extends AbstractCollider {

	public BoxCollider(GameEntity e) {
		this.entity = e;
	}

	public BoxCollider(BoxCollider c, GameEntity e){
		this.entity = e;
	}

	@Override
	public boolean IsHit(float x, float y) {
		if (!isActive) return false;
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		Visual visual = (Visual)this.entity.getComponent(Visual.class);
		Ray r = ((CameraSystem)ServiceLocator.GetService(CameraSystem.class)).GetCamera().getPickRay(x, y);
		float H = visual.GetTexture().getRegionHeight();
		float W = visual.GetTexture().getRegionWidth();
		
		float angle = pos.GetAngle();
		float centerX = pos.GetXPos() + visual.GetTexture().getRegionWidth()/2;
		float centerY = pos.GetYPos() + visual.GetTexture().getRegionHeight()/2;
		
		float dx =  r.origin.x - centerX;
		float dy =  r.origin.y - centerY;
		
		angle = -(float) Math.toRadians(angle);
		float xC = centerX + dx * (float)Math.cos(angle) - dy * (float)Math.sin(angle);
		float yC = centerY + dx * (float)Math.sin(angle) + dy * (float)Math.cos(angle);
		
		return xC >= pos.GetXPos() && 
			   xC <  pos.GetXPos() + W && 
			   yC >= pos.GetYPos() && 
			   yC <  pos.GetYPos() + H ? true : false;
	}

	@Override
	public boolean IsHit(CircleCollider c) {
		if (!isActive || !c.getIsActive()) return false;
		return c.IsHit(this);
	}

	@Override
	public boolean IsHit(BoxCollider c) {
		return false;
	}

	@Override
	public boolean IsHit(Collideable c) {
		return c.IsHit(this);
	}

}
