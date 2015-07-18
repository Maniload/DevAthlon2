package me.mani.glide.listener;

import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.event.PlayerPassRingEvent;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;
import me.mani.glide.util.Title;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
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
			new Title("", "�eEnergiering!").send(player);
			break;
		case BIG_SPEED:
			glidePlayer.setSpeed(glidePlayer.getSpeed() + 0.1);
			new Title("", "�eGro�er Geschwindigkeitsring!").send(player);
			break;
		case CLEAR:
			Bukkit.getOnlinePlayers().forEach((onlinePlayer) -> {
				if (!onlinePlayer.equals(player) && GlidePlayer.getGlidePlayer(onlinePlayer).isIngame())
					GlidePlayer.getGlidePlayer(onlinePlayer).setSpeed(GlidePlayer.getGlidePlayer(onlinePlayer).getSpeed() - 0.05);
			});
			new Title("", "�eGegnerschw�chungsring!").send(player);
			break;
		case SPEED:
			glidePlayer.setSpeed(glidePlayer.getSpeed() + 0.05);
			new Title("", "�eGeschwindigkeitsring!").send(player);
			break;
		case TELEPORT:
			GlidePlayer headingPlayer = glidePlayer;
			for (Player onlinePlayer : Bukkit.getOnlinePlayers())
				if (!onlinePlayer.equals(player) && GlidePlayer.getGlidePlayer(onlinePlayer).isIngame() && GlidePlayer.getGlidePlayer(onlinePlayer).getDistance() < headingPlayer.getDistance())
					headingPlayer = GlidePlayer.getGlidePlayer(onlinePlayer);
			if (glidePlayer.equals(headingPlayer))
				Messenger.send(player, "Du bist schon der f�hrende Spieler.");
			else {
				headingPlayer.passRing(ev.getRing());
				Location headingLocation = headingPlayer.getPlayer().getLocation();
				headingPlayer.getPlayer().teleport(player);
				player.teleport(headingLocation);
				Effects.play(player, Sound.ENDERMAN_TELEPORT);
				Effects.play(headingPlayer.getPlayer(), Sound.ENDERMAN_TELEPORT);
			}
			break;
		}
		
		Effects.play(player, Sound.ORB_PICKUP);
		
	}

}
