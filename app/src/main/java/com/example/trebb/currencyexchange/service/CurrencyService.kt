package com.example.trebb.currencyexchange.service

import com.example.trebb.currencyexchange.model.Currency
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import io.reactivex.Observable


interface CurrencyService {

    @GET("exchangerates/rates/c/usd/today/")
    fun getUSDCurrency(): Observable<Currency>


    companion object {
        fun create(): CurrencyService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("http://api.nbp.pl/api/")
                .build()

            return retrofit.create(CurrencyService::class.java)
        }
    }
}