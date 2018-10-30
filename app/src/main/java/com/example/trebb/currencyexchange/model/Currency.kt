package com.example.trebb.currencyexchange.model

import java.math.BigDecimal
import java.util.*

data class Currency(
    val table: String,
    val currency: String,
    val code: String

)
// object Currency{
//    data class Result(val query: Query)
//    data class Query(val searchinfo: SearchInfo)
//    data class SearchInfo(val totalhits: Int)
//}