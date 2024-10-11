package fr.francoisdabonot.kmpapptoolkitlib.camera

import com.github.eduramiba.webcamcapture.drivers.NativeDriver
import com.github.eduramiba.webcamcapture.drivers.WebcamDeviceWithBufferOperations
import com.github.sarxos.webcam.Webcam
import java.awt.Image
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JLabel
import javax.swing.SwingUtilities

internal object CameraToolKit {
    private var currentCamera: Webcam? = null
    private val EXECUTOR: ScheduledExecutorService = Executors.newScheduledThreadPool(4)

    init {
        // windows / macos / (linux to be done)
        Webcam.setDriver(NativeDriver())
    }

    internal fun open(parameter: CameraToolKitParameter) {
        currentCamera =
            Webcam
                .getWebcams()
                .firstOrNull()

        /*currentCamera =
            Webcam
                .getWebcams()
                .firstOrNull {
                    it.name == parameter.camera.name
                } ?: throw Exception("Camera not found")*/
    }

    public val cameras: List<CameraToolkitDeviceCamera>
        get() =
            buildList {
                Webcam.getWebcams().forEach { camera ->
                    add(
                        CameraToolkitDeviceCamera(
                            name = camera.name ?: "NoName",
                            height = camera.viewSize.height,
                            width = camera.viewSize.width,
                        ),
                    )
                }
            }

    public fun captureImage(data: (ByteArray) -> Unit) {
        currentCamera?.image?.let { content ->
            val buffer = ByteArrayOutputStream()
            ImageIO.write(content, "png", buffer)
            data(buffer.toByteArray())
        }
    }

    public fun startCaptureVideo() {
    }

    public fun stopCaptureVideo(data: (ByteArray) -> Unit) {
    }

    public fun close() {
        currentCamera?.close() ?: println("No camera opened")
    }

    private fun getScaledImage(
        srcImg: Image,
        width: Int,
        height: Int,
    ): Image {
        val resizedImg = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2 = resizedImg.createGraphics()

        g2.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR,
        )
        g2.drawImage(srcImg, 0, 0, width, height, null)
        g2.dispose()

        return resizedImg
    }

    internal fun render(view: JLabel) {
        currentCamera?.let { camera ->
            val device = camera.device

            camera.lock.disable()
            camera.open()

            if (device is WebcamDeviceWithBufferOperations) {
                EXECUTOR.scheduleAtFixedRate({
                    val bufferedImage: BufferedImage? = device.image
                    bufferedImage?.let {
                        val image = it.getScaledInstance(view.width, view.height, Image.SCALE_SMOOTH)
                        val icon = ImageIcon(image)
                        SwingUtilities.invokeLater {
                            view.icon = icon
                            view.repaint()
                        }
                    }
                }, 0, 16, TimeUnit.MILLISECONDS)
            }
        }

        view.isVisible = true
    }
}
