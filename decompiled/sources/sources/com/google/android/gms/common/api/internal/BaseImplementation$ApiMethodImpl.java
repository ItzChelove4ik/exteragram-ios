package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public abstract class BaseImplementation$ApiMethodImpl<R extends Result, A extends Api.AnyClient> extends BasePendingResult<R> implements BaseImplementation$ResultHolder<R> {
    private final Api<?> api;
    private final Api.AnyClientKey<A> clientKey;

    public BaseImplementation$ApiMethodImpl(Api<?> r2, GoogleApiClient r3) {
        super((GoogleApiClient) Preconditions.checkNotNull(r3, "GoogleApiClient must not be null"));
        Preconditions.checkNotNull(r2, "Api must not be null");
        this.clientKey = r2.zac();
        this.api = r2;
    }

    private void setFailedResult(RemoteException r4) {
        setFailedResult(new Status(8, r4.getLocalizedMessage(), null));
    }

    public abstract void doExecute(A r1);

    public final Api<?> getApi() {
        return this.api;
    }

    public final Api.AnyClientKey<A> getClientKey() {
        return this.clientKey;
    }

    public void onSetFailedResult(R r1) {
    }

    public final void run(A r1) {
        doExecute(r1);     // Catch: RemoteException -> L4 DeadObjectException -> L7
        return;
    L7:
        e = move-exception;
        setFailedResult(e);
        throw e;
    L4:
        e = move-exception;
        setFailedResult(e);
    }

    public final void setFailedResult(Status r3) {
        Preconditions.checkArgument(!r3.isSuccess(), "Failed result must not be success");
        R r32 = createFailedResult(r3);
        setResult(r32);
        onSetFailedResult(r32);
    }

    public /* bridge */ /* synthetic */ void setResult(Object r1) {
        super.setResult((Result) r1);
    }
}
