package com.google.android.recaptcha.internal;

import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzacw implements zzst {
    private final byte[] zza;
    private final byte[] zzb;
    private final byte[] zzc;

    public /* synthetic */ zzacw(byte[] r3, byte[] r4, byte[] r5, byte[] r6) {
        if (zzte.zza(1) == true) goto L5;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(new GeneralSecurityException("Can not use Ed25519 in FIPS-mode."));
        throw null;
    L5:
        if (r3.length != 32) goto L8;
        this.zza = (byte[]) r3.clone();
        this.zzb = r4;
        this.zzc = r5;
        zztp.zzd();
        return;
    L8:
        FieldSet$$ExternalSyntheticBUOutline0.m("Given public key's length is not %s.", new Object[]{32});
        throw null;
    }

    private final void zzb(byte[] r3, byte[] r4) {
        if (r3.length != 64) goto L10;
        if (zztp.zzc(r4, r3, this.zza) == false) goto L7;
        return;
    L7:
        zzsj$$ExternalSyntheticBUOutline1.m("Signature check failed.");
        return;
    L10:
        throw new GeneralSecurityException(String.format("The length of the signature is not %s.", new Object[]{64}));
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r4, byte[] r5) {
        byte[] r0 = this.zzb;
        int r1 = r0.length;
        if (r1 != 0) goto L9;
        if (this.zzc.length != 0) goto L9;
        zzb(r4, r5);
        return;
    L9:
        if (zzwc.zzc(r0, r4) == false) goto L15;
        byte[] r02 = this.zzc;
        if (r02.length == 0) goto L13;
        r5 = zzacu.zza(new byte[][]{r5, r02});
    L13:
        zzb(Arrays.copyOfRange(r4, r1, r4.length), r5);
        return;
    L15:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
    }
}
