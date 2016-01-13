package game.architecture.components;

import java.io.Serializable;

import game.architecture.entity.GameEntity;

public abstract class Component implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean isActive = false;
	protected GameEntity entity;

	public void Activate(){
		isActive = true;
	}

	public void Deactivate(){
		isActive = false;
	}
	
	public GameEntity getEntity(){
		return entity;
	}
	
	public boolean getIsActive(){
		return isActive;
	}
	
	public void Update(){}
}
