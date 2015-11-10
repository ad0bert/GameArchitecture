package game.architecture.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.RenderSystem;

public class Visual extends Component {

	private TextureRegion texture;
	
	public Visual(GameEntity e){
		super(e);
		ServiceLocator.GetService(RenderSystem.class).Add(this);
	}
	
	public void AddTexture(TextureRegion tex){	
		texture = tex;
	}
	
	public void Render(SpriteBatch batch) {
		if (!isActive) return;
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
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
