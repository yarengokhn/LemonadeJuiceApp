package com.example.lemonadejuiceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonadejuiceapp.ui.theme.LemonadeJuiceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeJuiceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp(modifier:Modifier=Modifier) {
    var currentState by remember { mutableStateOf(1) }

    var squezzeClickCount by remember { mutableStateOf(0) }
    
    when(currentState){
       1 -> LemonTextandImage(imageResourceId =R.drawable.lemon_tree,
           contentDescriptionId =R.string.lemon_tree_content_description,
           textResourceId =R.string.lemon_select,
           onImageClick = {
               currentState =2
               squezzeClickCount = (2..6).random()

           }

           )
        2 -> LemonTextandImage(imageResourceId =R.drawable.lemon_squeeze,
            contentDescriptionId =R.string.lemon_content_description,
            textResourceId =R.string.lemon_squeeze,
            onImageClick = {
                squezzeClickCount--
                if (squezzeClickCount == 0) {
                    currentState = 3
                }
            }

        )

        3 -> LemonTextandImage(imageResourceId =R.drawable.lemon_drink,
            contentDescriptionId =R.string.lemonade_content_description,
            textResourceId =R.string.lemon_drink,
            onImageClick = {

                currentState =4
            }

        )

        4-> LemonTextandImage(imageResourceId =R.drawable.lemon_restart,
            contentDescriptionId =R.string.empty_glass_content_description,
            textResourceId =R.string.lemon_empty_glass,
            onImageClick = {
                    currentState = 1

            }

        )



    }



}
@Composable
fun LemonTextandImage(modifier: Modifier= Modifier,
                      imageResourceId:Int,
                      contentDescriptionId:Int,
                      textResourceId:Int,
                      onImageClick:() ->Unit,
                      ){

    Column (modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Button(
            onClick = onImageClick,) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(contentDescriptionId)
            )
        }

        Text(text= stringResource(textResourceId))


    }


}


@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeJuiceAppTheme {
        LemonApp()
    }
}