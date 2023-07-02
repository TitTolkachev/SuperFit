package com.example.superfit.presentation.view.model

enum class ValidationError {
    // Auth
    EMPTY_USER_NAME,
    EMPTY_EMAIL,
    EMPTY_CODE,
    EMPTY_REPEAT_CODE,
    INVALID_EMAIL,
    INCORRECT_REPEAT_CODE,
    TOO_LONG_CODE,
    INVALID_CHAR_IN_CODE,

    // Body
    EMPTY_BODY_FIELD,
    INVALID_INPUT_BODY_FIELD,
    OUT_OF_BOUNDS_BODY_FIELD
}