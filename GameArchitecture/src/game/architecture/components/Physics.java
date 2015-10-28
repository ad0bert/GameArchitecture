package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.systems.PhysicsSystem;

public class Physics extends Component implements Pose {

	public Physics(){
		ServiceLocator.GetService(PhysicsSystem.class).Add(this);
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void calc() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		if (!IsActive) return;
		
	}

}
