package com.example.trebb.currencyexchange.service

import com.example.trebb.currencyexchange.model.Currency
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Service used to retrieve current currency info from NBP API
 */
interface CurrencyService {

    /**
     * Function that retrieves current currency info from NBP API.
     * @param currency - a country code that is passed to get currency of specified country
     */
    @GET("exchangerates/rates/c/{currency}/")
    fun getCurrency(@Path("currency") currency: String): Observable<Currency>

    /**
     * Logic to create service by RetroFit Framework and connect to API
     */
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