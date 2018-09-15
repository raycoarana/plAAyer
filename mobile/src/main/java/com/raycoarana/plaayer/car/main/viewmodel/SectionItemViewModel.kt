package com.raycoarana.plaayer.car.main.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.main.domain.AppSection
import com.raycoarana.plaayer.core.ui.ItemViewModel

class SectionItemViewModel constructor(
        private val navigator: Navigator,
        val title: String,
        val appSection: AppSection
) : ItemViewModel(R.layout.item_app_section) {
    fun open() {
        navigator.navigateToSection(title, appSection)
    }
}
