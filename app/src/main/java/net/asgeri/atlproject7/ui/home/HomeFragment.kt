package net.asgeri.atlproject7.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.asgeri.atlproject7.base.BaseFragment
import net.asgeri.atlproject7.databinding.FragmentHomeBinding
import net.asgeri.atlproject7.utils.gone
import net.asgeri.atlproject7.utils.visible

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val brandAdapter = BrandAdapter()
    private val productAdapter = ProductAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.rvBrand.adapter = brandAdapter
        binding.rvProduct.adapter = productAdapter

        val list = arrayListOf<Int>()

        lifecycleScope.launch {
            getNumber().filter { it % 2 == 0 }.collect {
                list.add(it)
            }
        }

    }

    suspend fun getNumber() = flow {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
    }

    private fun observeData() {
        viewModel.brandData.observe(viewLifecycleOwner) {
            brandAdapter.updateList(it)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            productAdapter.updateList(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.animationView.visible()
                binding.constraintHome.alpha = 0.3f
            } else {
                binding.animationView.gone()
                binding.constraintHome.alpha = 1f
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }


}