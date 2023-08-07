package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.artgallery.data.DataSource
import com.example.artgallery.model.PigCard
import com.example.artgallery.ui.theme.ArtGalleryTheme

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text("Pig Archieves") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtLayout(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            ArtAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){contentPadding ->
        Column(modifier.padding(contentPadding)) {
            NavHost(
                navController = navController,
                startDestination = PigScreen.Piglist.name,
                modifier = Modifier
            ) {
                composable(route = PigScreen.Piglist.name) {
                    PigCardList(pigList = DataSource.Pigs, navController = navController)
                }

                composable(route = PigScreen.Fogpig.name) {
                    ArtAndDescription(
                        artPiece = R.drawable.fogpigcompletev2,
                        artDesc = R.string.fog_pig_description,
                        artHeading = R.string.fog_pig_heading,
                        artSubHeading = R.string.fog_pig_subheading,
                        artSubHeading2 = R.string.fog_pig_subheading2,
                        artSubHeading3 = R.string.fog_pig_subheading3,
                        artFullText = R.string.fog_pig_full_text
                    )
                }

                composable(route = PigScreen.Citypig.name) {
                    ArtAndDescription(
                        artPiece = R.drawable.city_pig,
                        artDesc = R.string.city_pig_description,
                        artHeading = R.string.city_pig_heading,
                        artSubHeading = R.string.city_pig_subheading,
                        artSubHeading2 = R.string.city_pig_subheading2,
                        artSubHeading3 = R.string.city_pig_subheading3,
                        artFullText = R.string.city_pig_full_text
                    )
                }
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
    modifier: Modifier = Modifier

) {
    //always be passing modifier, only when its the layout do you not
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
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
        //ButtonRow(incrementCounter,decrementCounter)
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

@Composable
fun PigCardLayout(
    pig: PigCard,
    index: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { navController.navigate(PigScreen.values().drop(1)[index].name) }

    ){
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
    navController: NavHostController,
    modifier: Modifier = Modifier

){
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        
        itemsIndexed(pigList){index,PigCard ->
            PigCardLayout(PigCard, index, navController)
        }
    }
}


enum class PigScreen() {
    Piglist,
    Fogpig,
    Citypig,
}
