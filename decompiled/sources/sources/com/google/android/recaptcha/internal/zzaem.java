package com.google.android.recaptcha.internal;

import com.google.android.gms.internal.vision.zzid$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
final class zzaem extends zzaeo {
    private final byte[] zzb;
    private final int zzc;
    private final int zzd;

    public zzaem(byte[] r3, int r4, int r5) {
        super(null);
        zzaer.zzB(r4, r4 + r5, r3.length);
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final byte zza(int r2) {
        int r0 = this.zzc;
        return this.zzb[r0 + r2];
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzb() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzc(int r2, int r3) {
        int r32 = zzaer.zzB(r2, r3, this.zzd);
        if (r32 != 0) goto L7;
        return zzaer.zza;
    L7:
        return new zzaem(this.zzb, this.zzc + r2, r32);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaer zzd(int r2, int r3) {
        int r32 = zzaer.zzB(r2, r3, this.zzd);
        if (r32 != 0) goto L7;
        return zzaer.zza;
    L7:
        return new zzaem(this.zzb, this.zzc + r2, r32);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zze(byte[] r2, int r3, int r4, int r5) {
        int r0 = this.zzc;
        System.arraycopy(this.zzb, r0 + r3, r2, r4, r5);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final ByteBuffer zzf() {
        return ByteBuffer.wrap(this.zzb, this.zzc, this.zzd).asReadOnlyBuffer();
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final void zzg(zzaej r3) {
        ((zzaex) r3).zzr(this.zzb, this.zzc, this.zzd);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final String zzh(Charset r4) {
        return new String(this.zzb, this.zzc, this.zzd, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final boolean zzi(zzaer r3) {
        if ((r3 instanceof zzaep) == true) goto L10;
        if ((r3 instanceof zzaem) == true) goto L10;
        return r3.zzi(this);
    L10:
        return zzj(r3, 0, this.zzd);
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
        return zzaer.zzC(this.zzb, this.zzc, ((zzaep) r4).zzm(), r5, r6);
    L11:
        if ((r4 instanceof zzaem) == false) goto L14;
        zzaem r43 = (zzaem) r4;
        return zzaer.zzC(this.zzb, this.zzc, r43.zzb, r43.zzc + r5, r6);
    L14:
        zzaer r44 = r4.zzd(r5, r0);
        int r52 = this.zzc;
        return r44.equals(zzd(r52, r6 + r52));
    L18:
        int r3 = this.zzd;
        int r45 = String.valueOf(r6).length();
        zzid$$ExternalSyntheticBUOutline0.m((r45 + 18) + String.valueOf(r3).length(), r6, r3);
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final int zzk(int r2, int r3, int r4) {
        int r0 = this.zzc;
        return zzagf.zzc(r2, this.zzb, r0 + r3, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzaer
    public final zzaev zzl() {
        return zzaev.zzE(this.zzb, this.zzc, this.zzd, true);
    }

    public final /* synthetic */ byte[] zzm() {
        return this.zzb;
    }

    public final /* synthetic */ int zzn() {
        return this.zzc;
    }
}
