package me.mani.glide.command;

import me.mani.glide.GameState;
import me.mani.glide.GlideCommand;
import me.mani.glide.manager.GameManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
		
		GameManager.getGameManager().startWarmUp();	
		return "Das Spiel wurde gestartet.";
	}

}
