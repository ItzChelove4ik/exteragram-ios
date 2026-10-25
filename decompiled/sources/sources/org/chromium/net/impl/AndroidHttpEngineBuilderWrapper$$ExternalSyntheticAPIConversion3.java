package org.chromium.net.impl;

import android.net.http.QuicOptions;
import j$.time.Duration;
import j$.time.TimeConversions;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion3 {
    public static /* synthetic */ QuicOptions.Builder m(QuicOptions.Builder r0, Duration r1) {
        return r0.setIdleConnectionTimeout(TimeConversions.convert(r1));
    }
}
