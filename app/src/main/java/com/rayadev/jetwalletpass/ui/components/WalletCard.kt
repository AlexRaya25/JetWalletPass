package com.rayadev.jetwalletpass.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.rayadev.jetwalletpass.R

@Composable
fun WalletCard(
    logoUrl: Int,
    cardTitle: String,
    subheader: String,
    header: String,
    qrValue: Int,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(logoUrl),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = cardTitle,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                color = Color.White,
                thickness = 0.2.dp
            )

            Text(
                text = subheader,
                style = MaterialTheme.typography.titleMedium.copy (
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                textAlign = TextAlign.Start
            )

            Text(
                text = header,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier
                    .padding(start = 12.dp),
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(qrValue),
                contentDescription = "QR Code",
                modifier = Modifier
                    .size(240.dp)
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WalletCardPreview() {
    WalletCard(
        logoUrl = R.drawable.pass_google_logo,
        cardTitle = "Pruebas Wallet Api",
        subheader = "Android Developer",
        header = "RayaDev",
        qrValue = R.drawable.qr_image,
        backgroundColor = Color(0xFF4285F4)
    )
}
