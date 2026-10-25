package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zace extends RegisterListenerMethod {
    final /* synthetic */ RegistrationMethods.Builder zaa;

    public zace(RegistrationMethods.Builder r1, ListenerHolder r2, Feature[] r3, boolean r4, int r5) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2, r3, r4, r5);
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final void registerListener(Api.AnyClient r1, TaskCompletionSource<Void> r2) {
        this.zaa.zaa().accept(r1, r2);
    }
}
