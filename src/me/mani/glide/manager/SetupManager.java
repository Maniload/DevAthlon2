package me.mani.glide.manager;

import java.util.ArrayList;
import java.util.List;

import me.mani.glide.command.StartCommand;
import me.mani.glide.listener.PlayerInteractListener;
import me.mani.glide.listener.PlayerJoinListener;
import me.mani.glide.listener.PlayerMoveListener;
import me.mani.glide.listener.PlayerQuitListener;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SetupManager {

	private FileConfiguration config;
	public LocationManager locationManager;
	
	public SetupManager(FileConfiguration config) {
		this.config = config;
		setup();
	}
	
	private void setup() {
		setupLocations();
		setupManagers();
		setupListener();
		setupCommands();
	}
	
	@SuppressWarnings("unchecked")
	private void setupLocations() {
		locationManager = new LocationManager((List<Location>) config.getList("spawnLocations", new ArrayList<>()));
	}
	
	private void setupManagers() {}
	
	private void setupListener() {
		new PlayerJoinListener();
		new PlayerMoveListener();
		new PlayerQuitListener();
		new PlayerInteractListener();
	}
	
	private void setupCommands() {
		new StartCommand();
	}
	
}
