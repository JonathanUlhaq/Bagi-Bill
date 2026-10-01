package id.jnu.bagibill

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.jnu.bagibill.domain.usecase.PayPerPersonUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val payPerPersonUseCase: PayPerPersonUseCase,
) : ViewModel() {
    var inputTotalBalance by mutableStateOf("")
        private set

    var inputTotalPerson by mutableStateOf("")
        private set

    var inputTax by mutableStateOf("")
        private set

    var splitAmount by mutableStateOf("")
        private set

    var inputTotalPersonFinal by mutableStateOf("")
        private set

    fun updateInputTotalBalance(text: String) {
        inputTotalBalance = text.filter { it.isDigit() }
        splitAmount = "-"
    }

    fun updateInputTotalPerson(text: String) {
        inputTotalPerson = text
        splitAmount = "-"
        inputTotalPersonFinal = "-"
    }

    fun updateInputTax(text: String) {
        inputTax = text
        splitAmount = "-"
        inputTotalPersonFinal = "-"
    }

    private fun updateSplitAmount(amount: String) {
        splitAmount = amount
        splitAmount = "-"
        inputTotalPersonFinal = "-"
    }

    fun increaseTax() {
        val inputPerson = inputTotalPerson.safeInt() + 1
        updateInputTotalPerson(inputPerson.toString())
    }

    fun decreaseTax() {
        val inputTotalPerson = inputTotalPerson.safeInt()
        if (inputTotalPerson > 0) {
            updateInputTotalPerson((inputTotalPerson - 1).toString())
        }
    }

    fun submitBagiBill() {
        val splitBill = payPerPersonUseCase.invoke(
            subTotal = inputTotalBalance.safeLong(),
            tax = inputTax.safeInt(),
            totalPerson = inputTotalPerson.safeInt()
        )
        splitAmount = splitBill
        inputTotalPersonFinal = inputTotalPerson
    }

    private fun String.safeInt(): Int = try {
        this.toInt()
    } catch (_: Exception) {
        0
    }

    fun isButtonEnable(): Boolean = inputTax.isNotEmpty() && inputTax.safeInt() != 0 &&
            inputTotalPerson.isNotEmpty() && inputTotalPerson.safeInt() != 0 &&
            inputTotalBalance.isNotEmpty() && inputTotalBalance.safeLong() != 0L

    private fun String.safeLong(): Long = try {
        this.toLong()
    } catch (_: Exception) {
        0L
    }
}