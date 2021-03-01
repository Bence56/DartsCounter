package com.example.nhf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nhf.MatchCenter.MatchCenterActivity
import com.example.nhf.Profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_Profiles.setOnClickListener(){
            intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent);
        }
        btn_MatchCenter.setOnClickListener(){
            intent = Intent(this, MatchCenterActivity::class.java)
            startActivity(intent)
        }
    }
}