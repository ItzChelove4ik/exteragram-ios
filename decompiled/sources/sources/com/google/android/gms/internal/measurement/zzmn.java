package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzmn implements zzmj {
    private final zzkk zza;

    public zzmn(zzkk r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
    }

    private static ListenableFuture zzf(Task r3) {
        return Futures.catchingAsync(zzlb.zza(r3, null), ApiException.class, zzml.zza, MoreExecutors.directExecutor());
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final ListenableFuture zza(String r3, String r4) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(_UrlKt.FRAGMENT_ENCODE_SET);
        return zzf(this.zza.zzb(r3, _UrlKt.FRAGMENT_ENCODE_SET, null).continueWith(MoreExecutors.directExecutor(), new zzmm(this)));
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final ListenableFuture zzb(String r1) {
        Preconditions.checkNotNull(r1);
        return zzf(this.zza.zzc(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final ListenableFuture zzc(zzme r1) {
        return zzf(this.zza.zzd(r1));
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final ListenableFuture zzd() {
        return zzf(this.zza.zze());
    }

    @Override // com.google.android.gms.internal.measurement.zzmj
    public final ListenableFuture zze(zzpm r1) {
        return zzf(this.zza.zzf(r1));
    }
}
