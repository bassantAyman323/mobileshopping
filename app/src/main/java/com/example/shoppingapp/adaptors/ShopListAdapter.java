package com.example.shoppingapp.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.databinding.ShopRawBinding;
import com.example.shoppingapp.models.Product;
import com.example.shoppingapp.views.shopFregment;

public class ShopListAdapter extends ListAdapter<Product,ShopListAdapter.ShopViewHolder>{
    ShopInterface shopInterface;
    public ShopListAdapter( ShopInterface shopInterface) {
        super(Product.itemCallback);
        this.shopInterface = shopInterface;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ShopRawBinding shopRawBinding = ShopRawBinding.inflate(layoutInflater, parent, false);
        shopRawBinding.setShopInterface(shopInterface);
        return new ShopViewHolder(shopRawBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Product product = getItem(position);
        holder.shopRawBinding.setProduct(product);
        holder.shopRawBinding.executePendingBindings();

    }
    class ShopViewHolder extends RecyclerView.ViewHolder {

        ShopRawBinding shopRawBinding;

        public ShopViewHolder(ShopRawBinding binding) {
            super(binding.getRoot());
            this.shopRawBinding = binding;

        }
    }

    public interface ShopInterface {
        void addItem(Product product);
        void onItemClick(Product product);
    }
}