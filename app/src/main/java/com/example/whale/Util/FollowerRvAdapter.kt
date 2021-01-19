package com.example.whale.Adapters

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.whale.ProfileInfoActivity
import com.example.whale.R
import com.example.whale.ThingsTodo
import com.google.firebase.database.FirebaseDatabase

class FollowerRvAdapter(val context: Context, val questList: ArrayList<ThingsTodo>) :
    RecyclerView.Adapter<FollowerRvAdapter.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.follower_rv_item, parent,false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return questList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(questList[position], context)

    }

    inner class Holder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        val point1 = itemView?.findViewById<TextView>(R.id.item_point)
        val quest1 = itemView?.findViewById<TextView>(R.id.item_quest)
        val layoutQuest = itemView?.findViewById<LinearLayout>(R.id.layoutQuest)
        fun bind(toDo: ThingsTodo, context: Context)
        {
            point1?.text = toDo.point
            quest1?.text = toDo.task
        }
    }

    fun deleteTask(userName: String, taskName:String){
//필요한 것 = 업애려고하는 과제의 이름과 그 과제를 할당받은 팔로워의 이름
        FirebaseDatabase.getInstance().getReference().child(userName).child(taskName).removeValue()
    }

}