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
import com.satya.anmp_uts_hobbyapp_160421048.Model.Paragraf
import com.satya.anmp_uts_hobbyapp_160421048.Model.User
import org.json.JSONObject

class ParagrafViewModel(application: Application): AndroidViewModel(application) {
    val paragrafLD = MutableLiveData<ArrayList<Paragraf>>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetchParagraf(id: Int){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.101.43/hobbyapps/fetchParagraf.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

                Log.d("showvoley", it.toString())
                val obj = JSONObject(it)

                if(obj.getString("result") == "success"){
                    val data = obj.getJSONArray("data")

                    val sType = object : TypeToken<List<Paragraf>>() { }.type
                    val result = Gson().fromJson<List<Paragraf>>(data.toString(), sType)
                    paragrafLD.value = result as ArrayList<Paragraf>

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