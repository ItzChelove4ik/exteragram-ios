package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes5.dex */
public final class zzbc implements ChannelApi.ChannelListener {
    private final ChannelClient.ChannelCallback zza;

    public zzbc(ChannelClient.ChannelCallback r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 != null) goto L7;
        return false;
    L7:
        if (zzbc.class == r3.getClass()) goto L10;
        return false;
    L10:
        return this.zza.equals(((zzbc) r3).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onChannelClosed(Channel r1, int r2, int r3) {
        this.zza.onChannelClosed(zzbd.zzb(r1), r2, r3);
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onChannelOpened(Channel r1) {
        this.zza.onChannelOpened(zzbd.zzb(r1));
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onInputClosed(Channel r1, int r2, int r3) {
        this.zza.onInputClosed(zzbd.zzb(r1), r2, r3);
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onOutputClosed(Channel r1, int r2, int r3) {
        this.zza.onOutputClosed(zzbd.zzb(r1), r2, r3);
    }
}
