package com.payfire.database.model.cart

import androidx.room.*
import com.payfire.DEFAULT_CART_NAME

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart): Long

    @Transaction
    @Query("SELECT * FROM cart WHERE name = :name")
    suspend fun getCart(name: String): Cart?

    @Transaction
    @Query("DELETE FROM cart WHERE name = :name")
    suspend fun deleteCart(name: String)

    @Transaction
    @Query("SELECT * FROM cart WHERE cartId = :id")
    suspend fun getCartWithProducts(id: Long): List<CartWithProducts>

    @Transaction
    @Query("SELECT * FROM cart WHERE name = :name")
    suspend fun getCartWithProducts(name: String): List<CartWithProducts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartProductCrossRef(join: CartProductCrossRef)

    suspend fun getDefaultCart() : Cart {
        var cart = this.getCart(DEFAULT_CART_NAME)
        if(cart == null) {
            cart = loadDefaultCart()
        }

        return cart
    }

    private suspend fun loadDefaultCart() : Cart {
        val defaultCart = Cart(DEFAULT_CART_NAME)
        this.insertCart(defaultCart)

        return this.getCart(DEFAULT_CART_NAME)!!
    }
}