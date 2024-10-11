package fr.francoisdabonot.kmpapptoolkitlib.helpers

import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIViewController
import platform.UIKit.UIWindowScene

internal object ContextHelper {
    fun getRootViewController(): UIViewController =
        UIApplication.sharedApplication
            .connectedScenes
            .mapNotNull { it as? UIWindowScene }
            .firstOrNull { it.activationState == UISceneActivationStateForegroundActive }
            ?.keyWindow
            ?.rootViewController ?: throw Exception("The App must have a RootViewController")
}
