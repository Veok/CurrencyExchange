package com.example.trebb.currencyexchange

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    private var currency: String? = null
    private var ask: String? = null
    private var bid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        currency = intent.getStringExtra("CurrentCurrency")
        ask =  intent.getStringExtra("Ask")
        bid =  intent.getStringExtra("Bid")
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(R.id.fragment2) as CurrencyInfo
        fragment.changeFlag(intent.getStringExtra("CurrentFlag"))
        btn_search2.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

    }



}
