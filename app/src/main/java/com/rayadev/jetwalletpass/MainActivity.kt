package com.rayadev.jetwalletpass

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rayadev.jetwalletpass.ui.theme.JetWalletPassTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rayadev.jetwalletpass.ui.components.AddToGoogleWallet
import com.rayadev.jetwalletpass.ui.components.AddToGoogleWalletSuccess
import com.rayadev.jetwalletpass.ui.components.IndeterminateCircularIndicator
import com.rayadev.jetwalletpass.ui.components.WalletCard
import com.rayadev.jetwalletpass.viewmodels.WalletUiState
import com.rayadev.jetwalletpass.viewmodels.WalletViewModel

class MainActivity : ComponentActivity() {

    private val addToGoogleWalletRequestCode = 1000
    private val model: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val payState: WalletUiState by model.walletUiState.collectAsStateWithLifecycle()

            JetWalletPassTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(payState, ::requestSavePass)
                }
            }
        }
    }

     private fun requestSavePass() {
        model.savePassesJwt(model.genericObjectJwt, this, addToGoogleWalletRequestCode)
    }

    @Deprecated("Deprecated and in use by Google Pay")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == addToGoogleWalletRequestCode) {
            when (resultCode) {
                RESULT_OK -> Toast.makeText(
                    this, getString(R.string.add_google_wallet_success), Toast.LENGTH_LONG
                ).show()
                else -> { }
            }
        }
    }
}

@Composable
fun MainScreen(payState: WalletUiState, requestSavePass: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        WalletCard(
            logoUrl = R.drawable.pass_google_logo,
            cardTitle = "Pruebas Wallet Api",
            subheader = "Android Developer",
            header = "RayaDev",
            qrValue = R.drawable.qr_image,
            backgroundColor = Color(0xFF4285F4)
        )

        when (payState) {
            is WalletUiState.PassAdded -> AddToGoogleWalletSuccess()
            is WalletUiState.Available -> AddToGoogleWallet(requestSavePass)
            else -> IndeterminateCircularIndicator()
        }
    }
}