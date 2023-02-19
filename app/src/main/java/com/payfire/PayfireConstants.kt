package com.payfire

import com.payfire.database.model.product.Product
import com.payfire.ui.product.ProductUI

val DEFAULT_CART_NAME = "default"

fun transformDbProductsToUiProducts(dbProducts : List<Product>) : List<ProductUI> {
    val uiProducts = mutableListOf<ProductUI>()
    dbProducts.forEach {
        uiProducts.add(transformDbProductToUiProduct(it))
    }

    return uiProducts
}

fun transformDbProductToUiProduct(dbProduct: Product) : ProductUI {
    return ProductUI(dbProduct.name, dbProduct.image, dbProduct.price)
}

fun transformUiProductsToDbProducts(uiProducts : List<ProductUI>) : List<Product> {
    val dbProducts = mutableListOf<Product>()
    uiProducts.forEach {
        dbProducts.add(transformUiProductToDbProduct(it))
    }

    return dbProducts
}

fun transformUiProductToDbProduct(uiProduct: ProductUI) : Product {
    return Product(uiProduct.name, uiProduct.image, uiProduct.price)
}