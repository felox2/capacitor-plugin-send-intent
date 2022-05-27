package com.gustavosanjose.sendintentplugin

import android.content.Intent
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin

@CapacitorPlugin
class SendIntent : Plugin() {
    /**
     * Handle ACTION_VIEW intents to store a URL that was used to open the app
     *
     * @param intent
     */
    override fun handleOnNewIntent(intent: Intent) {
        super.handleOnNewIntent(intent)

        if (intent.action != Intent.ACTION_SEND)
            return

        // Get the extras from the intent
        val bundle = intent.extras
        val extras = JSObject()

        for (key in bundle!!.keySet()) {
            val value = bundle[key]
            extras.put(key, value)
        }

        val ret = JSObject()
        ret.put("type", intent.type)
        ret.put("extras", extras)

        notifyListeners(EVENT_SEND_ACTION_INTENT, ret, true)
    }

    companion object {
        private const val EVENT_SEND_ACTION_INTENT = "appSendActionIntent"
    }
}