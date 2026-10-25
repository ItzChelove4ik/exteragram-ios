package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public abstract class TaskApiCall<A extends Api.AnyClient, ResultT> {
    private final Feature[] zaa;
    private final boolean zab;
    private final int zac;

    public static class Builder<A extends Api.AnyClient, ResultT> {
        private RemoteCall zaa;
        private boolean zab;
        private Feature[] zac;
        private int zad;

        public /* synthetic */ Builder(byte[] r1) {
            this.zab = true;
            this.zad = 0;
        }

        public TaskApiCall<A, ResultT> build() {
            if (this.zaa == null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "execute parameter required");
            return new zacn(this, this.zac, this.zab, this.zad);
        L5:
            r0 = false;
            goto L6
        }

        public Builder<A, ResultT> run(RemoteCall<A, TaskCompletionSource<ResultT>> r1) {
            this.zaa = r1;
            return this;
        }

        public Builder<A, ResultT> setAutoResolveMissingFeatures(boolean r1) {
            this.zab = r1;
            return this;
        }

        public Builder<A, ResultT> setFeatures(Feature... r1) {
            this.zac = r1;
            return this;
        }

        public Builder<A, ResultT> setMethodKey(int r1) {
            this.zad = r1;
            return this;
        }

        public final /* synthetic */ RemoteCall zaa() {
            return this.zaa;
        }
    }

    public TaskApiCall(Feature[] r2, boolean r3, int r4) {
        this.zaa = r2;
        boolean r0 = false;
        if (r2 == null) goto L6;
        if (r3 == false) goto L6;
        r0 = true;
    L6:
        this.zab = r0;
        this.zac = r4;
    }

    public static <A extends Api.AnyClient, ResultT> Builder<A, ResultT> builder() {
        return new Builder(null);
    }

    public abstract void doExecute(A r1, TaskCompletionSource<ResultT> r2);

    public boolean shouldAutoResolveMissingFeatures() {
        return this.zab;
    }

    public final Feature[] zaa() {
        return this.zaa;
    }

    public final int zab() {
        return this.zac;
    }
}
