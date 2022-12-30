package com.kmm.network_sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform