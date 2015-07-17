package me.mani.glide;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class GlidePlayer {
	
	private static Map<Player, GlidePlayer> glidePlayers = new HashMap<>();
	private Player player;	
	private boolean ingame;
	private boolean flying;
	
	private GlidePlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isIngame() {
		return ingame;
	}
	
	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}
	
	public boolean isFlying() {
		return flying;
	}
	
	public void setFlying(boolean flying) {
		
	}
	
	public static GlidePlayer getGlidePlayer(Player player) {
		if (!glidePlayers.containsKey(player))
			glidePlayers.put(player, new GlidePlayer(player));
		return glidePlayers.get(player);
	}

}
