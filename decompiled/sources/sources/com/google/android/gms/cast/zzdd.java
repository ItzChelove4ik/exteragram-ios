package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzdd implements RemoteMediaPlayer.MediaChannelResult {
    private final Status zza;
    private final JSONObject zzb;

    public zzdd(Status r1, JSONObject r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
    public final JSONObject getCustomData() {
        return this.zzb;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult, com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
