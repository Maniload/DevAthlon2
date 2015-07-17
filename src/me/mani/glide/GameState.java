package me.mani.glide;

public enum GameState {
	NONE, WARM_UP, INGAME;
	
	private static GameState gameState;
	
	public static GameState getGameState() {
		return gameState;
	}
	
	public static void setGameState(GameState gameState) {
		GameState.gameState = gameState;
	}
	
}
