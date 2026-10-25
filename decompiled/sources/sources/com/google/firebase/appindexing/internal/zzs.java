package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.icing.zzaa;
import com.google.android.gms.internal.icing.zzae;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
abstract class zzs extends TaskApiCall<zzae, Void> implements BaseImplementation$ResultHolder<Status> {
    protected TaskCompletionSource<Void> zzb;

    public zzs() {
        super(null, false, 9004);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    public final /* bridge */ /* synthetic */ void doExecute(Api.AnyClient r1, TaskCompletionSource<Void> r2) {
        this.zzb = r2;
        zza((zzaa) ((zzae) r1).getService());
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
    public final /* bridge */ /* synthetic */ void setResult(Object r2) {
        Status r22 = (Status) r2;
        boolean r0 = r22.isSuccess();
        TaskCompletionSource<Void> r1 = this.zzb;
        if (r0 == false) goto L6;
        r1.setResult(null);
        return;
    L6:
        r1.setException(zzaf.zza(r22, "User Action indexing error, please try again."));
    }

    public abstract void zza(zzaa r1);
}
