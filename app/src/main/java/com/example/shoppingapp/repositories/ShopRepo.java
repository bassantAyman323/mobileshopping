package com.example.shoppingapp.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.shoppingapp.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private MutableLiveData<List<Product>> MutableProductList;
    public MutableLiveData<List<Product>> getProducts(){
        if (MutableProductList==null) {
            MutableProductList = new MutableLiveData<>();
            loadProducts();
        }
        return MutableProductList;
    }
    private void loadProducts(){
        List <Product>ProductList=new ArrayList<>();
        ProductList.add(new Product(UUID.randomUUID().toString(),"Zokinvy",86.04,"Eiger",
                "https://www.aopa.org/-/media/images/aopa-main/go-fly/medical/health-conditions/substance-abuse/alcoholdrugguidelines.jpg?mw=650&mh=366&as=1&hash=F09D269DF06FB18DFA353D0A435C7275F8237538"));
        ProductList.add(new Product(UUID.randomUUID().toString(),"Myalept",74.1509,"Aegerion",
                "https://th.bing.com/th/id/OIP.PFYXlv3kD_caT_Opgc3WRAHaFj?pid=ImgDet&rs=1"));
        ProductList.add(new Product(UUID.randomUUID().toString(),"Mavenclad",60.371,"Emds serono",
                "https://th.bing.com/th/id/OIP.Hyvbrx_vbXAqMpGSeqn8vAHaFU?pid=ImgDet&rs=1"));
        ProductList.add(new Product(UUID.randomUUID().toString(),"Ravicti",57.99,"Horizon",
                "https://th.bing.com/th/id/OIP.sp0_JauzVfkv9R38p5E7wAHaE6?pid=ImgDet&rs=1"));
        ProductList.add(new Product(UUID.randomUUID().toString(),"Actimmune",55.31,"Horizon",
                "https://th.bing.com/th/id/R98baed793cf412a4ef5148963797cc3f?rik=xguV3iRY%2bHkmug&pid=ImgRaw"));
        ProductList.add(new Product(UUID.randomUUID().toString(),"Oxervate",48.49,"Dompe",
                "https://d.newsweek.com/en/full/630478/gettyimages-684080166.jpg"));
        MutableProductList.setValue(ProductList);


    }
}
