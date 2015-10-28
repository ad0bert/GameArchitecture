package game.architecture.systems;

import game.architecture.components.Component;

public class RenderSystem extends SystemTemplate {

	@Override
	public void Update() {
		for (Component c : comps){
			c.Update();
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

}
