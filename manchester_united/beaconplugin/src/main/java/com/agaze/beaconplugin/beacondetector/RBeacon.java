package com.agaze.beaconplugin.beacondetector;


import java.util.Calendar;

public class RBeacon {

    public static final String BEACON_INIT_CREDENTIALS = "BEACON_INIT_CREDENTIALS";
    public static final String BEACON_NOTIFICATION_CREDENTIALS = "BEACON_NOTIFICATION_CREDENTIALS";

    public String appId;
    public String serviceId;
    public String locationId;
    public String status;
    public Calendar entryTime;
    public Calendar lastNotificationTime;
    public Calendar lastWifiTime;
}
