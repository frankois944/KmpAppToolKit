package fr.francoisdabonot.kmpapptoolkit.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fr.francoisdabonot.kmpapptoolkit.composeapp.generated.resources.Res
import fr.francoisdabonot.kmpapptoolkit.composeapp.generated.resources.compose_multiplatform
import fr.francoisdabonot.kmpapptoolkitlib.camera.CameraToolKitParameter
import fr.francoisdabonot.kmpapptoolkitlib.camera.CameraToolKitView
import fr.francoisdabonot.kmpapptoolkitlib.camera.CameraToolkitDeviceCamera
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            /*Button(onClick = {
                CameraToolKit.open(
                    parameter = CameraToolKitParameter(),
                )
            }) {
                Text("Open Camera!")
            }*/
            CameraToolKitView(
                modifier = Modifier.fillMaxSize(),
                param = CameraToolKitParameter(camera = CameraToolkitDeviceCamera(name = "test", width = 100, height = 100)),
            )

            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}
