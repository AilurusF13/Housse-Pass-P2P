package fr.ailurus.housepassp2p

import android.app.Application
import fr.ailurus.housepassp2p.data.repository.RepositoryManagerImpl
import fr.ailurus.housepassp2p.security.keystore.JavaKeystoreSystem

class HousePassP2PApp: Application() {
    val keystoreManager by lazy { JavaKeystoreSystem(applicationContext) }
    val repositoryManager by lazy { RepositoryManagerImpl(applicationContext, keystoreManager) }
}