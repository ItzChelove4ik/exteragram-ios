package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
public class RegistrationMethods<A extends Api.AnyClient, L> {
    public final RegisterListenerMethod<A, L> register;
    public final UnregisterListenerMethod zaa;
    public final Runnable zab;

    public static class Builder<A extends Api.AnyClient, L> {
        private RemoteCall zaa;
        private RemoteCall zab;
        private Runnable zac;
        private ListenerHolder zad;
        private Feature[] zae;
        private boolean zaf;
        private int zag;

        public /* synthetic */ Builder(byte[] r1) {
            this.zac = zacg.zaa;
            this.zaf = true;
        }

        public RegistrationMethods<A, L> build() {
            boolean r1 = false;
            if (this.zaa == null) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "Must set register function");
            if (this.zab == null) goto L9;
            boolean r02 = true;
        L10:
            Preconditions.checkArgument(r02, "Must set unregister function");
            if (this.zad == null) goto L13;
            r1 = true;
        L13:
            Preconditions.checkArgument(r1, "Must set holder");
            return new RegistrationMethods(new zace(this, this.zad, this.zae, this.zaf, this.zag), new zacf(this, (ListenerHolder.ListenerKey) Preconditions.checkNotNull(this.zad.getListenerKey(), "Key must not be null")), this.zac, null);
        L9:
            r02 = false;
            goto L10
        L5:
            r0 = false;
            goto L6
        }

        public Builder<A, L> register(RemoteCall<A, TaskCompletionSource<Void>> r1) {
            this.zaa = r1;
            return this;
        }

        public Builder<A, L> setAutoResolveMissingFeatures(boolean r1) {
            this.zaf = r1;
            return this;
        }

        public Builder<A, L> setFeatures(Feature... r1) {
            this.zae = r1;
            return this;
        }

        public Builder<A, L> setMethodKey(int r1) {
            this.zag = r1;
            return this;
        }

        public Builder<A, L> unregister(RemoteCall<A, TaskCompletionSource<Boolean>> r1) {
            this.zab = r1;
            return this;
        }

        public Builder<A, L> withHolder(ListenerHolder<L> r1) {
            this.zad = r1;
            return this;
        }

        public final /* synthetic */ RemoteCall zaa() {
            return this.zaa;
        }

        public final /* synthetic */ RemoteCall zab() {
            return this.zab;
        }
    }

    public /* synthetic */ RegistrationMethods(RegisterListenerMethod r1, UnregisterListenerMethod r2, Runnable r3, byte[] r4) {
        this.register = r1;
        this.zaa = r2;
        this.zab = r3;
    }

    public static <A extends Api.AnyClient, L> Builder<A, L> builder() {
        return new Builder(null);
    }
}
