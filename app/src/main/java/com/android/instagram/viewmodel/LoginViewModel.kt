package com.android.instagram.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
	
	var userLogin by mutableStateOf(String())
		private set
	
	var password by mutableStateOf(String())
		private set
	
	fun onChangeUserLogin(newValue: String) {
		userLogin = newValue
	}
	
	fun onChangePassword(newValue: String) {
		password = newValue
	}
	
	fun clear() {
		userLogin = String()
	}
}