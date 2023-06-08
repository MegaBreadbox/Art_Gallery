package com.example.artgallery

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.artgallery.ui.theme.ArtGalleryTheme
import org.junit.Rule
import org.junit.Test

class StateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun state_on_first_button_click(){
        composeTestRule.setContent{
            ArtGalleryTheme{
                ArtLayout()
            }
        }
        composeTestRule.onNodeWithContentDescription("Back arrow").performClick()
        val expectedState = 2
        composeTestRule.onNode("")
    }
}