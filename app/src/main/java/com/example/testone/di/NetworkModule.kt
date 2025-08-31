package com.example.testone.di

import com.example.testone.data.remote.ApiService
import com.example.testone.data.remote.RegisterService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UserUrl

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PhpUrl

    /*@Provides
    @PhpUrl
    fun providePhpUrl() = "https://kotlin.handsonandroid.com/"

    @Provides
    @UserUrl
    fun providesUserUrl() = "https://jsonplaceholder.typicode.com/"*/

    @UserUrl
    @Provides
    fun providesUserRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    @PhpUrl
    @Provides
    fun providesPhpRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl("https://kotlin.handsonandroid.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val customInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build(),
        )
    }

    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(customInterceptor)
            .build()
    }

    @Provides
    fun provideApiService(@UserUrl retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideRegisterService(@PhpUrl retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}
