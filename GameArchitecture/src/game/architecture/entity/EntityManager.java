package game.architecture.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

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
}
