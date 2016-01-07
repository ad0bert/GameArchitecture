package game.architecture.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.architecture.components.*;
import game.architecture.components.collider.*;
import game.architecture.components.physics.Body;

public class EntityFactory {
	public enum eFactory{Edit, Play};
	public enum eWheel{Cog1, Cog_Shadow, Cog_n};
	
	public GameEntity CreateWheelEntity(float x, float y, float angle, float speed, eWheel type){
		GameEntity wheel = new GameEntity();
		Visual visual = new Visual(wheel);
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("atlas.pack"));
		switch(type){
		case Cog1:
			visual.AddTexture(ta.findRegion("cog1"));
			break;
		case Cog_Shadow:
			visual.AddTexture(ta.findRegion("cog1_shadow"));
			break;
		case Cog_n:
			visual.AddTexture(ta.findRegion("cog_n"));
			break;
		default:
			visual.AddTexture(ta.findRegion("cog1"));
			break;
		}
		wheel.AddComponent(visual);
		Pose pos = new StaticRotatingPos(wheel);
		pos.SetXPos(x);
		pos.SetYPos(y);
		pos.SetAngle(angle);
		pos.SetXScale(1);
		pos.SetYScale(1);
		((StaticRotatingPos)pos).setAngularSpeed(speed);
		wheel.AddComponent((StaticRotatingPos)pos);
		
		AbstractCollider col = new CircleCollider(wheel);
		wheel.AddComponent(col);
		
		return wheel;
	}
	
	public GameEntity CreatePhysicsWheelEntity(float x, float y, float angle, float speed, eWheel type){
		GameEntity wheel = new GameEntity();
		Visual visual = new Visual(wheel);
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("atlas.pack"));
		switch(type){
		case Cog1:
			visual.AddTexture(ta.findRegion("cog1"));
			break;
		case Cog_Shadow:
			visual.AddTexture(ta.findRegion("cog1_shadow"));
			break;
		case Cog_n:
			visual.AddTexture(ta.findRegion("cog_n"));
			break;
		default:
			visual.AddTexture(ta.findRegion("cog1"));
			break;
		}
		wheel.AddComponent(visual);
		Pose pos = new Body(wheel);
		pos.SetXPos(x);
		pos.SetYPos(y);
		pos.SetAngle(angle);
		pos.SetXScale(1);
		pos.SetYScale(1);
		((Body)pos).mass(1);

		wheel.AddComponent((Body)pos);
		
		AbstractCollider col = new CircleCollider(wheel);
		wheel.AddComponent(col);
		
		return wheel;
	}
	
	public GameEntity CreateBoxEntity(float x, float y, float angle){
		GameEntity plattform = new GameEntity();
		Visual visual = new Visual(plattform);
		TextureAtlas ta = new TextureAtlas(Gdx.files.internal("platform.pack"));
		visual.AddTexture(ta.findRegion("texture"));
		plattform.AddComponent(visual);
		Pose pos = new StaticPos(plattform);
		pos.SetXPos(x);
		pos.SetYPos(y);
		pos.SetAngle(angle);
		pos.SetXScale(1);
		pos.SetYScale(1);
		plattform.AddComponent((StaticPos)pos);
		AbstractCollider col = new BoxCollider(plattform);
		plattform.AddComponent(col);
		return plattform;
	}

	public GameEntity CreateDuplicate(GameEntity ge) {
		GameEntity entity = new GameEntity(ge);
		entity.Activate();
		return entity;
	}
}
