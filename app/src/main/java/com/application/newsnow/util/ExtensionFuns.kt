package com.application.newsnow.util

import android.view.View
import android.widget.ProgressBar
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


suspend fun <T : Any?> Call<T>.awaitResponseToSuspend(progressBar: ProgressBar): Response<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                progressBar.visibility = View.GONE
                continuation.resume(response)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resumeWithException(t)
            }
        })

        continuation.invokeOnCancellation {
            this.cancel()
        }
    }
}