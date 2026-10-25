package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzjy extends zzkp {
    final /* synthetic */ TaskCompletionSource zza;

    public zzjy(zzkk r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzkq
    public final void zzb(Status r2, byte[] r3) {
        if (r2.isSuccess() == true) goto L12;
        TaskUtil.setResultOrApiException(r2, null, this.zza);
        return;
    L12:
        zzno r32 = zzno.zzc(r3, zzadf.zza());     // Catch: zzaeh -> L7
        TaskUtil.setResultOrApiException(r2, r32, this.zza);
        return;
    L7:
        e = move-exception;
        this.zza.setException(e);
    }
}
