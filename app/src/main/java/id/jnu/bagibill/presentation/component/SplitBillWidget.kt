package id.jnu.bagibill.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.jnu.bagibill.R
import id.jnu.bagibill.ui.theme.BagiBillColor
import id.jnu.bagibill.ui.theme.BagiBillTheme

@Composable
fun SplitBillWidget(
    amount: String,
    peopleAmount: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(14.dp,RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        BagiBillColor.GreenGrass,
                        BagiBillColor.GreenDark
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.bill_per_person),
                style = MaterialTheme.typography.titleSmall,
                color = BagiBillColor.GreenLight,
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = stringResource(R.string.rp_currency),
                    style = MaterialTheme.typography.labelMedium,
                    color = BagiBillColor.GreenLight,
                    fontSize = 16.sp
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = amount,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    fontSize = 26.sp
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = BagiBillColor.GreenDarker,
                        shape = RoundedCornerShape(40.dp)
                    )
            ) {
                Text(
                    text = "Dibagi $peopleAmount Orang",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    letterSpacing = 0.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 18.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplitBillWidgetPreview() {
    BagiBillTheme {
        SplitBillWidget("125.000", "10")
    }
}