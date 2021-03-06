package dk.nodes.nstack.kotlin.providers

import dk.nodes.nstack.kotlin.managers.AppOpenSettingsManager
import dk.nodes.nstack.kotlin.managers.AssetCacheManager
import dk.nodes.nstack.kotlin.managers.PrefManager

internal class ManagersModule(
    private val nStackModule: NStackModule
) {

    fun provideAppOpenSettingsManager(): AppOpenSettingsManager {
        return AppOpenSettingsManager(nStackModule.provideClientAppInfo(), nStackModule.providePreferences())
    }

    fun provideAssetCacheManager(): AssetCacheManager {
        return AssetCacheManager(nStackModule.provideContextWrapper())
    }

    fun providePrefManager(): PrefManager {
        return PrefManager(nStackModule.providePreferences())
    }
}
