package game.architecture.components;

import com.badlogic.gdx.math.collision.Ray;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CameraSystem;

public class CircleCollider extends Collideable {

	public CircleCollider(GameEntity e) {
		super(e);
	}

	public CircleCollider(CircleCollider c, GameEntity e){
		super(c, e);
	}
	@Override
	public boolean IsHit(float x, float y){
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		Visual visual = (Visual)this.entity.getComponent(Visual.class);
		Ray r = ((CameraSystem)ServiceLocator.GetService(CameraSystem.class)).GetCamera().getPickRay(x, y);
		
		float rad = visual.GetTexture().getRegionHeight() / 2;
		float circleMidX = pos.GetXPos() + rad;
		float circleMidY = pos.GetYPos() + rad;
		float distancesquared = (r.origin.x - circleMidX) * (r.origin.x - circleMidX) + (r.origin.y - circleMidY) * (r.origin.y - circleMidY);
		
		
		return distancesquared <= rad * rad;
	}
	
	@Override
	public boolean IsHitCircle(float x, float y, float rad){
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		Visual visual = (Visual)this.entity.getComponent(Visual.class);
		float thisRad = visual.GetTexture().getRegionHeight() / 2;
		
		float dx = x - pos.GetXPos();
		float dy = y - pos.GetYPos();
		double distance = Math.sqrt(dx*dx + dy*dy);
		
		
		return (distance < (rad) + thisRad);
	}
	
	@Override
	public boolean IsHitBox(float x, float y, float height, float width){
		
		return false;
	}
	
}
