package dev.rice.modules.impl.render;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import dev.rice.Rice;
import dev.rice.events.Event;
import dev.rice.events.impl.EventMotion;
import dev.rice.events.impl.EventRender;
import dev.rice.events.impl.EventUpdate;
import dev.rice.modules.Category;
import dev.rice.modules.Module;
import dev.rice.settings.impl.BooleanSetting;
import dev.rice.settings.impl.ModeSetting;
import dev.rice.settings.impl.NumberSetting;
import dev.rice.ui.clickgui.ClickGui;
import dev.rice.ui.notification.Notification;
import dev.rice.ui.notification.Notification.Direction;
import dev.rice.utils.font.FontUtil;
import dev.rice.utils.render.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class Notifications extends Module {
	
	private List<Notification> notifs;
	
	public static ModeSetting mode = new ModeSetting("Mode", "Rice", "Rice");
	
	public Notifications() {
		super("Notifications", "client notifications.", Category.RENDER);
		this.addSettings(mode);
	}
	
	public void onEnable(){
		
	}
	
	public void onDisable() {
		notifs.clear();
	}
	
	public void onUpdateAlways() {
		this.suffix = mode.getMode();
	}
	
	public void onEvent(Event e) {
		
		if(e instanceof EventRender) {
			Rice.instance.notificationManager.render();
			
			ScaledResolution sr = new ScaledResolution(mc);
			
			notifs = Rice.instance.notificationManager.notifications;
			
			if(notifs.isEmpty()) 
				return;
			
			int count = 0;
			for(Notification notif : notifs) {
				
				Gui.drawRect(sr.getScaledWidth() - notif.width, sr.getScaledHeight() - 30 - notif.height, sr.getScaledWidth(), sr.getScaledHeight() - 60 - notif.height, 0x90000000);
				
				FontUtil.riceFont18.drawString(notif.getName(), sr.getScaledWidth() - notif.width + 8, sr.getScaledHeight() - 55 - notif.height, -1);
				FontUtil.riceFont16.drawString(notif.getMessage(), sr.getScaledWidth() - notif.width + 8, sr.getScaledHeight() - 42 - notif.height, -1);
				
				if(notif.width < 120 && notif.getDirection().equals(Direction.INC)) {
					notif.width += 10;
				}
				
				if(notif.width > 0 && notif.getDirection().equals(Direction.OUG)) {
					notif.width -= 10;
				}
				
				if(notif.height > count * 35)
					notif.height -= 5;
				
				if(notif.height < count * 35)
					notif.height += 5;
				
				notif.progress = (System.currentTimeMillis() - notif.getTime()) / 5000;
				
				count++;
				if(notif.showed) count--;
			}
			
		}
	}
}
