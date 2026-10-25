package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.RoundingMode;
import java.util.Objects;
import kotlin.UByte;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
class zzru extends zzrv {
    public static final /* synthetic */ int $r8$clinit = 0;
    final zzrq zza;
    final Character zzb;

    public zzru(zzrq r2, Character r3) {
        this.zza = r2;
        if (r3 != null) goto L5;
    L9:
        this.zzb = r3;
        return;
    L5:
        if (r2.zzd(SignatureVisitor.INSTANCEOF) == false) goto L9;
        f$$ExternalSyntheticBUOutline1.m(zzot.zza("Padding character %s was already in alphabet", new Object[]{r3}));
        throw null;
    }

    public zzru(String r2, String r3, Character r4) {
        this(new zzrq(r2, r3.toCharArray()), r4);
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof zzru) == false) goto L10;
        zzru r42 = (zzru) r4;
        if (this.zza.equals(r42.zza) == false) goto L10;
        if (Objects.equals(this.zzb, r42.zzb) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final int hashCode() {
        Character r0 = this.zzb;
        return this.zza.hashCode() ^ Objects.hashCode(r0);
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("BaseEncoding.");
        zzrq r1 = this.zza;
        r0.append(r1);
        if ((8 % r1.zzb) == 0) goto L9;
        Character r3 = this.zzb;
        if (r3 != null) goto L7;
        r0.append(".omitPadding()");
        goto L9
    L7:
        r0.append(".withPadChar('");
        r0.append(r3);
        r0.append("')");
    L9:
        return r0.toString();
    }

    @Override // com.google.android.recaptcha.internal.zzrv
    public void zza(Appendable r3, byte[] r4, int r5, int r6) {
        int r0 = 0;
        zzoq.zzg(0, r6, r4.length);
    L3:
        if (r0 >= r6) goto L5;
        int r52 = this.zza.zzd;
        zzd(r3, r4, r0, Math.min(r52, r6 - r0));
        r0 = r0 + r52;
        goto L3
    }

    @Override // com.google.android.recaptcha.internal.zzrv
    public int zzb(byte[] r17, CharSequence r18) {
        CharSequence r1 = zzf(r18);
        int r2 = r1.length();
        zzrq r0 = this.zza;
        if (r0.zzb(r2) == false) goto L20;
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r3 >= r1.length()) goto L19;
        long r5 = 0;
        int r7 = 0;
        int r8 = 0;
    L8:
        int r9 = r0.zzc;
        if (r7 >= r9) goto L14;
        r5 = r5 << r0.zzb;
        if ((r3 + r7) >= r1.length()) goto L13;
        r5 = r5 | r0.zzc(r1.charAt(r8 + r3));
        r8 = r8 + 1;
    L13:
        r7 = r7 + 1;
        goto L8
    L14:
        int r72 = r0.zzd;
        int r82 = r8 * r0.zzb;
        int r10 = (r72 - 1) * 8;
    L16:
        if (r10 < ((r72 * 8) - r82)) goto L18;
        r17[r4] = (byte) ((r5 >>> r10) & 255);
        r10 = r10 - 8;
        r4 = r4 + 1;
        goto L16
    L18:
        r3 = r3 + r9;
        goto L6
    L19:
        return r4;
    L20:
        int r12 = r1.length();
        StringBuilder r32 = new StringBuilder(String.valueOf(r12).length() + 21);
        r32.append("Invalid input length ");
        r32.append(r12);
        throw new zzrt(r32.toString());
    }

    @Override // com.google.android.recaptcha.internal.zzrv
    public final int zzc(int r3) {
        zzrq r2 = this.zza;
        int r32 = zzrx.zzb(r3, r2.zzd, RoundingMode.CEILING);
        return r2.zzc * r32;
    }

    public final void zzd(Appendable r10, byte[] r11, int r12, int r13) {
        zzoq.zzg(r12, r12 + r13, r11.length);
        zzrq r0 = this.zza;
        int r1 = r0.zzd;
        int r2 = 0;
        if (r13 > r1) goto L5;
        boolean r3 = true;
    L6:
        zzoq.zza(r3);
        long r32 = 0;
        int r5 = 0;
    L8:
        if (r5 >= r13) goto L10;
        r32 = (r32 | (r11[r12 + r5] & UByte.MAX_VALUE)) << 8;
        r5 = r5 + 1;
        goto L8
    L10:
        int r112 = (r13 + 1) * 8;
        int r122 = r0.zzb;
    L12:
        if (r2 >= (r13 * 8)) goto L15;
        r10.append(r0.zza(r0.zza & ((int) (r32 >>> ((r112 - r122) - r2)))));
        r2 = r2 + r122;
        goto L12
    L15:
        if (this.zzb != null) goto L17;
        return;
    L17:
        if (r2 >= (r1 * 8)) goto L23;
        r10.append(SignatureVisitor.INSTANCEOF);
        r2 = r2 + r122;
        goto L17
    L23:
        return;
    L5:
        r3 = false;
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzrv
    public final int zze(int r3) {
        return (int) (((this.zza.zzb * r3) + 7) / 8);
    }

    @Override // com.google.android.recaptcha.internal.zzrv
    public final CharSequence zzf(CharSequence r3) {
        r3.getClass();
        if (this.zzb != null) goto L5;
        return r3;
    L5:
        int r2 = r3.length();
    L6:
        r2 = r2 - 1;
        if (r2 < 0) goto L11;
        if (r3.charAt(r2) == '=') goto L6;
    L11:
        return r3.subSequence(0, r2 + 1);
    }
}
