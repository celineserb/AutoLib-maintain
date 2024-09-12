package com.clovertech.autolib.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.databinding.NotificationLayoutBinding
import com.clovertech.autolib.model.Task
import com.clovertech.autolib.model.Notification


class NotificationsAdapter(val context: Context):RecyclerView.Adapter<NotificationsAdapter.NotificationHolder>()
{

    var data = mutableListOf<Notification>()
    val tasks = mutableListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        val inflater = LayoutInflater.from(context)
        val binding = NotificationLayoutBinding.inflate(inflater,parent, false)
        return NotificationHolder(binding)

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        holder.titre.text = context.getString(R.string.notif_title)
        //holder.date.text=data[position].date
        //holder.image.setImageResource(data[position].image)
        holder.lu.isVisible= !data[position].lu
        holder.itemView.setOnClickListener{

        }

    }

    fun setNotificationList(list: List<Notification>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    inner class NotificationHolder(binding: NotificationLayoutBinding)
        :RecyclerView.ViewHolder(binding.root) {
        val titre: TextView = binding.contenu
        val date: TextView = binding.date
        //val image: ImageView = view.findViewById(R.id.roundedimage)
        val lu: ImageView = binding.lu
    }


}

