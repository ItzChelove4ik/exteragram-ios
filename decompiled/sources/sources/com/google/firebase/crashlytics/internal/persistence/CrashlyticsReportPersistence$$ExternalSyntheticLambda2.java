package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.util.function.Predicate;

/* loaded from: classes5.dex */
public final /* synthetic */ class CrashlyticsReportPersistence$$ExternalSyntheticLambda2 implements Predicate {
    public /* synthetic */ CrashlyticsReportPersistence$$ExternalSyntheticLambda2() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object r1) {
        return ((File) r1).exists();
    }
}
