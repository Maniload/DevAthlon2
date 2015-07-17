package me.mani.glide.listener;

import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.manager.CountdownManager;
import me.mani.glide.util.Effects;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener extends GlideListener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		Player player = ev.getPlayer();
		ItemStack itemStack = ev.getItem();
		Action action = ev.getAction();
		
		if (itemStack != null && (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)) {
			if (itemStack.getType() == Material.FEATHER) {
				player.setVelocity(player.getVelocity().setY(3.0).add(player.getLocation().getDirection().multiply(2.0)));
				CountdownManager.createCountdown((event) -> {
					player.spigot().playEffect(player.getLocation(), Effect.SMOKE, 0, 4, 1f, 1f, 1f, 0, 100, 100);
					Effects.play(player, Sound.FIREWORK_BLAST);
				}, () -> GlidePlayer.getGlidePlayer(player).setFlying(true), 0, 10, 1L);
				player.getInventory().remove(Material.FEATHER);
			}
		}
		
	}
	
}
