package com.clement.newsapp.feature.headlines.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        Modifier
            .padding(20.dp),
        textAlign = TextAlign.Center)
}