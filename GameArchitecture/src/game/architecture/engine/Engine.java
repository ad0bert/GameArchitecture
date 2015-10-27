package game.architecture.engine;

import game.architecture.components.Visual;
import game.architecture.entity.GameEntity;
import game.architecture.systems.RenderSystem;
import game.architecture.systems.SystemTemplate;

public class Engine {
	private GameEntity ge;
	
	public Engine(){
		GameEntity rock = new GameEntity();
		rock.AddComponent(new Visual());
		//rock.AddComponent(new Physics());
	}
	
	public void Update(){
		// foreach GameEntity -> ge.Update() ???
	}
	
}
