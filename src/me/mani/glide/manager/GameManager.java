package me.mani.glide.manager;

import me.mani.glide.GameState;
import me.mani.glide.Glide;
import me.mani.glide.GlidePlayer;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.util.Vector;



public class GameManager {
	
	private static GameManager gameManager;
	
	public GameManager(SetupManager setupManager) {
		GameState.setGameState(GameState.NONE);
		gameManager = this;
	}
	
	public void startWarmUp() {
		Messenger.sendAll("Jetzt sollte das Spiel starten.");
		Effects.playAll(Sound.ORB_PICKUP);
		Bukkit.getScheduler().runTaskTimer(Glide.getInstance(), () -> {
			
			Bukkit.getOnlinePlayers().forEach((player) -> {
				if (GlidePlayer.getGlidePlayer(player).isFlying()) {
					Vector direction = player.getLocation().getDirection();
					double y = Math.min(Math.max(direction.getY(), -1.3), 1.3);
					double z = Math.min(Math.max(direction.getZ(), -1.3), 1.3);
					Vector vector = new Vector(-1.3, y, z);
					player.setVelocity(vector);
				}
			});
			
		}, 0L, 1L);
	}
	
	private void startGame() {}

	public void finishGame() {}
	
	public static GameManager getGameManager() {
		return gameManager;
	}

}
