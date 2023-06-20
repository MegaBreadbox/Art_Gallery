package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.artgallery.data.DataSource
import com.example.artgallery.model.PigCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
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
   ){
       var currentState by remember{ mutableStateOf(1) }
       when (currentState){
           1->{
               PigCardList(pigList = DataSource.Pigs, {currentState = 2}, {currentState = 1} )
           }

           2->{
               ArtAndDescription(
                   artPiece = R.drawable.fogpigcompletev2,
                   artDesc = R.string.fog_pig_description,
                   artHeading = R.string.fog_pig_heading,
                   artSubHeading = R.string.fog_pig_subheading,
                   artSubHeading2 = R.string.fog_pig_subheading2,
                   artSubHeading3 = R.string.fog_pig_subheading3,
                   artFullText = R.string.fog_pig_full_text,
                   incrementCounter = {
                       currentState = 3
                   },
                   decrementCounter = {
                       currentState = 1
                   },
                   modifier = Modifier
               )
           }
           3->{
               ArtAndDescription(
                   artPiece = R.drawable.city_pig,
                   artDesc = R.string.city_pig_description,
                   artHeading = R.string.city_pig_heading,
                   artSubHeading = R.string.city_pig_subheading,
                   artSubHeading2 = R.string.city_pig_subheading2,
                   artSubHeading3 = R.string.city_pig_subheading3,
                   artFullText = R.string.city_pig_full_text,

                   incrementCounter = {
                       currentState = 3
                   },
                   decrementCounter = {
                       currentState = 2
                   },
                   modifier = Modifier
               )
           }

       }
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
    incrementCounter: () -> Unit,
    decrementCounter: () -> Unit,
    modifier: Modifier = Modifier

){
    //always be passing modifier, only when its the layout do you not
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
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
        ButtonRow(incrementCounter,decrementCounter)
    }

}
//Lambdas are placed above modifier
@Composable
private fun ButtonRow(
    incrementCounter: () -> Unit,
    decrementCounter: () -> Unit,
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
        //Spent about an hour debugging a lambda syntax error. Yep
        Button(onClick = decrementCounter) {
            Icon(
                Icons.Outlined.ArrowBack,
                contentDescription = stringResource(id = R.string.arrow_back_description),
            )
        }
        Button(onClick = incrementCounter) {
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

}

@Composable
fun PigCardLayout(
    pig: PigCard,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier.fillMaxWidth()){
        Column{
            Image(
                painter = painterResource(pig.imageResourceId),
                contentScale = ContentScale.FillWidth,
                contentDescription = stringResource(id = pig.stringResourceId),
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(pig.stringResourceId),
                modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun PigCardList(
    pigList: List<PigCard>,
    increment: () -> Unit,
    decrement: () -> Unit,
    modifier: Modifier = Modifier

){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        
        items(pigList){PigCard ->
            PigCardLayout(PigCard)
        }

        item{ButtonRow(increment, decrement)}
    }
}