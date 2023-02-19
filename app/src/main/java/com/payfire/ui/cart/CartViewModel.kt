package com.payfire.ui.cart

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.payfire.DEFAULT_CART_NAME
import com.payfire.database.AppDatabase
import com.payfire.database.model.cart.Cart
import com.payfire.database.model.product.Product
import com.payfire.transformDbProductsToUiProducts
import com.payfire.ui.product.ProductUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val productsInCart = MutableLiveData<List<ProductUI>>()

    private val cartDao by lazy { AppDatabase.invoke(application).cartDao() }
    fun setup() {
        viewModelScope.launch(Dispatchers.IO) {
            val cart = cartDao.getDefaultCart()
            val cartWithProducts = cartDao.getCartWithProducts(cart.cartId).first()
            val uiProducts = transformDbProductsToUiProducts(cartWithProducts.products)
            productsInCart.postValue(uiProducts)
        }
    }


}