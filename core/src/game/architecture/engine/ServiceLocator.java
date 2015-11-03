package game.architecture.engine;

import java.util.ArrayList;
import java.util.List;

import game.architecture.systems.PhysicsSystem;
import game.architecture.systems.RenderSystem;
import game.architecture.systems.SystemTemplate;

public final class ServiceLocator {

	private static List<SystemTemplate> systems;
	private static boolean isRunning = true;
	
	static {
		systems = new ArrayList<SystemTemplate>();
		AddService(new RenderSystem());
		AddService(new PhysicsSystem());
	}

	private static void AddService(SystemTemplate st) {
		systems.add(st);
	}

	public static SystemTemplate GetService(Class c) {
		for (SystemTemplate st : systems) {
			if (st.getClass().equals(c))
				return st;
		}
		return null;
	}

	public static void Update() {
		for (SystemTemplate st : systems)
			st.Update();
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		ServiceLocator.isRunning = isRunning;
	}
}