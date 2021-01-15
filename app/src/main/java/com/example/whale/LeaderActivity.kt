package com.example.whale

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_leader.*
import kotlinx.android.synthetic.main.activity_main.*

class LeaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader)

        val layout1 = findViewById<LinearLayout>(R.id.layout1)
btn_person.setOnClickListener{

    val intent5 = Intent(this, addperson::class.java)
    intent5.putExtra("new2", intent.getStringExtra("new"))
    startActivity(intent5)

}
        layout1.setOnClickListener {
            Toast.makeText(this, "알람 ON!", Toast.LENGTH_LONG).show()
        }
        queryObserveData()
        queryObserveDataforadd()
    }

    fun queryObserveDataforadd() {
        //val layout2 = findViewById<EditText>(R.id.btn_idforlogin).text.toString()
        //val weight =
        if (intent.hasExtra("new")) {
            FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("id", intent.getStringExtra("new"))
                .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                    var map: Map<String, Any> =
                        querySnapshot?.documents?.first()?.data as Map<String, Any>
                    first_profile_name.text = map["friend"].toString()
                } }
        else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }

        if (intent.hasExtra("person")) {
            FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("nickname", intent.getStringExtra("person"))
                .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                    var map: Map<String, Any> =
                        querySnapshot?.documents?.first()?.data as Map<String, Any>
                    QuestButTest.text = map["id"].toString()
                } }
        else {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
    }

    fun queryObserveData() {
        //val layout2 = findViewById<EditText>(R.id.btn_idforlogin).text.toString()
        //val weight =

        if (intent.hasExtra("new")) {

        }  else {
            //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
            FirebaseFirestore.getInstance()
                .collection("users")
                .whereEqualTo("id", intent.getStringExtra("new"))
                .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                    var map: Map<String, Any> =
                        querySnapshot?.documents?.first()?.data as Map<String, Any>
                    UserName.text = map["nickname"].toString()
                }

        if (intent.hasExtra("new")) {

        }  else {
            //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
        FirebaseFirestore.getInstance()
            .collection("users")
            .whereEqualTo("id", intent.getStringExtra("new"))
            .addSnapshotListener() { querySnapshot, firebaseFireStoreException ->
                var map: Map<String, Any> =
                    querySnapshot?.documents?.first()?.data as Map<String, Any>
                username_1.text = map["nickname"].toString()
            }
    }
}