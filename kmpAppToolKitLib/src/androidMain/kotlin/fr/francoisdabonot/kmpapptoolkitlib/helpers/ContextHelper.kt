package fr.francoisdabonot.kmpapptoolkitlib.helpers

import android.content.Context
import java.lang.ref.WeakReference

internal object ContextHelper {
    private var weakReference: WeakReference<Context>? = null

    /**
     * Store the android context inside a weakReference
     * It will be used for accessing the android context inside the shared module
     *
     * @param context
     */
    public fun setContext(context: Context) {
        weakReference = WeakReference(context)
    }

    internal fun getContext(): Context =
        requireNotNull(
            weakReference?.get(),
        ) { "A android context is require, please call ContextHelper.setContext()" }
}
