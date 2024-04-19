package com.satya.anmp_uts_hobbyapp_160421048.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.satya.anmp_uts_hobbyapp_160421048.R
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.UserViewModel
import com.satya.anmp_uts_hobbyapp_160421048.databinding.FragmentRegistBinding
import org.json.JSONObject


/**
 * A simple [Fragment] subclass.
 * Use the [RegistFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistFragment : Fragment() {
    private lateinit var binding: FragmentRegistBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnSignUp.setOnClickListener {

            val username = binding.txtUsername.text
            val password = binding.txtConfirmPassword.text
            val fname = binding.txtFirstName.text
            val lname = binding.txtLastName.text
            val email = binding.txtEmail.text

//            if(binding.txtPassword.text == binding.txtConfirmPassword.text){
//                password = binding.txtPassword.text.toString()
//            }

            viewModel.signUpRequest(username.toString(), password.toString(), fname.toString(), lname.toString(), email.toString(), view.context)

            val action = RegistFragmentDirections.actionLogin()
            Navigation.findNavController(it).navigate(action)
        }
    }
}