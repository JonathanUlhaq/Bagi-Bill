package id.jnu.bagibill.util

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Long.formatThousand(): String {
    val symbols = DecimalFormatSymbols(Locale("id", "ID")).apply {
        groupingSeparator = '.'
    }
    val formatter = DecimalFormat("#,###", symbols)
    return formatter.format(this)
}

fun String.unformatThousand(): Long = try {
    this.replace(".", "").toLong()
} catch (_: Exception) {
    0L
}
