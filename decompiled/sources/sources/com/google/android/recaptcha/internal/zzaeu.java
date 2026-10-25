package com.google.android.recaptcha.internal;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
final class zzaeu extends zzaev {
    private final InputStream zzf;
    private final byte[] zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;

    public /* synthetic */ zzaeu(InputStream r1, int r2, byte[] r3) {
        super(null);
        this.zzm = Integer.MAX_VALUE;
        this.zzf = r1;
        this.zzg = new byte[4096];
        this.zzh = 0;
        this.zzj = 0;
        this.zzl = 0;
    }

    private final void zzI(int r3) {
        if (zzJ(r3) == false) goto L5;
        return;
    L5:
        if (r3 <= ((Integer.MAX_VALUE - this.zzl) - this.zzj)) goto L8;
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return;
    L8:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    private final boolean zzJ(int r8) {
        int r0 = this.zzj;
        int r1 = r0 + r8;
        int r2 = this.zzh;
        if (r1 <= r2) goto L36;
        int r12 = this.zzl;
        int r4 = r12 + r0;
        if (zzN(r4, r8, Integer.MAX_VALUE) == false) goto L8;
        return false;
    L8:
        if (zzN(r4, r8, this.zzm) == false) goto L10;
        return false;
    L10:
        if (r0 <= 0) goto L38;
        if (r2 <= r0) goto L13;
        byte[] r13 = this.zzg;
        System.arraycopy(r13, r0, r13, 0, r2 - r0);
    L13:
        r12 = this.zzl + r0;
        this.zzl = r12;
        r2 = this.zzh - r0;
        this.zzh = r2;
        this.zzj = 0;
    L38:
        int r02 = this.zzf.read(this.zzg, r2, Math.min(4096 - r2, (Integer.MAX_VALUE - r12) - r2));     // Catch: zzagk -> L33
        if (r02 != 0) goto L18;
    L31:
        String r7 = String.valueOf(this.zzf.getClass());
        StringBuilder r22 = new StringBuilder(((r7.length() + 39) + String.valueOf(r02).length()) + 41);
        r22.append(r7);
        r22.append("#read(byte[]) returned invalid result: ");
        r22.append(r02);
        r22.append("\nThe InputStream implementation is buggy.");
        throw new IllegalStateException(r22.toString());
    L18:
        if (r02 < (-1)) goto L31;
        if (r02 > 4096) goto L31;
        if (r02 <= 0) goto L30;
        this.zzh += r02;
        zzz();
        if (this.zzh < r8) goto L25;
        return true;
    L25:
        if (zzJ(r8) == true) goto L40;
        return false;
    L40:
        return true;
    L30:
        return false;
    L33:
        e = move-exception;
        e.zza();
        throw e;
    L36:
        zzsj$$ExternalSyntheticBUOutline0.m(String.valueOf(r8).length() + 66, "refillBuffer() called when ", r8, " bytes were already available in buffer");
        return false;
    }

    private final byte[] zzK(int r4, boolean r5) {
        byte[] r52 = zzL(r4);
        if (r52 == null) goto L5;
        return r52;
    L5:
        int r53 = this.zzj;
        int r0 = this.zzh;
        int r1 = r0 - r53;
        this.zzl += r0;
        this.zzj = 0;
        this.zzh = 0;
        List r2 = zzM(r4 - r1);
        byte[] r42 = new byte[r4];
        System.arraycopy(this.zzg, r53, r42, 0, r1);
        Iterator r3 = r2.iterator();
    L7:
        if (r3.hasNext() == false) goto L9;
        byte[] r54 = (byte[]) r3.next();
        int r22 = r54.length;
        System.arraycopy(r54, 0, r42, r1, r22);
        r1 = r1 + r22;
        goto L7
    L9:
        return r42;
    }

    private final byte[] zzL(int r7) {
        if (r7 == 0) goto L4;
        int r0 = this.zzl;
        int r1 = this.zzj;
        int r02 = r0 + r1;
        if (zzN(r02, r7, Integer.MAX_VALUE) == true) goto L37;
        int r2 = this.zzm;
        if (zzN(r02, r7, r2) == false) goto L14;
        if (r2 < r02) goto L11;
        zzy(r2 - r02);
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L14:
        int r03 = this.zzh - r1;
        int r12 = r7 - r03;
        if (r12 >= 4096) goto L40;
    L25:
        byte[] r13 = new byte[r7];
        System.arraycopy(this.zzg, this.zzj, r13, 0, r03);
        this.zzl += this.zzh;
        this.zzj = 0;
        this.zzh = 0;
    L26:
        if (r03 >= r7) goto L36;
        int r22 = this.zzf.read(r13, r03, r7 - r03);     // Catch: zzagk -> L33
        if (r22 == (-1)) goto L32;
        this.zzl += r22;
        r03 = r03 + r22;
        goto L26
    L32:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L33:
        e = move-exception;
        e.zza();
        throw e;
    L36:
        return r13;
    L40:
        if (r12 <= this.zzf.available()) goto L25;
        return null;
    L22:
        e = move-exception;
        e.zza();
        throw e;
    L37:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return null;
    L4:
        return zzagf.zza;
    }

    private final List zzM(int r7) {
        ArrayList r0 = new ArrayList();
    L3:
        if (r7 <= 0) goto L17;
        int r1 = Math.min(r7, 4096);
        byte[] r2 = new byte[r1];
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L16;
        int r4 = this.zzf.read(r2, r3, r1 - r3);     // Catch: zzagk -> L13
        if (r4 == (-1)) goto L11;
        this.zzl += r4;
        r3 = r3 + r4;
        goto L5
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L13:
        e = move-exception;
        e.zza();
        throw e;
    L16:
        r7 = r7 - r1;
        r0.add(r2);
        goto L3
    L17:
        return r0;
    }

    private static boolean zzN(int r0, int r1, int r2) {
        if (r2 >= r0) goto L4;
        return true;
    L4:
        if (r1 > (r2 - r0)) goto L10;
        return false;
    L10:
        return true;
    }

    private final void zzz() {
        int r0 = this.zzh + this.zzi;
        this.zzh = r0;
        int r1 = this.zzl + r0;
        int r2 = this.zzm;
        if (r1 <= r2) goto L6;
        int r12 = r1 - r2;
        this.zzi = r12;
        this.zzh = r0 - r12;
        return;
    L6:
        this.zzi = 0;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzA(int r4) {
        if (r4 < 0) goto L13;
        int r0 = this.zzl + this.zzj;
        if (r4 > (Integer.MAX_VALUE - r0)) goto L12;
        int r1 = this.zzm;
        if (zzN(r0, r4, r1) == true) goto L9;
        this.zzm = r0 + r4;
        zzz();
        return r1;
    L9:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L12:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return 0;
    L13:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final void zzB(int r1) {
        this.zzm = r1;
        zzz();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final boolean zzC() {
        if (this.zzj == this.zzh) goto L5;
        return false;
    L5:
        if (zzJ(1) == true) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzD() {
        return this.zzl + this.zzj;
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
        return Double.longBitsToDouble(zzx());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final float zzd() {
        return Float.intBitsToFloat(zzw());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zze() {
        return zzu();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzf() {
        return zzu();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzg() {
        return zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzh() {
        return zzx();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzi() {
        return zzw();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final boolean zzj() {
        if (zzu() == 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final String zzk() {
        int r0 = zzt();
        if (r0 <= 0) goto L9;
        int r1 = this.zzh;
        int r2 = this.zzj;
        if (r0 > (r1 - r2)) goto L9;
        String r3 = new String(this.zzg, r2, r0, StandardCharsets.UTF_8);
        this.zzj += r0;
        return r3;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 >= 0) goto L14;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L14:
        if (r0 > this.zzh) goto L18;
        zzI(r0);
        String r22 = new String(this.zzg, this.zzj, r0, StandardCharsets.UTF_8);
        this.zzj += r0;
        return r22;
    L18:
        return new String(zzK(r0, false), StandardCharsets.UTF_8);
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final String zzl() {
        int r0 = zzt();
        int r1 = this.zzj;
        int r2 = this.zzh;
        if (r0 > (r2 - r1)) goto L6;
        if (r0 <= 0) goto L6;
        byte[] r22 = this.zzg;
        this.zzj = r1 + r0;
    L15:
        return zzail.zzc(r22, r1, r0);
    L6:
        if (r0 != 0) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r0 < 0) goto L16;
        r1 = 0;
        if (r0 > r2) goto L13;
        zzI(r0);
        r22 = this.zzg;
        this.zzj = r0;
        goto L15
    L13:
        r22 = zzK(r0, false);
        goto L15
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final zzaer zzm() {
        int r0 = zzt();
        int r1 = this.zzh;
        int r2 = this.zzj;
        if (r0 > (r1 - r2)) goto L7;
        if (r0 <= 0) goto L7;
        zzaer r12 = zzaer.zzt(this.zzg, r2, r0, false);
        this.zzj += r0;
        return r12;
    L7:
        if (r0 == 0) goto L9;
        if (r0 < 0) goto L21;
        byte[] r13 = zzL(r0);
        if (r13 != null) goto L14;
        int r14 = this.zzj;
        int r22 = this.zzh;
        int r4 = r22 - r14;
        this.zzl += r22;
        this.zzj = 0;
        this.zzh = 0;
        List r23 = zzM(r0 - r4);
        byte[] r02 = new byte[r0];
        System.arraycopy(this.zzg, r14, r02, 0, r4);
        Iterator r6 = r23.iterator();
    L17:
        if (r6.hasNext() == false) goto L20;
        byte[] r15 = (byte[]) r6.next();
        int r24 = r15.length;
        System.arraycopy(r15, 0, r02, r4, r24);
        r4 = r4 + r24;
        goto L17
    L20:
        return zzaer.zzu(r02);
    L14:
        return zzaer.zzt(r13, 0, r13.length, false);
    L21:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L9:
        return zzaer.zza;
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzn() {
        return zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzo() {
        return zzt();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzp() {
        return zzw();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzq() {
        return zzx();
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final int zzr() {
        return zzaev.zzG(zzt());
    }

    @Override // com.google.android.recaptcha.internal.zzaev
    public final long zzs() {
        return zzaev.zzH(zzu());
    }

    public final int zzt() {
        int r0 = this.zzj;
        int r1 = this.zzh;
        if (r1 == r0) goto L41;
        byte[] r2 = this.zzg;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzj = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L41;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        int r02 = r32 ^ (-128);
    L38:
        this.zzj = r12;
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
        return (int) zzv();
    }

    public final long zzu() {
        int r0 = this.zzj;
        int r1 = this.zzh;
        if (r1 == r0) goto L44;
        byte[] r2 = this.zzg;
        int r3 = r0 + 1;
        byte r4 = r2[r0];
        if (r4 < 0) goto L10;
        this.zzj = r3;
        return r4;
    L10:
        if ((r1 - r3) < 9) goto L44;
        int r12 = r0 + 2;
        int r32 = (r2[r3] << 7) ^ r4;
        if (r32 >= 0) goto L14;
        long r22 = r32 ^ (-128);
    L41:
        this.zzj = r12;
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
        return zzv();
    }

    public final long zzv() {
        int r0 = 0;
        long r1 = 0;
    L4:
        if (r0 >= 64) goto L12;
        if (this.zzj != this.zzh) goto L8;
        zzI(1);
    L8:
        byte[] r3 = this.zzg;
        int r4 = this.zzj;
        this.zzj = r4 + 1;
        r1 = r1 | ((r3 & ByteCompanionObject.MAX_VALUE) << r0);
        if ((r3[r4] & ByteCompanionObject.MIN_VALUE) == 0) goto L10;
        r0 = r0 + 7;
        goto L4
    L10:
        return r1;
    L12:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return 0;
    }

    public final int zzw() {
        int r0 = this.zzj;
        if ((this.zzh - r0) >= 4) goto L5;
        zzI(4);
        r0 = this.zzj;
    L5:
        byte[] r1 = this.zzg;
        this.zzj = r0 + 4;
        int r4 = r1[r0] & UByte.MAX_VALUE;
        int r2 = r1[r0 + 1] & UByte.MAX_VALUE;
        int r3 = r1[r0 + 2] & UByte.MAX_VALUE;
        int r02 = r1[r0 + 3] & UByte.MAX_VALUE;
        return ((r4 | (r2 << 8)) | (r3 << 16)) | (r02 << 24);
    }

    public final long zzx() {
        int r1 = this.zzj;
        if ((this.zzh - r1) >= 8) goto L5;
        zzI(8);
        r1 = this.zzj;
    L5:
        byte[] r2 = this.zzg;
        this.zzj = r1 + 8;
        long r4 = r2[r1];
        long r10 = r2[r1 + 2];
        long r12 = r2[r1 + 3];
        long r14 = r2[r1 + 4];
        long r8 = r2[r1 + 5];
        long r42 = (((r4 & 255) | ((r2[r1 + 1] & 255) << 8)) | ((r10 & 255) << 16)) | ((r12 & 255) << 24);
        long r6 = r8 & 255;
        return (((r2[r1 + 6] & 255) << 48) | ((r42 | ((r14 & 255) << 32)) | (r6 << 40))) | ((r2[r1 + 7] & 255) << 56);
    }

    public final void zzy(int r11) {
        int r0 = this.zzh;
        int r1 = this.zzj;
        int r02 = r0 - r1;
        if (r11 > r02) goto L9;
        if (r11 < 0) goto L9;
        this.zzj = r1 + r11;
        return;
    L9:
        if (r11 < 0) goto L49;
        int r4 = this.zzl + r1;
        if (zzN(r4, r11, Integer.MAX_VALUE) == true) goto L47;
        int r12 = this.zzm;
        if (zzN(r4, r11, r12) == false) goto L18;
        if (r12 < r4) goto L16;
        zzy(r12 - r4);
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return;
    L18:
        this.zzl = r4;
        this.zzh = 0;
        this.zzj = 0;
    L19:
        if (r02 >= r11) goto L39;
    L32:
        th = move-exception;
        this.zzl += r02;
        zzz();
        throw th;
    L21:
        long r42 = r11 - r02;
        long r6 = this.zzf.skip(r42);     // Catch: Throwable -> L32 zzagk -> L34
    L24:
        if (r6 < 0) goto L30;
        if (r6 > r42) goto L30;
        if (r6 == 0) goto L39;
        r02 = r02 + ((int) r6);
    L30:
        String r13 = String.valueOf(this.zzf.getClass());     // Catch: Throwable -> L32
        StringBuilder r5 = new StringBuilder(((r13.length() + 31) + String.valueOf(r6).length()) + 41);     // Catch: Throwable -> L32
        r5.append(r13);     // Catch: Throwable -> L32
        r5.append("#skip returned invalid result: ");     // Catch: Throwable -> L32
        r5.append(r6);     // Catch: Throwable -> L32
        r5.append("\nThe InputStream implementation is buggy.");     // Catch: Throwable -> L32
        throw new IllegalStateException(r5.toString());     // Catch: Throwable -> L32
    L34:
        e = move-exception;
        e.zza();     // Catch: Throwable -> L32
        throw e;     // Catch: Throwable -> L32
    L39:
        this.zzl += r02;
        zzz();
        if (r02 >= r11) goto L59;
        int r03 = this.zzh;
        int r14 = r03 - this.zzj;
        this.zzj = r03;
        zzI(1);
    L42:
        int r2 = r11 - r14;
        int r3 = this.zzh;
        if (r2 <= r3) goto L45;
        r14 = r14 + r3;
        this.zzj = r3;
        zzI(1);
        goto L42
    L45:
        this.zzj = r2;
        return;
    L59:
        return;
    L47:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return;
    L49:
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}
