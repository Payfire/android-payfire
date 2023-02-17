package com.payfire.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payfire.model.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val products = MutableLiveData<List<Product>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Default) {
            //products.postValue(ProductsRepository().fetchAllProductsRetrofit())

            products.postValue(loadProducts())
        }
    }

    private fun loadProducts(): List<Product> {
        return mutableListOf(
            Product("coca_cola", "Coca Cola 1L", "coca_cola", 1.60),
            Product("coca_cola_zero", "Coca Cola Zero 1L", "coca_cola_zero", 1.60),
            Product("coca_cola_ken", "Coca Cola 0,330L", "coca_cola_ken", 1.00),
            Product("coca_cola_zero_ken", "Coca Cola Zero 0,330L", "coca_cola_zero_ken", 1.00),
            Product("green", "Green 0,330L", "green", 1.30),
            Product("monster", "Monster 0,5L", "monster", 2.00),
            Product("redbull", "RedBull 0,330L", "redbull", 1.80),
            Product("redbull_no_sugar", "RedBull No Sugar 0,330L", "redbull_no_sugar", 2.20),
        )
    }
}