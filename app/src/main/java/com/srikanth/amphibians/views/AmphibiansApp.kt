package com.srikanth.amphibians.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.srikanth.amphibians.Model.Amphibians
import com.srikanth.amphibians.viewModel.AmphibiansUiState
import com.srikanth.amphibians.viewModel.AmphibiansViewModel


@Composable
fun AmphibiansApp(modifier: Modifier = Modifier) {
    var avm: AmphibiansViewModel =viewModel();

    val amphibians:AmphibiansUiState by avm.amphibiansUiState

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        when{
            amphibians.error->{
                Box(
                    modifier=Modifier.fillMaxSize()
                ){
                   Text("Error",modifier=Modifier.align(Alignment.Center))
                }
            }
            amphibians.loading->{
               Box(
                   modifier=Modifier.fillMaxSize()
               ){
                   CircularProgressIndicator(modifier=Modifier.align(Alignment.Center))
               }
            }
            else->
               AmphibiansList(amphibians=amphibians.amphinbiansList)

        }
    }
}


@Composable
fun AmphibiansList(
    modifier: Modifier = Modifier,
    amphibians: List<Amphibians>
) {
    Scaffold (
        topBar = {
            Text(
                "Amphibians",
                modifier=Modifier.padding(top = 50.dp, start = 8.dp, bottom = 8.dp),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight=FontWeight.Bold
            )
        },
        modifier = Modifier.fillMaxSize()
    ){
        innerPadding->
        LazyColumn(
            modifier=modifier.padding(innerPadding)
        ){
            items(amphibians){
            Amphibian(amphibian = it, modifier = modifier)

            }
        }
    }
    
}


@Composable
fun Amphibian(
    modifier: Modifier = Modifier,
    amphibian:Amphibians
) {

Card(
    modifier= Modifier
        .fillMaxWidth()
        .padding(8.dp),
    elevation =CardDefaults.cardElevation(10.dp),
    shape = RoundedCornerShape(8)
){
    Column(
        modifier=Modifier
    ){
        Text(
            amphibian.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
            )
        Image(
            painter = rememberAsyncImagePainter(amphibian.img_src),
            "",
            modifier= Modifier
                .fillMaxWidth()
                .aspectRatio(2f),
            contentScale = ContentScale.Crop
        )
        Text(
            amphibian.description,
            modifier = Modifier.padding(6.dp), style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Justify
        )

    }
}

}


@Preview(showBackground = true)
@Composable
fun Preview() {

    Amphibian(
        amphibian = Amphibians(
            name= "Great Basin Spadefoot",
            type="Toad",
    description =  "This toad spends most of its life underground due to the arid desert conditions in which it lives. Spadefoot toads earn the name because of their hind legs which are wedged to aid in digging. They are typically grey, green, or brown with dark spots.",
    img_src =  "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
        )
    )

}