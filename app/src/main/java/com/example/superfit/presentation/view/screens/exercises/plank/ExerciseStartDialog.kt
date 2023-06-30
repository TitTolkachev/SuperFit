package com.example.superfit.presentation.view.screens.exercises.plank

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.superfit.R
import com.example.superfit.presentation.theme.Gray42
import com.example.superfit.presentation.theme.Pink
import com.example.superfit.presentation.theme.Transparent30White

@Composable
fun ExerciseStartDialog(
    state: ExerciseStartDialogState,
    onStart: () -> Unit,
    onCancel: () -> Unit,
) {
    Dialog(onDismissRequest = onCancel) {
        Surface(
            shape = RoundedCornerShape(6.dp),
            color = Gray42
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 16.dp,
                )
            ) {

                // Title
                Text(
                    text = stringResource(id = R.string.exercise_start_dialog_title),
                    color = Color.White,
                    fontSize = 20.sp
                )

                // Text
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = LocalContext.current.getString(
                        R.string.exercise_start_dialog_text,
                        state.repeats
                    ),
                    color = Transparent30White,
                    fontSize = 16.sp
                )

                // Bottom buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = stringResource(id = R.string.exercise_start_dialog_cancel_button).uppercase(),
                            fontSize = 14.sp,
                            color = Pink,
                            modifier = Modifier.clickable(onClick = onCancel)
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(
                        onClick = onStart,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = stringResource(id = R.string.exercise_start_dialog_start_button).uppercase(),
                            fontSize = 14.sp,
                            color = Pink,
                            modifier = Modifier.clickable(onClick = onStart)
                        )
                    }
                }
            }
        }
    }
}