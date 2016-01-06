package game.architecture.components;

import game.architecture.entity.GameEntity;

public abstract class AbstractCollider extends Component implements Collideable{

	private GameEntity opponent;

	/**
	 * Clears the collision(s) of this collider.
	 */
	public void clearCollision() {
		opponent = null;
	}

	/**
	 * Sets the collision entity of this collider.
	 * 
	 * @param e
	 *            the entity that has been detected to interpenetrate with this
	 *            collider.
	 */
	public void setCollision(GameEntity e) {
		opponent = e;
	}
	
	@Override
	public GameEntity getCollision() {
		return opponent;
	}

	@Override
	public boolean isCollision() {
		return opponent != null;
	}
}
