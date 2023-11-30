package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.Serializable


class MainActivity : AppCompatActivity() {

    val cartItems = ArrayList<Product>()
    private lateinit var recyclerView: RecyclerView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val products = ArrayList<Product>()
        products.add(
            Product(
                "iPad",
                "iPad Pro 11-inch",
                400.0,
                "https://www.komplett.no/img/p/800/1219822.jpg"
            )
        )
        products.add(
            Product(
                "MacBook M3 Pro",
                "12-core CPU\n18-core GPU",
                2500.00,
                "https://www.apple.com/newsroom/images/product/mac/standard/Apple-MacBook-Pro-M2-Pro-and-M2-Max-hero-230117.jpg.og.jpg?202310101652"
            )
        )
        products.add(
            Product(
                "Dell Inspire",
                "13th Gen Intel® CoreTM i7",
                1499.00,
                "data:image/jpeg;base64,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"
            )
        )
        products.add(
            Product(
                "Logitech Keyboard",
                "Logitech - PRO X\nTKL LIGHTSPEED Wireless",
                199.00,
                "https://m.media-amazon.com/images/I/71TyGobdfQL._AC_UF894,1000_QL80_.jpg"
            )
        )
        products.add(
            Product(
                "MacBook M3 Max",
                "14-core CPU\n30-core GPU",
                3499.00,
                "https://m.media-amazon.com/images/I/61x8BQ8-5wL._AC_UF1000,1000_QL80_.jpg"
            )
        )


        recyclerView = findViewById(R.id.list_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ProductAdapter(products, ::addItemToCart)
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.view_cart).setOnClickListener {
            Toast.makeText(this, cartItems.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private fun addItemToCart(product: Product) {
        cartItems.add(product)
        Toast.makeText(this, "Item Added To cart Successfully", Toast.LENGTH_SHORT).show()
    }
}


data class Product(
    val productName: String,
    val productDescription: String,
    val cost: Double,
    val imageUrl: String
) : Serializable

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.product_name)
    val descriptionTextView: TextView = itemView.findViewById(R.id.product_description)
    val priceTextView: TextView = itemView.findViewById(R.id.product_price)
    val imageView: ImageView = itemView.findViewById(R.id.product_image)
    val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart)
}

class ProductAdapter(
    private val products: List<Product>,
    private val addToCart: (input: Product) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.nameTextView.text = product.productName
        holder.descriptionTextView.text = product.productDescription
        holder.priceTextView.text = "$${product.cost}"
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .into(holder.imageView)

        holder.addToCartButton.setOnClickListener {
            addToCart(products[position])
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailsActivity::class.java)
            intent.putExtra("product", product)
            holder.itemView.context.startActivity(intent)
        }


    }


    override fun getItemCount(): Int {
        return products.size
    }
}
