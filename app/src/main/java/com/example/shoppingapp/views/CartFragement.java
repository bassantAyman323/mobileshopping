package com.example.shoppingapp.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adaptors.CartListAdapter;
import com.example.shoppingapp.databinding.FragmentCartFragementBinding;
import com.example.shoppingapp.models.CartItem;
import com.example.shoppingapp.view.model.ShopViewModel;

import java.util.List;

public class CartFragement extends Fragment implements CartListAdapter.CartInterface  {
    private static final String TAG = "CartFragement";
    ShopViewModel shopViewModel;
    FragmentCartFragementBinding fragmentCartFragementBinding;
    NavController navController;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cart_fragement, container, false);
        fragmentCartFragementBinding=FragmentCartFragementBinding.inflate(inflater,container,false);
        return fragmentCartFragementBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CartListAdapter cartListAdapter=new CartListAdapter();
        fragmentCartFragementBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartFragementBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL));
        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {

            @Override
            public void onChanged(List<CartItem> cartItems) {
                         cartListAdapter.submitList(cartItems);
              fragmentCartFragementBinding.placeOrderButton.setEnabled(cartItems.size() > 0);
            }
        });
        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartFragementBinding.orderTotalTextView.setText("Total: $ " + aDouble.toString());
            }
        });

        fragmentCartFragementBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_cartFragement_to_orderFragement);
            }
        });

    }
    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem, quantity);
    }
}