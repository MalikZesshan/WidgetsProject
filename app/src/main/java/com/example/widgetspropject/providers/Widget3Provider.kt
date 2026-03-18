package com.example.widgetspropject.providers

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.widgetspropject.R

class Widget3Provider : AppWidgetProvider() {

    companion object {
        var time = "--:--"
        var islamicDate = "----"
        var gregorianDate = "----"
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "UPDATE_WIDGET") {

            time = intent.getStringExtra("time") ?: "--:--"
            islamicDate = intent.getStringExtra("islamicDate") ?: "----"
            gregorianDate = intent.getStringExtra("gregorianDate") ?: "----"

            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val component = ComponentName(context, Widget3Provider::class.java)
        val ids = manager.getAppWidgetIds(component)

        for (id in ids) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout_3)

            views.setTextViewText(R.id.timeText, time)
            views.setTextViewText(R.id.islamicDate, islamicDate)
            views.setTextViewText(R.id.gregorianDate, gregorianDate)

            manager.updateAppWidget(id, views)
        }
    }
}