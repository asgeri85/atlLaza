package net.asgeri.atlproject7.source.remote

import net.asgeri.atlproject7.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

}