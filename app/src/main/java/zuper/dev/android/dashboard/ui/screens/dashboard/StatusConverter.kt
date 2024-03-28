package zuper.dev.android.dashboard.ui.screens.dashboard

import androidx.compose.ui.graphics.Color
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.ui.components.StatusChart
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.LightBlue
import zuper.dev.android.dashboard.ui.theme.MediumBlue
import zuper.dev.android.dashboard.ui.theme.Red
import zuper.dev.android.dashboard.ui.theme.Yellow


val invoiceStatusColorMap = mapOf(
    InvoiceStatus.Draft to Yellow,
    InvoiceStatus.Pending to LightBlue,
    InvoiceStatus.Paid to Green,
    InvoiceStatus.BadDebt to Red
)

val jobStatusColorMap = mapOf(
    JobStatus.YetToStart to MediumBlue,
    JobStatus.InProgress to LightBlue,
    JobStatus.Cancelled to Yellow,
    JobStatus.Completed to Green,
    JobStatus.Incomplete to Red
)

fun <T, S> convertToStatusCharts(
    objects: List<T>,
    statusColorMap: Map<S, Color>,
    statusExtractor: (T) -> S
): List<StatusChart> {

    val statusCounts = objects.groupingBy(statusExtractor)
        .eachCount()

    return statusCounts.map { (status, count) ->
        StatusChart(count, statusColorMap[status] ?: Red, status.toString())
    }
}