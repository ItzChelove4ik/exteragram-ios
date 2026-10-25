package com.google.firebase.crashlytics;

import android.os.Bundle;
import com.google.android.gms.cast.MediaError;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BlockingAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.BreadcrumbAnalyticsEventReceiver;
import com.google.firebase.crashlytics.internal.analytics.CrashlyticsOriginAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.analytics.UnavailableAnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.breadcrumbs.DisabledBreadcrumbSource;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class AnalyticsDeferredProxy {
    private final Deferred<AnalyticsConnector> analyticsConnectorDeferred;
    private volatile AnalyticsEventLogger analyticsEventLogger;
    private final List<BreadcrumbHandler> breadcrumbHandlerList;
    private volatile BreadcrumbSource breadcrumbSource;

    public static /* synthetic */ void $r8$lambda$9OFGxA2N0nOG8dDPC_xdm7JYRUo(AnalyticsDeferredProxy r5, Provider r6) {
        r5.getClass();
        Logger.getLogger().d("AnalyticsConnector now available.");
        AnalyticsConnector r62 = (AnalyticsConnector) r6.get();
        CrashlyticsOriginAnalyticsEventLogger r0 = new CrashlyticsOriginAnalyticsEventLogger(r62);
        CrashlyticsAnalyticsListener r1 = new CrashlyticsAnalyticsListener();
        if (subscribeToAnalyticsEvents(r62, r1) == null) goto L17;
        Logger.getLogger().d("Registered Firebase Analytics listener.");
        BreadcrumbAnalyticsEventReceiver r63 = new BreadcrumbAnalyticsEventReceiver();
        BlockingAnalyticsEventLogger r2 = new BlockingAnalyticsEventLogger(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, TimeUnit.MILLISECONDS);
        monitor-enter(r5);
        Iterator<BreadcrumbHandler> r02 = r5.breadcrumbHandlerList.iterator();     // Catch: Throwable -> L10
    L8:
        if (r02.hasNext() == false) goto L12;
        r63.registerBreadcrumbHandler(r02.next());     // Catch: Throwable -> L10
        goto L8
    L12:
        r1.setBreadcrumbEventReceiver(r63);     // Catch: Throwable -> L10
        r1.setCrashlyticsOriginEventReceiver(r2);     // Catch: Throwable -> L10
        r5.breadcrumbSource = r63;     // Catch: Throwable -> L10
        r5.analyticsEventLogger = r2;     // Catch: Throwable -> L10
        monitor-exit(r5);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    L17:
        Logger.getLogger().w("Could not register Firebase Analytics listener; a listener is already registered.");
    }

    public static /* synthetic */ void $r8$lambda$Xg2Kwpim1JVk7PGmD_W0PC8IiHo(AnalyticsDeferredProxy r0, String r1, Bundle r2) {
        r0.analyticsEventLogger.logEvent(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jxPNZIoMD5TUXCrnSLExogZZxg0(AnalyticsDeferredProxy r1, BreadcrumbHandler r2) {
        monitor-enter(r1);
    L6:
        th = move-exception;
        throw th;
    L4:
        if ((r1.breadcrumbSource instanceof DisabledBreadcrumbSource) == false) goto L8;
        r1.breadcrumbHandlerList.add(r2);     // Catch: Throwable -> L6
    L8:
        r1.breadcrumbSource.registerBreadcrumbHandler(r2);     // Catch: Throwable -> L6
        monitor-exit(r1);     // Catch: Throwable -> L6
    }

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> r3) {
        this(r3, new DisabledBreadcrumbSource(), new UnavailableAnalyticsEventLogger());
    }

    public AnalyticsDeferredProxy(Deferred<AnalyticsConnector> r1, BreadcrumbSource r2, AnalyticsEventLogger r3) {
        this.analyticsConnectorDeferred = r1;
        this.breadcrumbSource = r2;
        this.breadcrumbHandlerList = new ArrayList();
        this.analyticsEventLogger = r3;
        init();
    }

    private void init() {
        this.analyticsConnectorDeferred.whenAvailable(new AnalyticsDeferredProxy$$ExternalSyntheticLambda2(this));
    }

    private static AnalyticsConnector.AnalyticsConnectorHandle subscribeToAnalyticsEvents(AnalyticsConnector r2, CrashlyticsAnalyticsListener r3) {
        AnalyticsConnector.AnalyticsConnectorHandle r0 = r2.registerAnalyticsConnectorListener("clx", r3);
        if (r0 != null) goto L8;
        Logger.getLogger().d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
        AnalyticsConnector.AnalyticsConnectorHandle r22 = r2.registerAnalyticsConnectorListener("crash", r3);
        if (r22 == null) goto L7;
        Logger.getLogger().w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
    L7:
        return r22;
    L8:
        return r0;
    }

    public AnalyticsEventLogger getAnalyticsEventLogger() {
        return new AnalyticsDeferredProxy$$ExternalSyntheticLambda1(this);
    }

    public BreadcrumbSource getDeferredBreadcrumbSource() {
        return new AnalyticsDeferredProxy$$ExternalSyntheticLambda0(this);
    }
}
