package com.example.aclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Notification extends AppCompatActivity {
    Button btNotifaction1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btNotifaction1=(Button)findViewById(R.id.btnttt);
        btNotifaction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
                {
                    NotificationChannel channel=new NotificationChannel("MyNotification1","MyNotification1", NotificationManager.
                            IMPORTANCE_DEFAULT);
                    NotificationManager manager=getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }
                NotificationCompat.Builder builder=new NotificationCompat.Builder(Notification.this,"MyNotification1")
                        .setContentTitle("Class Project Notification")
                        .setSmallIcon(R.drawable.msg)
                        .setAutoCancel(true)
                        .setContentText("Yes Notification");
                // Intent intent=new Intent(MainActivity.this, Home2.class);
                //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //  intent.putExtra("message",message);

                //   PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);builder.setContentIntent(pendingIntent);
                NotificationManagerCompat manager=NotificationManagerCompat.from(Notification.this);

                manager.notify(999,builder.build());

            }

        });
    }

}