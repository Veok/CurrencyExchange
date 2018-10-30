package com.example.trebb.currencyexchange.model

import java.math.BigDecimal
import java.util.*

data class Currency(
    val table: String,
    val currency: String,
    val code: String,
    val rates: List<Rates>
)
