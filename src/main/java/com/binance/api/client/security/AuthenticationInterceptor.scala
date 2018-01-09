package com.binance.api.client.security

import java.io.IOException

import com.binance.api.client.constant.BinanceApiConstants
import okhttp3._
import okio.Buffer

/**
  * A request interceptor that injects the API Key Header into requests, and signs messages, whenever required.
  */
private object AuthenticationInterceptor {

  /**
    * Extracts the request body into a String.
    *
    * @return request body as a string
    */
  def bodyToString(request: RequestBody): String = {
    val buffer = new Buffer
    try {
      val copy = request
      if (copy != null) copy.writeTo(buffer)
      else return ""
      buffer.readUtf8
    } finally if (buffer != null) buffer.close()
  }
}

class AuthenticationInterceptor(val apiKey: String, val secret: String) extends Interceptor {
  @throws[IOException]
  override def intercept(chain: Interceptor.Chain): Response = {
    val original            = chain.request
    val newRequestBuilder   = original.newBuilder
    val isApiKeyRequired    = original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_APIKEY) != null
    val isSignatureRequired = original.header(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED) != null
    newRequestBuilder
      .removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED)
      .removeHeader(BinanceApiConstants.ENDPOINT_SECURITY_TYPE_SIGNED)
    // Endpoint requires sending a valid API-KEY
    if (isApiKeyRequired || isSignatureRequired) newRequestBuilder.addHeader(BinanceApiConstants.API_KEY_HEADER, apiKey)
    // Endpoint requires signing the payload
    if (isSignatureRequired) {
      val payload = original.url.query
      if (payload.nonEmpty) {
        val signature = HmacSHA256Signer.sign(payload, secret)
        val signedUrl = original.url.newBuilder.addQueryParameter("signature", signature).build
        newRequestBuilder.url(signedUrl)
      }
    }
    // Build new request after adding the necessary authentication information
    val newRequest = newRequestBuilder.build
    chain.proceed(newRequest)
  }
}
