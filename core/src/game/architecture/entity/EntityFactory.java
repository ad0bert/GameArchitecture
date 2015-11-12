package game.architecture.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import game.architecture.components.Collideable;
import game.architecture.components.Pose;
import game.architecture.components.StaticRotatingPos;
import game.architecture.components.Visual;



public class EntityFactory {
	public enum eFactory{Edit, Play};
	public enum eWheel{Cog1, Cog_Shadow, Cog_n};
	
	public GameEntity CreateWheelEntity(int x, int y, int angle, int speed, eWheel type){
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
		((StaticRotatingPos)pos).setAngularSpeed(speed);
		wheel.AddComponent((StaticRotatingPos)pos);
		
		Collideable col = new Collideable(wheel);
		wheel.AddComponent(col);
		
		return wheel;
	}
	
	public GameEntity CreateBoxEntity(){
		return null;
	}
}
