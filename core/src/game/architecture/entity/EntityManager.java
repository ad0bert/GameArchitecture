package game.architecture.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Disposable;

import game.architecture.components.Colideable;

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
		entities.clear();
	}

	public void Activate() {
		for (GameEntity e : entities)
			e.Activate();
	}

	public GameEntity FindEntityByPos(int screenX, int screenY) {
		for (GameEntity ge : entities){
			try {
				Colideable c = (Colideable)ge.getComponent(Colideable.class);
				if (c.IsHit(screenX, screenY))
					return ge;
			}catch(Exception e){
				// do nothing
			}
		}
		return null;
	}
}
