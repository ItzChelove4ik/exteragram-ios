package com.google.android.gms.internal.measurement;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzacs extends zzacv {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    public /* synthetic */ zzacs(byte[] r1, int r2, int r3, boolean r4, byte[] r5) {
        super(null);
        this.zzj = Integer.MAX_VALUE;
        this.zze = r1;
        this.zzf = 0;
        this.zzh = 0;
    }

    private final void zzU() {
        int r0 = this.zzf + this.zzg;
        this.zzf = r0;
        int r1 = this.zzj;
        if (r0 <= r1) goto L6;
        int r12 = r0 - r1;
        this.zzg = r12;
        this.zzf = r0 - r12;
        return;
    L6:
        this.zzg = 0;
    }

    public final long zzA() {
        int r0 = 0;
        long r1 = 0;
    L4:
        if (r0 >= 64) goto L9;
        r1 = r1 | ((r3 & ByteCompanionObject.MAX_VALUE) << r0);
        if ((zzI() & ByteCompanionObject.MIN_VALUE) == 0) goto L7;
        r0 = r0 + 7;
        goto L4
    L7:
        return r1;
    L9:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return 0;
    }

    public final int zzB() {
        int r0 = this.zzh;
        if ((this.zzf - r0) < 4) goto L6;
        byte[] r1 = this.zze;
        this.zzh = r0 + 4;
        int r4 = r1[r0] & UByte.MAX_VALUE;
        int r2 = r1[r0 + 1] & UByte.MAX_VALUE;
        int r3 = r1[r0 + 2] & UByte.MAX_VALUE;
        int r02 = r1[r0 + 3] & UByte.MAX_VALUE;
        return ((r4 | (r2 << 8)) | (r3 << 16)) | (r02 << 24);
    L6:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public final long zzC() {
        int r1 = this.zzh;
        if ((this.zzf - r1) < 8) goto L6;
        byte[] r2 = this.zze;
        this.zzh = r1 + 8;
        long r4 = r2[r1];
        long r10 = r2[r1 + 2];
        long r12 = r2[r1 + 3];
        long r14 = r2[r1 + 4];
        long r8 = r2[r1 + 5];
        long r42 = (((r4 & 255) | ((r2[r1 + 1] & 255) << 8)) | ((r10 & 255) << 16)) | ((r12 & 255) << 24);
        long r6 = r8 & 255;
        return (((r2[r1 + 6] & 255) << 48) | ((r42 | ((r14 & 255) << 32)) | (r6 << 40))) | ((r2[r1 + 7] & 255) << 56);
    L6:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzD(int r2) {
        if (r2 < 0) goto L13;
        int r22 = r2 + this.zzh;
        if (r22 < 0) goto L12;
        int r0 = this.zzj;
        if (r22 > r0) goto L9;
        this.zzj = r22;
        zzU();
        return r0;
    L9:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L12:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return 0;
    L13:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final void zzE(int r1) {
        this.zzj = r1;
        zzU();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzF() {
        int r0 = this.zzj;
        if (r0 != Integer.MAX_VALUE) goto L7;
        return -1;
    L7:
        return r0 - this.zzh;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final boolean zzG() {
        if (this.zzh != this.zzf) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzH() {
        return this.zzh;
    }

    public final byte zzI() {
        int r0 = this.zzh;
        if (r0 == this.zzf) goto L6;
        byte[] r1 = this.zze;
        this.zzh = r0 + 1;
        return r1[r0];
    L6:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public final byte[] zzJ(int r3) {
        if (r3 <= 0) goto L8;
        int r0 = this.zzf;
        int r1 = this.zzh;
        if (r3 > (r0 - r1)) goto L8;
        int r32 = r3 + r1;
        this.zzh = r32;
        return Arrays.copyOfRange(this.zze, r1, r32);
    L8:
        if (r3 > 0) goto L14;
        if (r3 == 0) goto L11;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L11:
        return zzaed.zza;
    L14:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzK(byte[] r3, int r4, int r5) {
        zzacv.zzT(r3, r4, r5);
        if (r5 != 0) goto L6;
        return 0;
    L6:
        int r52 = Math.min(r5, this.zzf - this.zzh);
        if (r52 != 0) goto L10;
        return -1;
    L10:
        System.arraycopy(this.zze, this.zzh, r3, r4, r52);
        this.zzh += r52;
        return r52;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final void zzL(int r3) {
        if (r3 < 0) goto L8;
        int r0 = this.zzf;
        int r1 = this.zzh;
        if (r3 > (r0 - r1)) goto L8;
        this.zzh = r1 + r3;
        return;
    L8:
        if (r3 >= 0) goto L11;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return;
    L11:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zza() {
        if (zzG() == false) goto L6;
        this.zzi = 0;
        return 0;
    L6:
        int r0 = zzv();
        this.zzi = r0;
        if ((r0 >>> 3) == 0) goto L9;
        return r0;
    L9:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final void zzb(int r1) {
        if (this.zzi != r1) goto L5;
        return;
    L5:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message end-group tag did not match expected tag.");
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final boolean zzc(int r7) {
        int r0 = r7 & 7;
        int r1 = 0;
        if (r0 == 0) goto L26;
        if (r0 != 1) goto L6;
        zzL(8);
        return true;
    L6:
        if (r0 != 2) goto L8;
        zzL(zzv());
        return true;
    L8:
        if (r0 == 3) goto L19;
        if (r0 != 4) goto L11;
        zzP();
        return false;
    L11:
        if (r0 != 5) goto L14;
        zzL(4);
        return true;
    L14:
        zzacs$$ExternalSyntheticBUOutline0.m();
        return false;
    L19:
        zzQ();
        zzb(((r7 >>> 3) << 3) | 4);
        return true;
    L26:
        if ((this.zzf - this.zzh) < 10) goto L33;
    L27:
        if (r1 >= 10) goto L32;
        byte[] r72 = this.zze;
        int r4 = this.zzh;
        this.zzh = r4 + 1;
        if (r72[r4] >= 0) goto L37;
        r1 = r1 + 1;
    L37:
        return true;
    L32:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return false;
    L33:
        if (r1 >= 10) goto L38;
        if (zzI() >= 0) goto L37;
        r1 = r1 + 1;
        goto L33
    L38:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final double zzd() {
        return Double.longBitsToDouble(zzC());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final float zze() {
        return Float.intBitsToFloat(zzB());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzf() {
        return zzz();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzg() {
        return zzz();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzh() {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzi() {
        return zzC();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzj() {
        return zzB();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final boolean zzk() {
        if (zzz() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final String zzl() {
        int r0 = zzv();
        if (r0 <= 0) goto L9;
        int r1 = this.zzf;
        int r2 = this.zzh;
        if (r0 > (r1 - r2)) goto L9;
        String r3 = new String(this.zze, r2, r0, StandardCharsets.UTF_8);
        this.zzh += r0;
        return r3;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 >= 0) goto L16;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L16:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final String zzm() {
        int r0 = zzv();
        if (r0 <= 0) goto L9;
        int r1 = this.zzf;
        int r2 = this.zzh;
        if (r0 > (r1 - r2)) goto L9;
        String r12 = zzagl.zzd(this.zze, r2, r0);
        this.zzh += r0;
        return r12;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 > 0) goto L16;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L16:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final zzacr zzn() {
        int r0 = zzv();
        if (r0 <= 0) goto L8;
        int r2 = this.zzf;
        int r3 = this.zzh;
        if (r0 > (r2 - r3)) goto L8;
        zzacr r1 = zzacr.zzk(this.zze, r3, r0, false);
        this.zzh += r0;
        return r1;
    L8:
        if (r0 != 0) goto L12;
        return zzacr.zza;
    L12:
        return zzacr.zzl(zzJ(r0), false);
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final byte[] zzo() {
        return zzJ(zzv());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzp() {
        return zzv();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzq() {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzr() {
        return zzB();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzs() {
        return zzC();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzt() {
        return zzacv.zzR(zzv());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzu() {
        return zzacv.zzS(zzz());
    }

    public abstract int zzv();

    public abstract int zzw();

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzx() {
        return zzw();
    }

    public final int zzy() {
        int r0 = this.zzh;
        int r1 = this.zzf;
        if (r1 == r0) goto L41;
        byte[] r2 = this.zze;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzh = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L41;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        int r02 = r32 ^ (-128);
    L38:
        this.zzh = r12;
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
        return (int) zzA();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzz() {
        int r0 = this.zzh;
        int r1 = this.zzf;
        if (r1 == r0) goto L44;
        byte[] r2 = this.zze;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzh = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L44;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        long r22 = r32 ^ (-128);
    L41:
        this.zzh = r12;
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
        return zzA();
    }
}
