package me.mani.glide.manager;

import java.util.function.Consumer;

import me.mani.glide.Glide;
import me.mani.glide.util.Effects;
import me.mani.glide.util.Messenger;

import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownManager {
	
	public static Countdown createCountdown(Consumer<CountdownCountEvent> countConsumer, Runnable finishConsumer, int from, int to, long interval) {
		Countdown c = new Countdown(from, to, countConsumer, finishConsumer);
		c.runTaskTimer(Glide.getInstance(), 0L, interval);
		return c;
	}
	
	public static class Countdown extends BukkitRunnable {
		
		private int from;
		private int to;
		private Consumer<CountdownCountEvent> countConsumer;
		private Runnable finishConsumer;
		private boolean downcounting;
		
		public Countdown(int from, int to, Consumer<CountdownCountEvent> countConsumer, Runnable finishConsumer) {
			this.from = from;
			this.to = to;
			this.countConsumer = countConsumer;
			this.finishConsumer = finishConsumer;		
			this.downcounting = (from > to);
		}
		
		@Override
		public void run() {
			CountdownCountEvent ev = new CountdownCountEvent(from);
			countConsumer.accept(ev);
			Messenger.sendAll(ev.hasMessage() ? ev.getMessage() : null);
			Effects.playAll(ev.hasSound() ? ev.getSound() : null);
			if (from == to)
				stop(false);
			else
				if (downcounting)
					from--;
				else
					from++;
		}	
		
		public void stop(boolean force) {
			this.cancel();
			if (!force)
				finishConsumer.run();
		}

	}
	
	public static class CountdownCountEvent {
		
		public CountdownCountEvent(int currentNumber) {
			this.currentNumber = currentNumber;
		}
		
		private int currentNumber;
		
		private String message;
		private Sound sound;	
		
		public void setMessage(String message) {
			this.message = message;
		}
		
		public boolean hasMessage() {
			return message != null;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setSound(Sound sound) {
			this.sound = sound;
		}
		
		public boolean hasSound() {
			return sound != null;
		}
		
		public Sound getSound() {
			return sound;
		}
		
		public int getCurrentNumber() {
			return this.currentNumber;
		}

	}
}
