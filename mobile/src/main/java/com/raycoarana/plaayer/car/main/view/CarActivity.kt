package com.raycoarana.plaayer.car.main.view

import android.os.Bundle
import com.google.android.apps.auto.sdk.CarActivity
import com.google.android.apps.auto.sdk.DayNightStyle
import com.google.android.apps.auto.sdk.MenuAdapter
import com.google.android.apps.auto.sdk.MenuItem
import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.main.di.CarActivityComponent
import com.raycoarana.plaayer.car.main.viewmodel.CarViewModel
import com.raycoarana.plaayer.common.di.fromAppComponentOf
import com.raycoarana.plaayer.core.di.CarActivityModule
import com.raycoarana.plaayer.databinding.CarActivityMainBinding
import javax.inject.Inject


class CarActivity : CarActivity() {

    @Inject
    lateinit var viewModel: CarViewModel

    lateinit var carActivityComponent : CarActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        carActivityComponent = fromAppComponentOf(this).plus(CarActivityModule(this))
        carActivityComponent.inject(this)

        setContentView(R.layout.car_activity_main)
        val binding = CarActivityMainBinding.bind(findViewById(R.id.container))
        binding.viewModel = viewModel

        val menuAdapter = object : MenuAdapter() {
            override fun getMenuItem(position: Int): MenuItem = MenuItem.Builder()
                    .setTitle(viewModel.menuItems[position].title)
                    .setType(MenuItem.Type.ITEM)
                    .build()

            override fun getMenuItemCount(): Int = viewModel.menuItems.size

            override fun onMenuItemClicked(position: Int) {
                viewModel.menuItems[position].open()
            }
        }

        viewModel.onCreate()
        carUiController.menuController.setRootMenuAdapter(menuAdapter)
        carUiController.statusBarController.setDayNightStyle(DayNightStyle.AUTO_INVERSE)
    }

}
