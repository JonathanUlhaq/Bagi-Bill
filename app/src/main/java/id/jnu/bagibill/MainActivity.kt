package id.jnu.bagibill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.core.view.WindowCompat
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import id.jnu.bagibill.presentation.component.InputSubTotalWidget
import id.jnu.bagibill.presentation.component.InputTaxWidget
import id.jnu.bagibill.presentation.component.InputTotalPersonWidget
import id.jnu.bagibill.presentation.component.SplitBillWidget
import id.jnu.bagibill.ui.theme.BagiBillColor
import id.jnu.bagibill.ui.theme.BagiBillTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true
        setContent {
            BagiBillTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .imePadding()
                    ) {
                        BagiBill(
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BagiBill(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            "Bagi Bill",
            style = MaterialTheme.typography.labelMedium,
            fontSize = 18.sp,
            color = BagiBillColor.GreenDarker,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        Spacer(Modifier.height(14.dp))
        BagiBillComponent(
            amount = viewModel.splitAmount,
            people = viewModel.inputTotalPersonFinal,
            inputSubTotal = viewModel.inputTotalBalance,
            inputSubTotalListener = {
                viewModel.updateInputTotalBalance(it)
            },
            inputTotalPerson = viewModel.inputTotalPerson,
            inputTotalPersonListener = {
                viewModel.updateInputTotalPerson(it)
            },
            decreaseValue = {
                viewModel.decreaseTax()
            },
            increaseValue = {
                viewModel.increaseTax()
            },
            taxPb = viewModel.inputTax,
            pbChangeListener = {
                viewModel.updateInputTax(it)
            },
            onSubmit = {
                viewModel.submitBagiBill()
            },
            isButtonEnable = viewModel.isButtonEnable()
        )
    }
}

@Composable
fun BagiBillComponent(
    amount: String,
    people: String,
    inputSubTotal: String,
    inputSubTotalListener: (String) -> Unit,
    inputTotalPerson: String,
    inputTotalPersonListener: (String) -> Unit,
    decreaseValue: (String) -> Unit,
    increaseValue: (String) -> Unit,
    taxPb: String,
    pbChangeListener: (String) -> Unit,
    isButtonEnable: Boolean = true,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        SplitBillWidget(
            amount = amount,
            peopleAmount = people
        )
        Spacer(Modifier.height(12.dp))
        InputSubTotalWidget(inputSubTotal, inputSubTotalListener)
        Spacer(Modifier.height(12.dp))
        InputTotalPersonWidget(
            textValue = inputTotalPerson,
            addValue = increaseValue,
            decreaseValue = decreaseValue,
            onTextChange = inputTotalPersonListener,
        )
        Spacer(Modifier.height(12.dp))
        InputTaxWidget(
            taxPb = taxPb,
            onPb1Change = pbChangeListener,
        )
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BagiBillColor.BlueSky,
                disabledContainerColor = BagiBillColor.BlueSky.copy(0.5f)
            ),
            onClick = onSubmit,
            enabled = isButtonEnable
        ) {
            Text("Hitung!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BagiBillPreview() {
    BagiBillTheme {
        BagiBillComponent(
            amount = "Rp10.000",
            people = "12",
            inputSubTotal = "",
            inputSubTotalListener = { _ ->

            },
            inputTotalPerson = "",
            inputTotalPersonListener = { _ -> },
            decreaseValue = {},
            increaseValue = {},
            taxPb = "",
            pbChangeListener = { },
        ) {

        }
    }
}