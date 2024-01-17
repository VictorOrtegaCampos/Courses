package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    CoursesList()
}

@Composable
fun CoursesCard(topic: Topic) {
    Card(modifier = Modifier.padding()) {
        Row {
            Box {
                Image(
                    painter = painterResource(topic.imageResourceId),
                    contentDescription = stringResource(topic.stringResourceId),
                    modifier = Modifier
                        .size(
                            width = (dimensionResource(R.dimen.padding_big)),
                            height = (dimensionResource(R.dimen.padding_big)))
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column  {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = Modifier.padding(
                        start = (dimensionResource(R.dimen.padding_medium)),
                        top = (dimensionResource(R.dimen.padding_medium)),
                        end = (dimensionResource(R.dimen.padding_medium)),
                        bottom = (dimensionResource(R.dimen.padding_small))),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(painter = painterResource(R.drawable.ic_grain) ,
                        contentDescription = null,
                        modifier = Modifier.padding(start = (dimensionResource(R.dimen.padding_medium)),
                            end = (dimensionResource(R.dimen.padding_small)))
                        )
                    Text(
                        text = topic.num.toString(),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesList() {
    Column ( modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(DataSource.topics) { topic ->
                CoursesCard(topic)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        CoursesApp()
    }
}