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
		boolean result =  entities.remove(ge);
		ge.dispose();
		return result;
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
}
