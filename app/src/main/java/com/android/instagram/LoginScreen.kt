package com.android.instagram

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.instagram.ui.theme.DisableButtonColor
import com.android.instagram.ui.theme.BackgroundTextFieldColor
import com.android.instagram.ui.theme.BackgroundTextFieldColorNight
import com.android.instagram.ui.theme.BorderColor
import com.android.instagram.ui.theme.ColorTextButton
import com.android.instagram.ui.theme.ButtonBackgroundColor
import com.android.instagram.ui.theme.Dark
import com.android.instagram.ui.theme.DisableButtonColorNight
import com.android.instagram.ui.theme.Shapes
import com.android.instagram.ui.theme.White
import java.util.*

@Composable
fun MainScreen() {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(8.dp)
	) {
		AppBar(modifier = Modifier.align(Alignment.TopCenter))
		MainContent(
			modifier = Modifier
				.align(Alignment.Center)
				.padding(horizontal = 22.dp)
		)
		BottomContent(
			Modifier
				.fillMaxWidth()
				.align(Alignment.BottomCenter)
		)
	}
}

@Composable
fun BottomContent(modifier: Modifier) {
	Column(modifier = modifier) {
		Divider()
		TextButton(
			onClick = { }, modifier = Modifier.align(Alignment.CenterHorizontally)
		) {
			val padding = Modifier.padding(horizontal = 2.dp)
			val textSize = 11.sp
			Text(
				text = stringResource(R.string.not_have_account),
				modifier = padding,
				fontSize = textSize,
				color = Color.Gray
			)
			Text(
				text = stringResource(R.string.sing_up),
				modifier = padding,
				fontSize = textSize,
				color = if (isSystemInDarkTheme()) Color.White else ColorTextButton
			)
		}
	}
}

@Composable
fun MainContent(modifier: Modifier) {
	var emailInput by rememberSaveable {
		mutableStateOf(String())
	}
	var passwordInput by rememberSaveable {
		mutableStateOf(String())
	}
	var isLoginButtonEnabled by rememberSaveable {
		mutableStateOf(false)
	}
	var showPassword by rememberSaveable {
		mutableStateOf(false)
	}
	val spaceBetweenText: Dp = 12.dp
	Column(modifier = modifier) {
		Logo(modifier = Modifier.align(Alignment.CenterHorizontally))
		Spacer(modifier = modifier.size(16.dp))
		MyTextField(emailInput, { newInput ->
			emailInput = newInput
			isLoginButtonEnabled = emailInput.isNotBlank() && passwordInput.isNotBlank()
		}, {
			Text(text = stringResource(R.string.phone_email_or_username))
		}, KeyboardOptions(keyboardType = KeyboardType.Email), null, VisualTransformation.None
		)
		Spacer(modifier = modifier.size(spaceBetweenText))
		MyTextField(passwordInput, { newPassword ->
			passwordInput = newPassword
			isLoginButtonEnabled = emailInput.isNotBlank() && passwordInput.isNotBlank()
		}, {
			Text(text = stringResource(R.string.password))
		}, KeyboardOptions(keyboardType = KeyboardType.Password), {
			IconButton(onClick = { showPassword = !showPassword }) {
				Icon(
					painter = painterResource(
						id = if (showPassword) R.drawable.not_show else R.drawable.show
					),
					contentDescription = "",
					tint = if (!showPassword) ButtonBackgroundColor else Color.Gray
				)
			}
		}, if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
		)
		Spacer(modifier = modifier.size(spaceBetweenText))
		LoginButton(
			isLoginButtonEnabled, Modifier.align(Alignment.CenterHorizontally)
		)
		val space: Dp = 8.dp
		Spacer(modifier = modifier.size(space))
		ButtonForgotPassword(Modifier.align(Alignment.CenterHorizontally))
		TextDivider()
		Spacer(modifier = modifier.size(space))
		ButtonFacebook()
	}
}

@Composable
fun ButtonFacebook(@DrawableRes icon: Int = R.drawable.face_icon) {
	Button(
		onClick = { }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
			backgroundColor = ButtonBackgroundColor, contentColor = Color.White
		), shape = Shapes.small
	) {
		Image(
			painter = painterResource(id = icon),
			contentDescription = stringResource(R.string.facebook_icon),
			modifier = Modifier.padding(end = 8.dp)
		)
		Text(stringResource(R.string.continue_with_facebook), fontWeight = FontWeight.Bold)
	}
}

@Composable
fun TextDivider() {
	Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
		val modifier = Modifier
			.weight(1f)
			.size(1.dp)
		Divider(modifier = modifier)
		Text(
			text = stringResource(R.string.or_message),
			fontSize = 12.sp,
			modifier = Modifier.padding(horizontal = 24.dp),
			textAlign = TextAlign.Center
		)
		Divider(modifier = modifier)
	}
}

@Composable
fun LoginButton(isEnabled: Boolean, modifier: Modifier) {
	Button(
		onClick = { },
		enabled = isEnabled,
		modifier = modifier.fillMaxWidth(),
		colors = ButtonDefaults.buttonColors(
			backgroundColor = ButtonBackgroundColor,
			contentColor = Color.White,
			disabledBackgroundColor = if (isSystemInDarkTheme()) DisableButtonColorNight else DisableButtonColor,
			disabledContentColor = if (isSystemInDarkTheme()) Color.Gray else Color.White
		)
	) {
		Text(text = stringResource(R.string.log_in), fontWeight = FontWeight.Bold, fontSize = 14.sp)
	}
}

@Composable
fun ButtonForgotPassword(modifier: Modifier) {
	TextButton(onClick = {}, modifier = modifier) {
		val size = 11.sp
		val padding = Modifier.padding(horizontal = 1.dp)
		Text(
			text = stringResource(R.string.forgot),
			color = Color.Gray,
			fontSize = size,
			modifier = padding
		)
		Text(
			text = stringResource(R.string.get_help),
			color = if (isSystemInDarkTheme()) Color.White else ColorTextButton,
			fontWeight = FontWeight.Bold,
			fontSize = size,
			modifier = padding
		)
	}
}

@Composable
fun MyTextField(
	text: String,
	onTextChange: (String) -> Unit,
	placeholder: @Composable (() -> Unit),
	keyboardOptions: KeyboardOptions,
	trailingIcon: @Composable (() -> Unit)?,
	visualTransformation: VisualTransformation
) {
	TextField(
		value = text,
		onValueChange = onTextChange,
		modifier = Modifier
			.fillMaxWidth()
			.border(
				width = (1).dp,
				if (!isSystemInDarkTheme()) BorderColor else BackgroundTextFieldColorNight,
				shape = RoundedCornerShape(5.dp)
			),
		singleLine = true,
		placeholder = placeholder,
		keyboardOptions = keyboardOptions,
		trailingIcon = trailingIcon,
		visualTransformation = visualTransformation,
		colors = TextFieldDefaults.textFieldColors(
			backgroundColor = if (isSystemInDarkTheme()) BackgroundTextFieldColorNight
			else BackgroundTextFieldColor,
			focusedIndicatorColor = Color.Transparent,
			unfocusedIndicatorColor = Color.Transparent
		),
	)
}

@Composable
fun Logo(@DrawableRes logo: Int = R.drawable.insta, modifier: Modifier) {
	Image(
		modifier = modifier,
		painter = painterResource(id = logo),
		contentDescription = stringResource(
			R.string.main_logo
		)
	)
}

@Composable
fun AppBar(modifier: Modifier) {
	val context = LocalContext.current
	val conf = LocalConfiguration.current
	val locale = conf.locales[0]
	var isExpanded by remember {
		mutableStateOf(false)
	}
	var selectedLanguage by rememberSaveable {
		mutableStateOf(locale.displayLanguage.lowercase())
	}
	val languages = listOf("english", "español")
	Row(modifier = modifier) {
		val weightModifier = Modifier.weight(1f)
		val color = if (isSystemInDarkTheme()) Dark else White
		Spacer(modifier = weightModifier)
		OutlinedTextField(
			value = selectedLanguage,
			trailingIcon = {
				Icon(
					painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
					contentDescription = ""
				)
			},
			colors = TextFieldDefaults.textFieldColors(
				disabledTextColor = Color.Gray,
				backgroundColor = color,
				unfocusedIndicatorColor = color
			),
			onValueChange = { newLanguage -> selectedLanguage = newLanguage },
			readOnly = true,
			enabled = false,
			modifier = Modifier
				.weight(1f)
				.clickable {
					isExpanded = true
				},
		)
		Spacer(modifier = weightModifier)
		DropdownMenu(
			expanded = isExpanded,
			onDismissRequest = { isExpanded = false },
			modifier = Modifier.fillMaxWidth()
		) {
			languages.forEach {
				DropdownMenuItem(onClick = {
					val lang = if (it == languages[0]) Locale("en") else Locale("es")
					conf.apply {
						setLocale(lang)
						setLayoutDirection(lang)
						context.resources.updateConfiguration(
							this,
							context.resources.displayMetrics
						)
					}
					(context as Activity).recreate()
					isExpanded = false
					selectedLanguage = it
				}) {
					Text(text = it)
				}
			}
		}
	}
}