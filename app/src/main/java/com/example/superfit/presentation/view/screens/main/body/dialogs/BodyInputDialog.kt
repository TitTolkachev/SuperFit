package com.example.superfit.presentation.view.screens.main.body.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.superfit.R
import com.example.superfit.presentation.theme.Gray42
import com.example.superfit.presentation.theme.GrayF2
import com.example.superfit.presentation.theme.LightPurple
import com.example.superfit.presentation.theme.Pink
import com.example.superfit.presentation.view.screens.main.body.BodyInputDialogState
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyInputDialog(
    state: BodyInputDialogState,
    onChange: (String) -> Unit,
    onSaveChanges: () -> Unit,
    onCloseDialog: () -> Unit,
) {
    Dialog(onDismissRequest = {}) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Gray42
        ) {
            Box(
                contentAlignment = Alignment.Center
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
                        text = stringResource(id = R.string.body_input_dialog_title) +
                                if (state.editHeight == true)
                                    stringResource(id = R.string.body_input_dialog_title_height)
                                else
                                    stringResource(id = R.string.body_input_dialog_title_weight),
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    // Input Field
                    TextField(
                        singleLine = true,
                        value = state.text,
                        onValueChange = { newText: String ->
                            onChange(newText)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {
                            Text(
                                text = if (state.editHeight == true)
                                    stringResource(id = R.string.body_input_dialog_title_height).replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.ROOT
                                        ) else it.toString()
                                    }
                                else
                                    stringResource(id = R.string.body_input_dialog_title_weight).replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.getDefault()
                                        ) else it.toString()
                                    },
                                color = LightPurple,
                                fontSize = 12.sp
                            )
                        },
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.body_input_dialog_input_field_hint),
                                color = GrayF2,
                                fontSize = 16.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.White,
                            containerColor = Color.Transparent
                        )
                    )

                    // Line
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = LightPurple)
                    )

                    // Bottom buttons
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = onCloseDialog,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = stringResource(id = R.string.body_input_dialog_cancel).uppercase(),
                                fontSize = 14.sp,
                                color = Pink,
                                modifier = Modifier.clickable(onClick = onCloseDialog)
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(
                            onClick = onSaveChanges,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                        ) {
                            Text(
                                text = stringResource(id = R.string.body_input_dialog_save).uppercase(),
                                fontSize = 14.sp,
                                color = Pink,
                                modifier = Modifier.clickable(onClick = onSaveChanges)
                            )
                        }
                    }
                }
            }
        }
    }
}