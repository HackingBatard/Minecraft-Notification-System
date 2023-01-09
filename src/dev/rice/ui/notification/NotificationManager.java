package dev.rice.ui.notification;

import java.util.concurrent.CopyOnWriteArrayList;

import dev.rice.ui.notification.Notification.Direction;

public class NotificationManager {
	
	public CopyOnWriteArrayList<Notification> notifications = new CopyOnWriteArrayList<Notification>();
	
	public void show(Notification notification) {
		//reset all values
		notification.showed = false;
		notification.width = 0;
		notification.height = 0;
		final long time = System.currentTimeMillis();
		notifications.add(notification);
		notification.setDirection(Direction.INC);
		
		notifications.removeIf(no -> no.getTime() + 5000 < time);
	}
	
	public void update() {
		final long time = System.currentTimeMillis();
		for(Notification notif : notifications) {
			if(notif.getTime() + 4500 < time)
				notif.setDirection(Direction.OUG);
			if(notif.getTime() + 4900 < time)
				notif.showed = true;
		}
	}
	
	public void render() {
		update();
	}
	
}
