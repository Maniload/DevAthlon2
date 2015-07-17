package me.mani.glide.map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Ring {
	
	private static final int RING_WIDTH_SQUARED = 25;
	
	private Location location;
	private Type type;
	
	public Ring(Location location, Type type) {
		this.location = location;
		this.type = type;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isPassing(Player player) {
		return player.getLocation().distanceSquared(location) <= RING_WIDTH_SQUARED;
	}
	
	public enum Type {
		ENERGY,
		SPEED,
		BIG_SPEED,
		TELEPORT,
		CLEAR,
		RANDOM;
	}

}
