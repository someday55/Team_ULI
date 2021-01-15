package com.example.whale

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_follower.*
import kotlinx.android.synthetic.main.activity_leader.*
import kotlinx.android.synthetic.main.activity_leader.followername

class FollowerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follower)
        queryObserveData()
        var todoList = arrayListOf<ThingsTodo>()
    }
    fun queryObserveData() {
        //val layout2 = findViewById<EditText>(R.id.btn_idforlogin).text.toString()
        //val weight =

        if (intent.hasExtra("new")) {
        }  else {
            //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        var a : String
        var b : String
        FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("id", intent.getStringExtra("new"))
            .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                var map: Map<String, Any> =
                    querySnapshot?.documents?.first()?.data as Map<String, Any>
                followername.text = map["nickname"].toString()
                a = map["nickname"].toString()

                FirebaseFirestore.getInstance()
                    .collection("leader")
                    .whereEqualTo("follower_name", a)
                    .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                        var map: Map<String, Any> =
                            querySnapshot?.documents?.first()?.data as Map<String, Any>
                            b = map["leader_email"].toString()


                        FirebaseFirestore.getInstance()
                            .collection("users")
                            .whereEqualTo("id", b)
                            .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                                var map: Map<String, Any> =
                                    querySnapshot?.documents?.first()?.data as Map<String, Any>
                                txt_title_ptlist.text = map["nickname"].toString()
                            }
                    }
            }



    }
}