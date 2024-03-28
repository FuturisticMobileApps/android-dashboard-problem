package zuper.dev.android.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.ui.theme.Gray44
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.LightThemePreview
import zuper.dev.android.dashboard.ui.theme.paddingSmall


@Composable
fun WelcomeText(modifier: Modifier = Modifier, text: String, iconResId: Int? = null) {

    Row(modifier = modifier) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(4.dp))

        iconResId?.let {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = "Welcome icon",
                modifier = Modifier
            )
        }
    }
}

@Composable
fun StatusText(
    modifier: Modifier = Modifier,
    isJob: Boolean = true,
    text: String,
    count: Int,
    color: Color,
    highlighterSize: Dp = 10.dp,
    horizontalArrangement: Arrangement.Horizontal
) {

    Row(
        modifier = modifier.padding(paddingSmall),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {

        SquareHighlighter(
            size = highlighterSize,
            color = color
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = if (isJob) "${text.getJobStatusString()} ($count)" else "${text.getInvoiceStatusString()} ($$count)",
            style = MaterialTheme.typography.bodySmall,
            color = Gray44
        )
    }
}

@Composable
fun TabRowTitle(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean
) {

    Text(
        modifier = modifier,
        text = text.getJobStatusString(),
        style = MaterialTheme.typography.headlineMedium,
        color = if (isSelected) TabRowDefaults.textSelectedColor() else TabRowDefaults.textUnselectedColor()
    )

}

fun String.getJobStatusString(): String =
    when (this) {
        "YetToStart" -> "Yet to start"
        "InProgress" -> "In-Progress"
        "Cancelled" -> "Cancelled"
        "Completed" -> "Completed"
        "Incomplete" -> "In-Complete"
        else -> "Yet to start"
    }

fun String.getInvoiceStatusString(): String =
    when (this) {
        "Draft" -> "Draft"
        "Pending" -> "Pending"
        "Paid" -> "Paid"
        "BadDebt" -> "Bad Debt"
        else -> ""
    }


@LightThemePreview
@Composable
fun PreviewStatusText() {
    Surface(modifier = Modifier.fillMaxWidth()) {
        StatusText(
            text = "Completed",
            color = Green,
            horizontalArrangement = Arrangement.Center,
            count = 123
        )
    }
}

