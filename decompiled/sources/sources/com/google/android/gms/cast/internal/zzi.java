package com.google.android.gms.cast.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzi extends zzaf {
    final /* synthetic */ TaskCompletionSource zza;

    public zzi(zzn r1, TaskCompletionSource r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzag
    public final void zzb(Bundle r1, ApiMetadata r2) {
        this.zza.setResult(r1);
    }
}
