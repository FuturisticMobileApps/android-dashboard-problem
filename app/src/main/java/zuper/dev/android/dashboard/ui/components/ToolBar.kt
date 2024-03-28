package zuper.dev.android.dashboard.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import zuper.dev.android.dashboard.ui.theme.LightThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    showNavIcon: Boolean = false,
    actionIcon: ImageVector = Icons.Default.ArrowBack,
    actionIconContentDescription: String = "Back to Dashboard",
    onBackClicked: () -> Unit = {},
) {

    Column {

        TopAppBar(
            title = { Text(text = title, style = MaterialTheme.typography.headlineMedium) },
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = TopBarDefaults.contentColor(),
                containerColor = TopBarDefaults.containerColor()
            ),
            navigationIcon = {
                if (showNavIcon)
                    IconButton(
                        onClick = onBackClicked
                    ) {
                        Icon(
                            imageVector = actionIcon,
                            contentDescription = actionIconContentDescription,
                            tint = TopBarDefaults.contentColor()
                        )
                    }
            }
        )

        Divider()
    }
}

object TopBarDefaults {

    @Composable
    fun containerColor() = MaterialTheme.colorScheme.surfaceVariant

    @Composable
    fun contentColor() = MaterialTheme.colorScheme.onSurface

}

@LightThemePreview
@Composable
fun PreviewJobsTopAppBar() {
    TopBar(
        title = "Jobs (60)",
        showNavIcon = true
    )
}

@LightThemePreview
@Composable
fun PreviewDashboardTopAppBar() {
    TopBar(
        title = "Dashboard"
    )
}