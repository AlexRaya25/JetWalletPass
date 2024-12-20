package com.rayadev.jetwalletpass

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.pay.Pay
import com.google.android.gms.pay.PayApiAvailabilityStatus
import com.google.android.gms.pay.PayClient
import com.google.wallet.button.WalletButton
import com.rayadev.jetwalletpass.ui.theme.JetWalletPassTheme

class MainActivity : ComponentActivity() {

    private val addToGoogleWalletRequestCode = 1000
    private val model: WalletViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val payState: WalletUiState by model.walletUiState.collectAsStateWithLifecycle()

            JetWalletPassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        when (payState) {
                            is WalletUiState.PassAdded -> AddToGoogleWalletSuccess()
                            is WalletUiState.Available -> AddToGoogleWallet(::requestSavePass)
                            else -> IndeterminateCircularIndicator()
                        }
                    }
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

                /* Handle other result scenarios
                 * Learn more at: https://developers.google.com/wallet/generic/android#5_add_the_object_to
                 */
                else -> { // Other uncaught errors }
                }
            }
        }
    }
}

@Composable
fun IndeterminateCircularIndicator() = CircularProgressIndicator(
    color = MaterialTheme.colorScheme.surfaceVariant,
    trackColor = MaterialTheme.colorScheme.secondary,
)

@Composable
fun AddToGoogleWallet(onWalletButtonClick: () -> Unit) = Column(
    modifier = Modifier
        .padding(20.dp),
    verticalArrangement = Arrangement.spacedBy(space = 10.dp),
) {
    WalletButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onWalletButtonClick,
    )
}

@Composable
fun AddToGoogleWalletSuccess() = Column(
    modifier = Modifier
        .padding(20.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Image(
        contentDescription = null,
        painter = painterResource(R.drawable.check_circle),
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
    )
    Text(text = stringResource(id = R.string.add_google_wallet_success))
}


@Preview(showBackground = true)
@Composable
fun WalletButtonPreview() {
    WalletButton(onClick = { /*TODO*/ })
}