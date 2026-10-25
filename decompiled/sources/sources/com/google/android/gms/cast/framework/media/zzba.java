package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONObject;

/* loaded from: classes4.dex */
final class zzba implements com.google.android.gms.cast.internal.zzat {
    final /* synthetic */ zzbc zza;

    public zzba(zzbc r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String r11, long r12, long r14, long r16) {
        zzbc r0 = this.zza;     // Catch: IllegalStateException -> L4
        r0.setResult(new zzbb(r0, new Status(2103)));     // Catch: IllegalStateException -> L4
    L6:
        Iterator r10 = this.zza.zzg.zzv().iterator();
    L8:
        if (r10.hasNext() == false) goto L10;
        ((RemoteMediaClient.Callback) r10.next()).zza(r11, r12, 2103, r14, r16);
        goto L8
    L10:
        return;
    L4:
        e = move-exception;
        RemoteMediaClient.zzq().e(e, "Result already set when calling onRequestReplaced", new Object[0]);
        goto L6
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String r15, long r16, int r18, Object r19, long r20, long r22) {
        MediaError r2 = null;
        if ((r19 instanceof com.google.android.gms.cast.internal.zzaq) == false) goto L5;
        com.google.android.gms.cast.internal.zzaq r0 = (com.google.android.gms.cast.internal.zzaq) r19;
    L26:
        zzbc r1 = this.zza;     // Catch: IllegalStateException -> L18
        int r9 = r18;
        Status r4 = new Status(r9);     // Catch: IllegalStateException -> L11
        if (r0 == null) goto L13;
        JSONObject r5 = r0.zza;     // Catch: IllegalStateException -> L11
    L14:
        if (r0 == null) goto L16;
        r2 = r0.zzb;     // Catch: IllegalStateException -> L11
    L16:
        r1.setResult(new zzbd(r4, r5, r2));     // Catch: IllegalStateException -> L11
    L21:
        Iterator r14 = this.zza.zzg.zzv().iterator();
    L23:
        if (r14.hasNext() == false) goto L25;
        ((RemoteMediaClient.Callback) r14.next()).zza(r15, r16, r9, r20, r22);
        r9 = r18;
        goto L23
    L25:
        return;
    L13:
        r5 = null;
    L11:
        e = e;
    L20:
        RemoteMediaClient.zzq().e(e, "Result already set when calling onRequestCompleted", new Object[0]);
    L18:
        e = e;
        r9 = r18;
        goto L20
    L5:
        r0 = null;
        goto L26
    }
}
