package me.mani.glide.manager;

import java.util.List;

import org.bukkit.Location;

public class LocationManager {
	
	private final List<Location> spawnLocations;
	
	public LocationManager(List<Location> spawnLocations) {
		this.spawnLocations = spawnLocations;
	}
	
	public List<Location> getSpawnLocations() {
		return spawnLocations;
	}

}
