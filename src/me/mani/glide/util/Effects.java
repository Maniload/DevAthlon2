package me.mani.glide.util;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Effects {
	
	public static void playAll(Sound sound) {
		if (sound == null)
			return;
		for (Player player : Bukkit.getOnlinePlayers())
			play(player, sound);
	}
	
	public static void play(Player player, Sound sound) {
		if (sound != null)
			player.playSound(player.getLocation(), sound, 1f, 1f);
	}

}
