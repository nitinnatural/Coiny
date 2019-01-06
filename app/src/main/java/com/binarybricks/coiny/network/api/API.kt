package com.binarybricks.coiny.network.api

import com.binarybricks.coiny.network.CRYPTO_COMPARE_API_KEY
import com.binarybricks.coiny.network.models.CryptoCompareHistoricalResponse
import com.binarybricks.coiny.network.models.CryptoPanicNews
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    //TODO need to pass api key and app name in all the request, break this file into 2 if possible and name it differently.

    @GET("all/coinlist")
    fun getCoinList(): Single<JsonObject>

    @GET("pricemultifull")
    fun getPricesFull(@Query("fsyms") fromSymbol: String, @Query("tsyms") toSymbol: String): Single<JsonObject>

    @GET("{period}")
    fun getCryptoHistoricalData(
            @Path("period") period: String,
            @Query("fsym") fromCurrencySymbol: String?,
            @Query("tsym") toCurrencySymbol: String?,
            @Query("limit") limit: Int,
            @Query("aggregate") aggregate: Int
    ): Single<CryptoCompareHistoricalResponse>

    @GET("price")
    fun getPrice(@Query("fsym") fromSymbol: String, @Query("tsyms") toSymbol: String, @Query("e") exchange: String): Single<JsonObject>

    @GET("all/exchanges")
    fun getExchangeList(): Single<JsonObject>

    @GET("exchanges/general")
    fun getExchangesInfo(@Query("api_key") apiKey: String = CRYPTO_COMPARE_API_KEY): Single<JsonObject>

    @GET("https://cryptopanic.com/api/posts/?auth_token=cd529bae09d5c505248fe05618da96ffb35ecffc")
    fun getCryptoNewsForCurrency(
            @Query("currencies") coinSymbol: String,
            @Query("filter") filter: String,
            @Query("public") public: Boolean
    ): Single<CryptoPanicNews>

    @GET("pricehistorical")
    fun getCoinPriceForTimeStamp(
            @Query("fsym") fromSymbol: String,
            @Query("tsyms") toSymbol: String,
            @Query("e") exchange: String,
            @Query("ts") timeStamp: String
    ): Single<JsonObject>


    @GET("top/totalvolfull")
    fun getTopCoinsByTotalVolume24hours(
            @Query("tsym") toSymbol: String,
            @Query("limit") limit: Int
    ): Single<JsonObject>

    @GET("top/mktcapfull")
    fun getTopCoinsByTotalVolume(
            @Query("tsym") toSymbol: String,
            @Query("limit") limit: Int
    ): Single<JsonObject>

    @GET("top/volumes")
    fun getTopPairsVolume(
            @Query("tsym") toSymbol: String,
            @Query("limit") limit: Int
    ): Single<JsonObject>

    @GET("v2/news/")
    fun getTopNewsArticleFromCryptocompare(
            @Query("lang") lang: String,
            @Query("sortOrder") sortOrder: String
    ): Single<JsonObject>


    @GET("https://api.coingecko.com/api/v3/coins/{coinName}/tickers")
    fun getCoinTicker(
            @Path("coinName") coinName: String
    ): Single<JsonObject>
}