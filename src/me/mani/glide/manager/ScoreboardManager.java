package me.mani.glide.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class ScoreboardManager {
	
	private Scoreboard scoreboard;
	private Objective objective;
	
	public ScoreboardManager() {
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = scoreboard.registerNewObjective("§bGlide", "dummy");
	}
	
	public Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	public void setScore(Player player, int score) {
		objective.getScore(player.getName()).setScore(score);
	}

}
