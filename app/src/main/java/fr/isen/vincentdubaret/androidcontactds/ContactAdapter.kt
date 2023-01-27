package fr.isen.vincentdubaret.androidcontactds

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

internal class ContactAdapter(private var myContext : Context, private var contactList: ArrayList<DataContactDetails>, private val listener: (DataContactDetails) -> Unit) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageAvatar: ImageView = view.findViewById(R.id.image_avatar)
        val textName : TextView = view.findViewById(R.id.text_name)
        val textAddress : TextView = view.findViewById(R.id.text_address)
        val textMail : TextView = view.findViewById(R.id.text_mail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]
        holder.textName.text = item.name.first + " " + item.name.last.uppercase()
        holder.textAddress.text = item.location.street.number + " " + item.location.street.name
        holder.textMail.text = item.email
        if (item.picture.medium.isNotEmpty()) {
            Picasso.with(myContext).load(item.picture.medium)
                .error(R.drawable.user)
                .into(holder.imageAvatar)
        } else {
            holder.imageAvatar.setImageResource(R.drawable.user)
        }
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = contactList.size
}