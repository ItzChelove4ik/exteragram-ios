package com.google.firebase.crashlytics.internal.breadcrumbs;

import com.google.firebase.crashlytics.internal.Logger;

/* loaded from: classes.dex */
public class DisabledBreadcrumbSource implements BreadcrumbSource {
    public DisabledBreadcrumbSource() {
    }

    @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource
    public void registerBreadcrumbHandler(BreadcrumbHandler r1) {
        Logger.getLogger().d("Could not register handler for breadcrumbs events.");
    }
}
