package zuper.dev.android.dashboard.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zuper.dev.android.dashboard.R
import zuper.dev.android.dashboard.ui.theme.LightThemePreview
import zuper.dev.android.dashboard.ui.theme.Shape


@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {

    ElevatedCard(
        modifier = modifier,
        shape = Shape.cardSmall,
        colors = CardDefaults.cardColors(
            containerColor = DashboardCardDefaults.cardContainerColor(),
            contentColor = DashboardCardDefaults.cardContentColor()
        ),
        content = content
    )
}


object DashboardCardDefaults {

    @Composable
    fun cardContainerColor() = MaterialTheme.colorScheme.surfaceVariant

    @Composable
    fun cardContentColor() = MaterialTheme.colorScheme.onSurface

    @Composable
    fun cardSubTextColor() = MaterialTheme.colorScheme.surfaceVariant

    @Composable
    fun filesContentColor() = MaterialTheme.colorScheme.onBackground

}

