package com.example.myapplication


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProductDetailsActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_item_details)
        var product : Product? = null;
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
          product = intent.getSerializableExtra("product",Product::class.java);
        else
           product =  intent.getSerializableExtra("product") as Product;

        if(product==null)return;

        // Display product details
        val productNameTextView = findViewById<TextView>(R.id.product_name)
        productNameTextView.text = product.productName

        val productDescriptionTextView = findViewById<TextView>(R.id.product_description)
        productDescriptionTextView.text = product.productDescription

        val productPriceTextView = findViewById<TextView>(R.id.product_price)
        productPriceTextView.text = "$${product.cost}"

        // Load product image using Glide or Picasso
        val productImageView = findViewById<ImageView>(R.id.product_image)
        Glide.with(this)
            .load(product.imageUrl)
            .into(productImageView)

        findViewById<Button>(R.id.button).setOnClickListener{
          this.finish();
        }
    }
}
