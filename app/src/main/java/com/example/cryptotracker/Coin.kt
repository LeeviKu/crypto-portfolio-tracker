package com.example.cryptotracker

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
/**
 * Data classes for json parsing
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Coin(
    val `data`: Data?,
    val status: String
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Data(
    val coins: List<CoinX>?,
    val stats: Stats?
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinX(
    val `24hVolume`: String?,
    val btcPrice: String?,
    val change: String?,
    val coinrankingUrl: String?,
    val color: String?,
    val iconUrl: String?,
    val listedAt: Int?,
    val lowVolume: Boolean?,
    val marketCap: String?,
    val name: String?,
    val price: String?,
    val rank: Int?,
    val sparkline: List<String>?,
    val symbol: String?,
    val tier: Int?,
    val uuid: String?
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Stats(
    val total: Int?,
    val total24hVolume: String?,
    val totalExchanges: Int?,
    val totalMarketCap: String?,
    val totalMarkets: Int?
)