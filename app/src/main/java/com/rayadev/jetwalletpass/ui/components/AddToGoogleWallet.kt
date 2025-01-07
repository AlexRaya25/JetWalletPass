package com.rayadev.jetwalletpass.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.wallet.button.WalletButton
import com.rayadev.jetwalletpass.R

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
    Text(text = "Pase agregado exitosamente a Google Wallet")
}
