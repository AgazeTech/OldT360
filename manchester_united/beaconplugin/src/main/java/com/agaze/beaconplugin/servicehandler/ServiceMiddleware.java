package com.agaze.beaconplugin.servicehandler;

import java.util.Calendar;

import org.altbeacon.beacon.Beacon;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.agaze.beaconplugin.beacondetector.RBeacon;


public class ServiceMiddleware {

	BeaconNotification BeaconNotification = new BeaconNotification();

	/**
	 * Notify the exit or entry of the beacon.
	 * @param rBeacon
	 * 			from the beacon pool the beacons status will check for the service
	 * @param context
	 * 			app context
     */
	public void NotificationService(RBeacon rBeacon, Context context){
		if(rBeacon.status.equals("exit")){
			BeaconNotification.Notification(rBeacon.locationId,"exit",context);
		}
		else{
			if(rBeacon.lastNotificationTime == null){
				rBeacon.lastNotificationTime = Calendar.getInstance();
				BeaconNotification.Notification(rBeacon.locationId,"entry",context);
			}
			else{
				Calendar temp1 =(Calendar)rBeacon.lastNotificationTime.clone();
				SharedPreferences preferences = context.getSharedPreferences(RBeacon.BEACON_NOTIFICATION_CREDENTIALS,0);
				temp1.add(Calendar.SECOND, preferences.getInt("DUPLICATE_BEACON_MIN_DELAY",0));
		    	Calendar CurrentTime = Calendar.getInstance();
		    	int status = temp1.compareTo(CurrentTime);
				if(status<0){
					rBeacon.lastNotificationTime = Calendar.getInstance();
					BeaconNotification.Notification(rBeacon.locationId,"entry",context);
				}
			}
		}
	}

}
