package org.chromium.net.impl;

import android.net.http.DnsOptions;
import j$.time.Duration;
import j$.time.TimeConversions;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion2 {
    public static /* synthetic */ DnsOptions.Builder m(DnsOptions.Builder r0, Duration r1) {
        return r0.setPersistHostCachePeriod(TimeConversions.convert(r1));
    }
}
