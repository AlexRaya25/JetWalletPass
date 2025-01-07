package com.rayadev.jetwalletpass.viewmodels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.pay.Pay
import com.google.android.gms.pay.PayApiAvailabilityStatus
import com.google.android.gms.pay.PayClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private val _walletUiState = MutableStateFlow<WalletUiState>(WalletUiState.Unknown)
    val walletUiState: StateFlow<WalletUiState> = _walletUiState.asStateFlow()

    private val walletClient: PayClient = Pay.getClient(application)

    init {
        viewModelScope.launch {
            fetchCanAddPassesToGoogleWallet()
        }
    }

    private suspend fun fetchCanAddPassesToGoogleWallet() {
        val status = walletClient
            .getPayApiAvailabilityStatus(PayClient.RequestType.SAVE_PASSES).await()

        val newState = when(status) {
            PayApiAvailabilityStatus.AVAILABLE -> WalletUiState.Available
            else -> WalletUiState.Error(
                CommonStatusCodes.ERROR,
                "Google Wallet is not available in this device."
            )
        }

        _walletUiState.update { newState }
    }

    val savePassesJwt: (String, Activity, Int) -> Unit = walletClient::savePassesJwt

    /* Tu Propio Jwt */
    val genericObjectJwt = ""

}


abstract class WalletUiState internal constructor(){
    object Unknown : WalletUiState()
    object Available : WalletUiState()
    class PassAdded : WalletUiState()
    class Error(val code: Int, val message: String? = null) : WalletUiState()
}
