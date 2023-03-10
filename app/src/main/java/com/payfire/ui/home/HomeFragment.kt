package com.payfire.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.payfire.ui.product.ProductsAdapter
import com.payfire.R
import com.payfire.databinding.FragmentHomeBinding
import com.payfire.ui.product.ProductUI

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireActivity()
        viewModel = ViewModelProvider(context)[HomeViewModel::class.java]

        viewModel.products.observe(requireActivity(), Observer {
            loadRecyclerView(context, it)
        })

        viewModel.setup()
    }

    private fun loadRecyclerView(context: Context, products: List<ProductUI>) {
        val recyclerView: RecyclerView? = view?.findViewById(R.id.recycler_view)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductsAdapter(context, products) { uiProduct ->
                viewModel.addProductToCart(uiProduct)
            }

        }
    }

}