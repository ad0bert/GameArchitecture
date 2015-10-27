package game.architecture.components;

import game.architecture.engine.ServiceLocator;
import game.architecture.systems.RenderSystem;

public class Visual extends Component {

	public Visual(){
		ServiceLocator.GetService(RenderSystem.class).Add(this);
	}
	
	@Override
	public void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Activate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void Deactivate() {
		// TODO Auto-generated method stub

	}

}
