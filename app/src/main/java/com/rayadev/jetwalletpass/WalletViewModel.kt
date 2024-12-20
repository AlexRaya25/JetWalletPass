package com.rayadev.jetwalletpass

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
import java.security.interfaces.RSAPrivateKey

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private val _walletUiState = MutableStateFlow<WalletUiState>(WalletUiState.Unknown)
    val walletUiState: StateFlow<WalletUiState> = _walletUiState.asStateFlow()

    // A client to interact with the Google Wallet API
    private val walletClient: PayClient = Pay.getClient(application)

    init {
        viewModelScope.launch {
            fetchCanAddPassesToGoogleWallet()
        }
    }

    /**
     * Determine whether the API to save passes to Google Pay is available on the device.
     */
    private suspend fun fetchCanAddPassesToGoogleWallet() {
        val status = walletClient
            .getPayApiAvailabilityStatus(PayClient.RequestType.SAVE_PASSES).await()

        val newState = when(status) {
            PayApiAvailabilityStatus.AVAILABLE -> WalletUiState.Available
            else -> WalletUiState.Error(CommonStatusCodes.ERROR,
                "Google Wallet is not available in this device.")
        }

        _walletUiState.update { newState }
    }

    /**
     * Exposes the `savePassesJwt` method in the wallet client
     */
    val savePassesJwt: (String, Activity, Int) -> Unit = walletClient::savePassesJwt

    /**
     * Exposes the `savePasses` method in the wallet client
     */
    val savePasses: (String, Activity, Int) -> Unit = walletClient::savePasses

    // Test generic object used to be created against the API
    // See https://developers.google.com/wallet/tickets/boarding-passes/web#json_web_token_jwt for more details
    val genericObjectJwt = ""
}

abstract class WalletUiState internal constructor(){
    object Unknown : WalletUiState()
    object Available : WalletUiState()
    class PassAdded : WalletUiState()
    class Error(val code: Int, val message: String? = null) : WalletUiState()
}
