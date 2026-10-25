package com.google.android.gms.common.internal;

import android.content.Context;
import com.google.android.gms.common.internal.service.zat;

/* loaded from: classes4.dex */
public abstract class TelemetryLogging {
    public static TelemetryLoggingClient getClient(Context r1) {
        return getClient(r1, TelemetryLoggingOptions.zaa);
    }

    public static TelemetryLoggingClient getClient(Context r1, TelemetryLoggingOptions r2) {
        return new zat(r1, r2);
    }
}
