package zuper.dev.android.dashboard.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.LightBlue
import zuper.dev.android.dashboard.ui.theme.LightThemePreview
import zuper.dev.android.dashboard.ui.theme.MediumBlue
import zuper.dev.android.dashboard.ui.theme.Red
import zuper.dev.android.dashboard.ui.theme.Shape
import zuper.dev.android.dashboard.ui.theme.Yellow
import zuper.dev.android.dashboard.ui.theme.statusChart


@Composable
fun StatusChart(
    modifier: Modifier = Modifier,
    statusList: List<StatusChart>
) {
    Canvas(
        modifier = modifier
            .clip(Shape.cardMedium)
            .height(statusChart)
            .fillMaxWidth()
    ) {
        val totalSum = statusList.sumOf { it.count }

        var startX = 0f

        statusList.forEach { statusChart ->
            val percentage = statusChart.count.toFloat() / totalSum.toFloat()
            val rectWidth = size.width * percentage

            drawRect(
                color = statusChart.color,
                topLeft = Offset(x = startX, y = 0f),
                size = Size(width = rectWidth, height = size.height)
            )

            startX += rectWidth
        }
    }
}

data class StatusChart(
    val count: Int,
    val color: Color,
    val status : String
)

fun sortStatusCharts(statusCharts: List<StatusChart>): List<StatusChart> {
    val statusOrder = listOf(
        "YetToStart",
        "InProgress",
        "Cancelled",
        "Completed",
        "Incomplete"
    )
    return statusCharts.sortedBy { statusOrder.indexOf(it.status) }
}

fun sortInvoiceCharts(statusCharts: List<StatusChart>): List<StatusChart> {
    val statusOrder = listOf(
        "Draft",
        "Pending",
        "Paid",
        "BadDept",
    )
    return statusCharts.sortedBy { statusOrder.indexOf(it.status) }
}





@LightThemePreview
@Composable
fun PreviewStatusChart() {

    val statusListLocal = listOf(
        StatusChart(25, LightBlue,"Completed"),
        StatusChart(10, MediumBlue,"Completed"),
        StatusChart(5, Yellow,"Completed"),
        StatusChart(15, Green,"Completed"),
        StatusChart(5, Red,"Completed")
    ).sortedByDescending { it.count }

    StatusChart(statusList = statusListLocal)

}



