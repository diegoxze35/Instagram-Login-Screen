package com.android.instagram.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.android.instagram.R

val InstagramSans = FontFamily(
	Font(resId = R.font.instagram_sans_regular, weight = FontWeight.Normal),
	Font(resId = R.font.instagram_sans_bold, weight = FontWeight.Bold)
)

val InstagramTypography = Typography(
	body1 = TextStyle(
		fontFamily = InstagramSans,
		fontWeight = FontWeight.Normal,
		fontSize = 17.sp
	),
	button = TextStyle(
		fontFamily = InstagramSans,
		fontWeight = FontWeight.Normal,
		fontSize = 17.sp
	),
	caption = TextStyle(
		fontFamily = InstagramSans,
		fontWeight = FontWeight.Bold
	),
	h1 = TextStyle(
		fontFamily = InstagramSans,
		fontWeight = FontWeight.Bold,
		fontSize = 24.sp
	)
)