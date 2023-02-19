package com.payfire.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.payfire.database.model.cart.Cart
import com.payfire.database.model.cart.CartDao
import com.payfire.database.model.cart.CartProductCrossRef
import com.payfire.database.model.product.Product
import com.payfire.database.model.product.ProductDao

@Database(entities = [Product::class, Cart::class, CartProductCrossRef::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao

    companion object {
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "payfiredatabase"
        ).build()
    }
}