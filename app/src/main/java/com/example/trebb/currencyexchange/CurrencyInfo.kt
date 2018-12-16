package com.example.trebb.currencyexchange

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView



/**
 * A simple [Fragment] subclass that contains logic to change country flags
 */
class CurrencyInfo : Fragment() {

    /**
     * Function that is invoked from Main Activity.
     * @param [country] is used to set proper country flag image in [Fragment] xml
     */
    fun changeFlag(country: String) {
        val imageView = view!!.findViewById<ImageView>(R.id.imageView2)
        when (country) {
            "USD" -> imageView.setImageResource(R.drawable.usa)
            "GBP" -> imageView.setImageResource(R.drawable.british)
            "JPY" -> imageView.setImageResource(R.drawable.japan)
            "EUR" -> imageView.setImageResource(R.drawable.eu)
            "CHF" -> imageView.setImageResource(R.drawable.swiss)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_currency_info, container, false)
    }

}
