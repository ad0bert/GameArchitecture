package game.architecture.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
		((StaticRotatingPos)pos).Update();
//		batch.draw(texture,  pos.GetXPos(), pos.GetYPos(), 
//				(Gdx.graphics.getHeight()-texture.getRegionHeight())/2, 
//				(Gdx.graphics.getWidth()-texture.getRegionWidth())/2, 
//				texture.getRegionWidth(), 
//				texture.getRegionHeight(), 
//				1, 1, 
//				pos.GetAngle());
		
		
		batch.draw(texture,
				pos.GetXPos(), pos.GetYPos(),
				texture.getRegionWidth()/2.0f,
				texture.getRegionHeight()/2.0f, 
				texture.getRegionWidth(), 
				texture.getRegionHeight(), 
				1f, 
				1f,
				pos.GetAngle());
		
		//batch.draw(texture, pos.GetXPos(), pos.GetYPos());
	}
}
