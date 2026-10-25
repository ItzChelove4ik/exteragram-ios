package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
final class zzes implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Status zza;
    private final Display zzb;

    public zzes(Display r2) {
        this.zza = Status.RESULT_SUCCESS;
        this.zzb = r2;
    }

    public zzes(Status r1) {
        this.zza = r1;
        this.zzb = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult
    public final Display getPresentationDisplay() {
        return this.zzb;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult, com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
