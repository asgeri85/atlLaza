package net.asgeri.atlproject7.source.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    private suspend fun <T> safeApiRequest(request: suspend () -> Response<T>) = flow {
        try {
            if (request.invoke().isSuccessful) {
                emit(NetworkResource.Success(request().body()))
            } else {
                emit(NetworkResource.Error(request().errorBody().toString()))
            }
        } catch (e: Exception) {
            emit(NetworkResource.Error(e.localizedMessage.toString()))
        }
    }.flowOn(Dispatchers.IO)


}