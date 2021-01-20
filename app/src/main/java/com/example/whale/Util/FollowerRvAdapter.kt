package com.example.whale.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.whale.App
import com.example.whale.R
import com.example.whale.ThingsTodo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile_info.*
import java.lang.Integer.parseInt

class FollowerRvAdapter(val context: Context, val questList: ArrayList<ThingsTodo>) :
    RecyclerView.Adapter<FollowerRvAdapter.Holder>(){
    var questList1 = arrayListOf<String>()
    var pointList1 = arrayListOf<Int>()
    var todoList = arrayListOf<ThingsTodo>()
    fun search(string: String)
    {
        FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("id", string)
            .addSnapshotListener()
            { querySnapshot, firebaseFireStoreException ->
                var map: Map<String, Any> =
                    querySnapshot?.documents?.first()?.data as Map<String, Any>
                questList1 = map["questList"] as ArrayList<String>
                pointList1 = map["pointList"] as ArrayList<Int>
                for(s in pointList1.indices)
                {
                    var thing1 = ThingsTodo(questList1[s], pointList1[s])
                    todoList.add(thing1)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.follower_rv_item, parent,false)
        return Holder(view)
    }
    override fun getItemCount(): Int {
        return questList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(questList[position], context)

        holder.layoutQuest?.setOnClickListener{
            var namefordelete = ""
            when (App.who) {
                1 -> {
                    namefordelete = App.follower_1[1]
                    search(App.follower_1[1])
                }
                2 -> {
                    namefordelete = App.follower_2[1]
                    search(App.follower_2[1])
                }
                3 -> {
                    namefordelete = App.follower_3[1]
                    search(App.follower_3[1])
                }
                4 -> {
                    namefordelete =App.follower_4[1]
                    search(App.follower_4[1])
                }
                5 -> {
                    namefordelete =App.follower_5[1]
                    search(App.follower_5[1])
                }
                6 -> {
                    namefordelete =App.follower_6[1]
                    search(App.follower_6[1])
                }
            }
            val y : String
            for(s in pointList1.indices)
            {
                if(holder.point1?.text.toString()== pointList1[s].toString()){
                  pointList1.remove(s)
                    questList1.removeAt(s)
                }

            }


            FirebaseFirestore.getInstance()
            .collection("users")
            .document(namefordelete)
            .update("questList", questList1)

            FirebaseFirestore.getInstance()
                .collection("users")
                .document(namefordelete)
                .update("pointList",pointList1)

        var a = holder.point1?.text.toString()
        var b = holder.quest1?.text.toString()
//            var point = 0
//            point += parseInt(a)
        //퀘스트 삭제
        //deleteTask("정현", holder.quest1?.text.toString())
        Toast.makeText(context,"퀘스트 ${b}이(가) 선택되었습니다.", Toast.LENGTH_SHORT).show()
    }
    }

    inner class Holder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        val quest1 = itemView?.findViewById<TextView>(R.id.item_quest)
        val point1 = itemView?.findViewById<TextView>(R.id.item_point)
        val layoutQuest = itemView?.findViewById<LinearLayout>(R.id.LayoutQuest)

        fun bind(toDo: ThingsTodo, context: Context)
        {
            quest1?.text = toDo.title
            point1?.text = toDo.point.toString()
        }
    }

}