package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;

/* loaded from: classes5.dex */
public interface WebViewNavigationListenerBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onFirstContentfulPaint(InvocationHandler r1, long r2);

    void onFirstContentfulPaintMillis(InvocationHandler r1, long r2);

    void onLargestContentfulPaintMillis(InvocationHandler r1, long r2);

    void onNavigationCompleted(InvocationHandler r1);

    void onNavigationRedirected(InvocationHandler r1);

    void onNavigationStarted(InvocationHandler r1);

    void onPageDOMContentLoadedEventFired(InvocationHandler r1);

    void onPageDeleted(InvocationHandler r1);

    void onPageLoadEventFired(InvocationHandler r1);

    void onPerformanceMarkMillis(InvocationHandler r1, String r2, long r3);
}
