package me.mani.glide.event;

import me.mani.glide.map.Ring;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerPassRingEvent extends PlayerEvent {
	
	private static final HandlerList handlers = new HandlerList();

	private Ring ring;
	
	public PlayerPassRingEvent(Player who, Ring ring) {
		super(who);
		this.ring = ring;
	}
	
	public Ring getRing() {
		return ring;
	}

	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
