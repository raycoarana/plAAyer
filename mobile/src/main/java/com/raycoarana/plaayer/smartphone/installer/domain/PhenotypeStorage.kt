package com.raycoarana.plaayer.smartphone.installer.domain

import android.annotation.SuppressLint
import com.raycoarana.plaayer.BuildConfig
import com.raycoarana.plaayer.core.database.Database
import javax.inject.Inject

class PhenotypeStorage @Inject constructor(private val database: Database) {
    companion object {

        @SuppressLint("SdCardPath")
        const val DATABASE_PATH = "/data/data/com.google.android.gms/databases/phenotype.db"

        const val PACKAGE_1 = "com.google.android.gms.car#car"
        const val PACKAGE_2 = "com.google.android.gms.car"
        const val CURRENT_APP_PACKAGE_NAME = BuildConfig.APPLICATION_ID

        const val DEFAULT_VERSION = 230

        const val QUERY_SELECT_VERSION = "SELECT version FROM Packages WHERE packageName=?"
        const val QUERY_DELETE_FLAGS = "DELETE FROM Flags WHERE packageName=? AND name=? AND version=?;"
        const val QUERY_INSERT_FLAGS = "INSERT INTO Flags (packageName, version, flagType, partitionId, user, name, stringVal, committed) VALUES (?, ?, 0, 0, \"\", ?, ?, 1);"

        const val FLAG_NAME_APP_BLACK_LIST = "app_black_list"
        const val FLAG_NAME_APP_WHITE_LIST = "app_white_list"
    }

    fun whitelistApp() {
        database.use(DATABASE_PATH) {
            val (packageName: String, version: Int) = it.detectPackageAndVersion()
            it.execute(QUERY_DELETE_FLAGS, packageName, FLAG_NAME_APP_BLACK_LIST, version)
            it.execute(QUERY_DELETE_FLAGS, packageName, FLAG_NAME_APP_WHITE_LIST, version)
            it.execute(QUERY_INSERT_FLAGS, packageName, version, FLAG_NAME_APP_WHITE_LIST, CURRENT_APP_PACKAGE_NAME)
        }
    }

    private fun Database.detectPackageAndVersion(): Pair<String, Int> {
        val version1 = getVersion(PACKAGE_1)
        if (version1 != null) {
            return Pair(PACKAGE_1, version1)
        }

        val version2 = getVersion(PACKAGE_2)
        if (version2 != null) {
            return Pair(PACKAGE_2, version2)
        }

        return Pair(PACKAGE_1, DEFAULT_VERSION)
    }

    private fun Database.getVersion(packageName: String): Int? =
        executeSelect(QUERY_SELECT_VERSION, arrayOf(packageName))
                .map { it["version"] as Int }
                .firstOrNull()
}
