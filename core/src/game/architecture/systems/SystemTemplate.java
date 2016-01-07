package game.architecture.systems;

import java.util.ArrayList;
import java.util.List;

import game.architecture.components.Component;

public abstract class SystemTemplate {
	protected List<Component> comps = new ArrayList<Component>();
	
	public abstract void Update(float delta);
	public abstract void Add(Component c);
	public abstract void Remove(Component c);
}
