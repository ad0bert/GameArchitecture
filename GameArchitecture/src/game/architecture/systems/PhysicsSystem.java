package game.architecture.systems;

import game.architecture.components.Component;

public class PhysicsSystem extends SystemTemplate {

	@Override
	public void Update() {
		for (Component p : comps)
			p.Update();
	}

	@Override
	public void Add(Component c) {
		comps.add(c);
	}

	@Override
	public void Remove(Component c) {
		comps.remove(c);
	}

}
