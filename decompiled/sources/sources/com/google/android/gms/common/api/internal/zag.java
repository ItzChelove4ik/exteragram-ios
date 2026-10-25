package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public final class zag extends zac {
    private final TaskApiCall zaa;
    private final TaskCompletionSource zab;
    private final StatusExceptionMapper zad;

    public zag(int r1, TaskApiCall r2, TaskCompletionSource r3, StatusExceptionMapper r4) {
        super(r1);
        this.zab = r3;
        this.zaa = r2;
        this.zad = r4;
        if (r1 == 2) goto L5;
        return;
    L5:
        if (r2.shouldAutoResolveMissingFeatures() == false) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        throw null;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zaa(zabk r1) {
        return this.zaa.zaa();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zab(zabk r1) {
        return this.zaa.shouldAutoResolveMissingFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final int zac(zabk r1) {
        return this.zaa.zab();
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zad(Status r2) {
        StatusExceptionMapper r0 = this.zad;
        this.zab.trySetException(r0.getException(r2));
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zae(Exception r1) {
        this.zab.trySetException(r1);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zaf(zaaa r1, boolean r2) {
        r1.zab(this.zab, r2);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zag(zabk r3) {
        this.zaa.doExecute(r3.zaf(), this.zab);     // Catch: RuntimeException -> L4 RemoteException -> L6 DeadObjectException -> L12
        return;
    L12:
        e = move-exception;
        throw e;
    L6:
        e = move-exception;
        zad(zai.zai(e));
        return;
    L4:
        e = move-exception;
        this.zab.trySetException(e);
    }
}
