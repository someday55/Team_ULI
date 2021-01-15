package com.example.whale

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.InternalChannelz.id
import kotlinx.android.synthetic.main.activity_addperson.*
import kotlinx.android.synthetic.main.activity_join_membership.*
import kotlinx.android.synthetic.main.activity_leader.*
import kotlinx.android.synthetic.main.activity_main.*

class addperson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addperson)
        val b = intent.getStringExtra("new2")
        btn_AddPerson.setOnClickListener{
            //val addP = editText_AddPerson.text.toString()
            saveDataforfriend()
            val intent6 = Intent(this, LeaderActivity::class.java)
            intent6.putExtra("person", editText_AddPerson.text.toString())
            intent6.putExtra("new3", b)
            startActivity(intent6)

        }
    }
    fun saveDataforfriend(){
        var leader = intent.getStringExtra("new2")
        var setfriend = editText_AddPerson.text.toString()
        var map = mutableMapOf<String, Any>()
        map["leader_email"] = leader.toString()
        map["follower_name"] = setfriend
         FirebaseFirestore.getInstance()
            .collection("leader")
            .document()
            .set(map)
    }



}