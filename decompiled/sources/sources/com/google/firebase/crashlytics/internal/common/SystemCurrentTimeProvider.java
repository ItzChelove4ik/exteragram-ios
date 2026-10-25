package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes.dex */
public class SystemCurrentTimeProvider implements CurrentTimeProvider {
    public SystemCurrentTimeProvider() {
    }

    @Override // com.google.firebase.crashlytics.internal.common.CurrentTimeProvider
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
