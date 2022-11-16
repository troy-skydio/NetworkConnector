package com.skydio.networkconnector.domain

import android.content.Context
import android.net.*
import android.net.wifi.WifiNetworkSpecifier

class ConnectToNetworkUseCase(
    private val connectivityManager: ConnectivityManager
) {

    constructor(context: Context) : this(context.getSystemService(ConnectivityManager::class.java))

    operator fun invoke(
        ssid: String,
        password: String
    ) {
        val requestCallback = RequestCallback()

        val networkSpecifier = WifiNetworkSpecifier.Builder()
            .setSsid(ssid)
            .setWpa2Passphrase(password)
            .build()

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_TRUSTED)
            .setNetworkSpecifier(networkSpecifier)
            .build()

        connectivityManager.requestNetwork(networkRequest, requestCallback, 10000)
    }

    private inner class RequestCallback : ConnectivityManager.NetworkCallback() {

        override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
            super.onBlockedStatusChanged(network, blocked)
        }

        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
        }

        override fun onLinkPropertiesChanged(
            network: Network,
            linkProperties: LinkProperties
        ) {
            super.onLinkPropertiesChanged(network, linkProperties)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
        }

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
        }

        override fun onUnavailable() {
            super.onUnavailable()
        }
    }

}
