package com.satya.anmp_uts_hobbyapp_160421048.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.satya.anmp_uts_hobbyapp_160421048.R
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.UserViewModel
import com.satya.anmp_uts_hobbyapp_160421048.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        binding.labelCreateAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionRegist()
            Navigation.findNavController(it).navigate(action)
        }

        binding.btnSignIn.setOnClickListener {
            val username = binding.txtUsername.text
            val password = binding.txtPassword.text

            viewModel.signInRequest(username.toString(), password.toString(), it)
        }
    }
}