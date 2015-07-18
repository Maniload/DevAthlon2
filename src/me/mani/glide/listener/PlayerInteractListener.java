package me.mani.glide.listener;

import java.util.ArrayList;
import java.util.List;

import me.mani.glide.GameState;
import me.mani.glide.Glide;
import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.manager.CountdownManager;
import me.mani.glide.map.Ring;
import me.mani.glide.map.Ring.Type;
import me.mani.glide.util.Effects;
import me.mani.glide.util.ItemUtil;
import me.mani.glide.util.Messenger;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

public class PlayerInteractListener extends GlideListener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		
		Player player = ev.getPlayer();
		Block block = ev.getClickedBlock();
		ItemStack itemStack = ev.getItem();
		Action action = ev.getAction();
		
		if (itemStack != null)
				if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
					if (itemStack.getType() == Material.FEATHER && GameState.getGameState() == GameState.INGAME) {
						player.setVelocity(player.getVelocity().setY(3.0).add(player.getLocation().getDirection().multiply(2.0)));
						CountdownManager.createCountdown((event) -> {
							player.getWorld().spigot().playEffect(player.getLocation(), Effect.SMOKE, 0, 4, 1f, 1f, 1f, 0, 100, 500);
							Effects.play(player, Sound.FIREWORK_BLAST);
						}, () -> {
							GlidePlayer.getGlidePlayer(player).setFlying(true);
							player.getInventory().setItem(8, ItemUtil.createItem(new ItemStack(Material.BARRIER), "§cHoverboard verlassen"));
						}, 0, 10, 1L);
						player.getInventory().remove(Material.FEATHER);
					}
					else if (itemStack.getType() == Material.BARRIER) {
						if (GlidePlayer.getGlidePlayer(player).isIngame() && GlidePlayer.getGlidePlayer(player).isFlying()) {
							GlidePlayer.getGlidePlayer(player).setFlying(false);
							Effects.play(player, Sound.ITEM_BREAK);
						}
						player.getInventory().remove(Material.BARRIER);
					}
					else if (itemStack.getType() == Material.STICK) {
						Glide.getInstance().reloadConfig();
						FileConfiguration configuration = Glide.getInstance().getConfig();
						@SuppressWarnings("unchecked")
						List<Location> list = (List<Location>) configuration.getList("spawnLocations", new ArrayList<>());
						list.add(player.getLocation());
						configuration.set("spawnLocations", list);
						Glide.getInstance().saveConfig();
						Messenger.send(player, "Neuer Spawn wurde gespeichert. Neue Anzahl: " + list.size());
						Messenger.send(player, "Ein Reload / Restart ist erforderlich, um zu spielen.");
					}
					else if (itemStack.getType() == Material.BLAZE_ROD) {
						if (block != null && block.getType() == Material.WOOL) {
							Glide.getInstance().reloadConfig();
							FileConfiguration configuration = Glide.getInstance().getConfig();
							@SuppressWarnings("unchecked")
							List<Ring> list = (List<Ring>) configuration.getList("rings", new ArrayList<>());
							list.add(new Ring(block.getLocation(), Type.getType(((Wool) block.getState().getData()).getColor())));
							configuration.set("rings", list);
							Glide.getInstance().saveConfig();
							Messenger.send(player, "Neuer Ring wurde gespeichert. Neue Anzahl: " + list.size());
							Messenger.send(player, "Ein Reload / Restart ist erforderlich, um zu spielen.");
						}
						else
							Messenger.send(player, "Dies ist kein gültiger Ring.");
					}
				}
				else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
					if (itemStack.getType() == Material.STICK) {
						Glide.getInstance().reloadConfig();
						FileConfiguration configuration = Glide.getInstance().getConfig();
						configuration.set("spawnLocations", new ArrayList<>());
						Glide.getInstance().saveConfig();
						Messenger.send(player, "Spawn zurückgesetzt. Alle Spawns wurden gelöscht.");
						Messenger.send(player, "Ein Reload / Restart ist erforderlich, um zu spielen.");
					}
					else if (itemStack.getType() == Material.BLAZE_ROD) {
						Glide.getInstance().reloadConfig();
						FileConfiguration configuration = Glide.getInstance().getConfig();
						@SuppressWarnings("unchecked")
						List<Ring> list = (List<Ring>) configuration.getList("rings", new ArrayList<>());
						if (list.size() > 0) {
							list.remove(list.size() - 1);
							configuration.set("rings", list);
							Glide.getInstance().saveConfig();
							Messenger.send(player, "Der letzte Ring wurde entfernt. Neue Anzahl: " + list.size());
							Messenger.send(player, "Ein Reload / Restart ist erforderlich, um zu spielen.");
						}
						else
							Messenger.send(player, "Es sind noch keine Ringe eingespeichert.");
					}
				}
		
	}
	
}
