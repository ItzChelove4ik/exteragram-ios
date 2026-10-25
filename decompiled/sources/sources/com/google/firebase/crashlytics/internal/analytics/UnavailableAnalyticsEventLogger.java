package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import com.google.firebase.crashlytics.internal.Logger;

/* loaded from: classes.dex */
public class UnavailableAnalyticsEventLogger implements AnalyticsEventLogger {
    public UnavailableAnalyticsEventLogger() {
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
    public void logEvent(String r1, Bundle r2) {
        Logger.getLogger().d("Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
    }
}
