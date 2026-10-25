package com.google.android.gms.wearable;

import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes5.dex */
final class zzk extends ChannelClient.ChannelCallback {
    final /* synthetic */ WearableListenerService zza;

    public /* synthetic */ zzk(WearableListenerService r1, zzj r2) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onChannelClosed(ChannelClient.Channel r1, int r2, int r3) {
        this.zza.onChannelClosed(r1, r2, r3);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onChannelOpened(ChannelClient.Channel r1) {
        this.zza.onChannelOpened(r1);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onInputClosed(ChannelClient.Channel r1, int r2, int r3) {
        this.zza.onInputClosed(r1, r2, r3);
    }

    @Override // com.google.android.gms.wearable.ChannelClient.ChannelCallback
    public final void onOutputClosed(ChannelClient.Channel r1, int r2, int r3) {
        this.zza.onOutputClosed(r1, r2, r3);
    }
}
