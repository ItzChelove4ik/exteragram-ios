package com.google.android.gms.internal.mlkit_language_id_common;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzln implements Provider {
    public final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzln(TransportFactory r1) {
        this.zza = r1;
    }

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), zzlp.zza);
    }
}
