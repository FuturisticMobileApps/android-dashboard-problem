package zuper.dev.android.dashboard.ui.screens.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import zuper.dev.android.dashboard.DashboardApp
import zuper.dev.android.dashboard.data.model.JobApiModel
import zuper.dev.android.dashboard.data.model.JobStatus
import zuper.dev.android.dashboard.ui.components.Divider
import zuper.dev.android.dashboard.ui.components.JobStatusTabs
import zuper.dev.android.dashboard.ui.components.StatusChart
import zuper.dev.android.dashboard.ui.components.TopBar
import zuper.dev.android.dashboard.ui.components.sortStatusCharts
import zuper.dev.android.dashboard.ui.screens.dashboard.DashboardViewModel
import zuper.dev.android.dashboard.ui.screens.dashboard.JobsCard
import zuper.dev.android.dashboard.ui.screens.dashboard.convertToStatusCharts
import zuper.dev.android.dashboard.ui.screens.dashboard.jobStatusColorMap
import zuper.dev.android.dashboard.ui.theme.Gray44

@Composable
fun JobsScreen(
    dashboardViewModel: DashboardViewModel,
    onBackClicked: () -> Unit
) {
    val dataRepository = DashboardApp.appModule.dataRepository
    var jobsList by remember { mutableStateOf(dashboardViewModel.jobList) }
    var jobStatus by remember { mutableStateOf(JobStatus.YetToStart) }
    var isRefreshing by remember { mutableStateOf(false) }

    val filteredList = jobsList.filter { it.status == jobStatus }
    val jobsChart by remember(jobsList) {
        mutableStateOf(convertToStatusCharts(jobsList, jobStatusColorMap, JobApiModel::status))
    }
    val (completedCount, totalJobs) = remember(jobsChart) {
        val completed = jobsChart.count { it.status == JobStatus.Completed.name }
        val total = jobsChart.sumOf { it.count }
        completed to total
    }

    Scaffold(
        topBar = { TopBar(title = "Jobs ($totalJobs)", showNavIcon = true) { onBackClicked() } }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 20.dp)) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "$totalJobs Jobs",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray44
                    )

                    Text(
                        text = "$completedCount of $totalJobs Completed",
                        style = MaterialTheme.typography.bodySmall,
                        color = Gray44
                    )

                }

                StatusChart(
                    modifier = Modifier.padding(top = 10.dp),
                    statusList = jobsChart.sortedByDescending { it.count }
                )

            }

            Divider()

            JobStatusTabs(sortStatusCharts(jobsChart)) { status -> jobStatus = status }

            SwipeRefresh(
                modifier = Modifier.padding(vertical = 10.dp),
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = {
                    isRefreshing = true
                    jobsList = dataRepository.getJobs()
                    isRefreshing = false
                },
            ) {
                LazyColumn {
                    items(filteredList) { job ->
                        JobsCard(
                            modifier = Modifier
                                .padding(horizontal = 14.dp)
                                .padding(top = 12.dp, bottom = 1.dp)
                                .fillMaxWidth(),
                            jobApiModel = job
                        )
                    }
                }
            }
        }
    }
}




