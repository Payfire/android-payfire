package com.payfire.database.model.cart

import androidx.room.*
import com.payfire.database.model.product.Product

@Entity
class Cart(
    val name: String = "default",
) {
    @PrimaryKey(autoGenerate = true)
    var cartId: Long = 0
}

@Entity
class CartEntry(val productId: Long) {
    @PrimaryKey(autoGenerate = true)
    var cartEntryId: Long = 0

    var count: Int = 0

}

@Entity(primaryKeys = ["cartId", "cartEntryId"])
data class CartCartEntryCrossRef(
    val cartId: Long,
    val cartEntryId: Long
)

data class CartWithEntries(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "cartId",
        entityColumn = "cartEntryId",
        associateBy = Junction(CartCartEntryCrossRef::class)
    )
    val cartEntries: List<CartEntry>
)