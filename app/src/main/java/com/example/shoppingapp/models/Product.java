package com.example.shoppingapp.models;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class Product {
    private String id;
    private String Name;
    private double Price;
    private String Description;
    private String ImageURL;

    public Product(String id, String name, double price, String description, String imageURL) {
        this.id = id;
        Name = name;
        Price = price;
        Description = description;
        ImageURL = imageURL;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Description='" + Description + '\'' +
                ", ImageURL='" + ImageURL + '\'' +
                '}';
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getImageURL() {
        return ImageURL;
    }
    
    public static DiffUtil.ItemCallback<Product> itemCallback=new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getDescription().equals(product.getDescription()) &&
                getImageURL().equals(product.getImageURL());
    }
    @BindingAdapter("android:productImage")

    public static void LoadImage(ImageView imageView , String ImageURL){
        Glide.with(imageView).load(ImageURL).fitCenter().into(imageView);
}
    }
