package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzvx {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final Map zzd;

    public zzvx() {
        this.zza = new HashMap();
        this.zzb = new HashMap();
        this.zzc = new HashMap();
        this.zzd = new HashMap();
    }

    public zzvx(zzvy r3) {
        this.zza = new HashMap(r3.zzd());
        this.zzb = new HashMap(r3.zze());
        this.zzc = new HashMap(r3.zzf());
        this.zzd = new HashMap(r3.zzg());
    }

    public final zzvx zza(zzue r3) {
        Map r0 = this.zza;
        if (r0.containsKey(r3.zzb()) == false) goto L11;
        zzue r02 = (zzue) r0.get(r3.zzb());
        if (r02.equals(r3) == false) goto L10;
        if (r3.equals(r02) == false) goto L10;
        return this;
    L10:
        throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(r3.zzb().toString()));
    L11:
        r0.put(r3.zzb(), r3);
        return this;
    }

    public final zzvx zzb(zzub r4) {
        Map r0 = this.zzb;
        String r1 = r4.zzb();
        if (r0.containsKey(r1) == false) goto L11;
        zzub r02 = (zzub) r0.get(r1);
        if (r02.equals(r4) == true) goto L7;
    L9:
        zzsj$$ExternalSyntheticBUOutline1.m("Attempt to register non-equal parser for already existing object of type: ".concat(r1));
        return null;
    L7:
        if (r4.equals(r02) == false) goto L9;
        return this;
    L11:
        r0.put(r1, r4);
        return this;
    }

    public final zzvx zzc(zzvi r3) {
        Map r0 = this.zzc;
        if (r0.containsKey(r3.zza()) == false) goto L11;
        zzvi r02 = (zzvi) r0.get(r3.zza());
        if (r02.equals(r3) == false) goto L10;
        if (r3.equals(r02) == false) goto L10;
        return this;
    L10:
        throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: ".concat(r3.zza().toString()));
    L11:
        r0.put(r3.zza(), r3);
        return this;
    }

    public final zzvx zzd(zzvf r4) {
        Map r0 = this.zzd;
        String r1 = r4.zza();
        if (r0.containsKey(r1) == false) goto L11;
        zzvf r02 = (zzvf) r0.get(r1);
        if (r02.equals(r4) == true) goto L7;
    L9:
        zzsj$$ExternalSyntheticBUOutline1.m("Attempt to register non-equal parser for already existing object of type: ".concat(r1));
        return null;
    L7:
        if (r4.equals(r02) == false) goto L9;
        return this;
    L11:
        r0.put(r1, r4);
        return this;
    }

    public final /* synthetic */ Map zze() {
        return this.zza;
    }

    public final /* synthetic */ Map zzf() {
        return this.zzb;
    }

    public final /* synthetic */ Map zzg() {
        return this.zzc;
    }

    public final /* synthetic */ Map zzh() {
        return this.zzd;
    }
}
