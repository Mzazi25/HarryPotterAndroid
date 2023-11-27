/*
 * Copyright 2023 HarryPotterAndroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mzazi.network.utils

import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

/**
 * Utility object to check if the network has internet connectivity by sending a ping to Google's primary DNS
 * If successful, then there is internet connectivity, otherwise not
 */
object DoesNetworkHaveInternet {

    /**
     * Checks if the network has internet connectivity by sending the ping.
     * This method has to be executed on a background thread
     *
     * @param socketFactory The socket factory to create a socket.
     * @return `true` if the network has internet connectivity, `false` otherwise.
     */
    fun execute(socketFactory: SocketFactory): Boolean {
        return try {
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
}