package com.agaze.beaconplugin.beacondetector;

import android.content.Context;
import android.util.Log;


import com.agaze.beaconplugin.servicehandler.ServiceMiddleware;

import org.altbeacon.beacon.Beacon;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class BeaconDetector {

    Context context;
    ServiceMiddleware serviceMiddleware = new ServiceMiddleware();
    public static HashMap<String, RBeacon> beaconPool = new HashMap<String,RBeacon>();

    /**
     * Default constructor
     *
     */
    public BeaconDetector(){
    }

    /**
     *
     * Constructor accept the context
     * @param c
     *
     */
    public BeaconDetector(Context  c){
        this.context =c;
    }

    /**
     *
     * @param beacon
     *
     */
    public BeaconDetector(Beacon beacon){
        Log.i("inside BeaconDetector","inside BeaconDetector");
        if(checkForAppid(beacon)){
            Log.i("RBeacon", beacon.getId1().toString());
            addBeacon(beacon);
        }
    }

    /**
     *Check the appId of the detected beacon
     */
    private Boolean checkForAppid(Beacon RBeacon){
        Log.i("inside checkForAppid","inside checkForAppid");
        return true;
    }

    /**
     *
     * @param beacon
     *
     * @catch IndexOutOfBoundsException
     */
    public  void addBeacon(Beacon beacon )
    {
        Log.i("inside addBeacon","inside addBeacon");
        if(beaconPool.containsKey(beacon.getId1().toString()))
        {
            Log.i("inside beaconsInRegion.containsKey","inside beaconsInRegion.containsKey");
            try
            {
                beaconPool.get(beacon.getId1().toString()).status="entry";
                beaconPool.get(beacon.getId1().toString()).entryTime= Calendar.getInstance();
                serviceMiddleware.NotificationService(beaconPool.get(beacon.getId1().toString()),context);
                Log.i("enrtytime",beaconPool.get(beacon.getId1().toString()).entryTime.toString());
            }
            catch(Exception e)
            {
                Log.i(" EXCEPTION ",e.toString());
            }
        }
        else
        {
            RBeacon rBeacon = new RBeacon();
            rBeacon.appId = beacon.getId1().toString();
            rBeacon.serviceId = beacon.getId1().toString();
            rBeacon.locationId = beacon.getId1().toString();
            rBeacon.entryTime = Calendar.getInstance();
            rBeacon.status = "entry";

            serviceMiddleware.NotificationService(rBeacon,context);
            Log.i("insde else addbeacon ","insde else addbeacon");
            try
            {
                beaconPool.put(rBeacon.appId, rBeacon);
            }
            catch(Exception e)

            {
                Log.i(" EXCEPTION ",e.toString());

            }

        }
    }

    /**
     *
     * @param rBeacon
     */
    public void removeBeacon(RBeacon rBeacon)
    {
        Log.i("inside removeBeacon","inside removeBeacon");
        rBeacon.status = "exit";
        serviceMiddleware.NotificationService(rBeacon,context);

        //beaconPool.remove(rBeacon.appId);
    }

    /**
     *
     *
     */
    public void checkForbeaconExit()
    {
        Log.i("inside checkForbeaconExit","inside checkForbeaconExit");
        Iterator itr = beaconPool.values().iterator();
        while (itr.hasNext()) {
            //Map.Entry pair = (Map.Entry)itr.next();
            RBeacon rBeacon = (RBeacon) itr.next();
            if(!rBeacon.status.equals("exit")){
                Calendar temp = (Calendar)rBeacon.entryTime.clone();
                temp.add(Calendar.SECOND, 30);
                Log.i("temp",temp.toString());
                Calendar CurrentTime = Calendar.getInstance();
                Log.i("CurrentTime",CurrentTime.toString());
                int status = temp.compareTo(CurrentTime);
                Log.i("status",Integer.toString(status));
                if(status<0)
                {
                    removeBeacon(rBeacon);
                }
                else
                    Log.i("inside"," time not reached");
            }
        }
    }


}