package zuper.dev.android.dashboard.ui.screens.dashboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.data.model.InvoiceStatus
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.ui.components.BaseCard
import zuper.dev.android.dashboard.ui.components.Divider
import zuper.dev.android.dashboard.ui.components.StatusChart
import zuper.dev.android.dashboard.ui.components.StatusText
import zuper.dev.android.dashboard.ui.components.WelcomeText
import zuper.dev.android.dashboard.ui.convertTime
import zuper.dev.android.dashboard.ui.theme.Gray44
import zuper.dev.android.dashboard.ui.theme.Green
import zuper.dev.android.dashboard.ui.theme.LightBlue
import zuper.dev.android.dashboard.ui.theme.LightThemePreview
import zuper.dev.android.dashboard.ui.theme.MediumBlue
import zuper.dev.android.dashboard.ui.theme.Red
import zuper.dev.android.dashboard.ui.theme.Shape
import zuper.dev.android.dashboard.ui.theme.Yellow
import zuper.dev.android.dashboard.ui.theme.defaultCardPadding


@Composable
fun StatusChartLayout(isJob: Boolean, statusList: List<StatusChart>) {

    val isOddSize = statusList.size % 2 != 0
    val statusListLocal = if (isOddSize) statusList.dropLast(1) else statusList

    Column {

        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(2)
        ) {

            itemsIndexed(statusListLocal) { index, status ->

                val horizontalArrangement =
                    if (index % 2 == 0) Arrangement.End else Arrangement.Start

                StatusText(
                    isJob = isJob,
                    text = status.status,
                    color = status.color,
                    count = status.count,
                    horizontalArrangement = horizontalArrangement,
                )
            }

        }

        if (statusList.size % 2 != 0)
            StatusText(
                isJob = isJob,
                text = statusList.last().status,
                color = statusList.last().color,
                count = statusList.last().count,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )

    }
}


@Composable
fun StatusCard(
    isJob: Boolean,
    modifier: Modifier = Modifier,
    statusList: List<StatusChart>,
    cardPadding: Dp = defaultCardPadding,
    onCardClick: (() -> Unit?)? = null
) {

    val completedCount =
        if (isJob) statusList.find { it.status == JobStatus.Completed.name }?.count ?: 0 else
            statusList.find { it.status == InvoiceStatus.Paid.name }?.count ?: 0

    val totalJobs = statusList.sumOf { it.count }


    BaseCard(modifier = modifier.clickable { onCardClick?.invoke() }) {

        Column {

            Text(
                text = stringResource(id = if (isJob) R.string.job_status else R.string.invoice_status),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(cardPadding)
            )

            Divider()

            Column(modifier = Modifier.padding(cardPadding)) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = if (isJob) "$totalJobs Jobs" else "Total value ($$totalJobs)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray44
                    )

                    Text(
                        text = if (isJob) "$completedCount of $totalJobs Completed" else "$$completedCount collected",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray44
                    )

                }

                StatusChart(
                    modifier = Modifier.padding(vertical = 8.dp),
                    statusList = statusList.sortedByDescending { it.count })

                StatusChartLayout(isJob, statusList)
            }

        }


    }

}

@Composable
fun JobsCard(
    modifier: Modifier = Modifier,
    cardPadding: Dp = defaultCardPadding,
    jobApiModel: JobApiModel
) {

    BaseCard(modifier = modifier) {

        Column(modifier = Modifier.padding(cardPadding)) {

            Text(
                text = "#${jobApiModel.jobNumber}", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = jobApiModel.title, style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = convertTime(jobApiModel.startTime, jobApiModel.endTime),
                style = MaterialTheme.typography.bodySmall,
                color = Gray44
            )
        }
    }

}


@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    cardPadding: Dp = defaultCardPadding,
    name: String,
    date: String,
    @DrawableRes profileIcon: Int,
    profileIconSize: Dp = 36.dp,
    @DrawableRes welcomeIcon: Int = R.drawable.ic_welcome
) {

    BaseCard(
        modifier = modifier
    ) {

        Row(modifier = Modifier.padding(cardPadding)) {

            Column(modifier = Modifier.weight(1f)) {

                WelcomeText(text = "Hello, $name!", iconResId = welcomeIcon)

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = date, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }

            Image(
                painter = painterResource(id = profileIcon),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(profileIconSize)
                    .clip(Shape.cardSmall)
            )

        }

    }
}


@LightThemePreview
@Composable
fun PreviewProfileCard() {
    ProfileCard(
        modifier = Modifier
            .fillMaxWidth(),
        name = "Henry Jones",
        date = "Friday, January 6th 2022",
        profileIcon = R.drawable.ic_launcher_background
    )
}

@LightThemePreview
@Composable
fun PreviewWelcomeCard() {
    JobsCard(
        modifier = Modifier
            .fillMaxWidth(),
        jobApiModel = JobApiModel(
            jobNumber = 121,
            title = "Interior design",
            startTime = "10: 30 AM",
            endTime = "11 : 00 AM",
            status = JobStatus.Completed
        )
    )
}

@LightThemePreview
@Composable
fun PreviewJobsCard() {

    val statusListLocal = listOf(
        StatusChart(25, LightBlue, "Completed"),
        StatusChart(10, MediumBlue, "leted"),
        StatusChart(5, Yellow, "Compld"),
        StatusChart(15, Green, "Completed"),
        StatusChart(5, Red, "Completed")
    ).sortedByDescending { it.count }

    StatusCard(isJob = true, statusList = statusListLocal)

}


