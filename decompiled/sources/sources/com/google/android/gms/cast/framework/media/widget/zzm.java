package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzm implements RemoteMediaClient.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    public /* synthetic */ zzm(ExpandedControllerActivity r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        this.zza.zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
        ExpandedControllerActivity r2 = this.zza;
        String r0 = r2.getResources().getString(R.string.cast_expanded_controller_loading);
        r2.zzf().setText(r0);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        ExpandedControllerActivity r1 = this.zza;
        RemoteMediaClient r0 = r1.zza();
        if (r0 == null) goto L10;
        if (r0.hasMediaSession() == false) goto L10;
        r1.zzd = false;
        r1.zzc();
        r1.zzd();
        return;
    L10:
        if (r1.zzd == true) goto L13;
        r1.finish();
        return;
    }
}
