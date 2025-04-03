package com.reviver.dailypulsemobileappproject

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform