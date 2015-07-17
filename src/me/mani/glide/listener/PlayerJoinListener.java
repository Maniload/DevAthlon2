package me.mani.glide.listener;

import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener extends GlideListener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		
		GlidePlayer.getGlidePlayer(ev.getPlayer());
		
	}
	
}
