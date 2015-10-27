package game.architecture.engine;

import game.architecture.components.Visual;
import game.architecture.entity.GameEntity;
import game.architecture.systems.RenderSystem;
import game.architecture.systems.SystemTemplate;

public class Engine {
	private GameEntity ge;
	
	public Engine(){
		ge = new GameEntity();
		
		RenderSystem rs = new RenderSystem();
		ServiceLocator.AddService(rs);
		SystemTemplate st = ServiceLocator.GetService(RenderSystem.class);
		if (st != null)
			st.Add(new Visual());
	}
	
	public void Update(){
		ServiceLocator.Update();
	}
	
}
