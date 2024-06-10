package net.asgeri.atlproject7.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.asgeri.atlproject7.base.BaseFragment
import net.asgeri.atlproject7.databinding.FragmentRegisterBinding
import net.asgeri.atlproject7.utils.gone
import net.asgeri.atlproject7.utils.visible

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.editEmailLogin.text.toString().trim()
        val password = binding.editPasswordLogin.text.toString().trim()
        val rePassword = binding.editRePassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()) {
            if (password == rePassword) {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    viewModel.registerUser(email, password)
                } else {
                    Toast.makeText(context, "Email düz deyil", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Şifreler eyni değil", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Boş ola bilmez", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        viewModel.isSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Kayıt başarılı", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            } else {
                Toast.makeText(context, "Xetalı Giriş", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) binding.animationView.visible() else binding.animationView.gone()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}