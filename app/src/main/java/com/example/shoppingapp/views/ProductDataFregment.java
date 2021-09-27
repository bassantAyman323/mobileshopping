package com.example.shoppingapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.R;
import com.example.shoppingapp.databinding.FragmentProductDataFregmentBinding;
import com.example.shoppingapp.databinding.FragmentProductDataFregmentBindingImpl;
import com.example.shoppingapp.databinding.FragmentShopFregmentBinding;
import com.example.shoppingapp.view.model.ShopViewModel;


public class ProductDataFregment extends Fragment {

FragmentProductDataFregmentBinding productDataFregmentBinding;

    ShopViewModel shopViewModel;

    public ProductDataFregment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        productDataFregmentBinding= FragmentProductDataFregmentBinding.inflate(inflater,container,false);
        return productDataFregmentBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel = new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        productDataFregmentBinding.setShopViewModel(shopViewModel);
    }
}