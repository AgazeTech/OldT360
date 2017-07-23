package agaze.com.manchesterunited.Activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import agaze.com.manchesterunited.R;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private String longText = "Please open to make your visit to OT amazing and memorable!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sendNotification();
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), StartTour.class);
                //i.putExtra("activity","SplashActivity");
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void sendNotification(){

        NotificationManager notificationManager =(NotificationManager)getSystemService(Service.NOTIFICATION_SERVICE);
        //Notification notification=new Notification(R.mipmap.notifi,"Notification",System.currentTimeMillis());
        PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
        Notification.Builder builder = new Notification.Builder(SplashActivity.this);
        //notification.setLatestEventInfo(getApplicationContext(), subject, body,pending);
        //notificationManager.notify(0, notification);
        builder.setAutoCancel(false);
        builder.setTicker("OT-360");
        builder.setContentTitle("Welcome to OT-360");
        builder.setContentText("Please open to make your visit to OT amazing and memorable!");
        builder.setSmallIcon(R.drawable.noti);
        builder.setContentIntent(pending);
        builder.setOngoing(true);
        builder.setNumber(0);
        builder.setStyle(new Notification.BigTextStyle().bigText(longText));
        builder.setAutoCancel(true);
        builder.build();
        Notification notification = builder.getNotification();
        notificationManager.notify(11, notification);
    }
}
