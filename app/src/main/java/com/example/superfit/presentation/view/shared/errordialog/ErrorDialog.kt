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
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = state.text,
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