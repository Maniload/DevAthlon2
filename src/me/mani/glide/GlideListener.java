package me.mani.glide;

import org.bukkit.event.Listener;

public class GlideListener implements Listener {
	
	public GlideListener() {
		Glide.getInstance().getServer().getPluginManager().registerEvents(this, Glide.getInstance());
	}

}
