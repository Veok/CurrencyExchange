package com.example.trebb.currencyexchange

import com.example.trebb.currencyexchange.model.Currency
import com.example.trebb.currencyexchange.service.CurrencyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Test


class CurrencyServiceTest {
    private var disposable: Disposable? = null

    private val currencyService by lazy {
        CurrencyService.create()
    }
    private var currency: Currency? = null

    @Test
    fun testDataCorrectness() {
        disposable = currencyService.getCurrency("usd")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                currency = result
                assertTrue(currency!!.code == "USD")
            }

    }



}