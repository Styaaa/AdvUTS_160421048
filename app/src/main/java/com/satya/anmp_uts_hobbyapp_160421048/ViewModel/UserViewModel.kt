package com.satya.anmp_uts_hobbyapp_160421048.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.satya.anmp_uts_hobbyapp_160421048.Model.User
import com.satya.anmp_uts_hobbyapp_160421048.View.LoginFragment
import com.satya.anmp_uts_hobbyapp_160421048.View.LoginFragmentDirections
import com.satya.anmp_uts_hobbyapp_160421048.View.MainActivity
import org.json.JSONObject

class UserViewModel(application: Application): AndroidViewModel(application) {
    val usersLD = MutableLiveData<User>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun signUpRequest(username: String, password: String, fname: String, lname: String, email: String, activity: Context){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.101.43/hobbyapps/signup.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

//                Log.d("showvoley", it.toString())
                val obj = JSONObject(it)


                if(obj.getString("status") == "success"){
                    val data = obj.getString("message")

                    Log.d("showvoley", data)
                    Toast.makeText(activity, "Registrasi telah berhasil.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                if (username != null) params["username"] = username
                if (password != null) params["password"] = password
                if (fname != null) params["nama_depan"] = fname
                if (lname != null) params["nama_belakang"] = lname
                if (email != null) params["email"] = email

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun signInRequest(username: String, password: String, view: View){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.101.43/hobbyapps/signin.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

                Log.d("showvoley", it.toString())
                val obj = JSONObject(it)

                if(obj.getString("result") == "success"){
                    val data = obj.getJSONObject("data")

                    val sType = object : TypeToken<User>() { }.type
                    val result =Gson().fromJson<User>(data.toString(), sType)
                    usersLD.value = result

                    val action = LoginFragmentDirections.actionHome(result.id.toString())
                    Navigation.findNavController(view).navigate(action)

                    Toast.makeText(getApplication(), usersLD.value.toString(), Toast.LENGTH_SHORT).show()

                    Log.d("showvoley", data.toString())

                }
            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                if (username != null) params["username"] = username
                if (password != null) params["password"] = password

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun fetchUser(id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.101.43/hobbyapps/fetchUser.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {

                Log.d("showvoley", it.toString())
                val obj = JSONObject(it)

                if(obj.getString("result") == "success"){
                    val data = obj.getJSONObject("data")

                    val sType = object : TypeToken<User>() { }.type
                    val result =Gson().fromJson<User>(data.toString(), sType)
                    usersLD.value = result

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
                if (id != null) params["id"] = id

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    fun updateUser(password: String, fname: String, lname: String, email: String, id: String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.101.43/hobbyapps/updateUser.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("showvoley", it)
            },
            Response.ErrorListener {
                Log.d("showvoley", it.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                if (password != null) params["password"] = password
                if (fname != null) params["fname"] = fname
                if (lname != null) params["lname"] = lname
                if (email != null) params["email"] = email
                if (id != null) params["id"] = id

                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}