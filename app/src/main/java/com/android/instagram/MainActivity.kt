package com.android.instagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.instagram.ui.theme.InstagramTheme
import com.android.instagram.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	@OptIn(ExperimentalMaterialApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val viewModel = viewModel<LoginViewModel>()
			InstagramTheme {
				val modalState = rememberModalBottomSheetState(
					initialValue = ModalBottomSheetValue.Hidden
				)
				val (small, medium) =
					(dimensionResource(id = R.dimen.small_dp)
							to
							dimensionResource(id = R.dimen.medium_dp))
				ModalBottomSheetLayout(
					sheetState = modalState,
					sheetGesturesEnabled = true,
					sheetShape = RoundedCornerShape(
						topStart = medium,
						topEnd = medium
					),
					scrimColor = Color(red = 0, green = 0, blue = 0, alpha = 128),
					modifier = Modifier.background(
						brush = Brush.linearGradient(
							colors = listOf(
								MaterialTheme.colors.primaryVariant,
								MaterialTheme.colors.surface,
								MaterialTheme.colors.secondaryVariant
							)
						)
					),
					sheetContent = {
						val languages = LocalConfiguration.current.locales
						val coroutine = rememberCoroutineScope()
						Divider(
							modifier = Modifier
								.align(Alignment.CenterHorizontally)
								.padding(top = small)
								.width(medium * 2)
								.height(dimensionResource(id = R.dimen.border_dp) * 3)
								.clip(MaterialTheme.shapes.small),
							color = MaterialTheme.colors.onBackground
						)
						IconButton(
							onClick = {
								coroutine.launch {
									modalState.hide()
								}
							}
						) {
							Icon(
								imageVector = Icons.Default.Close,
								contentDescription = stringResource(
									id = R.string.close_selection
								)
							)
						}
						LazyColumn(
							contentPadding = PaddingValues(
								horizontal = medium,
								vertical = medium
							),
							modifier = Modifier
								.fillMaxWidth()
								.padding(horizontal = medium, vertical = small)
								.clip(MaterialTheme.shapes.medium)
								.background(color = MaterialTheme.colors.surface)
						) {
							item {
								Text(
									text = stringResource(id = R.string.select_your_language),
									style = MaterialTheme.typography.h1
								)
							}
							items(languages.size()) {
								Text(text = languages[it].displayName)
							}
						}
					}
				) {
					Surface(
						modifier = Modifier.fillMaxSize()
					) {
						LoginScreen(
							modifier = Modifier
								.fillMaxSize()
								.background(
									brush = Brush.linearGradient(
										colors = listOf(
											MaterialTheme.colors.primaryVariant,
											MaterialTheme.colors.surface,
											MaterialTheme.colors.secondaryVariant
										)
									)
								),
							userLogin = viewModel.userLogin,
							password = viewModel.password,
							onChangeUserLogin = viewModel::onChangeUserLogin,
							onChangePassword = viewModel::onChangePassword,
							onClearLogin = viewModel::clear,
							modalBottomSheetState = modalState
						)
					}
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
	InstagramTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
		) {
			LoginScreen(
				modifier = Modifier
					.fillMaxSize()
					.background(
						brush = Brush.linearGradient(
							colors = listOf(
								MaterialTheme.colors.primaryVariant,
								MaterialTheme.colors.surface,
								MaterialTheme.colors.secondaryVariant
							)
						)
					),
				userLogin = "",
				password = "",
				onChangeUserLogin = {},
				onChangePassword = {},
				onClearLogin = {},
				modalBottomSheetState = rememberModalBottomSheetState(
					initialValue = ModalBottomSheetValue.Hidden
				)
			)
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewDark() {
	InstagramTheme(darkTheme = true) {
		Surface(
			modifier = Modifier.fillMaxSize(),
		) {
			LoginScreen(
				modifier = Modifier
					.fillMaxSize()
					.background(
						brush = Brush.linearGradient(
							colors = listOf(
								MaterialTheme.colors.primaryVariant,
								MaterialTheme.colors.surface,
								MaterialTheme.colors.secondaryVariant
							)
						)
					),
				userLogin = "",
				password = "",
				onChangeUserLogin = {},
				onChangePassword = {},
				onClearLogin = {},
				modalBottomSheetState = rememberModalBottomSheetState(
					initialValue = ModalBottomSheetValue.Hidden
				)
			)
		}
	}
}