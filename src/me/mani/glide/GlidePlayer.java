package me.mani.glide;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.mani.glide.map.Ring;

import org.bukkit.entity.Player;

public class GlidePlayer {
	
	private static Map<Player, GlidePlayer> glidePlayers = new HashMap<>();
	private Player player;	
	private boolean ingame;
	private boolean flying;
	private double speed = 1.2;
	private float energy = .5f;
	private int distance;
	private Set<Ring> passedRings;
	
	private GlidePlayer(Player player) {
		this.player = player;
		passedRings = new HashSet<>();
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
		this.flying = flying;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public float getEnergy() {
		return energy;
	}
	
	public void setEnergy(float energy) {
		this.energy = energy;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public boolean passedRing(Ring ring) {
		return passedRings.contains(ring);
	}
	
	public void passRing(Ring ring) {
		passedRings.add(ring);
	}
	
	public static GlidePlayer getGlidePlayer(Player player) {
		if (!glidePlayers.containsKey(player))
			glidePlayers.put(player, new GlidePlayer(player));
		return glidePlayers.get(player);
	}

}
