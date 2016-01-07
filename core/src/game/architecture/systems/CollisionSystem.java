package game.architecture.systems;

import game.architecture.components.Component;
import game.architecture.components.collider.AbstractCollider;

public class CollisionSystem extends SystemTemplate {
	
	@Override
	public void Update(float delta) {
		clearCollisions();
		
		for (int i = 0; i < comps.size(); ++i) {
			for (int j = i + 1; j < comps.size(); ++j) {
				AbstractCollider a = (AbstractCollider) comps.get(i);
				AbstractCollider b = (AbstractCollider) comps.get(j);
				if (a.IsHit(b)) {
					a.setCollision(b.getEntity());
					b.setCollision(a.getEntity());
				}
			}
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
	
	private void clearCollisions() {
		for (int i = 0; i < comps.size(); ++i) {
			((AbstractCollider)comps.get(i)).clearCollision();
		}
	}
}
