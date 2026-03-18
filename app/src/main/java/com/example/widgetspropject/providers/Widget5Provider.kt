package com.example.widgetspropject.providers

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.widgetspropject.R

class Widget5Provider : AppWidgetProvider() {

    companion object {
        var islamicDay = "--:--"
        var islamicDate = "----"
        var islamicMonth = "--:--"
        var islamicYear = "----"
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "UPDATE_WIDGET") {

            islamicDay = intent.getStringExtra("islamicDay") ?: "--:--"
            islamicDate = intent.getStringExtra("islamicDate") ?: "----"
            islamicMonth = intent.getStringExtra("islamicMonth") ?: "----"
            islamicYear = intent.getStringExtra("islamicYear") ?: "----"

            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val component = ComponentName(context, Widget5Provider::class.java)
        val ids = manager.getAppWidgetIds(component)

        for (id in ids) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout_5)

            views.setTextViewText(R.id.islamicDayTV, islamicDay)
            views.setTextViewText(R.id.islamicDateTV, islamicDate)
            views.setTextViewText(R.id.islamicMonthTV, islamicMonth)
            views.setTextViewText(R.id.islamicYearTV, islamicYear)

            manager.updateAppWidget(id, views)
        }
    }
}