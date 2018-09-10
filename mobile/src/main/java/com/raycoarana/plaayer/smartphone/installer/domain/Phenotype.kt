package com.raycoarana.plaayer.smartphone.installer.domain

import com.raycoarana.plaayer.core.su.SuperUser
import javax.inject.Inject

class Phenotype @Inject constructor(
        private val superUser: SuperUser,
        private val storage: PhenotypeStorage
) {
    companion object {
        const val SERVICE_PHENOTYPE_CONFIGURATOR = "com.google.android.gms/.phenotype.service.sync.PhenotypeConfigurator"
        const val SERVICE_PHENOTYPE = "com.google.android.gms/.phenotype.service.PhenotypeService"
        const val SERVICE_PHENOTYPE_CHIMERA = "com.google.android.gms/.phenotype.service.PhenotypeChimeraService"
        const val SERVICE_PERSISTENT_DIRECT_BOOT = "com.google.android.gms/.chimera.PersistentDirectBootAwareApiService"

        const val ENABLE_WRITE_PERMISSIONS = "chmod 777 "
        const val DISABLE_WRITE_PERMISSIONS = "chmod 660 "

        const val DATABASE_PATH = PhenotypeStorage.DATABASE_PATH + "*"
        const val DATABASE_WAL_PATH = PhenotypeStorage.DATABASE_PATH + "-wal"
        const val DATABASE_SHM_PATH = PhenotypeStorage.DATABASE_PATH + "-shm"
        const val DATABASE_JOURNAL_PATH = PhenotypeStorage.DATABASE_PATH + "-journal"

        const val COMMAND_PM_ENABLE = "pm enable --user 0 "
        const val COMMAND_PM_DISABLE = "pm disable --user 0 "
        const val COMMAND_TOUCH = "touch "
        const val COMMAND_REMOVE = "rm "
    }

    fun install() {
        superUser.use {
            superUser.run(COMMAND_PM_DISABLE + SERVICE_PHENOTYPE_CONFIGURATOR)
            superUser.run(COMMAND_PM_DISABLE + SERVICE_PHENOTYPE)
            superUser.run(COMMAND_PM_DISABLE + SERVICE_PHENOTYPE_CHIMERA)
            superUser.run(COMMAND_PM_DISABLE + SERVICE_PERSISTENT_DIRECT_BOOT)

            superUser.run(COMMAND_TOUCH + DATABASE_WAL_PATH)
            superUser.run(COMMAND_TOUCH + DATABASE_SHM_PATH)
            superUser.run(COMMAND_TOUCH + DATABASE_JOURNAL_PATH)

            superUser.run(ENABLE_WRITE_PERMISSIONS + DATABASE_PATH)
            storage.whitelistApp()
            superUser.run(DISABLE_WRITE_PERMISSIONS + DATABASE_PATH)

            superUser.run(COMMAND_REMOVE + DATABASE_WAL_PATH)
            superUser.run(COMMAND_REMOVE + DATABASE_SHM_PATH)
            superUser.run(COMMAND_REMOVE + DATABASE_JOURNAL_PATH)
        }
    }

    fun restore() {
        superUser.use {
            superUser.run(COMMAND_PM_ENABLE + SERVICE_PHENOTYPE_CONFIGURATOR)
            superUser.run(COMMAND_PM_ENABLE + SERVICE_PHENOTYPE)
            superUser.run(COMMAND_PM_ENABLE + SERVICE_PHENOTYPE_CHIMERA)
            superUser.run(COMMAND_PM_ENABLE + SERVICE_PERSISTENT_DIRECT_BOOT)
        }
    }
}
