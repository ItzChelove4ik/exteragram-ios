package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzvq {
    private final Map zza;
    private final Map zzb;

    public /* synthetic */ zzvq(zzvs r2, byte[] r3) {
        this.zza = new HashMap(r2.zza());
        this.zzb = new HashMap(r2.zzb());
    }

    public /* synthetic */ zzvq(byte[] r1) {
        this.zza = new HashMap();
        this.zzb = new HashMap();
    }

    public final zzvq zza(zzvp r5) {
        zzvr r0 = new zzvr(r5.zza(), r5.zzb(), null);
        Map r1 = this.zza;
        if (r1.containsKey(r0) == false) goto L11;
        zzvp r12 = (zzvp) r1.get(r0);
        if (r12.equals(r5) == false) goto L10;
        if (r5.equals(r12) == false) goto L10;
        return this;
    L10:
        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: ".concat(r0.toString()));
    L11:
        r1.put(r0, r5);
        return this;
    }

    public final zzvq zzb(zzvu r4) {
        Map r0 = this.zzb;
        Class r1 = r4.zza();
        if (r0.containsKey(r1) == false) goto L11;
        zzvu r02 = (zzvu) r0.get(r1);
        if (r02.equals(r4) == false) goto L10;
        if (r4.equals(r02) == false) goto L10;
        return this;
    L10:
        throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type".concat(r1.toString()));
    L11:
        r0.put(r1, r4);
        return this;
    }

    public final /* synthetic */ Map zzc() {
        return this.zza;
    }

    public final /* synthetic */ Map zzd() {
        return this.zzb;
    }
}
