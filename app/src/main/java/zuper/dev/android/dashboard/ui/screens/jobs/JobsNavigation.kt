package zuper.dev.android.dashboard.ui.screens.jobs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import zuper.dev.android.dashboard.ui.screens.dashboard.DashboardViewModel

const val JOBS_ROUTE = "jobs_route"

fun NavController.navToJobsScreen() = navigate(JOBS_ROUTE)

fun NavGraphBuilder.jobsScreen(dashboardViewModel: DashboardViewModel, onBackClicked : () -> Unit) {
    composable(route = JOBS_ROUTE) {

        JobsScreen(
            dashboardViewModel,
            onBackClicked
        )
    }
}