package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.entity.GameEntity;
import game.architecture.systems.CollisionSystem;

public abstract class Collideable extends Component {

	public Collideable(GameEntity e) {
		super(e);
		ServiceLocator.GetService(CollisionSystem.class).Add(this);
	}

	public Collideable(Collideable c, GameEntity e) {
		super(c, e);
		ServiceLocator.GetService(CollisionSystem.class).Add(this);
	}
	
	public abstract boolean IsHit(float x, float y);
	public abstract boolean IsHit(Collideable c);
	public abstract boolean IsHit(CircleCollider c);
	public abstract boolean IsHit(BoxCollider c);

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
