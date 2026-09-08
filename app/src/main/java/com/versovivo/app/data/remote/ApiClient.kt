package com.versovivo.app.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Cliente de rede configurado conforme a Quick Info da API "A Bíblia Digital".
 * - Base URL: https://www.abibliadigital.com.br/api/
 * - Auth: API Key (Bearer Token)
 * - Format: REST
 * - Version: v1
 */
object ApiClient {
    private const val BIBLE_BASE_URL = "https://www.abibliadigital.com.br/api/"
    
    // ATENÇÃO: Insira seu Token real entre as aspas abaixo.
    // Deixe vazio ("") se não tiver uma chave para evitar erros de autenticação inválida.
    private const val API_TOKEN = "" 

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                // O User-Agent é obrigatório para evitar o erro 503 (bloqueio de segurança)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                .header("Accept", "application/json")
            
            // Adiciona o cabeçalho Bearer Token apenas se a chave for preenchida
            if (API_TOKEN.isNotBlank()) {
                requestBuilder.header("Authorization", "Bearer $API_TOKEN")
            }
            
            chain.proceed(requestBuilder.build())
        }
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BIBLE_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Cria uma instância de qualquer interface de serviço Retrofit.
     */
    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    /**
     * Mantém a compatibilidade com o VerseRepositoryImpl.
     */
    fun create(): ApiService = createService(ApiService::class.java)
}
