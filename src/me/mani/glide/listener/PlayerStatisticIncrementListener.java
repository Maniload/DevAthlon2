package me.mani.glide.listener;

import me.mani.glide.GlideListener;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

public class PlayerStatisticIncrementListener extends GlideListener {
	
	@EventHandler
	public void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent ev) {
		
		Player player = ev.getPlayer();
		
		if (ev.getStatistic() == Statistic.JUMP && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOL)
			player.setVelocity(player.getVelocity().setY(4.0));
		
	}

}
