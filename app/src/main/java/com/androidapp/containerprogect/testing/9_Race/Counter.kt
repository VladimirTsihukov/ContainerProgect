package com.androidapp.containerprogect.testing.`9_Race`

data class Counter(var count: Int = 0) {
    val pretty: String
        get() = count.toString()
            .reversed()
            .chunked(3)
            .reduce { s, acc -> "${acc}_${s}" }
}

val counter = Counter()
