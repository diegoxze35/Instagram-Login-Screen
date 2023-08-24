package com.android.instagram.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
	primary = Primary,
	onPrimary = Color.White,
	background = BackgroundDark,
	onBackground = OnBackgroundDark,
	primaryVariant = PrimaryVariantDark,
	secondaryVariant = SecondaryVariantDark,
	surface = DarkSurface,
)

private val LightColorPalette = lightColors(
	primary = Primary,
	onPrimary = Color.White,
	background = Color.White,
	onBackground = OnBackground,
	primaryVariant = PrimaryVariantColor,
	secondaryVariant = SecondaryVariantColor,
	surface = WhiteSurface,
	
	/* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun InstagramTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	val uiController = rememberSystemUiController()
	val colors = if (darkTheme) {
		uiController.setStatusBarColor(DarkSurface)
		DarkColorPalette
	} else {
		uiController.setStatusBarColor(WhiteSurface)
		LightColorPalette
	}
	
	MaterialTheme(
		colors = colors,
		typography = InstagramTypography,
		shapes = Shapes,
		content = content
	)
}