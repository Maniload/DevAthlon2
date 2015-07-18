package me.mani.glide.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.manager.GameManager;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;

public class EntityDamageListener extends GlideListener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent ev) {
		
		Entity entity = ev.getEntity();
		
		if (entity instanceof Player) {
			if (ev.getCause() == DamageCause.VOID) {
				((Player) entity).setGameMode(GameMode.SPECTATOR);
				entity.teleport(GameManager.getGameManager().locationManager.getSpawnLocations().get(0));
				GlidePlayer.getGlidePlayer((Player) entity).setIngame(false);
				Messenger.sendAll(entity.getName() + " ist raus.");
				Effects.playAll(Sound.AMBIENCE_THUNDER);
				Player possibleWinningPlayer = null;
				int playerLeft = 0;
				for (Player onlinePlayer : Bukkit.getOnlinePlayers())
					if (GlidePlayer.getGlidePlayer(onlinePlayer).isIngame()) {
						possibleWinningPlayer = onlinePlayer;
						playerLeft++;
					}
				if (playerLeft <= 1)
					GameManager.getGameManager().finishGame(possibleWinningPlayer);
			}
			ev.setCancelled(true);
		}
		
	}
	
}
