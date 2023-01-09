package dev.rice.ui.notification;

import net.minecraft.client.gui.Gui;

public class Notification {
	
	private String name;
	private String message;
	
	public boolean showed;
	
	public float progress;
	
	private Type type;
	private Direction direct;
	private long time;
	public int width;
	public int height;
	
	public Notification(String name, String message, long time, Type type) {
		this.name = name;
		this.message = message;
		this.time = time;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public void setDirection(Direction direction) {
		this.direct = direction;
	}
	
	public Direction getDirection() {
		return direct;
	}
	
	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
	}

	public enum Type{
		INFO,
		WARNING;
	}
	
	public enum Direction{
		INC,
		OUG;
	}
	
}
