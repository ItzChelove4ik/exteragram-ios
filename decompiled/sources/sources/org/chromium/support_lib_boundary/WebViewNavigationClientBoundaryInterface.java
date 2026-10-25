package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;

@Deprecated
/* loaded from: classes5.dex */
public interface WebViewNavigationClientBoundaryInterface extends FeatureFlagHolderBoundaryInterface {
    void onFirstContentfulPaint(InvocationHandler r1);

    void onNavigationCompleted(InvocationHandler r1);

    void onNavigationRedirected(InvocationHandler r1);

    void onNavigationStarted(InvocationHandler r1);

    void onPageDOMContentLoadedEventFired(InvocationHandler r1);

    void onPageDeleted(InvocationHandler r1);

    void onPageLoadEventFired(InvocationHandler r1);
}
