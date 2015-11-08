package game.architecture.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.architecture.engine.ServiceLocator;
import game.architecture.systems.RenderSystem;

public class Visual extends Component {

	private TextureRegion texture;
	private Pose pos;
	
	public Visual(){
		ServiceLocator.GetService(RenderSystem.class).Add(this);
	}
	
	public void AddTexture(TextureRegion tex){	
		texture = tex;
	}
	
	public void AddPosition(Pose pos){
		this.pos = pos;
	}
	
	public void Render(SpriteBatch batch) {
		if (!IsActive) return;
		pos.Update();
		batch.draw(texture,
				pos.GetXPos(), pos.GetYPos(),
				texture.getRegionWidth() / 2.0f,
				texture.getRegionHeight() / 2.0f, 
				texture.getRegionWidth(), 
				texture.getRegionHeight(), 
				1f, 
				1f,
				pos.GetAngle());
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
