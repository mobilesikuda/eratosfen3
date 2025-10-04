package ru.sikuda.mobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform