package fr.francoisdabonot.kmpapptoolkitlib.camera

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import java.awt.Dimension
import javax.swing.JLabel

@Composable
public actual fun CameraToolKitView(
    modifier: Modifier,
    param: CameraToolKitParameter,
) {
    var displayCamera by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        displayCamera = true
        CameraToolKit.open(param)
        onDispose {
            CameraToolKit.close()
            displayCamera = false
        }
    }

    if (displayCamera) {
        SwingPanel(
            modifier = modifier,
            factory = {
                JLabel().apply {
                    // layout = BoxLayout(this, BoxLayout.Y_AXIS)
                    size = Dimension(100, 200)
                    CameraToolKit.render(this)
                }
            },
            update = {
            },
        )
    }
}
