package me.mani.glide.listener;

import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.event.PlayerPassRingEvent;
import me.mani.glide.util.Title;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class PlayerPassRingListener extends GlideListener {
	
	@EventHandler
	public void onPlayerPassRing(PlayerPassRingEvent ev) {
		
		Player player = ev.getPlayer();
		GlidePlayer glidePlayer = GlidePlayer.getGlidePlayer(player);
		
		switch (ev.getRing().getType()) {
		case ENERGY:
			glidePlayer.setEnergy(glidePlayer.getEnergy() > .5f ? 1f :glidePlayer.getEnergy() + .5f);
			new Title("", "§eEnergiering!").send(player);
		case BIG_SPEED:
			glidePlayer.setSpeed(glidePlayer.getSpeed() + 0.1);
			new Title("", "§eGroßer Geschwindigkeitsring!").send(player);
			break;
		case CLEAR:
			Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
				if (!onlinePlayer.equals(player) && GlidePlayer.getGlidePlayer(onlinePlayer).isIngame())
					GlidePlayer.getGlidePlayer(onlinePlayer).setSpeed(GlidePlayer.getGlidePlayer(onlinePlayer).getSpeed() - 0.05);
			});
			new Title("", "§eGegnerschwächungsring!").send(player);
			break;
		case RANDOM:
			break;
		case SPEED:
			glidePlayer.setSpeed(glidePlayer.getSpeed() + 0.05);
			new Title("", "§eGeschwindigkeitsring!").send(player);
			break;
		case TELEPORT:
			
			break;
		}
		
	}

}
