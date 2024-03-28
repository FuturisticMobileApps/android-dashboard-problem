package zuper.dev.android.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import zuper.dev.android.dashboard.ui.screens.dashboard.DASHBOARD_ROUTE
import zuper.dev.android.dashboard.ui.screens.dashboard.DashboardViewModel
import zuper.dev.android.dashboard.ui.screens.dashboard.dashboardScreen
import zuper.dev.android.dashboard.ui.screens.jobs.jobsScreen
import zuper.dev.android.dashboard.ui.screens.jobs.navToJobsScreen
import zuper.dev.android.dashboard.ui.viewModelFactory

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = DASHBOARD_ROUTE
) {

    val dashboardViewModel = viewModel<DashboardViewModel> (
        factory = viewModelFactory{
            DashboardViewModel(DashboardApp.appModule.dataRepository)
        }
    )

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        dashboardScreen(dashboardViewModel) {
            navController.navToJobsScreen()
        }

        jobsScreen(dashboardViewModel){
            navController.popBackStack()
        }

    }

}