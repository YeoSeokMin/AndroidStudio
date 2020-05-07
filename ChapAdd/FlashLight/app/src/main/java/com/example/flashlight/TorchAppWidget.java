package com.example.flashlight;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class TorchAppWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.torch_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);


        Intent intent = new Intent(context,TorchService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,0);


        //강의
        views.setOnClickPendingIntent(R.id.torchLayout);



        //인터넷


        //views.setOnClickPendingIntent(R.id.torchLayout,pendingIntent);




        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

