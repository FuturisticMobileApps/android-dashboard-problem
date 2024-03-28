package zuper.dev.android.dashboard.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.data.Date
import zuper.dev.android.dashboard.ui.components.TopBar
import zuper.dev.android.dashboard.ui.components.sortInvoiceCharts
import zuper.dev.android.dashboard.ui.components.sortStatusCharts


@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel,
    onJobClicked: () -> Unit
) {

    val jobsChartList = dashboardViewModel.jobsState.collectAsState(emptyList()).value
    val invoiceChartList = dashboardViewModel.invoiceState.collectAsState(emptyList()).value

    Scaffold(
        topBar = { TopBar(title = "Dashboard") }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 14.dp)
        ) {

            ProfileCard(
                modifier = Modifier.padding(top = 12.dp),
                name = "Henry Jones",
                date = Date.getCurrentDate(),
                profileIcon = R.drawable.img_sample_avatar
            )

            StatusCard(
                isJob = true,
                modifier = Modifier.padding(top = 12.dp),
                statusList = sortStatusCharts(jobsChartList)
            ) {
                onJobClicked()
            }

            StatusCard(
                isJob = false,
                modifier = Modifier.padding(top = 12.dp),
                statusList = sortInvoiceCharts(invoiceChartList)
            )

        }
    }

}

