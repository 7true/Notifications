package tk.alltrue.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "notifyEx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = this.getResources();



        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        builder.setContentIntent(contentIntent)
                //.setSmallIcon(R.drawable.ic_launcher_cat);
                .setContentTitle(res.getString(R.string.notifytitle))
                .setContentText(res.getString(R.string.notifytext))
                .setSmallIcon(R.mipmap.ic_launcher_snow)
                .setTicker("Выключай компьютер, иди домой")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setLights(Color.RED, 5000, 5000);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                createNotificationChannel();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
                Notification notif = new Notification();
                notif.ledARGB = 0xFFff0000;
                notif.flags = Notification.FLAG_SHOW_LIGHTS;
                notif.ledOnMS = 100;
                notif.ledOffMS = 100;
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        }, 3000);

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
/*
    public void onClick(View view) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = this.getResources();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        builder.setContentIntent(contentIntent)
                //.setSmallIcon(R.drawable.ic_launcher_cat);
                .setContentTitle(res.getString(R.string.notifytitle))
                .setContentText(res.getString(R.string.notifytext))
                .setSmallIcon(R.mipmap.ic_launcher_snow)
                .setTicker("Выключай компьютер, иди домой")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setLights(Color.BLUE, 5000, 5000);
        createNotificationChannel();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        Notification notif = new Notification();
        notif.ledARGB = 0xFFff0000;
        notif.flags = Notification.FLAG_SHOW_LIGHTS;
        notif.ledOnMS = 100;
        notif.ledOffMS = 100;
        notificationManager.notify(NOTIFY_ID, builder.build());
    }*/
}
