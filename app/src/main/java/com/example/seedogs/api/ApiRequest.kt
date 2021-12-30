package com.example.seedogs.api

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://dog.ceo/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor().build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

private fun Unit.build(): OkHttpClient {

}

private fun OkHttpClient.Builder.addInterceptor() {

}

interface ApiRequest {

    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Dog

    data class Dog (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        @Transient
        val id: Int? = null,

        @Json(name = "message")
        @ColumnInfo(name = "image_url")
        val message: String,

        @Json(name = "status")
        @ColumnInfo(name = "status")
        val status: String
    ) {
        annotation class PrimaryKey(val autoGenerate: Boolean)
        annotation class ColumnInfo(val name: String)
    }

    // https://dog.ceo/api/breed/hound/images/random
    @GET("breed/{breed}/images/random")
    suspend fun getRandomBreedImage(
        @Path("breed") breed: String
    ): Dog
}

object DogApi {
    val retrofitService: DogApiService by lazy { retrofit.create(DogApiService::class.java) }

    interface DogApiService {


            @GET("breeds/image/random")
            suspend fun getRandomDogImage(): ApiRequest.Dog

            // https://dog.ceo/api/breed/hound/images/random
            @GET("breed/{breed}/images/random")
            suspend fun getRandomBreedImage(
                @Path("breed") breed: String
            ): ApiRequest.Dog


    }
}

}