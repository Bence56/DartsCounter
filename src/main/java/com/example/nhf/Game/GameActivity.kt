package com.example.nhf.Game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.nhf.Profile.ProfileItem
import com.example.nhf.R
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private var name1: String? = ""
    private var points1: Int = -1
    private var dartsLeft1 = 3
    private var name2: String? = ""
    private var points2: Int = -1
    private var dartsLeft2 = 3
    private var name3: String? = ""
    private var points3: Int = -1
    private var dartsLeft3 = 3
    private var name4: String? = ""
    private var points4: Int = -1
    private var dartsLeft4 = 3
    private var score: Int? = -1
    private var doubleOut: Boolean = false
    private var lastBTNDouble: Boolean = false
    var numOfPlayers: Int = 0;
    var players: ArrayList<String?> = ArrayList<String?>()


    var names: ArrayList<String> = ArrayList<String>()
    var points: ArrayList<Int> = ArrayList<Int>()
    var darts: ArrayList<Int> = ArrayList<Int>()
    var layouts: ArrayList<LinearLayout> = ArrayList<LinearLayout>()
    var elozoPont:Int =-1
    var aktindex:Int =0
    var szaznyolvanak: ArrayList<Int> = ArrayList<Int>()
    var aktDartSor:ArrayList<Int> = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //tv.text =intent.getStringExtra("player1") +intent.getStringExtra("player2")+intent.getBooleanExtra("doubleOut",true).toString()+intent.getIntExtra("score",-1).toString()
        var p1layout: LinearLayout = findViewById(R.id.p1layout)
        var p2layout: LinearLayout = findViewById(R.id.p2layout)
        var p3layout: LinearLayout = findViewById(R.id.p3layout)
        var p4layout: LinearLayout = findViewById(R.id.p4layout)
        //p1layout.setBackgroundColor(resources.getColor(R.color.green))
        //p2layout.setBackgroundColor(1)
        //p3layout.setBackgroundColor(1)
        //p4layout.setBackgroundColor(1)
        score = (intent.getIntExtra("score", -1))
        name1 = intent.getStringExtra("player1")
        points1 = score as Int
        name2 = intent.getStringExtra("player2")
        points2 = score as Int
        name3 = intent.getStringExtra("player3")
        points3 = score as Int
        name4 = intent.getStringExtra("player4")
        points4 = score as Int

        doubleOut = intent.getBooleanExtra("doubleOut", false)


        if (name1 != "No player") {
            numOfPlayers++;
            players.add(name1)
            points.add(points1)
            names.add(name1.toString())
            layouts.add(p1layout)
            darts.add(dartsLeft1)
            szaznyolvanak.add(0)
        }
        if (name2 != "No player") {
            numOfPlayers++;
            players.add(name2)
            points.add(points2)
            names.add(name2.toString())
            layouts.add(p2layout)
            darts.add(dartsLeft2)
            szaznyolvanak.add(0)
        }
        if (name3 != "No player") {
            numOfPlayers++;
            players.add(name3)
            points.add(points3)
            names.add(name3.toString())
            layouts.add(p3layout)
            darts.add(dartsLeft3)
            szaznyolvanak.add(0)
        }
        if (name4 != "No player") {
            numOfPlayers++;
            players.add(name4)
            points.add(points4)
            names.add(name4.toString())
            layouts.add(p4layout)
            darts.add(dartsLeft4)
            szaznyolvanak.add(0)
        }
        /*for (item:String? in players){
            if (item != null) {
                Log.d("players",item.toString() +" "+
                        item.length.toString())
            }
        }
        Log.d("players: ","score: "+score.toString())
        Log.d("players: ","numOfPlayers: "+numOfPlayers)
        Log.d("players: ","doubleOut: "+doubleOut.toString())*/
        elozoPont = score as Int
        setLayout()
        esemenyKezelok()
        layoutIgazit()
    }

    fun setLayout() {
        if (numOfPlayers >= 1) {
            var nametv: TextView = findViewById(R.id.p1name)
            var scoretv: TextView = findViewById(R.id.p1points)
            nametv.text = players.get(0)
            scoretv.text = points1.toString()
        } else {
            var nametv: TextView = findViewById(R.id.p1name)
            var scoretv: TextView = findViewById(R.id.p1points)
            var layout: LinearLayout = findViewById(R.id.p1layout)
            layout.setBackgroundColor(1)
            nametv.text = ""
            scoretv.text = ""
        }
        if (numOfPlayers >= 2) {
            var nametv: TextView = findViewById(R.id.p2name)
            var scoretv: TextView = findViewById(R.id.p2points)
            nametv.text = players.get(1)
            scoretv.text = points1.toString()
        } else {
            var nametv: TextView = findViewById(R.id.p2name)
            var scoretv: TextView = findViewById(R.id.p2points)
            var layout: LinearLayout = findViewById(R.id.p2layout)
            layout.setBackgroundColor(1)
            nametv.text = ""
            scoretv.text = ""
        }
        if (numOfPlayers >= 3) {
            var nametv: TextView = findViewById(R.id.p3name)
            var scoretv: TextView = findViewById(R.id.p3points)
            nametv.text = players.get(2)
            scoretv.text = points1.toString()
        } else {
            var nametv: TextView = findViewById(R.id.p3name)
            var scoretv: TextView = findViewById(R.id.p3points)
            var layout: LinearLayout = findViewById(R.id.p3layout)
            layout.setBackgroundColor(1)
            nametv.text = ""
            scoretv.text = ""
        }
        if (numOfPlayers >= 4) {
            var nametv: TextView = findViewById(R.id.p4name)
            var scoretv: TextView = findViewById(R.id.p4points)
            nametv.text = players.get(3)
            scoretv.text = points1.toString()
        } else {
            var nametv: TextView = findViewById(R.id.p4name)
            var scoretv: TextView = findViewById(R.id.p4points)
            var layout: LinearLayout = findViewById(R.id.p4layout)
            layout.setBackgroundColor(1)
            nametv.text = ""
            scoretv.text = ""
        }
    }

    fun esemenyKezelok() {
        var buttons:ArrayList<Button> = ArrayList<Button>()
        buttons.add(findViewById(R.id.S1btn))
        buttons.add(findViewById(R.id.D1btn))
        buttons.add(findViewById(R.id.T1btn))
        buttons.add(findViewById(R.id.S2btn))
        buttons.add(findViewById(R.id.D2btn))
        buttons.add(findViewById(R.id.T2btn))
        buttons.add(findViewById(R.id.S3btn))
        buttons.add(findViewById(R.id.D3btn))
        buttons.add(findViewById(R.id.T3btn))
        buttons.add(findViewById(R.id.S4btn))
        buttons.add(findViewById(R.id.D4btn))
        buttons.add(findViewById(R.id.T4btn))
        buttons.add(findViewById(R.id.S5btn))
        buttons.add(findViewById(R.id.D5btn))
        buttons.add(findViewById(R.id.T5btn))
        buttons.add(findViewById(R.id.S6btn))
        buttons.add(findViewById(R.id.D6btn))
        buttons.add(findViewById(R.id.T6btn))
        buttons.add(findViewById(R.id.S7btn))
        buttons.add(findViewById(R.id.D7btn))
        buttons.add(findViewById(R.id.T7btn))
        buttons.add(findViewById(R.id.S8btn))
        buttons.add(findViewById(R.id.D8btn))
        buttons.add(findViewById(R.id.T8btn))
        buttons.add(findViewById(R.id.S9btn))
        buttons.add(findViewById(R.id.D9btn))
        buttons.add(findViewById(R.id.T9btn))
        buttons.add(findViewById(R.id.S10btn))
        buttons.add(findViewById(R.id.D10btn))
        buttons.add(findViewById(R.id.T10btn))
        buttons.add(findViewById(R.id.S11btn))
        buttons.add(findViewById(R.id.D11btn))
        buttons.add(findViewById(R.id.T11btn))
        buttons.add(findViewById(R.id.S12btn))
        buttons.add(findViewById(R.id.D12btn))
        buttons.add(findViewById(R.id.T12btn))
        buttons.add(findViewById(R.id.S13btn))
        buttons.add(findViewById(R.id.D13btn))
        buttons.add(findViewById(R.id.T13btn))
        buttons.add(findViewById(R.id.S14btn))
        buttons.add(findViewById(R.id.D14btn))
        buttons.add(findViewById(R.id.T14btn))
        buttons.add(findViewById(R.id.S15btn))
        buttons.add(findViewById(R.id.D15btn))
        buttons.add(findViewById(R.id.T15btn))
        buttons.add(findViewById(R.id.S16btn))
        buttons.add(findViewById(R.id.D16btn))
        buttons.add(findViewById(R.id.T16btn))
        buttons.add(findViewById(R.id.S17btn))
        buttons.add(findViewById(R.id.D17btn))
        buttons.add(findViewById(R.id.T17btn))
        buttons.add(findViewById(R.id.S18btn))
        buttons.add(findViewById(R.id.D18btn))
        buttons.add(findViewById(R.id.T18btn))
        buttons.add(findViewById(R.id.S19btn))
        buttons.add(findViewById(R.id.D19btn))
        buttons.add(findViewById(R.id.T19btn))
        buttons.add(findViewById(R.id.S20btn))
        buttons.add(findViewById(R.id.D20btn))
        buttons.add(findViewById(R.id.T20btn))
        for (btn:Button in buttons){
            btn.setOnClickListener { egyEsemeny(btn) }
            if (btn.text[0] == 'S'){
                btn.setBackgroundColor(resources.getColor(R.color.white))
            }
            else if(btn.text[0] == 'D'){
                btn.setBackgroundColor(resources.getColor(R.color.green))
            }
            else if(btn.text[0] == 'T'){
                btn.setBackgroundColor(resources.getColor(R.color.red))
            }
        }
        var bull:Button = findViewById(R.id.bullbtn)
        bull.setOnClickListener { bullEsemeny() }
        var bullsEye:Button = findViewById(R.id.bullseyebtn)
        bullsEye.setOnClickListener { bullseyeEsemeny() }
        var zero_1:Button = findViewById(R.id.ZERO_1btn)
        zero_1.setOnClickListener { ZEROEsemeny() }
        var zero_2:Button = findViewById(R.id.ZERO_2btn)
        zero_2.setOnClickListener { ZEROEsemeny() }

    }
    

    fun egyEsemeny(gomb: Button) {
        var toSplit: String = gomb.text.toString()
        var multiplier: Int = 1;
        var value: Int = 0
        if (toSplit.toCharArray().get(0) == 'D') {
            multiplier = 2
            lastBTNDouble = true
        } else if (toSplit.toCharArray().get(0) == 'T') {
            multiplier = 3
            lastBTNDouble = false
        } else if (toSplit.toCharArray().get(0) == 'T') {
            multiplier = 1
            lastBTNDouble = false
        }
        toSplit = toSplit.removeRange(0, 1)
        value = toSplit.toInt()
        lep(multiplier*value)
        Log.d(
            "players",
            gomb.text.toString() + " multiplier: " + multiplier.toString() + " value: " + value.toString()
        )
    }

    fun bullEsemeny(){
        lep(25)
    }
    fun bullseyeEsemeny(){
        lep(50)
    }
    fun ZEROEsemeny(){
        lep(0)
    }

    private fun lep(curScore :Int) {
        if (points.get(aktindex)- curScore < 0){
                dartsToNull()
        }
        else if (points.get(aktindex)-curScore == 0){
            if (doubleOut == true){
                if (doubleOut && lastBTNDouble){
                    Toast.makeText(this,"The winner is "+names.get(aktindex)+"!!",Toast.LENGTH_LONG).show()
                    gyozelemesadatbazis(players.get(aktindex), aktindex)
                    points.set(aktindex,points.get(aktindex)-curScore)
                    darts.set(aktindex,darts.get(aktindex)-1)

                }
                else{
                    dartsToNull()
                }
            }
            else{
                Toast.makeText(this,"The winner is "+names.get(aktindex)+"!!",Toast.LENGTH_LONG).show()
                gyozelemesadatbazis(players.get(aktindex).toString(),aktindex)
                points.set(aktindex,points.get(aktindex)-curScore)
                darts.set(aktindex,darts.get(aktindex)-1)
            }
        }
        else{
            points.set(aktindex,points.get(aktindex)-curScore)
            darts.set(aktindex,darts.get(aktindex)-1)
            if (curScore == 60){
                aktDartSor.add(60)
            }
        }
        ellenoriz()
        layoutIgazit()
    }

    private fun gyozelemesadatbazis(get: String?, aktidx: Int) {
        intent = Intent(this,WinnerActivity::class.java)
        intent.putExtra("winnerName",get.toString())
        intent.putExtra("winner180",szaznyolvanak.get(aktidx).toString())
        startActivity(intent)
        //onStop()
    }

    private fun layoutIgazit() {
        aktNameTV.text = names.get(aktindex).toString()
        aktPontTV.text = points.get(aktindex).toString()
        aktDartsLeftTV.text = darts.get(aktindex).toString()
        for (i:Int in 0..numOfPlayers-1){
            if (i == aktindex){
                layouts.get(i).setBackgroundColor(resources.getColor(R.color.red))
            }
            else{
                layouts.get(i).setBackgroundColor(resources.getColor(R.color.grey))
            }
            if (i==0){
                p1points.text = points.get(i).toString()
            }
            else if (i==1){
                p2points.text = points.get(i).toString()
            }
            else if (i==2){
                p3points.text = points.get(i).toString()
            }
            else if (i==3){
                p4points.text = points.get(i).toString()
            }
        }
    }

    private fun dartsToNull() {
        darts.set(aktindex,0)
    }

    private fun ellenoriz(){
        /*var van180:Boolean =true
        if (aktDartSor.size ==3){
            for (i:Int in 0..aktDartSor.size){
                if (aktDartSor.get(i) != 60){
                    van180 = false
                }
            }
            if (van180){
                szaznyolvanak.set(aktindex,szaznyolvanak.get(aktindex)+1)
            }
        }*/
        if (darts.get(aktindex) == 0 && aktindex != numOfPlayers-1){
            aktindex++
        }
        else if (darts.get(aktindex) == 0 && aktindex == numOfPlayers-1){
            aktindex =0
            for (i:Int in 0..numOfPlayers-1){
                darts.set(i,3)
            }
        }
    }
}