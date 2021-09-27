package com.example.shoppingapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adaptors.ShopListAdapter;
import com.example.shoppingapp.databinding.FragmentShopFregmentBinding;
import com.example.shoppingapp.models.Product;
import com.example.shoppingapp.view.model.ShopViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class shopFregment extends Fragment implements ShopListAdapter.ShopInterface {
    private static final String TAG ="Shopfragment" ;
    FragmentShopFregmentBinding shopFregmentBinding;
    public ShopListAdapter shopListAdapter;
    private ShopViewModel shopViewModel;
    public NavController navController;



    public shopFregment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        shopFregmentBinding= FragmentShopFregmentBinding.inflate(inflater,container,false);
        return shopFregmentBinding.getRoot();



    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopListAdapter = new ShopListAdapter(this);
       shopFregmentBinding.shopRecyclerView.setAdapter(shopListAdapter);
       shopFregmentBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        shopFregmentBinding.shopRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.HORIZONTAL));

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> product) {
          shopListAdapter.submitList(product);
            }
        });



        navController = Navigation.findNavController(view);

        }

    @Override
    public void addItem(Product product) {
//         Log.d(TAG,"addItem: "+product.toString());
        boolean isAdded =shopViewModel.addItemToCart(product);
        Log.d(TAG,"addItem: "+product.getName()+isAdded());
        if (isAdded) {
            Snackbar.make(requireView(), product.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_shopFregment_to_cartFragement);
                        }
                    })
                    .show();
        } else {
            Snackbar.make(requireView(), "Already have the max quantity in cart.", Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    public void onItemClick(Product product) {
        Log.d(TAG,"onclick:"+product.toString());
        shopViewModel.setProduct(product);
        navController.navigate(R.id.action_shopFregment_to_productDataFregment);
    }
}