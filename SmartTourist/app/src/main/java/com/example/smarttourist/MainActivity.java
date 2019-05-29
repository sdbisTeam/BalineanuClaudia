package com.example.smarttourist;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.smarttourist.NotificationUtils.ANDROID_CHANNEL_ID;

public class MainActivity extends AppCompatActivity  {

    Button btnLocation;
    Button btnHotels;
    Button btnRestaurant;
    Button btnAttractions;
    Button btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnLocation = (Button) findViewById(R.id.btnLocation);
        //define the behavior of the button
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define a new Intent
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                //starting the new Activity
                startActivity(i);

            }
        });

        btnHotels = (Button) findViewById(R.id.btnHotels);
        btnHotels.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, HotelsActivity.class);

                startActivity(i);
            }
        });


        btnRestaurant = (Button) findViewById(R.id.btnRestaurants);
        btnRestaurant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, RestaurantsActivity.class);

                startActivity(i);




            }
        });


        btnAttractions = (Button) findViewById(R.id.btnAttractions);
        btnAttractions.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, AttractionsActivity.class);

                startActivity(i);
            }
        });



        btnRefresh = (Button) findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, RefreshActivity.class);

                startActivity(i);



            }
        });

 /*
        int i ;
        for(i=0;i<3;i++) {
            String title;
            title = multi[i][2];
            String body = "Mai ai 200 de metri pana la destinatie!!!";


            mNotificationUtils = new NotificationUtils(this);
            if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                Notification.Builder nb = mNotificationUtils.
                        getAndroidChannelNotification(title, "By " + body);

                mNotificationUtils.getManager().notify(101, nb.build());
            }

        }
*/
/*
        Context mContext;
        mContext = this;
*/


    }// aici se inchide on create

    //aici e  in afara lu on create in clasa






    private void addNotification(String title,String body) {

        Context mContext;
        mContext = MyApplication.getAppContext();

        NotificationCompat.Builder builder =
        new NotificationCompat.Builder(mContext, ANDROID_CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.stat_notify_more)
                        .setContentTitle(title)
                        .setContentText(body);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }




    Timer myTimer;
    @Override
    protected void onStart() {
        super.onStart();
        myTimer= new Timer();
        //this will update every minute
        myTimer.scheduleAtFixedRate(new UpdateMyTimerTask(), 0, 60000);


    }



    public class UpdateMyTimerTask extends TimerTask {

        @Override
        public void run() {
            //your method
           /* printAndroidLabel();
            Intent i = new Intent(MainActivity.this, MapsActivity.class);

            TextView txt1 = findViewById(R.id.txtCLat);
            TextView txt2 = findViewById(R.id.txtCLong);

            System.out.println(txt1 + "     " + txt2);
*/

            Double c_lat = 0.0;
            Double c_long = 0.0;
            Bundle extras = getIntent().getExtras();
            if(extras != null){
                c_lat = extras.getDouble("c_lat");
                c_long = extras.getDouble("c_long");
            }

            System.out.println(c_lat + "     " + c_long);

            double dist;



            int i ;
            for(i=0;i<3;i++){ //9
                dist = 0.0;
                String text1 = multi[i][0];
                double value1 = Double.parseDouble(text1);

                String text2 = multi[i][1];
                double value2 = Double.parseDouble(text2);
                dist = distance(c_lat, value1, c_long, value2);
                System.out.println(dist+ "     asta e distanta pt elem    "  + i );


                if(dist<2000 && notif[i] ){
                    notif[i]=false;
                    ///

                    String title = multi[i][2];
                    String body = "Aproape ai ajuns la destinatie!!!";
                    addNotification(title,body);



                }

                if(dist<2000){
                    sendMess();



                }
            }
        }
    }


    public void sendMess() {
        SmsManager sms;
        sms = SmsManager.getDefault();
        String destinationNumber = "0753505914"; //for the second emulator
        String mesaj = "A ajuns la destinatie!!";

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS}, 1);

        try {
            sms.sendTextMessage(destinationNumber, null, mesaj, null, null);
            runOnUiThread(new Runnable() {
                              public void run() {
            Toast notificare = Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG);
            notificare.show();
                              }
            });
        } catch (final Exception eroare) {
            runOnUiThread(new Runnable() {
                              public void run() {
            Toast notificare = Toast.makeText(getApplicationContext(), "Trouble: " +
                    eroare.getMessage(), Toast.LENGTH_LONG);
            notificare.show();
                              }
            });
        }
    }


    //private NotificationUtils mNotificationUtils;





    boolean[] notif = new boolean[]{
            true,true,true,true,true,true,true,true,true
    } ;

    String[][] multi = new String[][]{
            {"47.159468","27.599773","FireRibs"},{"47.157044","27.589736","KFC"},{"47.164216","27.5778053","Mamma Mia"},{"0.0","0.0","0.0"},{"0.0","0.0","0.0"},{"0.0","0.0","0.0"},{"0.0","0.0","0.0"},{"0.0","0.0","0.0"},{"0.0","0.0","0.0"}    };


/*
    double[][] multi = new double[][]{
        {47.159468,27.599773},{47.157044,27.589736},{47.164216,27.5778053},{0.0,0.0},{0.0,0.0},{0.0,0.0},{0.0,0.0},{0.0,0.0},{0.0,0.0}    };
*/

    public static double distance(double lat2, double lat1, double lon2,
                                  double lon1) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = (Math.sin(latDistance / 2)) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        //return Math.sqrt(distance);
        return distance;
    }



    @Override
    protected void onStop() {
        super.onStop();
        //stop timer
        myTimer.cancel();
    }

    //public void printAndroidLabel(){
       // System.out.println("Hi Android!");
   // }



    ///////////////////////
    /*
                    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notify=new Notification.Builder
                            (getApplicationContext()).setContentTitle(textNotif).setContentText(body).setContentTitle(textNotif).build();

                    notify.flags |= Notification.FLAG_AUTO_CANCEL;
                    notif.notify(0, notify);
*/

    /////////////////////////////

    /*
                    String textNotif;
                    textNotif = multi[i][2];
                    String body = "Mai ai 200 de metri pana la destinatie!!!";


                    String CHANNEL_ID = "my_channel_01";// The id of the channel.
                    //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID);

                    Context context = MainActivity.this;

                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.createNotificationChannel(mChannel);

                    NotificationCompat notification =
                            new NotificationCompat.Builder(context, CHANNEL_ID)
                                    .setContentTitle(textNotif)
                                    .setContentText(body)
                                    .setChannelId(CHANNEL_ID).build();

                    // Issue the notification.
                    mNotificationManager.notify(0 , notification);

*/

    /////////////////////////////


    /*

    private void createNotification(Context context) {
        // Sets an ID for the notification, so it can be updated.
        context():
        createChannel(context());
        int notifyID = 1;
        // Create a notification and set the notification channel.
        Notification notification = new Notification.Builder(context)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannel(CHANNEL_ID)
//                .setNumber(1)    //Set numbers to be displayed in badge.
//                .setBadgeIconType(Notification.BADGE_ICON_SMALL)
                .build();
        // Issue the notification.
        mNotificationManager.notify(notifyID, notification);


    }

*/
    ///////////////////////////////


}
