package com.google.android.recaptcha.internal;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class zzaes extends zzaev {
    private final byte[] zzf;
    private final int zzg;
    private int zzh;
    private int zzi;
    private final int zzj;
    private int zzk;
    private int zzl;

    public /* synthetic */ zzaes(byte[] r1, int r2, int r3, boolean r4, byte[] r5) {
        super(null);
        this.zzl = Integer.MAX_VALUE;
        this.zzf = r1;
        int r32 = r3 + r2;
        this.zzg = r32;
        this.zzh = r32;
        this.zzi = r2;
        this.zzj = r2;
    }

    private final void zzI(int r2) {
        this.zzl = r2;
        int r0 = this.zzg;
        if (r2 <= r0) goto L5;
        r2 = r0;
    L5:
        this.zzh = r2;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzA(int r3) {
        if (r3 < 0) goto L16;
        int r0 = this.zzi;
        int r1 = r0 + r3;
        if (r1 >= 0) goto L11;
        int r02 = r0 - this.zzj;
        r1 = Integer.MAX_VALUE;
        if (r3 <= (Integer.MAX_VALUE - r02)) goto L11;
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return 0;
    L11:
        int r32 = this.zzl;
        if (r1 > r32) goto L15;
        zzI(r1);
        return r32;
    L15:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final void zzB(int r1) {
        zzI(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final boolean zzC() {
        if (this.zzi != this.zzh) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzD() {
        return this.zzi - this.zzj;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zza() {
        if (zzC() == false) goto L6;
        this.zzk = 0;
        return 0;
    L6:
        int r0 = zzt();
        this.zzk = r0;
        if ((r0 >>> 3) == 0) goto L9;
        return r0;
    L9:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final void zzb(int r1) {
        if (this.zzk != 0) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message end-group tag did not match expected tag.");
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final double zzc() {
        return Double.longBitsToDouble(zzz());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final float zzd() {
        return Float.intBitsToFloat(zzy());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zze() {
        return zzw();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzf() {
        return zzw();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzg() {
        return zzu();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzh() {
        return zzz();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzi() {
        return zzy();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final boolean zzj() {
        if (zzw() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final String zzk() {
        int r0 = zzt();
        if (r0 <= 0) goto L9;
        int r1 = this.zzh;
        int r2 = this.zzi;
        if (r0 > (r1 - r2)) goto L9;
        String r3 = new String(this.zzf, r2, r0, StandardCharsets.UTF_8);
        this.zzi += r0;
        return r3;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 >= 0) goto L16;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final String zzl() {
        int r0 = zzt();
        if (r0 <= 0) goto L9;
        int r1 = this.zzh;
        int r2 = this.zzi;
        if (r0 > (r1 - r2)) goto L9;
        String r12 = zzail.zzc(this.zzf, r2, r0);
        this.zzi += r0;
        return r12;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 > 0) goto L16;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final zzaer zzm() {
        int r0 = zzt();
        if (r0 <= 0) goto L8;
        int r2 = this.zzh;
        int r3 = this.zzi;
        if (r0 > (r2 - r3)) goto L8;
        zzaer r1 = zzaer.zzt(this.zzf, r3, r0, false);
        this.zzi += r0;
        return r1;
    L8:
        if (r0 == 0) goto L10;
        if (r0 <= 0) goto L16;
        int r22 = this.zzh;
        int r32 = this.zzi;
        if (r0 > (r22 - r32)) goto L16;
        int r02 = r0 + r32;
        this.zzi = r02;
        return zzaer.zzv(Arrays.copyOfRange(this.zzf, r32, r02), false);
    L16:
        if (r0 > 0) goto L19;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L19:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L10:
        return zzaer.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzn() {
        return zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzo() {
        return zzu();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzp() {
        return zzy();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzq() {
        return zzz();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzr() {
        return zzaev.zzG(zzt());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzs() {
        return zzaev.zzH(zzw());
    }

    public abstract int zzt();

    public abstract int zzu();

    public final int zzv() {
        int r0 = this.zzi;
        int r1 = this.zzh;
        if (r1 == r0) goto L41;
        byte[] r2 = this.zzf;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzi = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L41;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        int r02 = r32 ^ (-128);
    L38:
        this.zzi = r12;
        return r02;
    L14:
        int r42 = r0 + 3;
        int r13 = (r2[r12] << 14) ^ r32;
        if (r13 < 0) goto L18;
        r02 = r13 ^ 16256;
    L17:
        r12 = r42;
        goto L38
    L18:
        int r33 = r0 + 4;
        int r14 = r13 ^ (r2[r42] << 21);
        if (r14 >= 0) goto L22;
        r02 = (-2080896) ^ r14;
    L21:
        r12 = r33;
        goto L38
    L22:
        r42 = r0 + 5;
        byte r34 = r2[r33];
        int r15 = (r14 ^ (r34 << 28)) ^ 266354560;
        if (r34 >= 0) goto L36;
        r33 = r0 + 6;
        if (r2[r42] >= 0) goto L37;
        r42 = r0 + 7;
        if (r2[r33] >= 0) goto L36;
        r33 = r0 + 8;
        if (r2[r42] >= 0) goto L37;
        r42 = r0 + 9;
        if (r2[r33] >= 0) goto L36;
        int r03 = r0 + 10;
        if (r2[r42] < 0) goto L41;
        r12 = r03;
        r02 = r15;
    L37:
        r02 = r15;
    L36:
        r02 = r15;
    L41:
        return (int) zzx();
    }

    public final long zzw() {
        int r0 = this.zzi;
        int r1 = this.zzh;
        if (r1 == r0) goto L44;
        byte[] r2 = this.zzf;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzi = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L44;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        long r22 = r32 ^ (-128);
    L41:
        this.zzi = r12;
        return r22;
    L14:
        int r42 = r0 + 3;
        int r13 = (r2[r12] << 14) ^ r32;
        if (r13 < 0) goto L18;
        r22 = r13 ^ 16256;
    L17:
        r12 = r42;
        goto L41
    L18:
        int r33 = r0 + 4;
        int r14 = r13 ^ (r2[r42] << 21);
        if (r14 >= 0) goto L21;
        long r02 = (-2080896) ^ r14;
        r12 = r33;
        r22 = r02;
        goto L41
    L21:
        r42 = r0 + 5;
        long r5 = (r2[r33] << 28) ^ r14;
        if (r5 < 0) goto L24;
        r22 = r5 ^ 266354560;
        goto L17
    L24:
        r12 = r0 + 6;
        long r34 = (r2[r42] << 35) ^ r5;
        if (r34 >= 0) goto L28;
        long r52 = -34093383808L;
    L27:
        r22 = r34 ^ r52;
        goto L41
    L28:
        int r53 = r0 + 7;
        long r35 = r34 ^ (r2[r12] << 42);
        if (r35 < 0) goto L32;
        long r03 = 4363953127296L;
    L31:
        r22 = r35 ^ r03;
        r12 = r53;
        goto L41
    L32:
        r12 = r0 + 8;
        r34 = r35 ^ (r2[r53] << 49);
        if (r34 >= 0) goto L35;
        r52 = -558586000294016L;
        goto L27
    L35:
        r53 = r0 + 9;
        r35 = r34 ^ (r2[r12] << 56);
        if (r35 < 0) goto L38;
        r03 = 71499008037633920L;
        goto L31
    L38:
        r12 = r0 + 10;
        long r23 = r35 ^ (r2[r53] << 63);
        if (r23 < 0) goto L44;
        r22 = r23 ^ (-9151873028817141888L);
    L44:
        return zzx();
    }

    public final long zzx() {
        int r0 = 0;
        long r1 = 0;
    L4:
        if (r0 >= 64) goto L14;
        int r3 = this.zzi;
        if (r3 == this.zzh) goto L11;
        byte[] r4 = this.zzf;
        this.zzi = r3 + 1;
        r1 = r1 | ((r3 & ByteCompanionObject.MAX_VALUE) << r0);
        if ((r4[r3] & ByteCompanionObject.MIN_VALUE) == 0) goto L9;
        r0 = r0 + 7;
        goto L4
    L9:
        return r1;
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L14:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return 0;
    }

    public final int zzy() {
        int r0 = this.zzi;
        if ((this.zzh - r0) < 4) goto L6;
        byte[] r1 = this.zzf;
        this.zzi = r0 + 4;
        int r4 = r1[r0] & UByte.MAX_VALUE;
        int r2 = r1[r0 + 1] & UByte.MAX_VALUE;
        int r3 = r1[r0 + 2] & UByte.MAX_VALUE;
        int r02 = r1[r0 + 3] & UByte.MAX_VALUE;
        return ((r4 | (r2 << 8)) | (r3 << 16)) | (r02 << 24);
    L6:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public final long zzz() {
        int r1 = this.zzi;
        if ((this.zzh - r1) < 8) goto L6;
        byte[] r2 = this.zzf;
        this.zzi = r1 + 8;
        long r4 = r2[r1];
        long r10 = r2[r1 + 2];
        long r12 = r2[r1 + 3];
        long r14 = r2[r1 + 4];
        long r8 = r2[r1 + 5];
        long r42 = (((r4 & 255) | ((r2[r1 + 1] & 255) << 8)) | ((r10 & 255) << 16)) | ((r12 & 255) << 24);
        long r6 = r8 & 255;
        return (((r2[r1 + 6] & 255) << 48) | ((r42 | ((r14 & 255) << 32)) | (r6 << 40))) | ((r2[r1 + 7] & 255) << 56);
    L6:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }
}
