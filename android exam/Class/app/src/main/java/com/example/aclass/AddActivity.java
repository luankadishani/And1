package com.example.aclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText title_input, author_input, pages_input;
    Button add_button;
    String message="This is the notifcation";
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title_input.getText().toString().isEmpty() && author_input.getText().toString().isEmpty()
                        && pages_input.getText().toString().isEmpty() )
                {
                    Toast.makeText(AddActivity.this,"Fill all the above Data",Toast.LENGTH_SHORT).show();
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(title_input.getText().toString().trim(),
                            author_input.getText().toString().trim(),
                            Integer.valueOf(pages_input.getText().toString().trim()));

                    //Notification
                    if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
                    {
                        NotificationChannel channel=new NotificationChannel("MyNotification","MyNotification", NotificationManager.
                                IMPORTANCE_DEFAULT);
                        NotificationManager manager=getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                    }
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(AddActivity.this,"MyNotification")
                            .setContentTitle("Class Project Notification")
                            .setSmallIcon(R.drawable.msg)
                            .setAutoCancel(true)
                            .setContentText("Book Added");
                    Intent intent=new Intent(AddActivity.this, Lhome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("message",message);

                    PendingIntent pendingIntent=PendingIntent.getActivity(AddActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);builder.setContentIntent(pendingIntent);
                    NotificationManagerCompat manager=NotificationManagerCompat.from(AddActivity.this);

                    manager.notify(999,builder.build());

                }
            }
        });
    }
}

