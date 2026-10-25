package com.google.android.gms.internal.measurement;

import android.net.Uri;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final /* synthetic */ class zzts implements AsyncFunction {
    static final /* synthetic */ zzts zza = null;

    static {
        zza = new zzts();
    }

    private /* synthetic */ zzts() {
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* synthetic */ ListenableFuture apply(Object r1) {
        Uri r12 = (Uri) r1;
        return Futures.immediateFuture(_UrlKt.FRAGMENT_ENCODE_SET);
    }
}
