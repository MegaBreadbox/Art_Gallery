package com.example.artgallery

import android.media.audiofx.AudioEffect.Descriptor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtLayout()
                }
            }
        }
    }
}

@Composable
fun ArtLayout() {
   Column(
       modifier = Modifier,

   ){
       ArtAndDescription(
           artPiece = R.drawable.fogpigcompletev2,
           artDesc = R.string.fog_pig,
           modifier = Modifier
       )

   }
}
//Has to be generic to pass all art pieces thru it
@Composable
private fun ArtAndDescription(
    artPiece: Int,
    artDesc: Int,
    modifier: Modifier

){
    val annotatedArt = buildAnnotatedString {
        appendInlineContent(id = "artHolder")
        append(stringResource(id = artDesc))
    }

    val inLineLayout = mapOf(
        "artHolder" to InlineTextContent(
            Placeholder(400.sp, 00.sp, PlaceholderVerticalAlign.TextTop)
        ){
                Image(
                    painter = painterResource(artPiece),
                    contentDescription = stringResource(artDesc),
                    modifier = modifier.fillMaxSize()
                )
            }
        )
        //Think I got something here
        Text(annotatedArt, inlineContent = inLineLayout)

}

//Want to define a box for art and description and
//a row for the buttons
//Put em' all in a column

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtLayout()
    }
}