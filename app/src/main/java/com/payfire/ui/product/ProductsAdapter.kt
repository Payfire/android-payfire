package com.payfire.ui.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.payfire.R

class ProductsAdapter(
    private val context: Context,
    private val products: List<ProductUI>,
    private val onClickProduct: (product: ProductUI) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        //Picasso.get().load(product.imgUrl).into(holder.image)
        holder.image.setImageResource(
            context.resources.getIdentifier(
                product.image,
                "drawable",
                context.packageName
            )
        )
        holder.name.text = product.name
        holder.price.text = String.format("%.2f", product.price)

        holder.addButton.setOnClickListener {
            onClickProduct.invoke(product)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)

        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    class ProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView) {
        var name: TextView = productView.findViewById(R.id.name)
        var price: TextView = productView.findViewById(R.id.price)
        var image: ImageView = productView.findViewById(R.id.img)
        var addButton: Button = productView.findViewById(R.id.add_button)
    }
}