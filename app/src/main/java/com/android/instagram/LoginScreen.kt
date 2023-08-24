package com.android.instagram

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.android.instagram.ui.theme.OnBackground
import com.android.instagram.ui.theme.OnBackgroundDark
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
	modifier: Modifier = Modifier,
	userLogin: String,
	onChangeUserLogin: (String) -> Unit,
	password: String,
	onChangePassword: (String) -> Unit,
	onClearLogin: () -> Unit,
	modalBottomSheetState: ModalBottomSheetState
) {
	val padding = PaddingValues(
		vertical = dimensionResource(id = R.dimen.small_dp),
		horizontal = dimensionResource(id = R.dimen.medium_dp)
	)
	Column(
		modifier = modifier.padding(padding),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		TopContent(modifier = Modifier.weight(1f), modalBottomSheetState)
		val interactionSourceLogin = remember { MutableInteractionSource() }
		val isFocusedLogin by interactionSourceLogin.collectIsFocusedAsState()
		InstagramTextField(
			value = userLogin,
			onValueChange = onChangeUserLogin,
			textResource = R.string.phone_email_or_username,
			visualTransformation = VisualTransformation.None,
			interactionSource = interactionSourceLogin,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Email,
				imeAction = ImeAction.Next
			),
			trailingIcon = if (isFocusedLogin && userLogin.isNotEmpty()) ({
				IconButton(onClick = onClearLogin) {
					Icon(imageVector = Icons.Default.Clear, contentDescription = null)
				}
			}) else null
		)
		Spacer(modifier = Modifier.height(height = dimensionResource(id = R.dimen.small_dp) / 2))
		val passwordInteractionSource = remember { MutableInteractionSource() }
		val isFocusedPassword by passwordInteractionSource.collectIsFocusedAsState()
		var visualTransformation by remember {
			mutableStateOf<VisualTransformation>(PasswordVisualTransformation())
		}
		InstagramTextField(
			value = password,
			onValueChange = onChangePassword,
			textResource = R.string.password,
			visualTransformation = visualTransformation,
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Password,
				imeAction = ImeAction.Done
			),
			interactionSource = passwordInteractionSource,
			trailingIcon = if (isFocusedPassword) ({
				val (icon, newTransformation) =
					(visualTransformation is PasswordVisualTransformation).run {
					if (this) Icons.Default.Visibility to VisualTransformation.None
					else Icons.Default.VisibilityOff to PasswordVisualTransformation()
				}
				IconButton(onClick = {
					visualTransformation = newTransformation
				}) {
					Icon(
						imageVector = icon,
						contentDescription = null
					)
				}
			}) else null
		)
		OutlinedButton(
			onClick = {},
			shape = MaterialTheme.shapes.medium,
			modifier = Modifier.fillMaxWidth(),
			colors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.primary)
		) {
			Text(text = stringResource(id = R.string.log_in), color = Color.White)
		}
		TextButton(onClick = {}) {
			Text(
				text = stringResource(id = R.string.forgot_password),
				color = MaterialTheme.colors.onSurface
			)
		}
		BottomContent(modifier = Modifier.weight(1f))
	}
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun TopContent(
	modifier: Modifier = Modifier,
	modalBottomSheetState: ModalBottomSheetState
) {
	val coroutine = rememberCoroutineScope()
	Column(
		modifier,
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.SpaceEvenly
	) {
		Text(
			text = stringResource(id = R.string.language),
			color = MaterialTheme.colors.onBackground,
			style = MaterialTheme.typography.caption,
			modifier = Modifier.clickable {
				coroutine.launch {
					modalBottomSheetState.show()
				}
			}
		)
		Spacer(modifier = Modifier)
		Image(
			painter = painterResource(id = R.drawable.instagram),
			contentDescription = stringResource(id = R.string.icon_instagram)
		)
	}
}

@Composable
private fun BottomContent(modifier: Modifier = Modifier) {
	Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
		Spacer(modifier = Modifier.weight(1f))
		OutlinedButton(
			onClick = { /*TODO*/ },
			shape = MaterialTheme.shapes.small,
			modifier = Modifier.fillMaxWidth(),
			border = BorderStroke(
				width = dimensionResource(id = R.dimen.border_dp),
				color = MaterialTheme.colors.primary
			)
		) {
			Text(
				text = stringResource(id = R.string.create_new_account),
			)
		}
		Row(verticalAlignment = Alignment.CenterVertically) {
			Icon(
				painter = painterResource(id = R.drawable.meta),
				contentDescription = stringResource(
					id = R.string.icon_meta
				)
			)
			Spacer(modifier = Modifier.width(width = dimensionResource(id = R.dimen.border_dp)))
			Text(
				text = stringResource(id = R.string.meta),
				style = MaterialTheme.typography.caption
			)
		}
	}
}

@Composable
private fun InstagramTextField(
	value: String,
	onValueChange: (String) -> Unit,
	@StringRes textResource: Int,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	visualTransformation: VisualTransformation,
	keyboardOptions: KeyboardOptions,
	trailingIcon: (@Composable () -> Unit)?
) {
	val isFocused by interactionSource.collectIsFocusedAsState()
	val isSystemInDarkTheme = isSystemInDarkTheme()
	val borderColor = MaterialTheme.colors.onBackground
	val colorFocused: Color by remember {
		derivedStateOf {
			when {
				isFocused && isSystemInDarkTheme -> OnBackground
				isFocused -> OnBackgroundDark
				else -> borderColor
			}
		}
	}
	TextField(
		value = value,
		singleLine = true,
		interactionSource = interactionSource,
		onValueChange = onValueChange,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			backgroundColor = MaterialTheme.colors.background,
			textColor = MaterialTheme.colors.onSurface,
			unfocusedBorderColor = Color.Transparent,
			focusedBorderColor = Color.Transparent,
			focusedLabelColor = MaterialTheme.colors.onBackground,
		),
		shape = MaterialTheme.shapes.medium,
		label = {
			Text(
				text = stringResource(id = textResource),
				style = MaterialTheme.typography.body1,
				color = colorFocused
			)
		},
		modifier = Modifier
			.fillMaxWidth()
			.border(
				border = BorderStroke(
					width = dimensionResource(id = R.dimen.border_dp),
					color = colorFocused
				),
				shape = MaterialTheme.shapes.medium
			),
		visualTransformation = visualTransformation,
		keyboardOptions = keyboardOptions,
		trailingIcon = trailingIcon
	)
}