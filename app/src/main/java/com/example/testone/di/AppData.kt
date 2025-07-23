package com.example.testone.di

import com.example.testone.data.ApiService
import com.example.testone.data.repository.ListRepositoryImpl
import com.example.testone.domain.repository.ListRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppData {

    @Provides
    fun provideApiService(): ApiService {
        val moshiBuilder = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")

            .client(getRetrofitClient())
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder))
            .build().create(ApiService::class.java)
    }

    @Provides
    fun provideListRepository(apiService: ApiService): ListRepository {
        return ListRepositoryImpl(apiService)
    }


    private fun getRetrofitClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also { client ->
                /*if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }*/
            }.build()
    }

}