package com.rayadev.jetwalletpass.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun IndeterminateCircularIndicator() = CircularProgressIndicator(
    color = MaterialTheme.colorScheme.surfaceVariant,
    trackColor = MaterialTheme.colorScheme.secondary,
)
