package com.example.trebb.currencyexchange

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_calculator.*
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * [CalculatorActivity] is class that controls login to calculate currency from user input
 *
 * @param currency - have information about country code
 * @param ask - have information about actual currency ask
 * @param bid - have information about actual currency bid
 *   */
class CalculatorActivity : AppCompatActivity() {

    private var currency: String? = null
    private var ask: String? = null
    private var bid: String? = null
    private var isChangeCurrencyClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        setActivityModel()
        countryFlagSelection()

        changeCurrency.setOnClickListener {
            changeAndClearCalculationType()
        }

        btn_search2.setOnClickListener {
            goBackToMainActivity()
        }

        calculateOnEditTextChange()
    }


    /**
     * Function that changes type of Calculation and cleans textViews
     */
    private fun changeAndClearCalculationType() {
        isChangeCurrencyClicked = !isChangeCurrencyClicked
        textView3.text = ""
        editText2.text.clear()
    }


    /**
     * Function that is redirecting to MainActivity
     */
    private fun goBackToMainActivity() {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }


    /**
     * Model setter for used params in class
     */
    private fun setActivityModel() {
        currency = intent.getStringExtra("CurrentCurrency")
        ask = intent.getStringExtra("Ask")
        bid = intent.getStringExtra("Bid")
    }

    /**
     * Implementatin of [TextWatcher] class that is used to change [EditText] value
     */
    private fun calculateOnEditTextChange() {
        editText2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setProperCalculation(p0)
            }
        })
    }

    /**
     * Function that calls proper methods to calculate currencies
     */
    private fun setProperCalculation(p0: CharSequence?) {
        val df = DecimalFormat("#.###")
        df.roundingMode = RoundingMode.CEILING
        initCalculationIfToPLN(p0, df)
        initCalculationIfToOtherCurrencies(p0, df)
    }

    /**
     * Function used to calculate chosen currency to PLN
     */
    @SuppressLint("SetTextI18n")
    private fun initCalculationIfToOtherCurrencies(p0: CharSequence?, df: DecimalFormat) {
        if (!p0.isNullOrEmpty() && isChangeCurrencyClicked) {
            textView3.text = df.format(((p0.toString().toDouble() / bid.toString().toDouble()))) + " " +
                    currency
        }
        if (p0.isNullOrEmpty() && isChangeCurrencyClicked) {
            textView3.text = "0.0 " + currency
        }
    }

    /**
     * Function used to calculate PLN to chosen currency
     */
    @SuppressLint("SetTextI18n")
    private fun initCalculationIfToPLN(p0: CharSequence?, df: DecimalFormat) {
        if (!p0.isNullOrEmpty() && !isChangeCurrencyClicked) {
            textView3.text = df.format(((p0.toString().toDouble() * ask.toString().toDouble()))) + " PLN"
        }
        if (p0.isNullOrEmpty() && !isChangeCurrencyClicked) {
            textView3.text = "0.0 PLN"
        }
    }

    /**
     * Function that sets proper country flag after [CalculatorActivity] initialization
     */
    private fun countryFlagSelection() {
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(R.id.fragment2) as CurrencyInfo
        fragment.changeFlag(intent.getStringExtra("CurrentFlag"))
    }


}
