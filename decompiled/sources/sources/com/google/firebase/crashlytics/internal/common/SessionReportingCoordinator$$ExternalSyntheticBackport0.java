package com.google.firebase.crashlytics.internal.common;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/* loaded from: classes5.dex */
public abstract /* synthetic */ class SessionReportingCoordinator$$ExternalSyntheticBackport0 {
    public static /* synthetic */ Optional m(Optional r1, Supplier r2) {
        Objects.requireNonNull(r2);
        if (r1.isPresent() == false) goto L5;
        return r1;
    L5:
        Optional r12 = (Optional) r2.get();
        Objects.requireNonNull(r12);
        return r12;
    }
}
