package ai.selflabs.selfshell.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun PreviewDisclaimer(modifier: Modifier = Modifier) {
    Text(
        text = "Preview only. No real earnings are generated in SELF OS Personal Intelligence (Community Edition).",
        style = MaterialTheme.typography.bodySmall,
        fontWeight = FontWeight.Medium,
        color = MaterialTheme.colorScheme.tertiary,
        modifier = modifier
    )
}

@Composable
fun MockPreviewBanner(modifier: Modifier = Modifier) {
    Text(
        text = "Mock / Preview Only — not connected to SELF OS Core.",
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}
