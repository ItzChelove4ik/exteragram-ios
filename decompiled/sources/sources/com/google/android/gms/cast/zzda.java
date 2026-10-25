package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzda implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzdc zza;

    public zzda(zzdc r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String r1, long r2, long r4, long r6) {
        zzdc r0 = this.zza;     // Catch: IllegalStateException -> L4
        r0.setResult(new zzdb(r0, new Status(2103)));     // Catch: IllegalStateException -> L4
        return;
    L4:
        e = move-exception;
        Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String r1, long r2, int r4, Object r5, long r6, long r8) {
        JSONObject r22 = null;
        if ((r5 instanceof com.google.android.gms.cast.internal.zzaq) == false) goto L5;
        com.google.android.gms.cast.internal.zzaq r52 = (com.google.android.gms.cast.internal.zzaq) r5;
    L14:
        zzdc r0 = this.zza;     // Catch: IllegalStateException -> L11
        Status r3 = new Status(r4);     // Catch: IllegalStateException -> L11
        if (r52 == null) goto L9;
        r22 = r52.zza;     // Catch: IllegalStateException -> L11
    L9:
        r0.setResult(new zzdd(r3, r22));     // Catch: IllegalStateException -> L11
        return;
    L11:
        e = move-exception;
        Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        return;
    L5:
        r52 = null;
        goto L14
    }
}
