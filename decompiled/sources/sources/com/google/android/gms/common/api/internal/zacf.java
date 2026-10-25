package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zacf extends UnregisterListenerMethod {
    final /* synthetic */ RegistrationMethods.Builder zaa;

    public zacf(RegistrationMethods.Builder r1, ListenerHolder.ListenerKey r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        super(r2);
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final void unregisterListener(Api.AnyClient r1, TaskCompletionSource<Boolean> r2) {
        this.zaa.zab().accept(r1, r2);
    }
}
