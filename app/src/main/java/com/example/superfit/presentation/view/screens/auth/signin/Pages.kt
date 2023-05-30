package com.example.superfit.presentation.view.screens.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.superfit.R
import com.example.superfit.presentation.theme.montserratFamily
import com.example.superfit.presentation.view.shared.auth.AuthBrandText
import com.example.superfit.presentation.view.shared.auth.AuthEditText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInFirstPage(state: SignInScreenState, sendEvent: (SignInScreenUiEvent) -> Unit) {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        AuthBrandText(padding = PaddingValues(top = 68.dp))

        Column {
            AuthEditText(
                isEmail = true,
                placeholderText = LocalContext.current.getString(R.string.auth_user_name),
                paddingValues = PaddingValues(start = 54.dp, end = 50.dp, bottom = 12.dp),
                text = { state.userName }) { text ->
                sendEvent(SignInScreenUiEvent.NewUserNameText(text))
            }
            SignInButton { sendEvent(SignInScreenUiEvent.NextPage) }
        }

        ToSignUpButton { sendEvent(SignInScreenUiEvent.SignUp) }
    }
}

@Composable
fun SignInSecondPage(state: SignInScreenState, sendEvent: (SignInScreenUiEvent) -> Unit) {

    Image(
        painter = painterResource(id = R.drawable.auth_background_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Image(
        painter = painterResource(id = R.drawable.white_back_arrow),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 60.dp, start = 16.dp)
            .clickable { sendEvent(SignInScreenUiEvent.PrevPage) }
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        AuthBrandText(padding = PaddingValues(top = 68.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //TODO(Сделать правильно)
            Text(
                text = state.userName,
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = montserratFamily,
                fontWeight = FontWeight.W400
            )
            Button(onClick = { sendEvent(SignInScreenUiEvent.SignIn) }) {
                Text(text = "SignIn")
            }
        }

        Box {}
    }
}

@Composable
private fun SignInButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(start = 30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Text(
            text = LocalContext.current.getString(R.string.auth_sign_in),
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.white_right_arrow),
            contentDescription = null
        )
    }
}

@Composable
private fun ToSignUpButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.padding(bottom = 26.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        onClick = onClick
    ) {
        Text(
            text = LocalContext.current.getString(R.string.auth_sign_up),
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = montserratFamily,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.white_right_arrow),
            contentDescription = null
        )
    }
}