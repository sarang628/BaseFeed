import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Preview
@Composable
fun LazyColumnTest(){
    LazyColumn(modifier             = Modifier.fillMaxSize(),
               contentPadding       = PaddingValues(vertical    = 4.dp,
                                                    horizontal  = 4.dp),
               verticalArrangement  = Arrangement.spacedBy(space = 4.dp)
    ) {
        items(100){
            Text(modifier   = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color.Black),
                text        = "test",
                color       = Color.White,
                textAlign   = TextAlign.Center)
        }
    }
}

@Preview
@Composable
fun LazyColumnPositionChangeTest(){

    var data by remember { mutableStateOf<List<Int>>(listOf(1,2,3,4,5,6,7,8,9,10)) }

    Box() {
        LazyColumn(modifier             = Modifier.fillMaxSize()
                                                  .padding(bottom = 60.dp),
                   contentPadding       = PaddingValues(vertical    = 4.dp,
                                                        horizontal  = 4.dp),
                   verticalArrangement  = Arrangement.spacedBy(space = 4.dp)
        ) {
            items(data.size, key = {data[it]}){
                Text(modifier   = Modifier.height(100.dp)
                                          .fillMaxWidth()
                                          .background(Color.Black)
                                          .animateItem(),
                    text        = "${data[it]}",
                    color       = Color.White,
                    textAlign   = TextAlign.Center)
            }
        }
        Button(modifier = Modifier.align(Alignment.BottomCenter),
               onClick = {
                   data = data.swap(0, 2)
               }) {
            Text("Change Position")
        }
    }
}

fun <T> List<T>.swap(i: Int, j: Int): List<T> =
    toMutableList().apply {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }