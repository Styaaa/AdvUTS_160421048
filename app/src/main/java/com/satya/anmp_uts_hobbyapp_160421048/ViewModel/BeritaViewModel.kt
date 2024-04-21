package com.satya.anmp_uts_hobbyapp_160421048.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satya.anmp_uts_hobbyapp_160421048.Model.Berita
import com.satya.anmp_uts_hobbyapp_160421048.Model.User
import org.json.JSONObject

class BeritaViewModel(application: Application): AndroidViewModel(application) {
    val beritaLD = MutableLiveData<ArrayList<Berita>>()
    val beritaData = MutableLiveData<Berita>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchBerita() {

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.119.43/hobbyapps/fetchBerita.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val obj = JSONObject(it)

                if(obj.getString("result") == "OK"){
                    val data = obj.getJSONArray("data")

                    val sType = object : TypeToken<List<Berita>>() { }.type
                    val result = Gson().fromJson<List<Berita>>(data.toString(), sType)
                    beritaLD.value = result as ArrayList<Berita>
                }



            },{
                Log.d("showBerita", it.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun fetchBeritaById(id: Int){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.119.43/hobbyapps/fetchBeritaById.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

                Log.d("showvoley", it.toString())
                val obj = JSONObject(it)

                if(obj.getString("result") == "success"){
                    val data = obj.getJSONObject("data")

                    val sType = object : TypeToken<Berita>() { }.type
                    val result =Gson().fromJson<Berita>(data.toString(), sType)
                    beritaData.value = result

//                    Toast.makeText(getApplication(), usersLD.value.toString(), Toast.LENGTH_SHORT).show()

                    Log.d("showvoley", data.toString())

                }
            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                if (id != null) params["id"] = id.toString()

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}