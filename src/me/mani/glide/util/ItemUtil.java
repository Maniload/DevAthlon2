package me.mani.glide.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class ItemUtil {

	public static ItemStack createItem(ItemStack itemStack, String displayName) {
		return createItem(itemStack, displayName, new String[]{});
	}
	
	public static ItemStack createItem(ItemStack itemStack, String displayName, String... lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore(Arrays.asList(lore));
		itemMeta.spigot().setUnbreakable(true);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public static ItemStack createItem(ItemStack itemStack, String displayName, PotionType potionType, int level, boolean extendet, boolean splash) {
		return createItem(itemStack, displayName, potionType, level, extendet, splash, new String[]{});
	}
	
	public static ItemStack createItem(ItemStack itemStack, String displayName, PotionType potionType, int level, boolean extendet, boolean splash, String... lore) {
		itemStack = createItem(itemStack, displayName, lore);
		if (itemStack.getType() != Material.POTION)
			itemStack.setType(Material.POTION);
		Potion potion = new Potion(potionType, level);
		if (extendet)
			potion.extend();
		potion.setSplash(splash);
		potion.apply(itemStack);
		return itemStack;
	}
	
}
