package fr.francoisdabonot.kmpapptoolkitlib.camera

import co.touchlab.kermit.Logger

@JsFun("(output) => navigator.mediaDevices")
public external fun getDevices(vararg output: JsAny?): JsAny?

internal object CameraToolKit {
    fun open(parameter: CameraToolKitParameter) {
        Logger.i("CameraToolKit.open")
        Logger.i(getDevices(null).toString())
    }

    fun close() {
    }
}

// @JsFun("(output) => console.log(\"TOTOTO\")")
// public external fun consoleLog(vararg output: JsAny?)
