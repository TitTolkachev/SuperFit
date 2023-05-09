package com.example.superfit.presentation.view.shared.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.presentation.theme.SemiTransparentWhite
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.shared.MyTextField

@ExperimentalMaterial3Api
@Composable
fun AuthEditText(
    isEmail: Boolean = false,
    isPassword: Boolean = false,
    placeholderText: String,
    paddingValues: PaddingValues? = PaddingValues(start = 54.dp, end = 50.dp, bottom = 37.dp),
    text: () -> String,
    onValueChanged: (String) -> Unit
) {
    MyTextField(
        value = text.invoke(),
        onValueChange = onValueChanged,
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.W400
        ),
        placeholder = {
            Text(
                text = placeholderText,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.W400
            )
        },
        modifier = Modifier
            .padding(paddingValues!!)
            .drawBehind {
                val strokeWidth = 2 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    SemiTransparentWhite,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            placeholderColor = Color.White,
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        contentPadding = PaddingValues(2.dp),
        visualTransformation =
        if (isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None,
        keyboardOptions =
        if (isPassword)
            KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        else if (isEmail)
            KeyboardOptions(keyboardType = KeyboardType.Email)
        else
            KeyboardOptions.Default
    )
}