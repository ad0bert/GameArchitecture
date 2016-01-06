package game.architecture.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

import game.architecture.components.Collideable;
import game.architecture.components.Physics;
import game.architecture.components.Pose;
import game.architecture.components.Visual;
import game.architecture.entity.EntityFactory;
import game.architecture.entity.EntityFactory.eWheel;
import game.architecture.entity.EntityManager;
import game.architecture.entity.GameEntity;
import game.architecture.menu.MenuScreen;
import game.architecture.menu.Workbench;

public class Physic extends ScreenAdapter implements InputProcessor {
	private EntityManager world;
	private Workbench workbench;
	private GameEntity selectedItem = null;
	private EntityFactory entityFactory = new EntityFactory();
	private boolean keyRdown = false;

	public Physic(Workbench w) {
		workbench = w;
		Init();
		Gdx.input.setInputProcessor(this);
	}

	public void Init() {
		world = new EntityManager();
		// --------------------------------------
		world.AddEntity(entityFactory.CreateWheelEntity(ServiceLocator.V_WIDTH / 2 - 57, ServiceLocator.V_HEIGHT / 2, 0,
				1, eWheel.Cog1));
		world.AddEntity(entityFactory.CreateWheelEntity(ServiceLocator.V_WIDTH / 2, ServiceLocator.V_HEIGHT / 2, 13, -1,
				eWheel.Cog_Shadow));
		world.AddEntity(entityFactory.CreateWheelEntity(ServiceLocator.V_WIDTH / 2 + 57, ServiceLocator.V_HEIGHT / 2, 0,
				1, eWheel.Cog_n));

		world.AddEntity(entityFactory.CreateBoxEntity(50, 50, 0));

		world.AddEntity(entityFactory.CreatePhysicsWheelEntity(50, 100, 0, 0, eWheel.Cog_n));
		world.AddEntity(entityFactory.CreatePhysicsWheelEntity(50, 400, 0, 0, eWheel.Cog_n));
		
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
		super.hide();
		dispose();
	}

	@Override
	public void resize(int width, int height) {
		ServiceLocator.V_HEIGHT = height;
		ServiceLocator.V_WIDTH = width;
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
		switch (keycode) {
		case Keys.ESCAPE:
			dispose();
			((Game) Gdx.app.getApplicationListener()).setScreen(new MenuScreen(workbench));
			break;
		case Keys.R:
			keyRdown = true;
			break;
		case Keys.D:
			if (selectedItem != null) {
				Collideable c = (Collideable)selectedItem.getComponent(Collideable.class);
				if (!c.isCollision())
					world.AddEntity(entityFactory.CreateDuplicate(selectedItem));
			}
			break;
		case Keys.X:
			if (selectedItem != null) {
				selectedItem.Deactivate();
				if (world.RemoveEntity(selectedItem))
					selectedItem = null;

			}
			break;
		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.R:
			keyRdown = false;
			break;
		}
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
		if (selectedItem != null) {
			Collideable c = (Collideable)selectedItem.getComponent(Collideable.class);
			if (!c.isCollision()) {
				((Visual) selectedItem.getComponent(Visual.class)).toggleWobbel();
				Physics p = ((Physics) selectedItem.getComponent(Physics.class));
				if (p != null)
					p.Activate();
				selectedItem = null;
			}
		} else {
			selectedItem = world.FindEntityByPos(screenX, screenY);
			if (selectedItem == null)
				return false;
			((Visual) selectedItem.getComponent(Visual.class)).toggleWobbel();
			Physics p = ((Physics) selectedItem.getComponent(Physics.class));
			if (p != null)
				p.Deactivate();
		}
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
		if (selectedItem != null) {
			Pose p = ((Pose) selectedItem.getComponent(Pose.class));
			Visual v = ((Visual) selectedItem.getComponent(Visual.class));
			if (keyRdown) {
				p.SetAngle(screenY % 180);
			} else {
				p.SetXPos(screenX - v.GetTexture().getRegionWidth() / 2);
				p.SetYPos(ServiceLocator.V_HEIGHT - screenY - v.GetTexture().getRegionHeight() / 2);
				Collideable c = (Collideable)selectedItem.getComponent(Collideable.class);
				if (c.isCollision()) {
					v.SetColor(Color.RED);
				} else {
					v.SetColor(Color.WHITE);
				}
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
