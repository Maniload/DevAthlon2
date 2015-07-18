package me.mani.glide.manager;

import java.util.Iterator;

import me.mani.glide.GameState;
import me.mani.glide.Glide;
import me.mani.glide.GlidePlayer;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;



public class GameManager {
	
	public static final int MAP_LENGHT = 3000;
	
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
			player.setScoreboard(scoreboardManager.getScoreboard());
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
		Messenger.sendAll("Los gehts!");
		Bukkit.getOnlinePlayers().forEach((player) -> {
			player.setWalkSpeed(0.7f);
			GlidePlayer.getGlidePlayer(player).setIngame(true);
		});
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
					player.getWorld().spigot().playEffect(player.getLocation(), Effect.CLOUD, 0, 0, 0.1f, 0.1f, 0.1f, 0, 10, 500);
					glidePlayer.setEnergy(glidePlayer.getEnergy() - 0.003f);
					player.setExp(glidePlayer.getEnergy());
					if (glidePlayer.getEnergy() <= 0f)
						glidePlayer.setFlying(false);
					glidePlayer.setDistance(MAP_LENGHT + player.getLocation().getBlockX());
					scoreboardManager.setScore(player, glidePlayer.getDistance());
					Effects.play(player, Sound.ENDERDRAGON_WINGS);
				}
			});
			
		}, 0L, 1L);
	}

	public void finishGame(Player winningPlayer) {
		GameState.setGameState(GameState.NONE);
		Messenger.sendAll(winningPlayer.getName() + " hat das Spiel gewonnen.");
		Effects.playAll(Sound.LEVEL_UP);
	}
	
	public static GameManager getGameManager() {
		return gameManager;
	}

}
