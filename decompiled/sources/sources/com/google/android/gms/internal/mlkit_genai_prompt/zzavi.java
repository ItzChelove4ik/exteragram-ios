package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;

/* loaded from: classes4.dex */
public final class zzavi implements zzauv {
    private zzqc zza;
    private final zzqc zzb;
    private final zzaux zzc;

    public zzavi(Context r2, zzaux r3) {
        this.zzc = r3;
        CCTDestination r32 = CCTDestination.INSTANCE;
        TransportRuntime.initialize(r2);
        TransportFactory r22 = TransportRuntime.getInstance().newFactory(r32);
        if (r32.getSupportedEncodings().contains(Encoding.of("json")) == false) goto L5;
        this.zza = new zzpp(new zzave(r22));
    L5:
        this.zzb = new zzpp(new zzavh(r22));
    }

    public static Event zzb(zzaux r1, zzauu r2) {
        return Event.ofTelemetry(r2.zza(r1.zzc(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzauv
    public final void zza(zzauu r3) {
        zzaux r0 = this.zzc;
        if (r0.zzc() != 0) goto L8;
        zzqc r2 = this.zza;
        if (r2 == null) goto L10;
        ((Transport) r2.zza()).send(zzb(r0, r3));
        return;
    L10:
        return;
    L8:
        ((Transport) this.zzb.zza()).send(zzb(r0, r3));
    }
}
