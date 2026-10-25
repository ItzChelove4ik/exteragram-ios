package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;

/* loaded from: classes4.dex */
final /* synthetic */ class zzm implements ResultCallback {
    private final /* synthetic */ MediaQueue zza;

    public /* synthetic */ zzm(MediaQueue r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Result r1) {
        this.zza.zzd((RemoteMediaClient.MediaChannelResult) r1);
    }
}
