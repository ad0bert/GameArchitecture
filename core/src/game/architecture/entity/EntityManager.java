package game.architecture.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import game.architecture.components.Persistable;
import game.architecture.components.Visual;
import game.architecture.components.collider.Collideable;


public class EntityManager implements Disposable {

	private List<GameEntity> entities;
	
	public EntityManager(){
		entities = new ArrayList<GameEntity>();
	}
	
	public boolean AddEntity(GameEntity ge){
		return entities.add(ge);
	}
	
	public boolean RemoveEntity(GameEntity ge){
		boolean result =  entities.remove(ge);
		ge.dispose();
		return result;
	}

	@Override
	public void dispose() {
		for (GameEntity ge : entities){
			ge.Deactivate();
			ge.dispose();
		}
			
		entities.clear();
	}

	public void Activate() {
		for (GameEntity e : entities)
			e.Activate();
	}

	public GameEntity FindEntityByPos(int screenX, int screenY) {
		for (GameEntity ge : entities){
			try {
				Collideable c = (Collideable)ge.getComponent(Collideable.class);
				if (c.IsHit(screenX, screenY))
					return ge;
			}catch(Exception e){
				// do nothing
			}
		}
		return null;
	}
	
	public void store(File file) throws IOException {
		List<GameEntity> geList = new ArrayList<GameEntity>();
		
		for (GameEntity ge : entities){
			if (ge.getComponent(Persistable.class) != null)
				geList.add(ge);
		}
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {		
			out.writeObject(geList);
		}		
	}
	
	public void restore(File file) throws FileNotFoundException, IOException {
		this.dispose();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
			
			@SuppressWarnings("unchecked")
			List<GameEntity> restored = (List<GameEntity>) in.readObject();
			for (GameEntity ge : restored) {
				Visual v = (Visual)ge.getComponent(Visual.class);
				if (v != null)
				{
					String atlas;
					if (v.getTextureName().equals("texture")){
						atlas = "platform.pack";
					} else {
						atlas = "atlas.pack";
					}
					TextureAtlas ta = new TextureAtlas(Gdx.files.internal(atlas));
					v.AddTexture(ta.findRegion(v.getTextureName()), v.getTextureName());
					v.SetColor(Color.WHITE);
				}
				ge.Activate();
				
				entities.add(ge);
			}
			
		} catch (ClassNotFoundException e) {
			throw new IOException("invalid world file ", e);
		}
	}
	
}
