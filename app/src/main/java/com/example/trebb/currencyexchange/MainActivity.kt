package com.example.trebb.currencyexchange

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.example.trebb.currencyexchange.model.Currency
import com.example.trebb.currencyexchange.service.CurrencyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private var currency: Currency? = null
    private var currentFlag: String? = null
    private var bid: String? = null
    private var ask: String? = null

    private val currencyService by lazy {
        CurrencyService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.setOnClickListener {
            val myIntent = setDataForCalculatorActivity()
            startActivity(myIntent)
        }

        currencyChangeListener()
    }


    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }


    /**
     * Function that listens a spinner selection. After change it invokes methods to change country flag image
     * and search current currency from API
     */
    private fun currencyChangeListener() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                changeCountryFlag(p0)
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                changeCountryFlag(p0)
                beginSearch()
            }
        }
    }

    /**
     * Function that takes fragment instance and sets proper country flag image
     * depending an user selection of a spinner
     */
    private fun changeCountryFlag(p0: AdapterView<*>?) {
        val fm = supportFragmentManager
        val fragment = fm.findFragmentById(R.id.fragment2) as CurrencyInfo
        fragment.changeFlag(p0?.selectedItem.toString())
        currentFlag = p0?.selectedItem.toString()
    }


    /**
     * Function that sets data that is passed to CalculatorActivity
     */
    private fun setDataForCalculatorActivity(): Intent {
        val myIntent = Intent(this, CalculatorActivity::class.java)
        myIntent.putExtra("CurrentCurrency", currency?.code)
        myIntent.putExtra("Ask", ask)
        myIntent.putExtra("Bid", bid)
        myIntent.putExtra("CurrentFlag", currentFlag)
        return myIntent
    }

    /**
     * Function that uses CurrencyService to connect with API and to get current exchange rates.
     * After successful connection sets proper text fields in main_activity.xml
     * If not, shows an error message.
     */
    fun beginSearch() {
        val mySpinner = findViewById<Spinner>(R.id.spinner)
        disposable = currencyService.getCurrency(mySpinner.selectedItem.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                this.currency = result
                ask = result.rates[0].ask.toString()
                bid = result.rates[0].bid.toString()
                sell_result.text = "${result.rates[0].ask} PLN"
                buy_result.text = "${result.rates[0].bid} PLN"
            },
                {
                    Toast.makeText(this, R.string.errorWithConnection, Toast.LENGTH_SHORT).show()
                })
    }


}
