package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
class FcmLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final Queue<String> recentlyLoggedMessageIds;

    /* renamed from: $r8$lambda$M-CDoSR824X1lz7KkuQYoNjZqqI, reason: not valid java name */
    public static /* synthetic */ void m1958$r8$lambda$MCDoSR824X1lz7KkuQYoNjZqqI(FcmLifecycleCallbacks r0, Intent r1) {
        r0.logNotificationOpen(r1);
    }

    public FcmLifecycleCallbacks() {
        this.recentlyLoggedMessageIds = new ArrayDeque(10);
    }

    private void logNotificationOpen(Intent r4) {
        Bundle r0 = null;
        Bundle r42 = r4.getExtras();     // Catch: RuntimeException -> L11
        if (r42 == null) goto L17;
        String r1 = MessagingAnalytics.getMessageId(r42);     // Catch: RuntimeException -> L11
        if (TextUtils.isEmpty(r1) == false) goto L8;
    L13:
        r0 = r42.getBundle("gcm.n.analytics_data");     // Catch: RuntimeException -> L11
        goto L17
    L8:
        if (this.recentlyLoggedMessageIds.contains(r1) == true) goto L22;
        this.recentlyLoggedMessageIds.add(r1);     // Catch: RuntimeException -> L11
        goto L13
    L22:
        return;
    L17:
        if (MessagingAnalytics.shouldUploadScionMetrics(r0) == false) goto L23;
        MessagingAnalytics.logNotificationOpen(r0);
        return;
    L23:
        return;
    L11:
        e = move-exception;
        Log.w("FirebaseMessaging", "Failed trying to get analytics data from Intent extras.", e);
        goto L17
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @SuppressLint({"ThreadPoolCreation"})
    public void onActivityCreated(Activity r2, Bundle r3) {
        Intent r22 = r2.getIntent();
        if (r22 != null) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT > 25) goto L9;
        new Handler(Looper.getMainLooper()).post(new FcmLifecycleCallbacks$$ExternalSyntheticLambda0(this, r22));
        return;
    L9:
        logNotificationOpen(r22);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity r1, Bundle r2) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity r1) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity r1) {
    }
}
