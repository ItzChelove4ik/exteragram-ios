package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes5.dex */
public abstract class zzbd extends ChannelClient {
    public static /* bridge */ /* synthetic */ zzbq zzb(Channel r0) {
        return zzd(r0);
    }

    private static zzbq zzd(Channel r1) {
        Preconditions.checkNotNull(r1, "channel must not be null");
        return (zzbq) r1;
    }
}
