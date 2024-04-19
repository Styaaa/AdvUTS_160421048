package com.satya.anmp_uts_hobbyapp_160421048.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.satya.anmp_uts_hobbyapp_160421048.R
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.BeritaViewModel
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.UserViewModel
import com.satya.anmp_uts_hobbyapp_160421048.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: BeritaViewModel
    private val beritaListAdapter = BeritaListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BeritaViewModel::class.java)

//        val activity : AppCompatActivity = context as AppCompatActivity

        if(arguments != null) {
            val playerName =
                HomeFragmentArgs.fromBundle(requireArguments()).id

            (activity as MainActivity).idUser = playerName

//            Log.d("this", (activity as MainActivity).idUser)
        }

        viewModel.fetchBerita()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = beritaListAdapter

        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.beritaLD.observe(viewLifecycleOwner, Observer {
            beritaListAdapter.updateBeritaList(it)
        })
    }
}