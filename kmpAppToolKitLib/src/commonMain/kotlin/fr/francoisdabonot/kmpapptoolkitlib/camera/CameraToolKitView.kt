package fr.francoisdabonot.kmpapptoolkitlib.camera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public expect fun CameraToolKitView(
    modifier: Modifier = Modifier,
    param: CameraToolKitParameter,
)
