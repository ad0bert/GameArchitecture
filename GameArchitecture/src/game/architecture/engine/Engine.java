package game.architecture.engine;

import game.architecture.components.GameLogic;
import game.architecture.components.Physics;
import game.architecture.components.Visual;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;

public class Engine {
	private EntityManager Level1;
	private GameEntity World;
	private GameEntity Player;
	
	public Engine() {
		Init();
		GameLoop();
	}

	public void Init() {
		Level1 = new EntityManager();
		// --------------------------------------
		World = new GameEntity();
		World.AddComponent(new GameLogic());
		World.AddComponent(new Visual());
		Level1.AddEntity(World);
		// --------------------------------------
		Player = new GameEntity();
		Player.AddComponent(new Visual());
		Player.AddComponent(new Physics());
		Player.AddComponent(new GameLogic());
		Level1.AddEntity(Player);
		// --------------------------------------
		
	}
	
	public void GameLoop(){
		while(ServiceLocator.isRunning())
			ServiceLocator.Update();
	}

}
