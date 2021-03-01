package com.example.nhf.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.nhf.MainActivity
import com.example.nhf.R

class WinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)
        var tvname:TextView = findViewById(R.id.Winner180tv)
        var name:String = intent.getStringExtra("winnerName").toString()
        Log.d("players",name)
        tvname.text =name
        var tv180:TextView = findViewById(R.id.Winner180tv)
        var val180:String = intent.getStringExtra("winner180").toString()
        Log.d("players",val180)
        tv180.text = val180
        var btn:Button = findViewById(R.id.Winnerbtn)
        btn.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}