package me.mani.glide.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Title {
	
    private String title = "";
    private String subtitle = "";
    private int fadeInTime = -1;
    private int stayTime = -1;
    private int fadeOutTime = -1;
    private boolean ticks = false;

    public Title(String title) {
        this(title, "", 10, 20, 10, false);
    }

    public Title(String title, String subtitle) {
    	this(title, subtitle, 10, 20, 10, false);
    }

    public Title(Title title) {
        this(title.title, title.subtitle, title.fadeInTime, title.stayTime, title.fadeOutTime, title.ticks);
    }

    public Title(String title, String subtitle, int fadeInTime, int stayTime, int fadeOutTime, boolean isTicked) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeInTime = fadeInTime;
        this.stayTime = stayTime;
        this.fadeOutTime = fadeOutTime;
        this.ticks = isTicked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setFadeInTime(int time) {
        this.fadeInTime = time;
    }

    public void setFadeOutTime(int time) {
        this.fadeOutTime = time;
    }

    public void setStayTime(int time) {
        this.stayTime = time;
    }

    public void setTimingsToTicks() {
        ticks = true;
    }

    public void setTimingsToSeconds() {
        ticks = false;
    }

    public void send(Player player) {
    	resetTitle(player);
            
    	List<Packet<?>> allPackets = new ArrayList<>();
            
     	IChatBaseComponent titleText = ChatSerializer.a("{text:\"" + this.title + "\"}");
      	allPackets.add(new PacketPlayOutTitle(EnumTitleAction.TITLE, titleText));
            
      	IChatBaseComponent subtitelText = ChatSerializer.a("{text:\"" + this.subtitle + "\"}");
      	allPackets.add(new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, subtitelText));
            
     	allPackets.add(new PacketPlayOutTitle(this.ticks ? this.fadeInTime * 20 : this.fadeInTime, this.ticks ? this.stayTime * 20 : this.stayTime, this.ticks ? this.fadeOutTime * 20 : this.fadeOutTime));
            
      	for (Packet<?> p : allPackets)
         	((CraftPlayer) player).getHandle().playerConnection.sendPacket(p);
    }

    public void broadcast() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            send(p);
        }
    }

    public void clearTitle(Player player) {
    	PacketPlayOutTitle clear = new PacketPlayOutTitle(EnumTitleAction.CLEAR, ChatSerializer.a(""));
    	((CraftPlayer) player).getHandle().playerConnection.sendPacket(clear);
    }

    public void resetTitle(Player player) {
    	PacketPlayOutTitle clear = new PacketPlayOutTitle(EnumTitleAction.RESET, ChatSerializer.a(""));
     	((CraftPlayer) player).getHandle().playerConnection.sendPacket(clear);      
    }
    
}