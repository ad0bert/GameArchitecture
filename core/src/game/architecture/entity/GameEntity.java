package game.architecture.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Disposable;

import game.architecture.components.BoxCollider;
import game.architecture.components.CameraTarget;
import game.architecture.components.CircleCollider;
import game.architecture.components.Collideable;
import game.architecture.components.Component;
import game.architecture.components.Editable;
import game.architecture.components.GameLogic;
import game.architecture.components.Physics;
import game.architecture.components.StaticPos;
import game.architecture.components.StaticRotatingPos;
import game.architecture.components.Visual;

public class GameEntity implements Disposable {
	private List<Component> components;
	
	public GameEntity(){
		components = new ArrayList<Component>();
	}
	
	public GameEntity(GameEntity ge){
		components = new ArrayList<Component>();
		for (Component c : ge.getComponents()){
			if (c.getClass().equals(BoxCollider.class))
				components.add(new BoxCollider((BoxCollider)c, this));
//			else if(c.getClass().equals(CameraTarget.class))
//				components.add(new CameraTarget((CameraTarget)c, this));
			else if(c.getClass().equals(CircleCollider.class))
				components.add(new CircleCollider((CircleCollider)c, this));
//			else if(c.getClass().equals(Editable.class))
//				components.add(new Editable((Editable)c, this));
//			else if(c.getClass().equals(GameLogic.class))
//				components.add(new GameLogic((GameLogic)c, this));
//			else if(c.getClass().equals(Physics.class))
//				components.add(new Physics((Physics)c, this));
			else if(c.getClass().equals(StaticPos.class))
				components.add(new StaticPos((StaticPos)c, this));
			else if(c.getClass().equals(StaticRotatingPos.class))
				components.add(new StaticRotatingPos((StaticRotatingPos)c, this));
			else if(c.getClass().equals(Visual.class))
				components.add(new Visual((Visual)c, this));
		}
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

	@Override
	public void dispose() {
		components.clear();		
	}
	
	public List<Component> getComponents(){
		return components;
	}
}
