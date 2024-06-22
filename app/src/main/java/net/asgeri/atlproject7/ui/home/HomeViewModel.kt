package net.asgeri.atlproject7.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.asgeri.atlproject7.R
import net.asgeri.atlproject7.model.BrandModel
import net.asgeri.atlproject7.model.ProductResponse
import net.asgeri.atlproject7.source.NetworkResource
import net.asgeri.atlproject7.source.remote.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val brandData = MutableLiveData<List<BrandModel>>()
    val data = MutableLiveData<List<ProductResponse>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    init {
        getInitData()
    }

    private fun getInitData() {
        createBrand()
        getProducts()
    }

    private fun createBrand() {
        val brandList = listOf(
            BrandModel(R.drawable.nike, "Nike"),
            BrandModel(R.drawable.adidas, "Adidas"),
            BrandModel(R.drawable.fila, "Fila"),
            BrandModel(R.drawable.adidas, "Adidas"),
            BrandModel(R.drawable.fila, "Fila"),
            BrandModel(R.drawable.adidas, "Adidas"),
            BrandModel(R.drawable.fila, "Fila")
        )

        brandData.value = brandList
    }

    private fun getProducts() {
        loading.value = true
        viewModelScope.launch {
            when (val response = repository.getAllProducts()) {
                is NetworkResource.Success -> {
                    response.data?.let {
                        data.value = it
                        loading.value = false
                    }
                }

                is NetworkResource.Error -> {
                    response.message?.let {
                        error.value = it
                        loading.value = false
                    }
                }
            }
        }
    }

}