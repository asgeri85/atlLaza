package net.asgeri.atlproject7.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import net.asgeri.atlproject7.base.BaseFragment
import net.asgeri.atlproject7.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val brandAdapter = BrandAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding.rvBrand.adapter = brandAdapter
    }

    private fun observeData() {
        viewModel.brandData.observe(viewLifecycleOwner) {
            brandAdapter.updateList(it)
        }

        viewModel.data.observe(viewLifecycleOwner) {

        }

        viewModel.loading.observe(viewLifecycleOwner) {

        }

        viewModel.error.observe(viewLifecycleOwner) {

        }
    }


}