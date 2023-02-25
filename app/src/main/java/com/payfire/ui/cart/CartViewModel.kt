package com.payfire.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payfire.DEFAULT_CART_NAME
import com.payfire.database.AppDatabase
import com.payfire.database.model.cart.Cart
import com.payfire.database.model.cart.CartEntry
import com.payfire.database.model.product.Product
import com.payfire.transformDbProductToUiProduct
import com.payfire.transformDbProductsToUiProducts
import com.payfire.ui.product.ProductUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val productsInCart = MutableLiveData<List<ProductUI>>()

    private val cartDao by lazy { AppDatabase.invoke(application).cartDao() }

    private val productDao by lazy { AppDatabase.invoke(application).productDao() }

    fun setup() {
        viewModelScope.launch(Dispatchers.IO) {
            val cart = cartDao.getDefaultCart()
            val cartWithProducts = cartDao.getCartWithEntries(cart.cartId).first()
            val uiProducts = cartWithProducts.cartEntries.map {
                transformDbProductToUiProduct(productDao.getProduct(it.productId)).apply {
                    this.count = it.count
                }
            }

            productsInCart.postValue(uiProducts)
        }
    }

}