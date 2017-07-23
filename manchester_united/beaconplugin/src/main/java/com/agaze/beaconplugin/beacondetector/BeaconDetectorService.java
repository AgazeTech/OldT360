package com.agaze.beaconplugin.beacondetector;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.Iterator;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class BeaconDetectorService extends IntentService implements BeaconConsumer{

    private BeaconManager m_beaconMnager;
    private BeaconDetector m_beaconDetector;

    /**
     * Constructor name the worker thread
     *
     **/
    public BeaconDetectorService() {
        super("BeaconDetectorService");
    }

    /**
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
    }

    /**
     *
     * @param intent
     * @param startId
     */
    @Override
    public void onStart(Intent intent, int startId) {
        Log.i("BD service started","BD service started");
    }

    /**
     *
     * Decodes the  beacon fields from BLE
     * Here the layout is used for ALTBeacon {@code m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24}
     *
     * @exception
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();
        m_beaconDetector = new BeaconDetector(getApplicationContext());
        Log.i("BD service created","BD service created");
        m_beaconMnager = BeaconManager.getInstanceForApplication(this);
        try {
            m_beaconMnager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        }
        catch(Exception e) {e.printStackTrace();}
        m_beaconMnager.bind(this);
        Log.i("beaconOncreate","beaconmanager successfully connected");
    }

    /**
     * Callback will Interact the service with beacon.It made ranging and monitoring of beacons.
     * service that receives calls from the beacon Manager when new beacon fields are
     * posted or removed
     *
     */
    @Override
    public void onBeaconServiceConnect() {
        Log.i("onBeaconServiceConnect","onBeaconServiceConnect");
        m_beaconMnager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                Log.i("didRangeBeaconsInRegion","didRangeBeaconsInRegion");
                Iterator itr = beacons.iterator();
                if(itr.hasNext()){
                    while(itr.hasNext()) {
                        m_beaconDetector=new BeaconDetector((org.altbeacon.beacon.Beacon) itr.next());
                    }
                }
                else{
                    m_beaconDetector.checkForbeaconExit();
                }
            }
        });
        try {
            m_beaconMnager.startRangingBeaconsInRegion(new org.altbeacon.beacon.Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e){e.printStackTrace();}
    }
}
