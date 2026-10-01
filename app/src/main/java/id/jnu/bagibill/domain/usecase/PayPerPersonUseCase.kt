package id.jnu.bagibill.domain.usecase

import id.jnu.bagibill.util.formatThousand
import javax.inject.Inject

class PayPerPersonUseCase @Inject constructor() {
    fun invoke(
        subTotal: Long,
        tax: Int,
        totalPerson: Int
    ): String {
        if (totalPerson <= 0) return 0L.formatThousand()

        val pb = subTotal * (tax / 100.0)
        val grandTotal = subTotal + pb
        val perPerson = (grandTotal / totalPerson).toLong()
        return perPerson.formatThousand()
    }
}