package game.architecture.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import game.architecture.components.Pose;
import game.architecture.components.StaticPos;
import game.architecture.components.Visual;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;

public class Engine extends Game{
	private EntityManager Level1;
	private GameEntity World;

	public void Init() {
		Level1 = new EntityManager();
		// --------------------------------------
		
		World = new GameEntity();
		
		Visual v = new Visual();
		v.AddTexture("button.pack");
		Pose pos = new StaticPos();
		pos.SetXPos(100);
		pos.SetYPos(100);
		v.AddPosition(pos);
		
		Visual v2 = new Visual();
		v2.AddTexture("button.pack");
		Pose pos1 = new StaticPos();
		pos1.SetXPos(200);
		pos1.SetYPos(200);
		v2.AddPosition(pos1);
		
		World.AddComponent(v);	
		World.AddComponent(v2);
		Level1.AddEntity(World);
		// --------------------------------------
		
	}

	@Override
	public void create () {
		Init();
	}

	@Override
	public void render () {
		super.render();
		ServiceLocator.Update();
	}

}
