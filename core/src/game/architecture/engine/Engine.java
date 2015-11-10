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
	private EntityManager world;
	private Workbench workbench;
	
	public Engine(Workbench w){
		workbench = w;
		Init();
		Gdx.input.setInputProcessor(this);
	}
	
	public void Init() {
		world = new EntityManager();
		// --------------------------------------
		
		GameEntity wheel1 = new GameEntity();
		GameEntity wheel2 = new GameEntity();
		GameEntity wheel3 = new GameEntity();
		
		Visual v1 = new Visual(wheel1);
		Visual v2 = new Visual(wheel2);
		Visual v3 = new Visual(wheel3);
		
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("atlas.pack"));
		
		v1.AddTexture(ta.findRegion("cog1"));
		v2.AddTexture(ta.findRegion("cog1_shadow"));
		v3.AddTexture(ta.findRegion("cog_n"));
		
		wheel1.AddComponent(v1);
		wheel2.AddComponent(v2);
		wheel3.AddComponent(v3);
		
		Pose pos1 = new StaticRotatingPos(wheel1);
		Pose pos2 = new StaticRotatingPos(wheel2);
		Pose pos3 = new StaticRotatingPos(wheel3);
			
		pos1.SetXPos(-57);
		pos1.SetYPos(0);
		pos1.SetAngle(0);
		((StaticRotatingPos)pos1).setAngularSpeed(1);
		
		pos2.SetXPos(0);
		pos2.SetYPos(0);
		pos2.SetAngle(13);
		((StaticRotatingPos)pos2).setAngularSpeed(-1);
		
		pos3.SetXPos(57);
		pos3.SetYPos(0);
		pos3.SetAngle(0);
		((StaticRotatingPos)pos3).setAngularSpeed(1);
		
		wheel1.AddComponent((StaticRotatingPos)pos1);
		wheel2.AddComponent((StaticRotatingPos)pos2);
		wheel3.AddComponent((StaticRotatingPos)pos3);
		
		world.AddEntity(wheel1);
		world.AddEntity(wheel2);
		world.AddEntity(wheel3);
		
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
