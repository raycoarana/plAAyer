package com.raycoarana.plaayer.car.main.viewmodel

import com.raycoarana.plaayer.R
import com.raycoarana.plaayer.car.core.Navigator
import com.raycoarana.plaayer.car.main.domain.AppSection
import com.raycoarana.plaayer.core.CarResourceProvider
import javax.inject.Inject

class MainViewModel @Inject constructor(
        navigator: Navigator,
        resourceProvider: CarResourceProvider
) {
    val items: List<SectionItemViewModel> = listOf(
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_tv_title), AppSection.LIVE_TV),
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_video_title), AppSection.VIDEO),
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_audio_title), AppSection.AUDIO),
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_gallery_title), AppSection.GALLERY),
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_info_title), AppSection.INFO),
            SectionItemViewModel(navigator, resourceProvider.getString(R.string.section_car_title), AppSection.CAR)
    )
}
