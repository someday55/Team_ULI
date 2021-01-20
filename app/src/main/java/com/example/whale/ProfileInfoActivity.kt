package com.example.whale

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whale.Adapters.FollowerRvAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile_info.*
import java.lang.Integer.parseInt
import kotlin.collections.ArrayList

lateinit var auth: FirebaseAuth

class ProfileInfoActivity : AppCompatActivity(), View.OnClickListener {

    internal var dataSource: MutableList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)
        adding_task.setOnClickListener(this)

        var questList = arrayListOf<String>()
        var pointList = arrayListOf<Int>()
        var todoList = arrayListOf<ThingsTodo>()

        val fAdapter =
            FollowerRvAdapter(this, todoList)
        recycler_view.adapter = fAdapter

        val lm = LinearLayoutManager(this)
        recycler_view.layoutManager = lm
        recycler_view.setHasFixedSize(false)

        fun search(string: String)
        {
            FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("id", string)
                .addSnapshotListener()
                { querySnapshot, firebaseFireStoreException ->
                    var map: Map<String, Any> =
                        querySnapshot?.documents?.first()?.data as Map<String, Any>
                    questList = map["questList"] as ArrayList<String>
                    pointList = map["pointList"] as ArrayList<Int>
                    for(s in pointList.indices)
                    {
                        var thing1 = ThingsTodo(questList[s], pointList[s])
                        todoList.add(thing1)
                    }
                }
        }

        when (App.who) {
            1 -> {
                profile_email.text = App.follower_1[1]
                search(App.follower_1[1])
            }
            2 -> {
                profile_email.text = App.follower_2[1]
                search(App.follower_2[1])
            }
            3 -> {
                profile_email.text = App.follower_3[1]
                search(App.follower_3[1])
            }
            4 -> {
                profile_email.text = App.follower_4[1]
                search(App.follower_4[1])
            }
            5 -> {
                profile_email.text = App.follower_5[1]
                search(App.follower_5[1])
            }
            6 -> {
                profile_email.text = App.follower_6[1]
                search(App.follower_6[1])
            }
        }

        FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("id", profile_email.text)
            .addSnapshotListener()
            { querySnapshot, firebaseFireStoreException ->
                var map: Map<String, Any> =
                    querySnapshot?.documents?.first()?.data as Map<String, Any>
                App.name3 = map["nickname"].toString()
                App.finish_quest_ = parseInt(map["finishQuest"].toString())
                App.total_quest_ = parseInt(map["totalQuest"].toString())
            }

        ing_quest.text = (App.total_quest_-App.finish_quest_).toString()
        ed_quest.text = App.finish_quest_.toString()
        username1.text = App.name3
        username2.text = App.name3
        username3.text = App.name3

        if (App.refreshing2 != 0) {
            App.refreshing2--
            val intent = Intent(this, ForLoading4::class.java)
            startActivity(intent)
        }

        adding_task.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.task_adding_popup, null)
            val editText = dialogView.findViewById<EditText>(R.id.tastName)
            val editText2 = dialogView.findViewById<EditText>(R.id.taskPoint)
            val button = dialogView.findViewById<Button>(R.id.ok2)
            val button2 = dialogView.findViewById<Button>(R.id.cancel2)

            button.setOnClickListener {
                Toast.makeText(this, "퀘스트를 추가했습니다", Toast.LENGTH_SHORT).show()
                App.leader_quest++
                questList.add(editText.text.toString())
                pointList.add(parseInt(editText2.text.toString()))
                auth = FirebaseAuth.getInstance()
                val user = auth.currentUser?.email
                //총 제시한 퀘스트 수 증가하는 코드
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(user.toString()).update("leaderQuest", App.leader_quest)
                //여기부터 leader가 추가하면 해당 follower의 questList로 추가되는 코드!!!!
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(profile_email.text.toString())
                    .update("questList", questList)
                //해당 퀘스트의 포인트 추가되는 코드
                FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(profile_email.text.toString())
                    .update("pointList",pointList)
                val intent5 = Intent(this, ProfileInfoActivity::class.java)
                startActivity(intent5)
            }

            button2.setOnClickListener {
                val intent = Intent(this, ProfileInfoActivity::class.java)
                startActivity(intent)
            }

            builder.setView(dialogView).show()
        }

        btn_back.setOnClickListener {
            App.refreshing2++
            val intent5 = Intent(this, LeaderActivity::class.java)
            startActivity(intent5)
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            adding_task.id -> {
                val dlg = MyDialog2(this)
                dlg.setOnOKClickedListener { content ->
                    Toast.makeText(this, "과제를 추가했습니다", Toast.LENGTH_SHORT).show()
                }
                dlg.start("")
            }
        }
    }

}