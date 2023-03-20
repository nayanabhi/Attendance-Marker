package com.example.scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.scrollview.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val n = arrayOf("Manpreet Singh", "MD IFTEAR AHMED", "MEDIKONDA JAHNAVI CHOWDARY", "MEET VIRANI", "MRITYUNJAY KUMAR")
        val r = intArrayOf(20103088, 20103089, 20103090, 20103091, 20103092)
        val present = findViewById<Button>(R.id.present)
        val absent = findViewById<Button>(R.id.absent)
        val name = findViewById<TextView>(R.id.student_name)
        val roll = findViewById<TextView>(R.id.student_roll)
        name.text = n[0]
        roll.text = r[0].toString()
        var i = 0
//        for (j in 0..4) {
//            clear(r[j].toString())
//        }
        present.setOnClickListener(View.OnClickListener { view ->
            if(i == 5) {
                i = 0
//                Toast.makeText(applicationContext,"Attendance is done!",Toast.LENGTH_SHORT).show()
            }else {
                val key = r[i].toString()
                database = FirebaseDatabase.getInstance().getReference("Roll/$key")
                val updates: MutableMap<String, Any> = hashMapOf(
                    "Present" to ServerValue.increment(1)
                )
                database.updateChildren(updates)
                i += 1
                if(i == 5) i = 0
                name.text = n[i]
                roll.text = r[i].toString()
            }
        })
        absent.setOnClickListener(View.OnClickListener { view ->
            if(i == 5) {
                i = 0
//                Toast.makeText(applicationContext,"Attendance is done!",Toast.LENGTH_SHORT).show()
            }else {
                val key = r[i].toString()
                database = FirebaseDatabase.getInstance().getReference("Roll/$key")
                val updates: MutableMap<String, Any> = hashMapOf(
                    "Absent" to ServerValue.increment(1)
                )
                database.updateChildren(updates)
                i += 1
                if(i == 5) i = 0
                name.text = n[i]
                roll.text = r[i].toString()
            }

        })
    }
    private fun clear(key: String) {
        database = FirebaseDatabase.getInstance().getReference("Roll/$key")
        val upd: MutableMap<String, Any> = hashMapOf(
            "Present" to 0,
            "Absent" to 0
        )
        database.updateChildren(upd)
    }
}

