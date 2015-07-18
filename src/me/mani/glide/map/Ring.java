package me.mani.glide.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs (value="Ring")
public class Ring implements ConfigurationSerializable {
	
	private static final int RING_WIDTH_SQUARED = 25;
	
	private static Set<Ring> rings = new HashSet<>();
	
	private Location location;
	private Type type;
	
	public Ring(Location location, Type type) {
		this.location = location;
		this.type = type;
		rings.add(this);
	}
	
	public Ring(Map<String, Object> map) {
		location = (Location) map.get("location");
		type = Type.valueOf(String.valueOf(map.get("type")));
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isPassing(Player player) {
		return player.getLocation().distanceSquared(location) <= RING_WIDTH_SQUARED;
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<>();
		map.put("location", location);
		map.put("type", type.toString());
		return map;
	}
	
	public static Set<Ring> getRings() {
		return rings;
	}

	public static void addAll(Collection<Ring> rings) {
		rings.addAll(rings);
	}
	
	public enum Type {
		ENERGY,
		SPEED,
		BIG_SPEED,
		TELEPORT,
		CLEAR,
		RANDOM;
		
		public static Type getType(DyeColor dyeColor) {
			switch (dyeColor) {
			case YELLOW:
				return ENERGY;
			case LIGHT_BLUE:
				return SPEED;
			case BLUE:
				return BIG_SPEED;
			case PURPLE:
				return TELEPORT;
			case RED:
				return CLEAR;
			default:
				return RANDOM;
			}
		}
		
	}

}
