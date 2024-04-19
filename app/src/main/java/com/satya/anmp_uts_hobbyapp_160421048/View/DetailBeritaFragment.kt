package com.satya.anmp_uts_hobbyapp_160421048.View

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.satya.anmp_uts_hobbyapp_160421048.R
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.BeritaViewModel
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.ParagrafViewModel
import com.satya.anmp_uts_hobbyapp_160421048.ViewModel.UserViewModel
import com.satya.anmp_uts_hobbyapp_160421048.databinding.FragmentDetailBeritaBinding
import com.squareup.picasso.Picasso


/**
 * A simple [Fragment] subclass.
 * Use the [DetailBeritaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailBeritaFragment : Fragment() {
    private lateinit var binding: FragmentDetailBeritaBinding
    private lateinit var viewModelBerita: BeritaViewModel
    private lateinit var viewModelParagraf: ParagrafViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var i = 0

        viewModelBerita = ViewModelProvider(this).get(BeritaViewModel::class.java)
        viewModelParagraf = ViewModelProvider(this).get(ParagrafViewModel::class.java)

        if(arguments != null) {
            val beritaId =
                DetailBeritaFragmentArgs.fromBundle(requireArguments()).beritaId

            viewModelBerita.fetchBeritaById(beritaId)
            viewModelParagraf.fetchParagraf(beritaId)

            observeBerita()
            observeParagraf(i)
        }

        binding.btnNext.setOnClickListener {
            i++

            observeParagraf(i)
        }

        binding.btnPrev.setOnClickListener {
            i--

            observeParagraf(i)
        }
    }

    fun observeBerita() {
        viewModelBerita.beritaData.observe(viewLifecycleOwner, Observer {
            binding.txtJudulBerita.setText(viewModelBerita.beritaData.value?.judul)
            binding.txtKreator.setText(viewModelBerita.beritaData.value?.kreator)

            val url = viewModelBerita.beritaData.value?.gambar
            val builder = Picasso.Builder(binding.imgBerita.context)
            builder.listener{ picasso, uri, exception ->
                exception.printStackTrace()
            }
            Picasso.get().load(url).into(binding.imgBerita)
        })
    }

    fun observeParagraf(i: Int) {
        viewModelParagraf.paragrafLD.observe(viewLifecycleOwner, Observer {
            binding.txtSubJudul.setText(viewModelParagraf.paragrafLD.value?.get(i)?.subjudul)
            binding.txtPPar.setText(viewModelParagraf.paragrafLD.value?.get(i)?.konten)

            if(i == 0){
                binding.btnPrev.isEnabled = false
                binding.btnNext.isEnabled = true
            }
            else if(i == viewModelParagraf.paragrafLD.value?.count()!! - 1) {
                binding.btnNext.isEnabled = false
                binding.btnPrev.isEnabled = true
            }
            else{
                binding.btnPrev.isEnabled = true
                binding.btnNext.isEnabled = true
            }
        })
    }
}