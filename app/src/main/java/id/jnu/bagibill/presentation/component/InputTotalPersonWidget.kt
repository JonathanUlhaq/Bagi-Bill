package id.jnu.bagibill.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.jnu.bagibill.R
import id.jnu.bagibill.ui.theme.BagiBillColor
import id.jnu.bagibill.ui.theme.BagiBillTheme


@Composable
fun InputTotalPersonWidget(
    textValue: String,
    addValue: (String) -> Unit,
    decreaseValue: (String) -> Unit,
    onTextChange: (String) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Jumlah Orang",
            style = MaterialTheme.typography.titleSmall,
            color = BagiBillColor.BlueLight
        )
        Spacer(Modifier.height(8.dp))
        BagiBillInputWidget(
            value = textValue,
            textStyle = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            ),
            onTextChange = onTextChange
        ) { text, value ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Black.copy(0.01f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        color = Color.Black.copy(0.07f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "-",
                        style = MaterialTheme.typography.labelMedium,
                        color = BagiBillColor.GreenDarker.copy(0.5f),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable {
                                try {
                                    decreaseValue(value)
                                } catch (_: Exception) {
                                    // not do anything
                                }
                            }
                            .padding(start = 20.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    if (value.isEmpty() || value == "0") {
                        Text(
                            "0",
                            style = TextStyle(
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                                color = Color.Black.copy(0.3f),
                            )
                        )
                    } else {
                        text()
                    }
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.labelMedium,
                        color = BagiBillColor.GreenDarker,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable {
                                try {
                                    addValue(value)
                                } catch (_: Exception) {
                                    // not do anything
                                }
                            }
                            .padding(end = 20.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputTotalPersonWidgetPreview() {
//    BagiBillTheme {
//        InputTotalPersonWidget("") { }
//    }
}