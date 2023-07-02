package com.example.superfit.presentation.view.shared.errordialog

import androidx.compose.runtime.Immutable
import com.example.superfit.presentation.view.model.ErrorType
import com.example.superfit.presentation.view.model.ValidationError

@Immutable
data class ErrorDialogState(
    val text: String? = null,
    val errorType: ErrorType? = null,
    val validation: ValidationError? = null
)