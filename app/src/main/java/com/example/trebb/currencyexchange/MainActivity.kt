package com.example.trebb.currencyexchange

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.trebb.currencyexchange.service.CurrencyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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

        val y = 8;
    }

    private fun beginSearch() {
        var er: String
        disposable = currencyService.getDollarCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result -> txt_search_result.text = "${result.currency} result found" },
                { error -> {
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }});
    }
    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}
