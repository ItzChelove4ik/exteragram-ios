package org.chromium.net.impl;

import android.net.http.DnsOptions;
import j$.time.Duration;
import j$.time.TimeConversions;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidHttpEngineBuilderWrapper$$ExternalSyntheticAPIConversion0 {
    public static /* synthetic */ DnsOptions.StaleDnsOptions.Builder m(DnsOptions.StaleDnsOptions.Builder r0, Duration r1) {
        return r0.setFreshLookupTimeout(TimeConversions.convert(r1));
    }
}
