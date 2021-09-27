package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
TextView t1;
Button logout;
Button submit;
    SharedPreferences sharedPreferences;
    // to create shared preference name and key
    private static final String SHARED_PREF="mypref";
    private static final String KeyName="name";
    private static final String KeyPass="pass";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
     getSupportActionBar().hide();
        t1=findViewById(R.id.nameohome);
        logout=findViewById(R.id.logout);
        submit=findViewById(R.id.submit);
        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        String name=sharedPreferences.getString(KeyName,null);
        if(getOpPackageName() !=null){
            //set the item
            t1.setText("Hello"+" "+name);

        }
        //log out
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                finish();
                Toast.makeText(Home.this,"Logged out successfully",Toast.LENGTH_LONG).show();

            }
        });

        //submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Home.this,ShareButton.class);
                startActivity(i2);
            }
        });
    }
}