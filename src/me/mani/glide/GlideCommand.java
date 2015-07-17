package me.mani.glide;

import me.mani.glide.util.Messenger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class GlideCommand implements CommandExecutor {

	public GlideCommand(String label) {
		Glide.getInstance().getCommand(label).setExecutor(this);
	}
	
	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		String response = onCommand((Player) sender, args);
		if (response != null)
			Messenger.send((Player) sender, response);
		return true;
	}
	
	public abstract String onCommand(Player player, String[] args);

}
