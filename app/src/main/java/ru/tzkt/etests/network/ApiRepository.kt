package ru.tzkt.etests.network

import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import ru.tzkt.etests.models.ResultMessage

/**
 * Created by user on 11.04.2018.
 */
class ApiRepository(private val apiService: ApiService) {

    fun sendTestResults(message: ResultMessage): Observable<Result<Void>> {
        return apiService.sendTestResults(contentType = "application/json; charset=utf-8", message = message)
    }
}