package com.akondi.quandootask.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.akondi.quandootask.R
import com.akondi.quandootask.core.extensions.ScreenState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            MainViewModel.MainViewModelFactory(FindItemsInteractor())
        )[MainViewModel::class.java]

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(screenState: ScreenState<MainState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MainState) {
        hideProgress()
        when (renderState) {
            is MainState.ShowItems -> setItems(renderState.items)
            is MainState.ShowMessage -> showMessage(renderState.message)
        }
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun setItems(items: List<String>) {
        list.adapter = MainAdapter(items, viewModel::onItemClicked)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
