package com.example.superfit.presentation.view.shared.errordialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.superfit.R
import com.example.superfit.presentation.theme.Gray42
import com.example.superfit.presentation.view.model.ErrorType
import com.example.superfit.presentation.view.model.ValidationError

@Composable
fun ErrorDialog(
    state: ErrorDialogState,
    onClick: () -> Unit
) {
    if (state.text != null) {
        Dialog(onDismissRequest = onClick) {
            Surface(
                color = Gray42,
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    when (state.errorType) {
                        ErrorType.VALIDATION -> {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = stringResource(id = R.string.error_dialog_validation_error_text),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White,
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        ErrorType.UNEXPECTED -> {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = stringResource(id = R.string.error_dialog_unexpected_error_text),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Red,
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                        }

                        else -> {}
                    }
                    val text = when (state.validation) {
                        ValidationError.EMPTY_USER_NAME -> stringResource(id = R.string.EMPTY_USER_NAME)
                        ValidationError.EMPTY_EMAIL -> stringResource(id = R.string.EMPTY_EMAIL)
                        ValidationError.EMPTY_CODE -> stringResource(id = R.string.EMPTY_CODE)
                        ValidationError.EMPTY_REPEAT_CODE -> stringResource(id = R.string.EMPTY_REPEAT_CODE)
                        ValidationError.INVALID_EMAIL -> stringResource(id = R.string.INVALID_EMAIL)
                        ValidationError.INCORRECT_REPEAT_CODE -> stringResource(id = R.string.INCORRECT_REPEAT_CODE)
                        ValidationError.TOO_LONG_CODE -> stringResource(id = R.string.TOO_LONG_CODE)
                        ValidationError.INVALID_CHAR_IN_CODE -> stringResource(id = R.string.INVALID_CHAR_IN_CODE)
                        ValidationError.EMPTY_BODY_FIELD -> stringResource(id = R.string.EMPTY_BODY_FIELD)
                        ValidationError.INVALID_INPUT_BODY_FIELD -> stringResource(id = R.string.INVALID_INPUT_BODY_FIELD)
                        ValidationError.OUT_OF_BOUNDS_BODY_FIELD -> stringResource(id = R.string.OUT_OF_BOUNDS_BODY_FIELD)
                        else -> state.text
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = text,
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onClick, colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.error_dialog_button_text),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}