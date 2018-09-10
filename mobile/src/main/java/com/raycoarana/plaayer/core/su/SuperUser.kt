package com.raycoarana.plaayer.core.su

import android.util.Log
import eu.chainfire.libsuperuser.Shell
import javax.inject.Inject

class SuperUser @Inject constructor() {

    companion object {
        const val TAG = "SupperUser"
    }

    fun use(command: () -> Unit) {
        if (Shell.SU.available()) {
            command.invoke()
        }
    }

    fun run(command: String) {
        Shell.SU.run(command).forEach { Log.i(TAG, it) }
    }
}
