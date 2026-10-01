package id.jnu.bagibill.presentation.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ThousandSeparatorTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        
        if (originalText.isEmpty()) {
            return TransformedText(text, OffsetMapping.Identity)
        }
        
        val formattedText = formatWithThousandSeparator(originalText)
        
        return TransformedText(
            AnnotatedString(formattedText),
            ThousandSeparatorOffsetMapping(originalText, formattedText)
        )
    }
    
    private fun formatWithThousandSeparator(text: String): String {
        if (text.isEmpty()) return ""
        
        val reversed = text.reversed()
        val chunks = reversed.chunked(3)
        return chunks.joinToString(".").reversed()
    }
    
    private class ThousandSeparatorOffsetMapping(
        private val originalText: String,
        private val formattedText: String
    ) : OffsetMapping {
        
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 0) return 0
            
            val digitsBeforeOffset = originalText.take(offset)
            val separatorsCount = (digitsBeforeOffset.length - 1) / 3
            
            return offset + separatorsCount
        }
        
        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 0) return 0
            
            val charsBeforeOffset = formattedText.take(offset)
            val separatorsCount = charsBeforeOffset.count { it == '.' }
            
            return offset - separatorsCount
        }
    }
}
