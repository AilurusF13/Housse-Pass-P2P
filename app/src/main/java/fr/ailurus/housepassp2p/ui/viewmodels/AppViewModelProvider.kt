package fr.ailurus.housepassp2p.ui.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import fr.ailurus.housepassp2p.HousePassP2PApp

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SetupViewModel(housePassApp().repositoryManager)
        }
        initializer {
            LoginViewModel(housePassApp().repositoryManager)
        }
        initializer {
            VaultViewModel(housePassApp().repositoryManager)
        }
    }
}

private fun CreationExtras.housePassApp(): HousePassP2PApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HousePassP2PApp)