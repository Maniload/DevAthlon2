package me.mani.glide.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.mani.glide.GameState;
import me.mani.glide.GlideCommand;

public class StartCommand extends GlideCommand {

	public StartCommand() {
		super("start");
	}

	@Override
	public String onCommand(Player player, String[] args) {
		
		if (GameState.getGameState() != GameState.NONE)
			return "Das Spiel ist bereits gestartet.";
		else if (Bukkit.getOnlinePlayers().size() < 4 && !(args.length == 1 && args[0].equalsIgnoreCase("force")))
			return "Es werden mindestens 4 Spieler benötigt.";
		
		
		
		return "/start";
	}

}
