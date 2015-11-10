package game.architecture.entity;

import java.util.ArrayList;
import java.util.List;

import game.architecture.components.Component;

public class GameEntity {
	private List<Component> components;
	
	public GameEntity(){
		components = new ArrayList<Component>();
	}
	
	public void AddComponent(Component c){
		components.add(c);
	}
	
	public void Activate(){
		for (Component c : components)
			c.Activate();
	}
	
	public void Deactivate(){
		for (Component c : components)
			c.Deactivate();
	}
	
	public Component getComponent(Class c){
		for (Component comp : components){
			if (c.isInstance(comp))
				return comp;
		}
		return null;
	}
}
