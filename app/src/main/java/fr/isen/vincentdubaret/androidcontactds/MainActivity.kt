package fr.isen.vincentdubaret.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

import fr.isen.vincentdubaret.androidcontactds.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myContactAdapter : ContactAdapter
    private lateinit var parsedData : DataResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Getting item list
        val stringReq : StringRequest =
            object : StringRequest(
                Method.GET, "https://randomuser.me/api/?results=10&nat=fr",
                Response.Listener { response ->
                    parsedData = Gson().fromJson(response, DataResult::class.java)
                    Log.d("####HUMAN####", parsedData.toString())
                    renderMenu()
                },
                Response.ErrorListener { error ->
                    Log.d("API", "error => $error")
                }
            ){}
        Volley.newRequestQueue(this).add(stringReq)
    }
    private fun renderMenu() {
        val list = arrayListOf(1, 2, 3)
        myContactAdapter = ContactAdapter(this, parsedData.results) /*{
                contactDetails: ContactDetails -> myOnClickItem(contactDetails)
        }*/
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.contactsRecycleView.layoutManager = layoutManager
        binding.contactsRecycleView.adapter = myContactAdapter
    }

    //Onclick function to pass to recycle items
   // private fun myOnClickItem(contactDetails: ContactDetails){
   //     //Log.d("##########HUMAN############", message)
   //     val myIntent = Intent(this@MainActivity, ContactDetailActivity::class.java)
   //     myIntent.putExtra("contact_details", contactDetails as Serializable)
   //     startActivity(myIntent)
   // }

}