package game.architecture.components;

public class CameraTarget extends Component {
	private Pose pose;
	
	public CameraTarget(Pose p){
		pose = p;
	}
	
	public float GetXPos(){
		return pose.GetXPos();
	}
	
	public float GetYPos(){
		return pose.GetYPos();
	}
	
	public float GetAngle(){
		return pose.GetAngle();
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}
}
