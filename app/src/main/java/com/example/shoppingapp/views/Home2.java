package com.example.shoppingapp.views;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingapp.Home;
import com.example.shoppingapp.R;
import com.example.shoppingapp.ShareButton;
import com.example.shoppingapp.models.CartItem;
import com.example.shoppingapp.view.model.ShopViewModel;

import java.util.List;

public class Home2 extends AppCompatActivity {
    NavController navController;
ShopViewModel shopViewModel;
private int cartQuantity = 0;
private static final String TAG="Home2";
    //from home that we make for shared pref
    TextView t1;
    Button logout;
    Button submit;
    SharedPreferences sharedPreferences;
    // to create shared preference name and key
    private static final String SHARED_PREF="mypref";
    private static final String KeyName="name";
    private static final String KeyPass="pass";
    //////////
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
      navController= Navigation.findNavController(this,R.id.nav_host_fragment_container);
     NavigationUI.setupActionBarWithNavController(this,navController);
     shopViewModel=new ViewModelProvider(this).get(ShopViewModel.class);
     shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
         @Override
         public void onChanged(List<CartItem> cartItems) {
             int quantity = 0;
             for(CartItem cartItem: cartItems){
                 quantity +=cartItem.getQuantity();
             }
           cartQuantity = quantity;
             invalidateOptionsMenu();
         }
     });

        //from home
//        getSupportActionBar().hide();
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
                Toast.makeText(Home2.this,"Logged out successfully",Toast.LENGTH_LONG).show();

            }
        });

        //submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(Home2.this, ShareButton.class);
                startActivity(i2);
            }
        });
        /////from this page we should go to cart which was sharebutton
    }

    //cart

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.cartFragement);
        View actionView = menuItem.getActionView();
        TextView cartBadgeTextView = actionView.findViewById(R.id.cart_badge_text_view);
        cartBadgeTextView.setText(String.valueOf(cartQuantity));
        cartBadgeTextView.setVisibility(cartQuantity == 0 ? View.GONE : View.VISIBLE);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       return NavigationUI.onNavDestinationSelected(item,navController) ||
      super.onOptionsItemSelected(item);
    }
}