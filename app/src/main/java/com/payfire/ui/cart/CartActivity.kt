package com.payfire.ui.cart

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.payfire.R
import com.payfire.databinding.ActivityCartBinding
import com.payfire.database.model.product.Product
import com.payfire.ui.product.ProductUI

class CartActivity : AppCompatActivity() {

    lateinit var viewModel: CartViewModel

    private lateinit var _binding: ActivityCartBinding

    private val binding get() = _binding

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.let {
            supportActionBar!!.title = "CART"
        }
        setCartAdapter()
    }

    private fun setCartAdapter() {
        viewModel = ViewModelProvider(this)[CartViewModel::class.java]
        viewModel.productsInCart.observe(this, Observer {
            loadRecyclerView(this, it)
        })

        viewModel.setup()
    }

    private fun loadRecyclerView(context: Context, products: List<ProductUI>) {
        recyclerView = findViewById(R.id.cart_recycler_view)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CartAdapter(context, products)

        }
    }
}