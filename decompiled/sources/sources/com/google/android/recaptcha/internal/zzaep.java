package com.google.android.recaptcha.internal;

import com.google.android.gms.internal.vision.zzid$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzaep extends zzaeo {
    private final byte[] zzb;

    public zzaep(byte[] r2) {
        super(null);
        r2.getClass();
        this.zzb = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final byte zza(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzc(int r2, int r3) {
        byte[] r1 = this.zzb;
        int r32 = zzaer.zzB(r2, r3, r1.length);
        if (r32 != 0) goto L7;
        return zzaer.zza;
    L7:
        return new zzaem(r1, r2, r32);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzd(int r2, int r3) {
        byte[] r1 = this.zzb;
        int r32 = zzaer.zzB(r2, r3, r1.length);
        if (r32 != 0) goto L7;
        return zzaer.zza;
    L7:
        return new zzaem(r1, r2, r32);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zze(byte[] r1, int r2, int r3, int r4) {
        System.arraycopy(this.zzb, r2, r1, r3, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final ByteBuffer zzf() {
        return ByteBuffer.wrap(this.zzb).asReadOnlyBuffer();
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zzg(zzaej r3) {
        byte[] r2 = this.zzb;
        ((zzaex) r3).zzr(r2, 0, r2.length);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final String zzh(Charset r2) {
        return new String(this.zzb, r2);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final boolean zzi(zzaer r3) {
        if ((r3 instanceof zzaep) == false) goto L7;
        return Arrays.equals(this.zzb, ((zzaep) r3).zzb);
    L7:
        if ((r3 instanceof zzaem) == false) goto L11;
        return zzj(r3, 0, this.zzb.length);
    L11:
        return r3.zzi(this);
    }

    @Override // com.google.android.recaptcha.internal.zzaeo
    public final boolean zzj(zzaer r4, int r5, int r6) {
        if (r6 > r4.zzb()) goto L18;
        int r0 = r5 + r6;
        if (r0 <= r4.zzb()) goto L7;
        int r42 = r4.zzb();
        int r02 = String.valueOf(r5).length();
        int r1 = String.valueOf(r6).length();
        StringBuilder r12 = new StringBuilder((((r02 + 24) + r1) + 2) + String.valueOf(r42).length());
        r12.append("Ran off end of other: ");
        r12.append(r5);
        r12.append(", ");
        r12.append(r6);
        r12.append(", ");
        r12.append(r42);
        throw new IllegalArgumentException(r12.toString());
    L7:
        if ((r4 instanceof zzaep) == false) goto L11;
        return zzaer.zzC(this.zzb, 0, ((zzaep) r4).zzb, r5, r6);
    L11:
        if ((r4 instanceof zzaem) == false) goto L15;
        zzaem r43 = (zzaem) r4;
        return zzaer.zzC(this.zzb, 0, r43.zzm(), r43.zzn() + r5, r6);
    L15:
        return r4.zzd(r5, r0).equals(zzd(0, r6));
    L18:
        byte[] r3 = this.zzb;
        int r44 = String.valueOf(r6).length();
        int r32 = r3.length;
        zzid$$ExternalSyntheticBUOutline0.m((r44 + 18) + String.valueOf(r32).length(), r6, r32);
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzk(int r1, int r2, int r3) {
        return zzagf.zzc(r1, this.zzb, r2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaev zzl() {
        byte[] r3 = this.zzb;
        return zzaev.zzE(r3, 0, r3.length, true);
    }

    public final /* synthetic */ byte[] zzm() {
        return this.zzb;
    }
}
