package it.crypto2.world.entities.controller;

import it.marteEngine.World;
import it.marteEngine.entity.Entity;

// handle creature controller logic
public interface Controller {

	public void setWorld(World world);

	public World getWorld();

	public void update(int delta);

	public Entity getEntity();

}
