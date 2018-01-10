package com.binance.api.client

import com.binance.api.client.constant.BinanceApiConstants
import com.binance.api.client.impl.{BinanceApiAsyncRestClientImpl, BinanceApiService, BinanceApiWebSocketClientImpl}
import com.binance.api.client.security.AuthenticationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

import scala.concurrent.ExecutionContext

/**
  * Instantiates a new binance api client factory.
  *
  * @param apiKey the API key
  * @param secret the Secret
  */
class BinanceApiClientFactory(val apiKey: String, val secret: String)(implicit ex: ExecutionContext) {

  final lazy val service: BinanceApiService = {
    val httpClient = new OkHttpClient.Builder
    val builder    = new Retrofit.Builder().baseUrl(BinanceApiConstants.API_BASE_URL)
    var retrofit   = builder.build

    if (apiKey.nonEmpty && secret.nonEmpty) {
      val interceptor = new AuthenticationInterceptor(apiKey, secret)

      if (!httpClient.interceptors.contains(interceptor)) {
        httpClient.addInterceptor(interceptor)
        builder.client(httpClient.build)
        retrofit = builder.build
      }
    }

    retrofit.create(classOf[BinanceApiService])
  }

  /**
    * Creates a new asynchronous/non-blocking REST client.
    */
  def newAsyncRestClient = new BinanceApiAsyncRestClientImpl(service)

  /**
    * Creates a new web socket client used for handling data streams.
    */
  def newWebSocketClient = new BinanceApiWebSocketClientImpl
}
