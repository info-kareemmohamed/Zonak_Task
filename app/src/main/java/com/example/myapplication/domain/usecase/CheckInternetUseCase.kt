package com.example.myapplication.domain.usecase

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject


/**
 * Monitors network connectivity status using Kotlin Flows.
 *
 * Provides a Flow<Boolean> that emits `true` when the network is available and `false` when it's lost or unavailable.
 * Handles cleanup by unregistering the network callback when the Flow is closed.
 */

class CheckInternetUseCase @Inject constructor() {

    operator fun invoke(context: Context) = callbackFlow {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
            override fun onUnavailable() {
                trySend(false)
            }
            override fun onLosing(network: Network, maxMsToLive: Int) {
                trySend(false)
            }

        }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose {
            // Cleanup when the flow is closed
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.distinctUntilChanged()
}
