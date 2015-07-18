package me.mani.glide;

import me.mani.glide.manager.GameManager;
import me.mani.glide.manager.SetupManager;
import me.mani.glide.map.Ring;
import me.mani.glide.util.Messenger;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Glide extends JavaPlugin {

	private static Glide instance;
	
	@Override
	public void onEnable() {
		instance = this;
		Messenger.setPrefix("§8[§bGlide§8] §7");
		
		Messenger.sendAll("Glide - by Overload & Laubfrosch7");
		
		ConfigurationSerialization.registerClass(Ring.class, "Ring");
		new GameManager(new SetupManager(getConfig()));
	}

	public static Glide getInstance() {
		return instance;
	}
	
}
