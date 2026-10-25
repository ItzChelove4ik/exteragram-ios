package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;

/* loaded from: classes4.dex */
final /* synthetic */ class zzavh implements zzqc {
    private final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzavh(TransportFactory r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzqc
    public final /* synthetic */ Object zza() {
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), zzavf.zza);
    }
}
