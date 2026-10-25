package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzdb implements RemoteMediaPlayer.MediaChannelResult {
    final /* synthetic */ Status zza;

    public zzdb(zzdc r1, Status r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult
    public final JSONObject getCustomData() {
        return null;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult, com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
