package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class ShareButton extends AppCompatActivity {
Button notify;
    private JobScheduler mScheduler;
    private static final int JOB_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_button);
        notify=findViewById(R.id.notify);
        //youtube
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            NotificationChannel channel=new NotificationChannel("my notification","my notification", NotificationManager.IMPORTANCE_DEFAULT);
//            NotificationManager manager=getSystemService(NotificationManager.class);
//            manager.createNotificationChannel(channel);
//        }
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doctor
                mScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
                ComponentName serviceName = new ComponentName(getPackageName(),
                        NotificationJobService.class.getName());
                JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName);
                JobInfo myJobInfo = builder.build();
                mScheduler.schedule(myJobInfo);
                ///





            }
        });
//        if (mScheduler!=null){
//            mScheduler.cancelAll();
//            mScheduler = null;
//            Toast.makeText(ShareButton.this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {





//share
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }


    protected void onStart() {
        super.onStart();
        if (mScheduler!=null){
            mScheduler.cancelAll();
            mScheduler = null;
            Toast.makeText(ShareButton.this, "Jobs cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    //share
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id== R.id.share){
            ApplicationInfo api =getApplicationContext().getApplicationInfo();
            String apkpath=api.sourceDir;
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String M="Medical app";
            String B="try subject";
            String Sub="http://play.google.com";
            i.putExtra(Intent.EXTRA_SUBJECT, B);
            i.putExtra(Intent.EXTRA_TEXT, M);
            i.putExtra(Intent.EXTRA_TEXT, Sub);
            startActivity(Intent.createChooser(i,"Sharevia"));}
        return  true;
    }
}