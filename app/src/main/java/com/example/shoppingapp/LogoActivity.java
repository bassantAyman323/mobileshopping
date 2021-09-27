package com.example.shoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapp.views.Home2;

public class LogoActivity extends AppCompatActivity {
    Button button;
    EditText E1,E2;
    private static String TAG="lifecycle";
    SharedPreferences sharedPreferences;
    // to create shared preference name and key
    private static final String SHARED_PREF="mypref";
    private static final String KeyName="name";
    private static final String KeyPass="pass";
    String edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"oncreate");
        getSupportActionBar().hide();
        setContentView(R.layout.activity_logo);

        E1=findViewById(R.id.editText1);
        E2=findViewById(R.id.editTextTextPassword);
        button=findViewById(R.id.button);

        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

     //    to check data available or not
        String name=sharedPreferences.getString(KeyName,null);


      if(name != null ){

         Intent i=new Intent(LogoActivity.this,Home2.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
          Log.i(TAG,"if done");
          startActivity(i);
         finish();
          }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// to open the other activity
                // to write
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(KeyName,E1.getText().toString());
                editor.putInt(KeyPass, Integer.parseInt(E2.getText().toString()));
                editor.apply(); // to save changes

             Intent i=new Intent(LogoActivity.this,Home2.class);
             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
               finish();
                Toast.makeText(LogoActivity.this,"log in success",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    protected void onPause(){
        super.onPause();

//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString(KeyName,E1.getText().toString());
//        editor.putInt(KeyPass, Integer.parseInt(E2.getText().toString()));
//        editor.apply();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        // to check data available or not
        String name=sharedPreferences.getString(KeyName,null);
        if(name != null){
            Intent i=new Intent(LogoActivity.this,Home2.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

          //  startActivity(i);
            finish();
    }
}




}