package com.google.android.gms.internal.measurement;

import com.google.common.util.concurrent.AbstractFuture;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzkz extends AbstractFuture {
    Object zza;

    public zzkz(Object r1, Runnable r2) {
        this.zza = r1;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        this.zza = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        Object r0 = this.zza;
        if (r0 != null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L7:
        return r0.toString();
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean set(Object r1) {
        return super.set(r1);
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean setException(Throwable r1) {
        return super.setException(r1);
    }
}
