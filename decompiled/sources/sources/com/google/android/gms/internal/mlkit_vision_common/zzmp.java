package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* loaded from: classes4.dex */
public final class zzmp implements zzmc {
    private Provider zza;
    private final Provider zzb;
    private final zzme zzc;

    public zzmp(Context r2, zzme r3) {
        this.zzc = r3;
        CCTDestination r32 = CCTDestination.INSTANCE;
        TransportRuntime.initialize(r2);
        TransportFactory r22 = TransportRuntime.getInstance().newFactory(r32);
        if (r32.getSupportedEncodings().contains(Encoding.of("json")) == false) goto L5;
        this.zza = new Lazy(new zzmm(r22));
    L5:
        this.zzb = new Lazy(new zzmn(r22));
    }

    public static Event zzb(zzme r1, zzmb r2) {
        return Event.ofTelemetry(r2.zzd(r1.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmc
    public final void zza(zzmb r2) {
        if (this.zzc.zza() != 0) goto L8;
        Provider r0 = this.zza;
        if (r0 == null) goto L10;
        ((Transport) r0.get()).send(zzb(this.zzc, r2));
        return;
    L10:
        return;
    L8:
        ((Transport) this.zzb.get()).send(zzb(this.zzc, r2));
    }
}
