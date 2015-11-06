package game.architecture.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.architecture.components.Pose;
import game.architecture.components.StaticRotatingPos;
import game.architecture.components.Visual;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;

public class Engine implements Screen{
	private EntityManager Level1;
	private GameEntity World;

	public Engine(){
		Init();
	}
	
	public void Init() {
		Level1 = new EntityManager();
		// --------------------------------------
		
		World = new GameEntity();
		
		Visual v = new Visual();
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("atlas.pack"));
		v.AddTexture(ta.findRegion("cog1"));
		Pose pos = new StaticRotatingPos();
		pos.SetXPos(200);
		pos.SetYPos(200);
		pos.SetAngle(0);
		((StaticRotatingPos)pos).setAngularSpeed(1);
		v.AddPosition(pos);
		
		Visual v2 = new Visual();
		v2.AddTexture(ta.findRegion("cog1_shadow"));
		Pose pos1 = new StaticRotatingPos();
		pos1.SetXPos(257);
		pos1.SetYPos(200);
		pos1.SetAngle(12);
		((StaticRotatingPos)pos1).setAngularSpeed(-1);
		v2.AddPosition(pos1);
		
		Visual v3 = new Visual();
		v3.AddTexture(ta.findRegion("cog_n"));
		Pose pos2 = new StaticRotatingPos();
		pos2.SetXPos(314);
		pos2.SetYPos(200);
		pos2.SetAngle(0);
		((StaticRotatingPos)pos2).setAngularSpeed(1);
		v3.AddPosition(pos2);
		
		World.AddComponent(v);	
		World.AddComponent(v2);
		World.AddComponent(v3);
		Level1.AddEntity(World);
		// --------------------------------------
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ServiceLocator.Update();
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
