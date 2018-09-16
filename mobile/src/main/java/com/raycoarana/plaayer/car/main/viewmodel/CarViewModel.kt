package com.raycoarana.plaayer.car.main.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.main.domain.AppSection
import com.raycoarana.plaayer.core.ResourceProvider
import javax.inject.Inject

class CarViewModel @Inject constructor(
        private val navigator: Navigator,
        private val resourceProvider: ResourceProvider,
        main: MainViewModel
) {
    val menuItems: List<SectionItemViewModel> = listOf(Pair(getDashboardTitle(), AppSection.DASHBOARD))
            .plus(main.items.map { Pair(it.title, it.appSection) })
            .map { SectionItemViewModel(navigator, it.first, it.second) }

    fun onCreate() {
        navigator.navigateToSection(getDashboardTitle(), AppSection.DASHBOARD)
    }

    private fun getDashboardTitle() = resourceProvider.getString(R.string.section_dashboard_title)
}
