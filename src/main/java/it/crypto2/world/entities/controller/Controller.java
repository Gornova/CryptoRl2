package it.crypto2.world.entities.controller;

import it.marteEngine.World;
import it.marteEngine.entity.Entity;

// handle creature controller logic
public interface Controller {

	void setWorld(World world);

	World getWorld();

	void update(int delta);

	Entity getEntity();

}
