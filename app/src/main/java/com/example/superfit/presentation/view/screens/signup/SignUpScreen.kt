@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.superfit.presentation.view.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.theme.SemiTransparentWhite
import com.example.superfit.presentation.view.shared.AuthBrandText

@ExperimentalMaterial3Api
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {

    val state = viewModel.state

    SingUpScreenContent(state) { event: SignUpScreenUiEvent -> viewModel.accept(event) }
}

@ExperimentalMaterial3Api
@Composable
fun SingUpScreenContent(
    state: SignUpScreenState,
    sendEvent: (SignUpScreenUiEvent) -> Unit
) {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AuthBrandText(padding = PaddingValues(38.dp, 68.dp))

        EditText(isEmail = true, placeholderText = "Email", text = { state.emailValue }) { text ->
            sendEvent(SignUpScreenUiEvent.NewEmailText(text))
        }

        EditText(isPassword = true, placeholderText = "Code", text = { state.codeValue }) { text ->
            sendEvent(SignUpScreenUiEvent.NewPasswordText(text))
        }

        EditText(
            isPassword = true,
            placeholderText = "RepeatCode",
            text = { state.repeatCodeValue }) { text ->
            sendEvent(SignUpScreenUiEvent.NewRepeatPasswordText(text))
        }

        Button(onClick = {
            sendEvent(
                SignUpScreenUiEvent.SignUp(
                    state.emailValue,
                    state.codeValue,
                    state.repeatCodeValue
                )
            )
        }) {
            Text(text = "Sign Up")
        }
    }
}

@Composable
fun EditText(
    isEmail: Boolean = false,
    isPassword: Boolean = false,
    placeholderText: String,
    text: () -> String,
    onValueChanged: (String) -> Unit
) {

    TextField(
        value = text.invoke(),
        onValueChange = onValueChanged,
        placeholder = {
            Text(text = placeholderText)
        },
        modifier = Modifier
            .padding(start = 54.dp, end = 50.dp)
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
        singleLine = true,
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
