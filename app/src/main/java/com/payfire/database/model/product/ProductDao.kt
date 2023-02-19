package com.payfire.database.model.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(products: List<Product?>?)

    @Query("SELECT * FROM product WHERE name = :productName")
    suspend fun getProduct(productName: String): Product

    @Query("SELECT * FROM product")
    suspend fun getAll(): List<Product>

    @Query("DELETE FROM product WHERE productId = :id")
    suspend fun deleteProduct(id: Long)
}