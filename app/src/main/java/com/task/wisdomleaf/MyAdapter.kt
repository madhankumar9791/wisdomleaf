package com.task.wisdomleaf_task

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task.wisdomleaf.R

class MyAdapter(private val data: List<Property>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(property: Property) {
            val title = view.findViewById<TextView>(R.id.tvTitle)
            val imageView = view.findViewById<ImageView>(R.id.imageView)
            val description = view.findViewById<TextView>(R.id.tvDescription)
            val card = view.findViewById<CardView>(R.id.card)

            title.text = property.title
            description.text = property.description
            Toast.makeText(view.context, "Data Updated", Toast.LENGTH_SHORT).show();


            imageView.setOnClickListener(View.OnClickListener {
                Glide.with(view.context).load(property.image).centerCrop().into(imageView)

            })


            description.setOnClickListener {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(view.context)

                // set message of alert dialog
                dialogBuilder.setMessage(property.description)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action

                    // negative button text and action
                    .setNegativeButton("Close", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle(property.title)
                // show alert dialog
                alert.show()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }


}