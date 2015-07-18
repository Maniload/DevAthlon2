package me.mani.glide.manager;

import java.util.Iterator;

import me.mani.glide.GameState;
import me.mani.glide.Glide;
import me.mani.glide.GlidePlayer;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.util.Vector;



public class GameManager {
	
	public static final int MAP_LENGHT = 2500;
	public static final int START_X = 0;
	
	private static GameManager gameManager;
	
	public LocationManager locationManager;
	public InventoryManager inventoryManager;
	public ScoreboardManager scoreboardManager;
	
	public GameManager(SetupManager setupManager) {
		GameState.setGameState(GameState.NONE);
		locationManager = setupManager.locationManager;
		inventoryManager = new InventoryManager();
		scoreboardManager = new ScoreboardManager();
		gameManager = this;
	}
	
	public void startWarmUp() {
		GameState.setGameState(GameState.WARM_UP);
		Iterator<Location> it = locationManager.getSpawnLocations().iterator();
		Bukkit.getOnlinePlayers().forEach((player) -> {
			player.setGameMode(GameMode.ADVENTURE);
			player.setExp(0f);
			player.setLevel(0);
			player.teleport(it.next());
			inventoryManager.giveIngameInventory(player);
		});
		CountdownManager.createCountdown((ev) -> {
			
			if (ev.getCurrentNumber() == 15 || ev.getCurrentNumber() == 10 || ev.getCurrentNumber() <= 5) {
				ev.setMessage("Das Spiel startet in " + ev.getCurrentNumber() + " Sekunde" + (ev.getCurrentNumber() == 1 ? "" : "n") + ".");
				ev.setSound(Sound.FIZZ);
			}
			
		}, () -> startGame(), 15, 1, 20L);
	}
	
	private void startGame() {
		GameState.setGameState(GameState.INGAME);
		Bukkit.getOnlinePlayers().forEach((player) -> player.setWalkSpeed(0.5f));
		Bukkit.getScheduler().runTaskTimer(Glide.getInstance(), () -> {
			
			Bukkit.getOnlinePlayers().forEach((player) -> {
				GlidePlayer glidePlayer = GlidePlayer.getGlidePlayer(player);
				if (glidePlayer.isFlying()) {
					double speed = glidePlayer.getSpeed();
					Vector direction = player.getLocation().getDirection();
					double y = Math.min(Math.max(direction.getY(), -speed), speed);
					double z = Math.min(Math.max(direction.getZ(), -speed), speed);
					Vector vector = new Vector(-speed, y, z);
					player.setVelocity(vector);
					player.spigot().playEffect(player.getLocation(), Effect.CLOUD, 0, 0, 0.5f, 0.5f, 0.5f, 0, 50, 100);
					glidePlayer.setEnergy(glidePlayer.getEnergy() - 0.0005f);
					player.setExp(glidePlayer.getEnergy());
					if (glidePlayer.getEnergy() <= 0f)
						glidePlayer.setFlying(false);
					glidePlayer.setDistance(player.getLocation().getBlockX() + START_X - MAP_LENGHT);
				}
			});
			
		}, 0L, 1L);
	}

	public void finishGame() {}
	
	public static GameManager getGameManager() {
		return gameManager;
	}

}
