package game.architecture.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.architecture.engine.ServiceLocator;
import game.architecture.systems.RenderSystem;

public class Visual extends Component {

	private Texture texture;
	private Pose pos;
	
	public Visual(){
		ServiceLocator.GetService(RenderSystem.class).Add(this);
	}
	
	public void AddTexture(Texture tex){
		
		texture = tex;
	}
	
	public void AddPosition(Pose pos){
		this.pos = pos;
	}
	
	public void Render(SpriteBatch batch) {
		if (!IsActive) return;
		batch.draw(texture, pos.GetXPos(), pos.GetYPos());
	}
}
