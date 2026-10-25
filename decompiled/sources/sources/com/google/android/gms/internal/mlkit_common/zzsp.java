package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* loaded from: classes4.dex */
public final class zzsp implements zzrz {
    private Provider zza;
    private final Provider zzb;
    private final zzsb zzc;

    public zzsp(Context r2, zzsb r3) {
        this.zzc = r3;
        CCTDestination r32 = CCTDestination.INSTANCE;
        TransportRuntime.initialize(r2);
        TransportFactory r22 = TransportRuntime.getInstance().newFactory(r32);
        if (r32.getSupportedEncodings().contains(Encoding.of("json")) == false) goto L5;
        this.zza = new Lazy(new zzsm(r22));
    L5:
        this.zzb = new Lazy(new zzsn(r22));
    }
}
