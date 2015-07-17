package me.mani.glide.listener;

import me.mani.glide.GlideListener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener extends GlideListener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		
		Player player = ev.getPlayer();
		
		if (ev.getFrom().getX() != ev.getTo().getX() || ev.getFrom().getZ() != ev.getTo().getZ())
			player.teleport(ev.getFrom());
		
	}
	
}
