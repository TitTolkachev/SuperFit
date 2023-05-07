package com.example.superfit.presentation.view.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily

@Composable
fun AuthBrandText(padding: PaddingValues) {
    Text(
        text = LocalContext.current.getString(R.string.app_name),
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),
        fontSize = 64.sp,
        textAlign = TextAlign.Center,
        color = Color.White,
        fontFamily = montserratFamily,
        fontWeight = FontWeight.Bold
    )
}