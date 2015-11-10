package game.architecture.components;

import game.architecture.entity.GameEntity;

public class CameraTarget extends Component {
	
	public CameraTarget(GameEntity e){
		super(e);
	}
	
	public float GetXPos(){
		return 0;
	}
	
	public float GetYPos(){
		return 0;
	}
	
	public float GetAngle(){
		return 0;
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
