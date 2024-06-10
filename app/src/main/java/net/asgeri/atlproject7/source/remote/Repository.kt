package net.asgeri.atlproject7.source.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import net.asgeri.atlproject7.model.BrandModel
import javax.inject.Inject

class Repository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val productService: ProductService
) {

    suspend fun loginUser(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).await()

    suspend fun registerUser(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()

    suspend fun getAllProducts() = productService.getAllProducts()


}