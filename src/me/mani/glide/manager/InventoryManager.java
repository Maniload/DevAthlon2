package me.mani.glide.manager;

import me.mani.glide.util.ItemUtil;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {

	private ItemStack[] ingameInventoryContents;
	
	public InventoryManager() {
		prepareIngameInventory();
	}
	
	private void prepareIngameInventory() {
		ingameInventoryContents = new ItemStack[36];
		for (int i = 0; i <= 8; i++)
			ingameInventoryContents[i] = ItemUtil.createItem(new ItemStack(Material.FEATHER), "§7Hoverboard starten");
	}
	
	public void giveIngameInventory(Player player) {
		player.getInventory().clear();
		player.getInventory().setContents(ingameInventoryContents);
	}
	
}
