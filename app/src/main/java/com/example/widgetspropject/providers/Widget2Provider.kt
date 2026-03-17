package com.example.widgetspropject.providers

class Widget2Provider  : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        updateWidget(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == "UPDATE_WIDGET") {
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context) {
        val manager = AppWidgetManager.getInstance(context)
        val component = ComponentName(context, MyWidgetProvider::class.java)
        val ids = manager.getAppWidgetIds(component)

        val currentTime = System.currentTimeMillis()

        val time = android.text.format.DateFormat.format("hh:mm a", currentTime)
        val gregorian = android.text.format.DateFormat.format("EEE, d MMM yyyy", currentTime)

        // Simple Islamic date (approx)
        val islamic = getIslamicDate()

        for (id in ids) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)

            views.setTextViewText(R.id.timeText, time)
            views.setTextViewText(R.id.islamicDate, islamic)
            views.setTextViewText(R.id.gregorianDate, gregorian)

            manager.updateAppWidget(id, views)
        }
    }

    private fun getIslamicDate(): String {
        val calendar = java.util.Calendar.getInstance()

        val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)
        val year = calendar.get(java.util.Calendar.YEAR)

        val hijriYear = year - 579 // approx conversion
        val hijriMonth = "Rajab"   // placeholder (real calc complex)

        return "$day $hijriMonth $hijriYear"
    }
}