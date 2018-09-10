package com.raycoarana.plaayer.smartphone.installer

import com.raycoarana.plaayer.smartphone.installer.domain.Phenotype
import com.raycoarana.plaayer.core.ui.FeedbackFactory
import javax.inject.Inject

class InstallerViewModel @Inject constructor(
        private val phenotype: Phenotype,
        private val feedbackFactory: FeedbackFactory
) {
    fun install() {
        phenotype.install()

        feedbackFactory.toast("Done, reboot phone")
    }

    fun uninstall() {
        phenotype.restore()
    }
}
