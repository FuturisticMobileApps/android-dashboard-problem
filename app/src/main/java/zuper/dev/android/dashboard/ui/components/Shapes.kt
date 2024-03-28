package zuper.dev.android.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.ui.theme.Gray20

@Composable
fun SquareHighlighter(
    size: Dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color)
    )
}

@Composable
fun Divider(
    height: Dp = 1.dp
) {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(height)
        .background(color = MaterialTheme.colorScheme.outline))
}