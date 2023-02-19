package com.payfire.database.model.cart

import androidx.room.*
import com.payfire.database.model.product.Product

@Entity
class Cart(
    val name: String = "default",
) {
    @PrimaryKey(autoGenerate = false)
    var cartId: Long = 0
}

@Entity(primaryKeys = ["productId", "cartId"])
data class CartProductCrossRef(
    val cartId: Long,
    val productId: Long
)

data class CartWithProducts(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "productId",
        associateBy = Junction(CartProductCrossRef::class)
    )
    val products: List<Product>
)