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
import game.architecture.entity.EntityFactory;
import game.architecture.entity.EntityFactory.eWheel;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;
import game.architecture.menu.MenuScreen;
import game.architecture.menu.Workbench;

public class Editor implements Screen, InputProcessor{
	private EntityManager world;
	private Workbench workbench;
	private EntityFactory entityFactory = new EntityFactory();
	
	public Editor(Workbench w){
		workbench = w;
		Init();
		Gdx.input.setInputProcessor(this);
	}
	
	public void Init() {
		world = new EntityManager();
		// --------------------------------------
		world.AddEntity(entityFactory.CreateWheelEntity(-57, 0, 0, 1, eWheel.Cog1));
		world.AddEntity(entityFactory.CreateWheelEntity(0, 0, 13, -1, eWheel.Cog_Shadow));
		world.AddEntity(entityFactory.CreateWheelEntity(57, 0, 0, 1, eWheel.Cog_n));
		
		world.Activate();
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
		world.dispose();
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
		GameEntity ge = world.FindEntityByPos(screenX, screenY);
		if (ge == null)
			return false;
		return true;
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
