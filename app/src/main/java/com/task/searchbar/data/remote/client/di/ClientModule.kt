package com.task.searchbar.data.remote.client.di


import com.task.searchbar.data.di.DataScope
import com.task.searchbar.data.remote.ApiServices
import com.task.searchbar.data.remote.client.SettingsAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ClientModule {

    @DataScope
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(SettingsAPI.TIME_OUT, TimeUnit.MILLISECONDS)
        httpClient.writeTimeout(SettingsAPI.TIME_OUT, TimeUnit.MILLISECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(logging)

        return httpClient.build()
    }

    @DataScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SettingsAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun createAPI(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }


}
