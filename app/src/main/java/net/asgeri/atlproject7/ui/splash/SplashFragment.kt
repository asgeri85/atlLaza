package net.asgeri.atlproject7.ui.splash

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.asgeri.atlproject7.base.BaseFragment
import net.asgeri.atlproject7.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateScreen()
    }

    private fun navigateScreen() {
        lifecycleScope.launch {
            val isAuth = getAuth()
            delay(3000)
            if (isAuth) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }

        }
    }

    private fun getAuth(): Boolean {
        val sp = requireActivity().getSharedPreferences("product_local", Context.MODE_PRIVATE)

        return sp.getBoolean("isAuth", false)
    }

}