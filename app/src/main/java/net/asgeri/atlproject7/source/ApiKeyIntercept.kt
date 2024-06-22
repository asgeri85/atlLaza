package net.asgeri.atlproject7.source

import net.asgeri.atlproject7.utils.Constant.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

/*class ApiKeyIntercept  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        /*  val authorizedRequest = request.newBuilder()
              .header("Authorization", "Bearer $API_KEY")
              .build()*7



        val url = request.url.newBuilder().addQueryParameter("api_key", API_KEY).build()
        val newRequest = request.newBuilder().url(url).build()*/


        return chain.proceed(authorizedRequest)
    }
}*/