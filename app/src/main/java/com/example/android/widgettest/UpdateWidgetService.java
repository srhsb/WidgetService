package com.example.android.widgettest;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class UpdateWidgetService extends Service {
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        buildUpdate();
        Toast.makeText(this.getApplicationContext(), "widget added", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
//        return START_STICKY (Should be used)
    }

    private void buildUpdate()
    {
        //String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa", new java.util.Date()).toString();
        int number = (new Random().nextInt(100));
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.new_app_widget);
        view.setTextViewText(R.id.appwidget_text, String.valueOf(number));

        // Push update for this widget to the home screen
        ComponentName thisWidget = new ComponentName(this, NewAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(thisWidget, view);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
