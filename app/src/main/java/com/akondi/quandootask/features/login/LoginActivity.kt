package com.akondi.quandootask.features.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.akondi.quandootask.R
import com.akondi.quandootask.core.extensions.ScreenState
import com.akondi.quandootask.features.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: LoginViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        viewModel = ViewModelProviders.of(
//            this,
//            LoginViewModel.LoginViewModelFactory(LoginInteractor())
//        )[LoginViewModel::class.java]
//
//        viewModel.loginState.observe(::getLifecycle, ::updateUI)
//
//        button.setOnClickListener { onLoginClicked() }
//    }
//
//    private fun updateUI(screenState: ScreenState<LoginState>?) {
//        when (screenState) {
//            ScreenState.Loading -> progress.visibility = View.VISIBLE
//            is ScreenState.Render -> processLoginState(screenState.renderState)
//        }
//    }
//
//    private fun processLoginState(renderState: LoginState) {
//        progress.visibility = View.GONE
//        when (renderState) {
//            LoginState.Success -> startActivity(Intent(this, MainActivity::class.java))
//            LoginState.WrongUserName -> username.error = getString(R.string.username_error)
//            LoginState.WrongPassword -> password.error = getString(R.string.password_error)
//        }
//    }
//
//    private fun onLoginClicked() {
//        viewModel.onLoginClicked(username.text.toString(), password.text.toString())
//    }
//}
