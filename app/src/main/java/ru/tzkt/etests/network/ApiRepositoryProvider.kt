package ru.tzkt.etests.network

import android.content.Context

/**
 * Created by user on 11.04.2018.
 */
object ApiRepositoryProvider {
    fun provideApiRepository(context: Context): ApiRepository {
        return ApiRepository(ApiService.create(context))
    }
}