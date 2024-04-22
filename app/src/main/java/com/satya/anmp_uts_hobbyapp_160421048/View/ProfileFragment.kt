package com.satya.anmp_uts_hobbyapp_160421048.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.satya.anmp_uts_hobbyapp_160421048.R
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.UserViewModel
import com.satya.anmp_uts_hobbyapp_160421048.databinding.FragmentProfileBinding
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val id = (activity as MainActivity).idUser

        viewModel.fetchUser(id)

        observeUser()

        binding.btnUpdate.setOnClickListener {
            val fname = binding.txtFName.text
            val lname = binding.txtLName.text
            val password = binding.txtPasssword.text
            val email = binding.txtEmail.text

            viewModel.updateUser(password.toString(), fname.toString(), lname.toString(), email.toString(), id)

//            viewModel.fetchUser(id)
//
//            observeUser()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.usersLD.value = null

            (activity as MainActivity).idUser = ""

            val action = ProfileFragmentDirections.actionLogout()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun observeUser() {
        viewModel.usersLD.observe(viewLifecycleOwner, Observer {

            binding.txtUsername.setText(viewModel.usersLD.value?.username)
            binding.txtFName.setText(viewModel.usersLD.value?.nama_depan)
            binding.txtLName.setText(viewModel.usersLD.value?.nama_belakang)
            binding.txtEmail.setText(viewModel.usersLD.value?.email)
            binding.txtPasssword.setText(viewModel.usersLD.value?.password)

//            Log.d("hehe", viewModel.usersLD.value?.username.toString())
        })
    }
}