package org.telegram.messenger;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes5.dex */
public class ContactsWidgetProvider extends AppWidgetProvider {
    public ContactsWidgetProvider() {
    }

    private static int getCellsForSize(int r2) {
        int r0 = 2;
    L4:
        if ((r0 * 86) >= r2) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0 - 1;
    }

    public static void updateWidget(Context r13, AppWidgetManager r14, int r15) {
        ApplicationLoader.postInitApplication();
        int r0 = getCellsForSize(r14.getAppWidgetOptions(r15).getInt("appWidgetMaxHeight"));
        Intent r1 = new Intent(r13, ContactsWidgetService.class);
        r1.putExtra("appWidgetId", r15);
        r1.setData(Uri.parse(r1.toUri(1)));
        SharedPreferences r3 = r13.getSharedPreferences("shortcut_widget", 0);
        if (r3.getBoolean("deleted" + r15, false) == true) goto L27;
        int r5 = r3.getInt("account" + r15, -1);
        if (r5 != (-1)) goto L7;
        SharedPreferences.Editor r32 = r3.edit();
        r32.putInt("account" + r15, UserConfig.selectedAccount);
        r32.putInt("type" + r15, 0).apply();
    L7:
        ArrayList<Long> r9 = new ArrayList();
        if (r5 < 0) goto L10;
        int r7 = r15;
        AccountInstance.getInstance(r5).getMessagesStorage().getWidgetDialogIds(r7, 1, r9, null, null, false);
    L11:
        int r152 = (int) Math.ceil(r9.size() / 2.0f);
        if (r0 == 1) goto L26;
        if (r152 <= 1) goto L26;
        if (r0 == 2) goto L25;
        if (r152 <= 2) goto L25;
        if (r0 == 3) goto L24;
        if (r152 <= 3) goto L24;
        int r153 = R.layout.contacts_widget_layout_4;
    L28:
        RemoteViews r02 = new RemoteViews(r13.getPackageName(), r153);
        r02.setRemoteAdapter(r7, R.id.list_view, r1);
        r02.setEmptyView(R.id.list_view, R.id.empty_view);
        Intent r132 = new Intent(ApplicationLoader.applicationContext, LaunchActivity.class);
        r132.setAction("com.tmessages.openchat" + Math.random() + Integer.MAX_VALUE);
        r132.addFlags(67108864);
        r132.addCategory("android.intent.category.LAUNCHER");
        r02.setPendingIntentTemplate(R.id.list_view, PendingIntent.getActivity(ApplicationLoader.applicationContext, 0, r132, 167772160));
        r14.updateAppWidget(r7, r02);
        r14.notifyAppWidgetViewDataChanged(r7, R.id.list_view);
        return;
    L24:
        r153 = R.layout.contacts_widget_layout_3;
    L25:
        r153 = R.layout.contacts_widget_layout_2;
    L26:
        r153 = R.layout.contacts_widget_layout_1;
        goto L28
    L10:
        r7 = r15;
        goto L11
    L27:
        r7 = r15;
        r153 = R.layout.contacts_widget_layout_1;
        goto L28
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onAppWidgetOptionsChanged(Context r1, AppWidgetManager r2, int r3, Bundle r4) {
        updateWidget(r1, r2, r3);
        super.onAppWidgetOptionsChanged(r1, r2, r3, r4);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context r5, int[] r6) {
        super.onDeleted(r5, r6);
        ApplicationLoader.postInitApplication();
        int r0 = 0;
        SharedPreferences r4 = r5.getSharedPreferences("shortcut_widget", 0);
        SharedPreferences.Editor r52 = r4.edit();
    L4:
        if (r0 >= r6.length) goto L9;
        int r1 = r4.getInt("account" + r6[r0], -1);
        if (r1 < 0) goto L8;
        AccountInstance.getInstance(r1).getMessagesStorage().clearWidgetDialogs(r6[r0]);
    L8:
        r52.remove("account" + r6[r0]);
        r52.remove("type" + r6[r0]);
        r52.remove("deleted" + r6[r0]);
        r0 = r0 + 1;
        goto L4
    L9:
        r52.apply();
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
