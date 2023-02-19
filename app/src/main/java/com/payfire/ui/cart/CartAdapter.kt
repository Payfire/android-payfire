package com.payfire.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.payfire.R
import com.payfire.model.product.Product

class CartAdapter(
    private val context: Context,
    private val products: List<Product>,
) : RecyclerView.Adapter<CartAdapter.CartProductViewHolder>() {

    override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
        val product = products[position]
        holder.image.setImageResource(
            context.resources.getIdentifier(
                product.imgUrl,
                "drawable",
                context.packageName
            )
        )
        holder.name.text = product.name
        holder.price.text = String.format("%.2f", product.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_in_cart_card, parent, false)

        return CartProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    class CartProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
        var name: TextView = productView.findViewById(R.id.name)
        var price: TextView = productView.findViewById(R.id.price)
        var image: ImageView = productView.findViewById(R.id.img)
    }
}