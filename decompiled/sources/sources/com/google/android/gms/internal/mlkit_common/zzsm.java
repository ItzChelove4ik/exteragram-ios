package com.google.android.gms.internal.mlkit_common;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzsm implements Provider {
    public final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzsm(TransportFactory r1) {
        this.zza = r1;
    }

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        Encoding r0 = Encoding.of("json");
        zzso r1 = new zzso();
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, r0, r1);
    }
}
