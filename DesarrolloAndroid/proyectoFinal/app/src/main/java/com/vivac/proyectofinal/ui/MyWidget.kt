package com.vivac.proyectofinal.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.annotation.CallSuper
import com.vivac.proyectofinal.MainActivity
import com.vivac.proyectofinal.R

class MyWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout)
            views.setTextViewText(R.id.textView, "Hello, World!")
            // Agregamos un onClick en el TextView para abrir la MainActivity
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            views.setOnClickPendingIntent(R.id.textView, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    @CallSuper
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action == "OPEN_MAIN_ACTIVITY") {
            val mainActivityIntent = Intent(context, MainActivity::class.java)
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(mainActivityIntent)
        }
    }
}
