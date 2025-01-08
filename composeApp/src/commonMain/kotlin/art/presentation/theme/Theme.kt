import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import art.presentation.theme.BackgroundDark
import art.presentation.theme.BackgroundLight
import art.presentation.theme.ErrorContainerDark
import art.presentation.theme.ErrorContainerLight
import art.presentation.theme.ErrorDark
import art.presentation.theme.ErrorLight
import art.presentation.theme.GreenContainerDark
import art.presentation.theme.GreenContainerLight
import art.presentation.theme.GreenPrimaryDark
import art.presentation.theme.GreenPrimaryLight
import art.presentation.theme.GreenSecondaryContainerDark
import art.presentation.theme.GreenSecondaryContainerLight
import art.presentation.theme.GreenSecondaryDark
import art.presentation.theme.GreenSecondaryLight
import art.presentation.theme.GreenTertiaryContainerDark
import art.presentation.theme.GreenTertiaryContainerLight
import art.presentation.theme.GreenTertiaryDark
import art.presentation.theme.GreenTertiaryLight
import art.presentation.theme.OnBackgroundDark
import art.presentation.theme.OnBackgroundLight
import art.presentation.theme.OnErrorContainerDark
import art.presentation.theme.OnErrorContainerLight
import art.presentation.theme.OnErrorDark
import art.presentation.theme.OnErrorLight
import art.presentation.theme.OnGreenContainerDark
import art.presentation.theme.OnGreenContainerLight
import art.presentation.theme.OnGreenDark
import art.presentation.theme.OnGreenLight
import art.presentation.theme.OnGreenSecondaryContainerDark
import art.presentation.theme.OnGreenSecondaryContainerLight
import art.presentation.theme.OnGreenSecondaryDark
import art.presentation.theme.OnGreenSecondaryLight
import art.presentation.theme.OnGreenTertiaryContainerDark
import art.presentation.theme.OnGreenTertiaryContainerLight
import art.presentation.theme.OnGreenTertiaryDark
import art.presentation.theme.OnGreenTertiaryLight
import art.presentation.theme.OnSurfaceDark
import art.presentation.theme.OnSurfaceLight
import art.presentation.theme.OnSurfaceVariantDark
import art.presentation.theme.OnSurfaceVariantLight
import art.presentation.theme.OutlineDark
import art.presentation.theme.OutlineLight
import art.presentation.theme.SurfaceDark
import art.presentation.theme.SurfaceLight
import art.presentation.theme.SurfaceVariantDark
import art.presentation.theme.SurfaceVariantLight

val DarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    secondary = GreenSecondaryDark,
    tertiary = GreenTertiaryDark,
    onPrimary = OnGreenDark,
    primaryContainer = GreenContainerDark,
    onPrimaryContainer = OnGreenContainerDark,
    onSecondary = OnGreenSecondaryDark,
    secondaryContainer = GreenSecondaryContainerDark,
    onSecondaryContainer = OnGreenSecondaryContainerDark,
    onTertiary = OnGreenTertiaryDark,
    onTertiaryContainer = OnGreenTertiaryContainerDark,
    tertiaryContainer = GreenTertiaryContainerDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = OnSurfaceVariantDark,
    error = ErrorDark,
    onError = OnErrorDark,
    errorContainer = ErrorContainerDark,
    onErrorContainer = OnErrorContainerDark,
    outline = OutlineDark,
)

val LightColorScheme = lightColorScheme(
    primary = GreenPrimaryLight,
    secondary = GreenSecondaryLight,
    tertiary = GreenTertiaryLight,
    onPrimary = OnGreenLight,
    primaryContainer = GreenContainerLight,
    onPrimaryContainer = OnGreenContainerLight,
    onSecondary = OnGreenSecondaryLight,
    secondaryContainer = GreenSecondaryContainerLight,
    onSecondaryContainer = OnGreenSecondaryContainerLight,
    onTertiary = OnGreenTertiaryLight,
    onTertiaryContainer = OnGreenTertiaryContainerLight,
    tertiaryContainer = GreenTertiaryContainerLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = OnSurfaceVariantLight,
    error = ErrorLight,
    onError = OnErrorLight,
    errorContainer = ErrorContainerLight,
    onErrorContainer = OnErrorContainerLight,
    outline = OutlineLight,
)

@Composable
expect fun AppTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)