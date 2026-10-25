package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes4.dex */
final /* synthetic */ class zzad implements SuccessContinuation {
    static final /* synthetic */ zzad zza = null;

    static {
        zza = new zzad();
    }

    private /* synthetic */ zzad() {
    }

    @Override // com.google.android.gms.tasks.SuccessContinuation
    public final /* synthetic */ Task then(Object r1) {
        return Rpc.zzc((Bundle) r1);
    }
}
