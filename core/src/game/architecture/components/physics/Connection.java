package game.architecture.components.physics;

public interface Connection {

	public Body getBody(int idx) throws IndexOutOfBoundsException;
}
