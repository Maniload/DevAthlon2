package me.mani.glide.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.mani.glide.command.StartCommand;
import me.mani.glide.listener.EntityDamageListener;
import me.mani.glide.listener.PlayerInteractListener;
import me.mani.glide.listener.PlayerJoinListener;
import me.mani.glide.listener.PlayerMoveListener;
import me.mani.glide.listener.PlayerPassRingListener;
import me.mani.glide.listener.PlayerQuitListener;
import me.mani.glide.map.Ring;

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
		setupRings();
		setupListener();
		setupCommands();
	}
	
	@SuppressWarnings("unchecked")
	private void setupLocations() {
		locationManager = new LocationManager((List<Location>) config.getList("spawnLocations", new ArrayList<>()));
	}
	
	@SuppressWarnings("unchecked")
	private void setupRings() {
		Ring.addAll((Collection<Ring>) config.getList("rings", new ArrayList<>()));
	}
	
	private void setupListener() {
		new PlayerJoinListener();
		new PlayerMoveListener();
		new PlayerQuitListener();
		new PlayerInteractListener();
		new PlayerPassRingListener();
		new EntityDamageListener();
	}
	
	private void setupCommands() {
		new StartCommand();
	}
	
}
