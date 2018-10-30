package com.example.trebb.currencyexchange.model

import java.math.BigDecimal
import java.util.*

class Rates(
    val no: String,
    val effectiveDate: Date,
    val bid: BigDecimal,
    val ask: BigDecimal
)
