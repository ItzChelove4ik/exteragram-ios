package org.telegram.messenger;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes5.dex */
public class FeedWidgetProvider extends AppWidgetProvider {
    public FeedWidgetProvider() {
    }

    public static void updateWidget(Context r4, AppWidgetManager r5, int r6) {
        Intent r0 = new Intent(r4, FeedWidgetService.class);
        r0.putExtra("appWidgetId", r6);
        r0.setData(Uri.parse(r0.toUri(1)));
        RemoteViews r1 = new RemoteViews(r4.getPackageName(), R.layout.feed_widget_layout);
        r1.setRemoteAdapter(R.id.list_view, r0);
        r1.setEmptyView(R.id.list_view, R.id.empty_view);
        Intent r42 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
        r42.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        r42.addFlags(67108864);
        r42.addCategory("android.intent.category.LAUNCHER");
        r1.setPendingIntentTemplate(R.id.list_view, PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r42, 167772160));
        r5.updateAppWidget(r6, r1);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context r5, int[] r6) {
        super.onDeleted(r5, r6);
        int r0 = 0;
    L4:
        if (r0 >= r6.length) goto L6;
        r5.getSharedPreferences("shortcut_widget", 0).edit().remove("account" + r6[r0]).remove("dialogId" + r6[r0]).apply();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context r1, Intent r2) {
        super.onReceive(r1, r2);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context r2, AppWidgetManager r3, int[] r4) {
        super.onUpdate(r2, r3, r4);
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L6;
        updateWidget(r2, r3, r4[r1]);
        r1 = r1 + 1;
        goto L4
    }
}
