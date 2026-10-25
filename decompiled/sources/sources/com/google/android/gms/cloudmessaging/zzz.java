package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzz implements Continuation {
    private final /* synthetic */ Rpc zza;
    private final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzz(Rpc r1, Bundle r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task r2) {
        return this.zza.zza(this.zzb, r2);
    }
}
