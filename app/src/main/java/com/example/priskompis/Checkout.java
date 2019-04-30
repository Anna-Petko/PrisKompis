package com.example.priskompis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.priskompis.Adapter.ProductAdapter;
import com.example.priskompis.Model.Order;
import com.example.priskompis.Model.ProductModel;

import java.nio.file.FileSystemLoopException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Checkout extends AppCompatActivity {

    //a list to store all the products
    HashMap<String, ProductModel> productList;
    HashMap<String, Integer> productQuantity;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    Intent intent = this.getIntent();
    Bundle bundle = intent.getExtras();
//Type object = (Type) bundle.getSerializable("KEY");
    Order myOrder = (Order) bundle.getSerializable("order");

        recyclerView = (RecyclerView) findViewById(R.id.cartListView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = myOrder.getProducts();
        productQuantity = myOrder.getProductQuantity();
        Set<String> keys = productQuantity.keySet();
        System.out.println("keys: " + keys.size());
        //print all the keys
        for (String key : keys) {
            System.out.println(key);
            System.out.println(productQuantity.get(key));
        }
        //creating recyclerview adapter
        ProductAdapter adapter = new ProductAdapter(this, productList, productQuantity);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        TextView totalPrice = findViewById(R.id.txt_totalprice);
        totalPrice.setText(String.valueOf(myOrder.getTotalPrice()) + " SEK");

    }

}
