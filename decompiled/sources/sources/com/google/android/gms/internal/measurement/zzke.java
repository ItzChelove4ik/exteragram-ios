package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Preconditions;

/* loaded from: classes4.dex */
final /* synthetic */ class zzke implements Continuation {
    private final /* synthetic */ zzkk zza;
    private final /* synthetic */ zzme zzb;

    public /* synthetic */ zzke(zzkk r1, zzme r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task r4) {
        boolean r0 = r4.getException() instanceof UnsupportedApiCallException;
        zzme r1 = this.zzb;
        zzkk r3 = this.zza;
        if (r0 == false) goto L7;
        return r3.zzc(r1.zza());
    L7:
        if ((r4.getException() instanceof ApiException) == true) goto L9;
    L12:
        return r4;
    L9:
        if (((ApiException) Preconditions.checkNotNull((ApiException) r4.getException())).getStatusCode() != 29514) goto L12;
        return r3.zzc(r1.zza());
    }
}
