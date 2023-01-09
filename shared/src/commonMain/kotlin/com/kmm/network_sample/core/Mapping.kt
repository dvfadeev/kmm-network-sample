package com.kmm.network_sample.core

fun parseId(url: String): String {
    return url.split("/").dropLast(1).last()
}

fun getImageUrl(id: String): String {
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}
