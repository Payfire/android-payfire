package com.payfire.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.payfire.DEFAULT_CART_NAME
import com.payfire.database.AppDatabase
import com.payfire.database.model.cart.Cart
import com.payfire.database.model.cart.CartProductCrossRef
import com.payfire.database.model.cart.CartWithProducts
import com.payfire.database.model.product.Product
import com.payfire.database.model.product.ProductDao
import com.payfire.transformDbProductsToUiProducts
import com.payfire.ui.product.ProductUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val products = MutableLiveData<List<ProductUI>>()
    private val productDao by lazy { AppDatabase.invoke(application).productDao() }
    private val cartDao by lazy { AppDatabase.invoke(application).cartDao() }

    fun setup() {
        viewModelScope.launch(Dispatchers.IO) {
            var dbProducts = productDao.getAll()
            if (dbProducts.isEmpty()) {
                initialProductsLoad(productDao).also { dbProducts = it }
            }
            val uiProducts = transformDbProductsToUiProducts(dbProducts)
            products.postValue(uiProducts)
        }
    }

    fun addProductToCart(uiProduct: ProductUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val dbProduct = productDao.getProduct(uiProduct.name)
            val cart = cartDao.getDefaultCart()
            val cartProduct = CartProductCrossRef(cart.cartId, dbProduct.productId)
            cartDao.insertCartProductCrossRef(cartProduct)
            val cartWithProducts = cartDao.getCartWithProducts(cart.cartId).first()
            cartWithProducts.products
        }
    }

    private suspend fun initialProductsLoad(productDao: ProductDao): List<Product> {
        val products = mutableListOf(
            Product("Coca Cola 1L", "coca_cola", 1.60),
            Product("Coca Cola Zero 1L", "coca_cola_zero", 1.60),
            Product("Coca Cola 0,330L", "coca_cola_ken", 1.00),
            Product("Coca Cola Zero 0,330L", "coca_cola_zero_ken", 1.00),
            Product("Green 0,330L", "green", 1.30),
            Product("Monster 0,5L", "monster", 2.00),
            Product("RedBull 0,330L", "redbull", 1.80),
            Product("RedBull No Sugar 0,330L", "redbull_no_sugar", 2.20),
        )

        productDao.insertAllProducts(products)

        return productDao.getAll()
    }
}