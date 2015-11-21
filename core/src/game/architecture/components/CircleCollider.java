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
		if (!isActive) return false;
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
	public boolean IsHit(CircleCollider c){
		if (!isActive && !c.isActive) return false;
		Pose pos1 = (Pose)this.entity.getComponent(Pose.class);
		Pose pos2 = (Pose)c.entity.getComponent(Pose.class);
		
		Visual visual1 = (Visual)this.entity.getComponent(Visual.class);
		Visual visual2 = (Visual)c.entity.getComponent(Visual.class);
		
		float rad1 = visual1.GetTexture().getRegionHeight() / 2;
		float rad2 = visual2.GetTexture().getRegionHeight() / 2;
		
		float dx = pos1.GetXPos() - pos2.GetXPos();
		float dy = pos1.GetYPos() - pos2.GetYPos();
		
		double distance = Math.sqrt(dx*dx + dy*dy);
		
		return (distance < (rad1 + rad2));
	}
	
	@Override
	public boolean IsHit(BoxCollider c){
		if (!isActive && !c.isActive) return false;
		Pose pos2 = (Pose)this.entity.getComponent(Pose.class);
		Pose pos1 = (Pose)c.entity.getComponent(Pose.class);
		
		Visual visual2 = (Visual)this.entity.getComponent(Visual.class);
		Visual visual1 = (Visual)c.entity.getComponent(Visual.class);
		
		float H = visual1.GetTexture().getRegionHeight() * pos1.GetYScale();
		float W = visual1.GetTexture().getRegionWidth() * pos1.GetXScale();
		
		float radius = visual2.GetTexture().getRegionHeight() / 2;
		float angle = pos1.GetAngle();
		
		float centerX = pos1.GetXPos() + visual1.GetTexture().getRegionWidth()  / 2;
		float centerY = pos1.GetYPos() + visual1.GetTexture().getRegionHeight() / 2;
		
		float dx = (pos2.GetXPos() + radius) - centerX;
		float dy = (pos2.GetYPos() + radius) - centerY;
		
		float xC = centerX + dx * (float)Math.cos(angle) - dy * (float)Math.sin(angle);
		float yC = centerY + dx * (float)Math.sin(angle) + dy * (float)Math.cos(angle);
		
		dx = Math.abs((xC) - (pos1.GetXPos() + W));
		dy = Math.abs((yC) - (pos1.GetYPos() + H));

	    if (dx > (W / 2 + radius)) { return false; }
	    if (dy > (H / 2 + radius)) { return false; }

	    if (dx <= (W / 2)) { return true; } 
	    if (dy <= (H / 2)) { return true; }

	    float cornerDistance_sq = (dx - W / 2) * 
	    						  (dx - W / 2) +
	    						  (dy - H / 2) * 
	    						  (dy - H / 2);

	    return (cornerDistance_sq <= (radius*radius));
	}

	@Override
	public boolean IsHit(Collideable c) {
		return c.IsHit(this);
	}
	
}
