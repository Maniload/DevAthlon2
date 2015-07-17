package me.mani.glide.manager;

import me.mani.glide.GameState;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;

import org.bukkit.Sound;


public class GameManager {
	
	private static GameManager gameManager;
	
	public GameManager(SetupManager setupManager) {
		GameState.setGameState(GameState.NONE);
		gameManager = this;
	}
	
	public void startWarmUp() {
		Messenger.sendAll("Jetzt sollte das Spiel starten.");
		Effects.playAll(Sound.ORB_PICKUP);
	}
	
	private void startGame() {}

	public void finishGame() {}
	
	public static GameManager getGameManager() {
		return gameManager;
	}

}
