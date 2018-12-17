package com.example.trebb.currencyexchange

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_calculator.*
import java.math.RoundingMode
import java.text.DecimalFormat

class CalculatorActivity : AppCompatActivity() {

    private var currency: String? = null
    private var ask: String? = null
    private var bid: String? = null
    private var isChangeCurrencyClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        currency = intent.getStringExtra("CurrentCurrency")
        ask = intent.getStringExtra("Ask")
        bid = intent.getStringExtra("Bid")
        countryFlagSelection()

        changeCurrency.setOnClickListener {
            isChangeCurrencyClicked = !isChangeCurrencyClicked
            textView3.text = ""
            editText2.text.clear()
        }

        btn_search2.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val df = DecimalFormat("#.###")
                df.roundingMode = RoundingMode.CEILING
                if (!p0.isNullOrEmpty() && !isChangeCurrencyClicked) {
                    textView3.text = df.format(((p0.toString().toDouble() * ask.toString().toDouble()))) + " PLN"
                }
                if (p0.isNullOrEmpty() && !isChangeCurrencyClicked) {
                    textView3.text = "0.0 PLN"
                }
                if (!p0.isNullOrEmpty() && isChangeCurrencyClicked) {
                    textView3.text = df.format(((p0.toString().toDouble() / bid.toString().toDouble()))) + " " +
                            currency
                }
                if (p0.isNullOrEmpty() && isChangeCurrencyClicked) {
                    textView3.text = "0.0 " + currency
                }
            }
        })
    }


    private fun countryFlagSelection() {
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(R.id.fragment2) as CurrencyInfo
        fragment.changeFlag(intent.getStringExtra("CurrentFlag"))
    }


}
