package com.payfire.ui.cart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.payfire.ProductsAdapter
import com.payfire.R
import com.payfire.databinding.ActivityCartBinding
import com.payfire.databinding.ActivityMainBinding
import com.payfire.databinding.FragmentHomeBinding
import com.payfire.model.product.Product
import com.payfire.ui.home.HomeViewModel

class CartActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

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
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.productsInCart.observe(this, Observer {
            loadRecyclerView(this, it)
        })

        viewModel.setupCart()
    }

    private fun loadRecyclerView(context: Context, products: List<Product>) {
        recyclerView = findViewById(R.id.cart_recycler_view)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CartAdapter(context, products)

        }
    }
}