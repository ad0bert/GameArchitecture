package game.architecture.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.architecture.components.Pose;
import game.architecture.components.StaticRotatingPos;
import game.architecture.components.Visual;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;
import game.architecture.menu.MenuScreen;
import game.architecture.menu.Workbench;

public class Engine implements Screen, InputProcessor{
	private EntityManager level1;
	private GameEntity world;
	private Workbench workbench;
	
	public Engine(Workbench w){
		workbench = w;
		Init();
		Gdx.input.setInputProcessor(this);
	}
	
	public void Init() {
		level1 = new EntityManager();
		// --------------------------------------
		
		world = new GameEntity();
		
		Visual v = new Visual();
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("atlas.pack"));
		v.AddTexture(ta.findRegion("cog1"));
		Pose pos = new StaticRotatingPos();
		pos.SetXPos(-57);
		pos.SetYPos(0);
		pos.SetAngle(0);
		((StaticRotatingPos)pos).setAngularSpeed(1);
		v.AddPosition(pos);
		
		Visual v2 = new Visual();
		v2.AddTexture(ta.findRegion("cog1_shadow"));
		Pose pos1 = new StaticRotatingPos();
		pos1.SetXPos(0);
		pos1.SetYPos(0);
		pos1.SetAngle(12);
		((StaticRotatingPos)pos1).setAngularSpeed(-1);
		v2.AddPosition(pos1);
		
		Visual v3 = new Visual();
		v3.AddTexture(ta.findRegion("cog_n"));
		Pose pos2 = new StaticRotatingPos();
		pos2.SetXPos(57);
		pos2.SetYPos(0);
		pos2.SetAngle(0);
		((StaticRotatingPos)pos2).setAngularSpeed(1);
		v3.AddPosition(pos2);
		
		world.AddComponent(v);	
		world.AddComponent(v2);
		world.AddComponent(v3);
		level1.AddEntity(world);
		// --------------------------------------
		
	}

	@Override
	public void show() {
		
		
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
		level1.RemoveEntity(world);
		world.Deactivate();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode){
		case Keys.ESCAPE:
			dispose();
			 ((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(workbench));
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
