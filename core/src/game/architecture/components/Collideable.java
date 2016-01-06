package game.architecture.components;

import game.architecture.entity.GameEntity;

public interface Collideable {

	public boolean IsHit(float x, float y);
	public boolean IsHit(Collideable c);
	public boolean IsHit(CircleCollider c);
	public boolean IsHit(BoxCollider c);

	public GameEntity getCollision();
	public boolean isCollision();

}
