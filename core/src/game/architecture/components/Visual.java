package game.architecture.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.collision.Ray;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.RenderSystem;

public class Visual extends Component {

	private TextureRegion texture;
	private boolean isWobbel = false;
	private float wobbel = 0;
	private int count = 0;
	private Color col;
	
	public Visual(GameEntity e){
		super(e);
		col = Color.WHITE;
		ServiceLocator.GetService(RenderSystem.class).Add(this);
	}
	
	public void AddTexture(TextureRegion tex){	
		texture = tex;
	}
	
	public TextureRegion GetTexture(){
		return texture;
	}
	
	public void Render(SpriteBatch batch) {
		if (!isActive) return;
		Pose pos = (Pose)this.entity.getComponent(Pose.class);
		if (pos == null) return;
		pos.Update();
		batch.setColor(col);
		batch.draw(texture,
				pos.GetXPos()+wobbel, 
				pos.GetYPos()+wobbel,
				texture.getRegionWidth() / 2.0f,
				texture.getRegionHeight() / 2.0f, 
				texture.getRegionWidth(), 
				texture.getRegionHeight(), 
				1f, 
				1f,
				pos.GetAngle());
		
		if (count == 20) count = 0;
		else count++;
		
		if (isWobbel && count == 20) {
			if (wobbel <= 0) wobbel = 1;
			else wobbel = -1;
		}
	}

	@Override
	public void Update() {
		
	}

	public void toggleWobbel() {
		if (isWobbel) { 
			isWobbel = false;
			wobbel = 0;
		}
		else isWobbel = true;
	}

	public void SetColor(Color col) {
		this.col = col;
	}
}
