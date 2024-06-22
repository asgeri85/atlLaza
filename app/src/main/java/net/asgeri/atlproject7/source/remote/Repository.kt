package net.asgeri.atlproject7.source.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import net.asgeri.atlproject7.source.NetworkResource
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val productService: ProductService
) {

    suspend fun loginUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

    suspend fun registerUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun getAllProducts() = safeApiRequest {
        productService.getAllProducts()
    }




    private suspend fun <T> safeApiRequest(request: suspend () -> Response<T>): NetworkResource<T> {
        return try {
            if (request.invoke().isSuccessful) {
                NetworkResource.Success(request().body())
            } else {
                NetworkResource.Error(request().errorBody().toString())
            }
        } catch (e: Exception) {
            NetworkResource.Error(e.localizedMessage.toString())
        }
    }


}