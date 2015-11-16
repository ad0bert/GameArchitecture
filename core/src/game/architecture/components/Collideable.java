package game.architecture.components;

import game.architecture.entity.GameEntity;

public abstract class Collideable extends Component {

	public Collideable(GameEntity e) {
		super(e);
	}

	public abstract boolean IsHit(float x, float y);
	public abstract boolean IsHitCircle(float x, float y, float rad);
	public abstract boolean IsHitBox(float x, float y, float height, float width);

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
