package com.example.superfit.presentation.view.screens.main.body

import androidx.compose.runtime.Immutable

@Immutable
sealed class BodyInputDialogIntent {
    data class NewText(val text: String) : BodyInputDialogIntent()
    object SaveChanges : BodyInputDialogIntent()
    object CloseDialog : BodyInputDialogIntent()
}

@Immutable
data class BodyInputDialogState(
    val text: String = "",
    val editWeight: Boolean? = null,
    val editHeight: Boolean? = null,
)