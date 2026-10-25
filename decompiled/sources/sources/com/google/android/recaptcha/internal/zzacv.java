package com.google.android.recaptcha.internal;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzacv implements zzss {
    private final byte[] zza;

    private zzacv(byte[] r2, byte[] r3, byte[] r4) {
        if (zzte.zza(1) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        throw null;
    L5:
        if (r2.length != 32) goto L8;
        byte[] r22 = zztp.zzb(r2);
        this.zza = r22;
        zztp.zza(r22);
        return;
    L8:
        FieldSet$$ExternalSyntheticBUOutline0.m("Given private key's length is not %s", new Object[]{32});
        throw null;
    }

    public static zzss zzb(zzyi r5) {
        if (zzte.zza(1) == true) goto L14;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use Ed25519 in FIPS-mode.");
        return null;
    L14:
        return zzabu.zzb(r5);
    L6:
        byte[] r2 = r5.zzf().zzb(zzsd.zza());
        byte[] r3 = r5.zze().zze().zzc();
        if (r5.zzd().zzb().equals(zzyg.zzc) == false) goto L9;
        byte[] r52 = {0};
    L11:
        return new zzacv(r2, r3, r52);
    L9:
        r52 = new byte[0];
        goto L11
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r1) {
        throw null;
    }
}
