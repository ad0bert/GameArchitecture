package game.architecture.systems;

import java.util.ArrayList;
import java.util.List;

import game.architecture.components.Collideable;
import game.architecture.components.Component;
import game.architecture.entity.GameEntity;

public class CollisionSystem extends SystemTemplate {

	private List<GameEntity> entities = new ArrayList<GameEntity>();
	
	@Override
	public void Update() {
		entities.clear();
		for (Component c1 : comps)
			for (Component c2 : comps){
				if (c1.equals(c2))
					continue;
				if (!((Collideable)c1).IsHit((Collideable)c2))
					continue;
					
				entities.add(c1.getEntity());
			}
	}

	@Override
	public void Add(Component c) {
		comps.add(c);
	}

	@Override
	public void Remove(Component c) {
		comps.remove(c);
	}
	
	public boolean CheckHit(GameEntity ge){
		return entities.contains(ge);
	}
}
