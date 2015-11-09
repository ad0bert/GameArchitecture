package game.architecture.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.architecture.components.Component;
import game.architecture.components.Visual;

public class RenderSystem extends SystemTemplate {

	private SpriteBatch batch = new SpriteBatch();
	
	@Override
	public void Update() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		for (Component c : comps){
			((Visual)c).Render(batch);
		}
		batch.end();
	}

	@Override
	public void Add(Component c) {
		comps.add(c);
	}

	@Override
	public void Remove(Component c) {
		comps.remove(c);
	}
}
