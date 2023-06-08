package com.example.artgallery

import android.icu.number.Scale
import android.media.audiofx.AudioEffect.Descriptor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

       modifier = Modifier

           .verticalScroll(rememberScrollState()),

   ){
       ArtAndDescription(
           artPiece = R.drawable.fogpigcompletev2,
           artDesc = R.string.fog_pig_description,
           artHeading = R.string.fog_pig_heading,
           artSubHeading = R.string.fog_pig_subheading,
           artSubHeading2 = R.string.fog_pig_subheading2,
           artSubHeading3 = R.string.fog_pig_subheading3,
           artFullText = R.string.fog_pig_full_text,
           modifier = Modifier
       )
       ButtonRow()

   }
}
//Has to be generic to pass all art pieces thru it
@Composable
private fun ArtAndDescription(
    artPiece: Int,
    artDesc: Int,
    artHeading: Int,
    artSubHeading: Int,
    artSubHeading2: Int,
    artSubHeading3: Int,
    artFullText: Int,
    modifier: Modifier = Modifier

){
    //always be passing modifier, only when its the layout do you not
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        Image(
            painter = painterResource(artPiece),
            contentDescription = stringResource(artDesc),
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(330.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = stringResource(artHeading),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(bottom = 16.dp)

        )
        Text(
            text = stringResource(artSubHeading),
            fontSize = 18.sp,
            modifier = modifier
                .padding(bottom = 12.dp)

        )
        Text(
            text = stringResource(artSubHeading2),
            fontSize = 18.sp,
            modifier = modifier
                    .padding(bottom = 12.dp)
        )
        Text(
            text = stringResource(artSubHeading3),
            fontSize = 18.sp,
            modifier = modifier
                    .padding(bottom = 4.dp)
        )
        Text(
            //not really the best way to indent, but it works for now
            text = "     " + stringResource(artFullText)
        )
    }

}
@Composable
private fun ButtonRow(
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(top = 24.dp)
            .padding(bottom = 8.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()

    ){
        //When using a button you have to define a composable within
        Button(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back_description),
            )
        }
        Button(onClick = { /*TODO*/ }) {
            Icon(
                Icons.Outlined.ArrowForward,
                contentDescription = stringResource(id = R.string.arrow_forward_description)
            )
        }
    }
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