package com.google.android.gms.internal.fido;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.RoundingMode;
import java.util.Objects;
import kotlin.UByte;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
class zzge extends zzgf {
    private volatile zzgf zza;
    final zzgb zzb;
    final Character zzc;

    public zzge(zzgb r2, Character r3) {
        this.zzb = r2;
        if (r3 != null) goto L5;
    L9:
        this.zzc = r3;
        return;
    L5:
        if (r2.zzc(SignatureVisitor.INSTANCEOF) == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m(zzbo.zza("Padding character %s was already in alphabet", new Object[]{r3}));
        throw null;
    }

    public zzge(String r2, String r3, Character r4) {
        this(new zzgb(r2, r3.toCharArray()), r4);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzge) == false) goto L10;
        zzge r42 = (zzge) r4;
        if (this.zzb.equals(r42.zzb) == false) goto L10;
        if (Objects.equals(this.zzc, r42.zzc) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        Character r0 = this.zzc;
        return this.zzb.hashCode() ^ Objects.hashCode(r0);
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("BaseEncoding.");
        r0.append(this.zzb);
        if ((8 % this.zzb.zzb) == 0) goto L9;
        if (this.zzc != null) goto L7;
        r0.append(".omitPadding()");
        goto L9
    L7:
        r0.append(".withPadChar('");
        r0.append(this.zzc);
        r0.append("')");
    L9:
        return r0.toString();
    }

    public zzgf zza(zzgb r1, Character r2) {
        return new zzge(r1, r2);
    }

    @Override // com.google.android.gms.internal.fido.zzgf
    public void zzb(Appendable r3, byte[] r4, int r5, int r6) {
        int r0 = 0;
        zzbm.zze(0, r6, r4.length);
    L3:
        if (r0 >= r6) goto L5;
        zze(r3, r4, r0, Math.min(this.zzb.zzd, r6 - r0));
        r0 = r0 + this.zzb.zzd;
        goto L3
    }

    @Override // com.google.android.gms.internal.fido.zzgf
    public final int zzc(int r3) {
        zzgb r2 = this.zzb;
        int r32 = zzgh.zza(r3, r2.zzd, RoundingMode.CEILING);
        return r2.zzc * r32;
    }

    @Override // com.google.android.gms.internal.fido.zzgf
    public final zzgf zzd() {
        zzgf r0 = this.zza;
        if (r0 != null) goto L9;
        zzgb r02 = this.zzb;
        zzgb r1 = r02.zzb();
        if (r1 != r02) goto L7;
        r0 = this;
    L8:
        this.zza = r0;
        goto L9
    L7:
        r0 = zza(r1, this.zzc);
    L9:
        return r0;
    }

    public final void zze(Appendable r8, byte[] r9, int r10, int r11) {
        zzbm.zze(r10, r10 + r11, r9.length);
        int r1 = 0;
        if (r11 > this.zzb.zzd) goto L5;
        boolean r0 = true;
    L6:
        zzbm.zzc(r0);
        long r2 = 0;
        int r02 = 0;
    L8:
        if (r02 >= r11) goto L10;
        r2 = (r2 | (r9[r10 + r02] & UByte.MAX_VALUE)) << 8;
        r02 = r02 + 1;
        goto L8
    L10:
        int r92 = (r11 + 1) * 8;
        zzgb r102 = this.zzb;
    L12:
        if (r1 >= (r11 * 8)) goto L15;
        long r5 = r2 >>> ((r92 - r102.zzb) - r1);
        zzgb r03 = this.zzb;
        r8.append(r03.zza(((int) r5) & r03.zza));
        r1 = r1 + this.zzb.zzb;
        goto L12
    L15:
        if (this.zzc != null) goto L17;
        return;
    L17:
        if (r1 >= (this.zzb.zzd * 8)) goto L23;
        this.zzc.getClass();
        r8.append(SignatureVisitor.INSTANCEOF);
        r1 = r1 + this.zzb.zzb;
        goto L17
    L23:
        return;
    L5:
        r0 = false;
        goto L6
    }
}
