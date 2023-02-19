package com.payfire.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payfire.model.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {

    val productsInCart = MutableLiveData<MutableList<Product>>()

    fun setup() {
        viewModelScope.launch(Dispatchers.Default) {
            //products.postValue(ProductsRepository().fetchAllProductsRetrofit())

            productsInCart.postValue(loadProducts2())
        }
    }

    private fun loadProducts2(): MutableList<Product> {
        return productsInCart.value!!
    }

    private fun loadProducts(): MutableList<Product> {
        return mutableListOf(
            Product("coca_cola", "Coca Cola 1L", "coca_cola", 1.60),
            Product("coca_cola_zero", "Coca Cola Zero 1L", "coca_cola_zero", 1.60),
        )
    }

    fun addProductToList(product: Product) {
        productsInCart.value!!.add(product)
    }
}