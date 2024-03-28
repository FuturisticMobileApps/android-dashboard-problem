package zuper.dev.android.dashboard.ui.screens.dashboard

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val DASHBOARD_ROUTE = "dashboard_route"

fun NavController.navToDashboard() = navigate(DASHBOARD_ROUTE)

fun NavGraphBuilder.dashboardScreen(
    dashboardViewModel: DashboardViewModel,
    onJobClicked: () -> Unit
) {

    composable(route = DASHBOARD_ROUTE) {

        DashboardScreen(dashboardViewModel, onJobClicked)
    }
}