package dk.nodes.nstack.kotlin.managers

import dk.nodes.nstack.kotlin.models.ClientAppInfo
import dk.nodes.nstack.kotlin.util.Constants
import dk.nodes.nstack.kotlin.util.Preferences
import dk.nodes.nstack.kotlin.util.extensions.formatted
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import java.util.Date
import org.junit.Test

internal class AppOpenSettingsManagerTest {

    private val preferencesMock = mockk<Preferences>(relaxed = true)
    private val clientAppInfoMock = mockk<ClientAppInfo>()

    private val version = "version"

    private val appOpenSettingsManager = AppOpenSettingsManager(clientAppInfoMock, preferencesMock)

    init {
        every { clientAppInfoMock.versionName } returns version
    }

    @Test
    fun testSetAppUpdateSavesDate() {
        val dateSlot = slot<String>()
        every {
            preferencesMock.saveString(Constants.spk_nstack_last_updated, capture(dateSlot))
        } answers { }

        appOpenSettingsManager.setUpdateDate()

        verify { preferencesMock.saveString(Constants.spk_nstack_last_updated, any()) }
        assert(dateSlot.captured == Date().formatted)
    }

    @Test
    fun testSetAppUpdateSavesCurrentVersionAsOld() {
        appOpenSettingsManager.setUpdateDate()

        verify { preferencesMock.saveString(Constants.spk_nstack_old_version, version) }
    }

    @Test
    fun testLoadingAppOpenSettings() {
        val uuid = "uuid"
        val oldVersion = "oldVersion"
        val updateDate = Date()

        every { preferencesMock.loadString(Constants.spk_nstack_guid) } returns uuid
        every { preferencesMock.loadString(Constants.spk_nstack_old_version) } returns oldVersion
        every { preferencesMock.loadString(Constants.spk_nstack_last_updated) } returns updateDate.formatted
        every { clientAppInfoMock.deviceName } returns "deviceName"
        every { clientAppInfoMock.osVersion } returns "osVersion"

        val settings = appOpenSettingsManager.getAppOpenSettings()

        assert(settings.platform == "android")
        assert(settings.guid == uuid)
        assert(settings.version == version)
        assert(settings.oldVersion == oldVersion)
        assert(settings.lastUpdated.formatted == updateDate.formatted)
    }

    @Test
    fun `Test old version is current when there is no old version saved`() {
        every { preferencesMock.loadString(Constants.spk_nstack_old_version) } returns ""
        every { clientAppInfoMock.deviceName } returns "deviceName"
        every { clientAppInfoMock.osVersion } returns "osVersion"

        val settings = appOpenSettingsManager.getAppOpenSettings()

        assert(settings.oldVersion == version)
    }

    @Test
    fun `Test random uuid is saved when there is no uuid in preferences`() {
        val uuidSlot = slot<String>()

        every { preferencesMock.loadString(Constants.spk_nstack_guid) } returns ""
        every {
            preferencesMock.saveString(
                Constants.spk_nstack_guid,
                capture(uuidSlot)
            )
        } just Runs

        every { clientAppInfoMock.deviceName } returns "deviceName"
        every { clientAppInfoMock.osVersion } returns "osVersion"

        val settings = appOpenSettingsManager.getAppOpenSettings()

        verify { preferencesMock.saveString(Constants.spk_nstack_guid, any()) }
        assert(uuidSlot.captured.isNotEmpty())
        assert(settings.guid == uuidSlot.captured)
    }
}
