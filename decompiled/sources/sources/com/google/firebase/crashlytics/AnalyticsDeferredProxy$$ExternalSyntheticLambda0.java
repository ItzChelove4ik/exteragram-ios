package com.google.firebase.crashlytics;

import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;

/* loaded from: classes.dex */
public final /* synthetic */ class AnalyticsDeferredProxy$$ExternalSyntheticLambda0 implements BreadcrumbSource {
    public final /* synthetic */ AnalyticsDeferredProxy f$0;

    public /* synthetic */ AnalyticsDeferredProxy$$ExternalSyntheticLambda0(AnalyticsDeferredProxy r1) {
        this.f$0 = r1;
    }

    @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource
    public final void registerBreadcrumbHandler(BreadcrumbHandler r1) {
        AnalyticsDeferredProxy.$r8$lambda$jxPNZIoMD5TUXCrnSLExogZZxg0(this.f$0, r1);
    }
}
