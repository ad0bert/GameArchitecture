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
		if (!isActive || !c.isActive) return false;
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
	
	private boolean TestHit(float angle, float cx, float cy, float rad, float rcx, float rcy, float rx, float ry, float rh, float rw){
		// Rotate circle's center point back
		angle = -(float) Math.toRadians(angle);
		float dx =  (cx+rad) - rcx;
		float dy =  (cy+rad) - rcy;
		
		float xC = rcx + dx * (float)Math.cos(angle) - dy * (float)Math.sin(angle);
		float yC = rcy + dx * (float)Math.sin(angle) + dy * (float)Math.cos(angle);
		 
		// Closest point in the rectangle to the center of circle rotated backwards(unrotated)
		float cdx = (float)Math.abs(xC - rcx);
	    float cdy = (float)Math.abs(yC - rcy);

	    if (cdx > (rw/2 + rad)) { return false; }
	    if (cdy > (rh/2 + rad)) { return false; }

	    if (cdx <= (rw/2)) { return true; } 
	    if (cdy <= (rh/2)) { return true; }

	    float cornerDistance_sq = ((cdx - rw/2) * (cdx - rw/2)) +
	                         	  ((cdy - rh/2) * (cdy - rh/2));

	    return (cornerDistance_sq <= (rad*rad));
	}
	
	@Override
	public boolean IsHit(BoxCollider box){
		if (!isActive || !box.isActive) return false;
		Pose circlePos = (Pose)this.entity.getComponent(Pose.class);
		Pose boxPos = (Pose)box.entity.getComponent(Pose.class);
		
		Visual circleVisual = (Visual)this.entity.getComponent(Visual.class);
		Visual boxVisual = (Visual)box.entity.getComponent(Visual.class);
		
		float boxHight = boxVisual.GetTexture().getRegionHeight();
		float boxWidth = boxVisual.GetTexture().getRegionWidth();
		
		float radius = circleVisual.GetTexture().getRegionHeight() / 2;
		float angle = boxPos.GetAngle();
		
		float centerX = boxPos.GetXPos() + boxVisual.GetTexture().getRegionWidth()  / 2;
		float centerY = boxPos.GetYPos() + boxVisual.GetTexture().getRegionHeight() / 2;
				
		return TestHit(angle, circlePos.GetXPos(), circlePos.GetYPos(), radius, centerX, centerY, boxPos.GetXPos(), boxPos.GetYPos(), boxHight, boxWidth);
	}

	@Override
	public boolean IsHit(Collideable c) {
		return c.IsHit(this);
	}
	
}
