package game.architecture.components;

import game.architecture.entity.GameEntity;

public class BoxCollider extends Collideable {

	public BoxCollider(GameEntity e) {
		super(e);
	}

	@Override
	public boolean IsHit(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsHitCircle(float x, float y, float rad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsHitBox(float x, float y, float height, float width) {
		// TODO Auto-generated method stub
		return false;
	}

}
