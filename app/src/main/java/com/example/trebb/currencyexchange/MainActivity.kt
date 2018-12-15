package com.example.trebb.currencyexchange

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.trebb.currencyexchange.service.CurrencyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CurrencyInfo.OnFragmentInteractionListener {

    private var disposable: Disposable? = null

    private val currencyService by lazy {
        CurrencyService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.setOnClickListener {
            beginSearch()
        }
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    /**
     * Function that uses CurrencyService to connect with API and to get current exchange rates.
     * After successful connection sets proper text fields in main_activity.xml
     * If not, shows an error message.
     */
    fun beginSearch() {
        disposable = currencyService.getCurrency("usd")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                sell_result.text = "${result.rates[0].ask}"
                buy_result.text = "${result.rates[0].bid}"
            },
                {
                    Toast.makeText(this, R.string.errorWithConnection, Toast.LENGTH_SHORT).show()
                })
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}
