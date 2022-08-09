package com.estarta.core.extensions

import java.util.Date

fun Date.format(
): String {
    return this.toLocaleString()
}
