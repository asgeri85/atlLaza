package net.asgeri.atlproject7.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
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