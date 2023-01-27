package fr.isen.vincentdubaret.androidcontactds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso

import fr.isen.vincentdubaret.androidcontactds.databinding.ActivityContactDetailsBinding
import java.text.DateFormat
import java.util.*

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val myContactDetails = intent.extras?.get("contact_details") as DataContactDetails
        if (myContactDetails.picture.large.isNotEmpty()) {
            Picasso.with(this).load(myContactDetails.picture.large)
                .transform(CircleTransform())
                .error(R.drawable.user)
                .into(binding.imageAvatar)
        } else {
            binding.imageAvatar.setImageResource(R.drawable.user)
        }
        binding.textName.text = myContactDetails.name.first + " " + myContactDetails.name.last.uppercase()
        binding.textAddress.text = myContactDetails.location.street.number + " " + myContactDetails.location.street.name
        binding.textMail.text = myContactDetails.email
        binding.textPhone.text = myContactDetails.cell.replace("-", " ")
        binding.textBirthday.text =  myContactDetails.dob.date.substring(8,10) + "/" + myContactDetails.dob.date.substring(5,7) + "/" + myContactDetails.dob.date.substring(0,4)
    }
}