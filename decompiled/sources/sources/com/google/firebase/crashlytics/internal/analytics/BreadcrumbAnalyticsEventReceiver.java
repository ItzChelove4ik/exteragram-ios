package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class BreadcrumbAnalyticsEventReceiver implements AnalyticsEventReceiver, BreadcrumbSource {
    private BreadcrumbHandler breadcrumbHandler;

    public BreadcrumbAnalyticsEventReceiver() {
    }

    private static String serializeEvent(String r5, Bundle r6) {
        JSONObject r0 = new JSONObject();
        JSONObject r1 = new JSONObject();
        Iterator<String> r2 = r6.keySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        String r3 = r2.next();
        r1.put(r3, r6.get(r3));
        goto L4
    L6:
        r0.put("name", r5);
        r0.put("parameters", r1);
        return r0.toString();
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver
    public void onEvent(String r3, Bundle r4) {
        BreadcrumbHandler r2 = this.breadcrumbHandler;
        if (r2 == null) goto L10;
        r2.handleBreadcrumb("$A$:" + serializeEvent(r3, r4));     // Catch: JSONException -> L6
        return;
    L6:
        Logger.getLogger().w("Unable to serialize Firebase Analytics event to breadcrumb.");
        return;
    }

    @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource
    public void registerBreadcrumbHandler(BreadcrumbHandler r1) {
        this.breadcrumbHandler = r1;
        Logger.getLogger().d("Registered Firebase Analytics event receiver for breadcrumbs");
    }
}
