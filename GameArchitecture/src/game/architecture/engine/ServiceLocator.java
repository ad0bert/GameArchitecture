package game.architecture.engine;

import java.util.ArrayList;
import java.util.List;

import game.architecture.systems.RenderSystem;
import game.architecture.systems.SystemTemplate;

public final class ServiceLocator {

	private static List<SystemTemplate> systems;
	
	private ServiceLocator(){
		systems = new ArrayList<SystemTemplate>();
	}
	
	public static void AddService(SystemTemplate st){
		systems.add(st);
	}
	public static SystemTemplate GetService(Class<RenderSystem> c){
		for (SystemTemplate st : systems){
			if (st.getClass().equals(c))
				return st;
		}
		return null;
	}
	
	public static void Update(){
		for (SystemTemplate st : systems)
			st.Update();
	}
}
