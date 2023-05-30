package com.example.superfit.presentation.view.screens.signup

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.superfit.core.util.TestTags
import com.example.superfit.di.DataModule
import com.example.superfit.di.DomainModule
import com.example.superfit.presentation.MainActivity
import com.example.superfit.presentation.navigation.Screen
import com.example.superfit.presentation.theme.SuperFitTheme
import com.example.superfit.presentation.view.screens.auth.signup.SignUpScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class, DomainModule::class)
class SignUpScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalMaterial3Api::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            SuperFitTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.SignUp.route
                ) { composable(route = Screen.SignUp.route) { SignUpScreen(navController = navController) } }
            }
        }
    }

    @Test
    fun registrationButtonClick_doesNotMakeItToDisappear() {
        with(composeRule.onNodeWithTag(TestTags.SIGN_UP_BUTTON)) {
            assertIsDisplayed()
            performClick()
            assertIsDisplayed()
        }
    }
}