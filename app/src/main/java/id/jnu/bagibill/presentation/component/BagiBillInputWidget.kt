package id.jnu.bagibill.presentation.component

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun BagiBillInputWidget(
    value: String,
    textStyle: TextStyle,
    onTextChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    decorationBox: @Composable (@Composable (() -> Unit), String) -> Unit,
) {

    BasicTextField(
        value = value,
        onValueChange = onTextChange,
        decorationBox = { innerText ->
            decorationBox(innerText, value)
        },
        textStyle = textStyle,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = visualTransformation,
    )
}