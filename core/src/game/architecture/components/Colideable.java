package game.architecture.components;

import game.architecture.entity.GameEntity;

public class Colideable extends Component {

	public Colideable(GameEntity e) {
		super(e);
	}

	public boolean IsHit(float x, float y){
		return false;
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

}
