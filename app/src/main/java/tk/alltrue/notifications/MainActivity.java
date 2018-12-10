package tk.alltrue.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFY_ID = 101;
    private static final String CHANNEL_ID = "notifyEx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void onClick(View view) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,
                notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = this.getResources();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);

        builder.setContentIntent(contentIntent)
                //.setSmallIcon(R.drawable.ic_launcher_cat);
                .setContentTitle(res.getString(R.string.notifytitle))
                .setContentText(res.getString(R.string.notifytext))
                .setSmallIcon(R.mipmap.ic_launcher_snow)
                .setTicker("Выключай компьютер, иди домой")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        createNotificationChannel();

        NotificationManager notificationManager =
               (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }
}
