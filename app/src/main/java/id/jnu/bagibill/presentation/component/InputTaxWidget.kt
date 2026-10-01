package id.jnu.bagibill.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.jnu.bagibill.R
import id.jnu.bagibill.ui.theme.BagiBillColor
import id.jnu.bagibill.ui.theme.BagiBillTheme

@Composable
private fun InputTaxBoxWidget(
    textValue: String,
    modifier: Modifier = Modifier,
    boxLabel: String,
    fontColor: Color,
    labelColor: Color,
    boxColor: Color,
    onTextChange: (String) -> Unit,
) {

    BagiBillInputWidget(
        value = textValue,
        textStyle = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = fontColor,
            textAlign = TextAlign.Center,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both
            )
        ),
        onTextChange = onTextChange,
    ) { text, value ->
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = boxColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .wrapContentSize(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = boxLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = labelColor,
                    fontSize = 12.sp
                )
                Spacer(Modifier.height(4.dp))
                if (value.isEmpty() || value == "0") {
                    Text(
                        "-",
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = fontColor.copy(0.3f)
                        )
                    )
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        text()
                        Text("%")
                    }
                }
            }
        }
    }
}

@Composable
fun InputTaxWidget(
    taxPb: String,
    onPb1Change: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Pajak Restoran",
            style = MaterialTheme.typography.titleSmall,
            color = BagiBillColor.BlueLight
        )
        Spacer(Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputTaxBoxWidget(
                textValue = taxPb,
                boxLabel = "PB1 in %",
                labelColor = Color.White,
                fontColor = Color.White,
                boxColor = BagiBillColor.GreenDarker,
                onTextChange = onPb1Change
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputTaxWidgetPreview() {
    var textPb by remember {
        mutableStateOf("0")
    }

    var textTotal by remember {
        mutableStateOf("0")
    }

    BagiBillTheme {
        InputTaxWidget(
            taxPb = textPb,
            onPb1Change = {

            }
        )
    }
}