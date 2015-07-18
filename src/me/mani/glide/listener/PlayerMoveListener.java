package me.mani.glide.listener;

import me.mani.glide.GameState;
import me.mani.glide.GlideListener;
import me.mani.glide.GlidePlayer;
import me.mani.glide.event.PlayerPassRingEvent;
import me.mani.glide.manager.GameManager;
import me.mani.glide.map.Ring;






import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener extends GlideListener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		
		Player player = ev.getPlayer();
		
		if (GameState.getGameState() == GameState.WARM_UP && (ev.getFrom().getX() != ev.getTo().getX() || ev.getFrom().getZ() != ev.getTo().getZ()))
			player.teleport(ev.getFrom());
		else if (GameState.getGameState() == GameState.INGAME) {
			Ring.getRings().forEach((ring) -> {
				if (!GlidePlayer.getGlidePlayer(player).passedRing(ring) && ring.isPassing(player)) {
					GlidePlayer.getGlidePlayer(player).passRing(ring);
					Bukkit.getPluginManager().callEvent(new PlayerPassRingEvent(player, ring));
				}
			});
			if (GlidePlayer.getGlidePlayer(player).isIngame() && player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STAINED_GLASS)
				GameManager.getGameManager().finishGame(player);
		}
		
	}
	
}
