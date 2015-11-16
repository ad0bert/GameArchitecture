package game.architecture.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Disposable;

import game.architecture.components.CircleCollider;
import game.architecture.components.Collideable;
import game.architecture.components.Pose;
import game.architecture.components.Visual;

public class EntityManager implements Disposable {

	private List<GameEntity> entities;
	
	public EntityManager(){
		entities = new ArrayList<GameEntity>();
	}
	
	public boolean AddEntity(GameEntity ge){
		return entities.add(ge);
	}
	
	public boolean RemoveEntity(GameEntity ge){
		return entities.remove(ge);
	}

	@Override
	public void dispose() {
		for (GameEntity ge : entities)
			ge.dispose();
		entities.clear();
	}

	public void Activate() {
		for (GameEntity e : entities)
			e.Activate();
	}

	public GameEntity FindEntityByPos(int screenX, int screenY) {
		for (GameEntity ge : entities){
			try {
				Collideable c = (Collideable)ge.getComponent(Collideable.class);
				if (c.IsHit(screenX, screenY))
					return ge;
			}catch(Exception e){
				// do nothing
			}
		}
		return null;
	}
	
	public boolean CheckHit(GameEntity entity){
		Collideable c1 = (Collideable)entity.getComponent(Collideable.class);
		Pose p1 = (Pose)entity.getComponent(Pose.class);
		Visual v1 = (Visual)entity.getComponent(Visual.class);
		
		for (GameEntity ge : entities){
			Collideable c2 = (Collideable)ge.getComponent(Collideable.class);
			if (c1.getClass().equals(CircleCollider.class) &&
				c2.getClass().equals(CircleCollider.class) && !c1.equals(c2)){
				if (c2.IsHitCircle(p1.GetXPos(), p1.GetYPos(), v1.GetTexture().getRegionHeight()/2))
					return true;
			} else {
				// todo
			}
			
		}
		return false;
	}
}
