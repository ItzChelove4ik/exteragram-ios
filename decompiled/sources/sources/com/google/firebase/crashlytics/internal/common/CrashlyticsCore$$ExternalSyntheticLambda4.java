package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;

/* loaded from: classes.dex */
public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda4 implements BreadcrumbHandler {
    public final /* synthetic */ CrashlyticsCore f$0;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda4(CrashlyticsCore r1) {
        this.f$0 = r1;
    }

    @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler
    public final void handleBreadcrumb(String r1) {
        this.f$0.log(r1);
    }
}
