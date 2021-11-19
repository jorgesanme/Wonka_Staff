package com.example.wonka_staff.DI

import android.util.Log
import com.example.wonka_staff.repository.WillyWonkaAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.math.sin

object NetworkDIModule: DIBaseModule("NetworkDIModule") {
    // (name = this::class.simpleName!!)

    override val builder: DI.Builder.() -> Unit = {

        /** okhttp*/
        bind<OkHttpClient>() with singleton {
            Log.e("Debug", "OkhttpCliente")
            OkHttpClient().newBuilder().build()
        }

        /** Moshi*/
        bind<Moshi>() with singleton {
            Log.e("Debug", "Moshi")
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }

        /** Retrofit*/
        bind<Retrofit>() with singleton {
            Log.e("Debug", "retrofit")
            Retrofit.Builder()
                .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/oompa-loompas/")
                .client(instance())
                .addConverterFactory(MoshiConverterFactory.create(instance()))
                .build()
        }
        bind<WillyWonkaAPI>() with  singleton {
            instance<Retrofit>().create(WillyWonkaAPI::class.java)
        }
    }
}