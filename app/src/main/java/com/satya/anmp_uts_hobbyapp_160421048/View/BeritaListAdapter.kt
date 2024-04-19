package com.satya.anmp_uts_hobbyapp_160421048.View

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.satya.anmp_uts_hobbyapp_160421048.Model.Berita
import com.satya.anmp_uts_hobbyapp_160421048.databinding.BeritaItemBinding
import com.squareup.picasso.Picasso

class BeritaListAdapter(val beritaList: ArrayList<Berita>)
    : RecyclerView.Adapter<BeritaListAdapter.BeritaViewHolder>() {
    class BeritaViewHolder(var binding: BeritaItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BeritaListAdapter.BeritaViewHolder {
        val binding = BeritaItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return BeritaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeritaListAdapter.BeritaViewHolder, position: Int) {
        holder.binding.txtJudul.text = beritaList[position].judul
        holder.binding.txtCreator.text = beritaList[position].kreator
        holder.binding.txtDesc.text = beritaList[position].deskripsi

        val url = beritaList[position].gambar
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener{ picasso, uri, exception ->
            exception.printStackTrace()
        }
        Picasso.get().load(url).into(holder.binding.imgHobby)

        holder.binding.btnRead.setOnClickListener {
            val berita_id = beritaList[position].id

            val action = HomeFragmentDirections.actionDetailFragment(berita_id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return beritaList.size
    }

    fun updateBeritaList(newBeritalist: ArrayList<Berita>){
        beritaList.clear()
        beritaList.addAll(newBeritalist)
        notifyDataSetChanged()
    }
}