package com.google.android.gms.cloudmessaging;

import android.os.Looper;
import android.os.Message;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzy extends com.google.android.gms.internal.cloudmessaging.zzv {
    final /* synthetic */ Rpc zza;

    public zzy(Rpc r1, Looper r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        super(r2);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message r1) {
        this.zza.zzd(r1);
    }
}
