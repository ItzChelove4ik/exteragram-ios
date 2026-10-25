package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
abstract class zad extends zac {
    protected final TaskCompletionSource zaa;

    public zad(int r1, TaskCompletionSource r2) {
        super(r1);
        this.zaa = r2;
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zad(Status r2) {
        ApiException r0 = new ApiException(r2);
        this.zaa.trySetException(r0);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zae(Exception r1) {
        this.zaa.trySetException(r1);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zag(zabk r2) {
        zah(r2);     // Catch: RuntimeException -> L4 RemoteException -> L7 DeadObjectException -> L10
        return;
    L10:
        e = move-exception;
        zad(zai.zai(e));
        throw e;
    L7:
        e = move-exception;
        zad(zai.zai(e));
        return;
    L4:
        e = move-exception;
        this.zaa.trySetException(e);
    }

    public abstract void zah(zabk r1);
}
