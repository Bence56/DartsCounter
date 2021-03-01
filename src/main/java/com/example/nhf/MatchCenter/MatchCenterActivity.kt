package com.example.nhf.MatchCenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.nhf.Game.GameActivity
import com.example.nhf.Profile.*
import com.example.nhf.R
import kotlinx.android.synthetic.main.activity_match_center.*
import kotlin.math.log

class MatchCenterActivity : AppCompatActivity() {
    private val items = mutableListOf<ProfileItem>()
    private lateinit var database: ProfileListDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_center)
        lateinit var profileList:List<ProfileItem>
        val dbThread = Thread {
            var list: List<ProfileItem> =
                ProfileListDatabase.getInstance(this).profileItemDAO().getAll()
            profileList = list
            runOnUiThread {
                var i: Int = 1

                var nevek = Array<String>(size = list.size + 1) { "" }
                nevek.set(0, "No player")
                for (profile in list) {
                    nevek.set(i, list.get(i-1).name)
                    i = i + 1
                }
                var player1 = findViewById(R.id.player1spinner) as Spinner
                var player2 = findViewById(R.id.player2spinner) as Spinner
                var player3 = findViewById(R.id.player3spinner) as Spinner
                var player4 = findViewById(R.id.player4spinner) as Spinner
                player1.adapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nevek)
                player2.adapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nevek)
                player3.adapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nevek)
                player4.adapter =
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nevek)
            }

        }
        dbThread.start()
        var btn_GameOn = findViewById(R.id.gameOnbtn) as Button
        btn_GameOn.setOnClickListener {
            var letsGo:Boolean = true
            intent = Intent(this,GameActivity::class.java)

            var spinner:Spinner = findViewById(R.id.player1spinner)
            intent.putExtra("player1",spinner.selectedItem.toString())
            var name1:String = spinner.selectedItem.toString()
            spinner = findViewById(R.id.player2spinner)
            intent.putExtra("player2",spinner.selectedItem.toString())
            var name2:String = spinner.selectedItem.toString()
            spinner = findViewById(R.id.player3spinner)
            intent.putExtra("player3",spinner.selectedItem.toString())
            var name3:String = spinner.selectedItem.toString()
            spinner = findViewById(R.id.player4spinner)
            intent.putExtra("player4",spinner.selectedItem.toString())
            var name4:String = spinner.selectedItem.toString()

            if (name1 == "No player" && name2 == "No player" && name3 =="No player" && name4 == "No player"){
                Toast.makeText(this,"No player selected!",Toast.LENGTH_LONG).show()
                letsGo = false

            }

            var sw:Switch = findViewById(R.id.doubleOutSW)
            intent.putExtra("doubleOut",sw.isChecked)

            var score:RadioGroup =findViewById(R.id.scoreRG)
            var checked:Int = (score.checkedRadioButtonId)

            if (checked == R.id.radio_101){
                intent.putExtra("score",Integer.parseInt(radio_101.text.toString()))
                Log.d("score",Integer.parseInt(radio_101.text.toString()).toString())
            }
            else if (checked == R.id.radio_201){
                intent.putExtra("score",Integer.parseInt(radio_201.text.toString()))
                Log.d("score",Integer.parseInt(radio_201.text.toString()).toString())
            }
            else if (checked == R.id.radio_301){
                intent.putExtra("score",Integer.parseInt(radio_301.text.toString()))
                Log.d("score",Integer.parseInt(radio_301.text.toString()).toString())
            }
            else if (checked == R.id.radio_501){
                intent.putExtra("score",Integer.parseInt(radio_501.text.toString()))
                Log.d("score",Integer.parseInt(radio_501.text.toString()).toString())
            }
            else{
                letsGo = false
                Toast.makeText(this,"No score selected!",Toast.LENGTH_LONG).show()
            }
            if (letsGo) {
                startActivity(intent)
            }
        }
    }
}
