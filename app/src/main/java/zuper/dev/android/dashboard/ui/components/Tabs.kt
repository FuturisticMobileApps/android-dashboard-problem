package zuper.dev.android.dashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.data.model.JobStatus
import java.lang.reflect.Modifier


@Composable
fun JobStatusTabs(
    statusChartList: List<StatusChart>,
    onTabSelected: (JobStatus) -> Unit
) {
    val selectedTab = remember { mutableStateOf(JobStatus.YetToStart) }
    ScrollableTabRow(
        modifier = androidx.compose.ui.Modifier.padding(top = 10.dp),
        edgePadding = 0.dp,
        divider = { Divider() },
        selectedTabIndex = statusChartList.indexOfFirst { it.status == selectedTab.value.name },
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        statusChartList.forEach { statusChart ->
            val jobStatus = JobStatus.valueOf(statusChart.status)
            Tab(
                selected = selectedTab.value == jobStatus,
                onClick = {
                    selectedTab.value = jobStatus
                    onTabSelected(jobStatus)
                },
                selectedContentColor = TabRowDefaults.tabSelectedColor(),
                unselectedContentColor = TabRowDefaults.tabUnselectedColor(),
            ) {
                Text(
                    "${jobStatus.name.getJobStatusString()} (${statusChart.count})",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = androidx.compose.ui.Modifier.padding(12.dp)
                )
            }
        }
    }
}







object TabRowDefaults {

    @Composable
    fun tabContentColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun tabSelectedColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun tabUnselectedColor() = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

    @Composable
    fun textSelectedColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun textUnselectedColor() = MaterialTheme.colorScheme.onSurfaceVariant

}

