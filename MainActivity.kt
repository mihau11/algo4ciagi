package com.example.algorytmynaiwne

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wzorzec = ""
        var ilznakow = 0
        val tvBFwynik = findViewById<TextView>(R.id.tvBFwynik)
        val tvKMPwynik = findViewById<TextView>(R.id.tvKMPwynik)
        val tvBMwynik = findViewById<TextView>(R.id.tvBMwynik)
        val tvRKwynik = findViewById<TextView>(R.id.tvRKwynik)

        //Działanie po kliknieciu przysisku start

        findViewById<Button>(R.id.start).setOnClickListener {
            wzorzec = findViewById<EditText>(R.id.tbWzorzec).text.toString()
            ilznakow = findViewById<EditText>(R.id.tbIlosc).text.toString().toInt()
            if(wzorzec.length<=ilznakow){
                tvBFwynik.text = Algorytmy(wzorzec = wzorzec, iloscznakow = ilznakow).bruteForce().toString()+"ms"
                tvKMPwynik.text = Algorytmy(wzorzec = wzorzec, iloscznakow = ilznakow).kmp().toString()+"ms"
                tvBMwynik.text = Algorytmy(wzorzec = wzorzec, iloscznakow = ilznakow).boyerMoore().toString()+"ms"
                tvRKwynik.text = Algorytmy(wzorzec = wzorzec, iloscznakow = ilznakow).rabinKarp().toString()+"ms"
            }
            else {
                tvBFwynik.text = "ILOSC ZNAKOW POWINNA BYC WIEKSZA NIZ \"LEN WZORCA\""
                tvKMPwynik.text = "ILOSC ZNAKOW POWINNA BYC WIEKSZA NIZ \"LEN WZORCA\""
                tvBMwynik.text = "ILOSC ZNAKOW POWINNA BYC WIEKSZA NIZ \"LEN WZORCA\""
                tvRKwynik.text = "ILOSC ZNAKOW POWINNA BYC WIEKSZA NIZ \"LEN WZORCA\""

            }
        }
    }
}