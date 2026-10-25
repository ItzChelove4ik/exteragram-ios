package com.google.android.gms.internal.measurement;

import com.google.android.recaptcha.internal.zzsj$$ExternalSyntheticBUOutline0;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzacu extends zzacv {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    public /* synthetic */ zzacu(InputStream r1, int r2, byte[] r3) {
        super(null);
        this.zzl = Integer.MAX_VALUE;
        this.zze = r1;
        if (r2 >= 8) goto L5;
        r2 = 8;
    L5:
        this.zzf = new byte[r2];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private final void zzB() {
        int r0 = this.zzg + this.zzh;
        this.zzg = r0;
        int r1 = this.zzk + r0;
        int r2 = this.zzl;
        if (r1 <= r2) goto L6;
        int r12 = r1 - r2;
        this.zzh = r12;
        this.zzg = r0 - r12;
        return;
    L6:
        this.zzh = 0;
    }

    private final void zzC(int r3) {
        if (zzI(r3) == false) goto L5;
        return;
    L5:
        if (r3 <= ((Integer.MAX_VALUE - this.zzk) - this.zzi)) goto L8;
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return;
    L8:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    private final boolean zzI(int r8) {
        int r0 = this.zzi;
        int r1 = r0 + r8;
        int r2 = this.zzg;
        if (r1 <= r2) goto L36;
        int r12 = this.zzk;
        if (r8 <= ((Integer.MAX_VALUE - r12) - r0)) goto L8;
        return false;
    L8:
        if (((r12 + r0) + r8) <= this.zzl) goto L10;
        return false;
    L10:
        if (r0 <= 0) goto L14;
        if (r2 <= r0) goto L13;
        byte[] r13 = this.zzf;
        System.arraycopy(r13, r0, r13, 0, r2 - r0);
    L13:
        r12 = this.zzk + r0;
        this.zzk = r12;
        r2 = this.zzg - r0;
        this.zzg = r2;
        this.zzi = 0;
    L14:
        InputStream r02 = this.zze;
        byte[] r5 = this.zzf;
        int r03 = r02.read(r5, r2, Math.min(r5.length - r2, (Integer.MAX_VALUE - r12) - r2));     // Catch: zzaeh -> L33
        if (r03 != 0) goto L18;
    L31:
        String r7 = String.valueOf(this.zze.getClass());
        StringBuilder r22 = new StringBuilder(((r7.length() + 39) + String.valueOf(r03).length()) + 41);
        r22.append(r7);
        r22.append("#read(byte[]) returned invalid result: ");
        r22.append(r03);
        r22.append("\nThe InputStream implementation is buggy.");
        throw new IllegalStateException(r22.toString());
    L18:
        if (r03 < (-1)) goto L31;
        if (r03 > this.zzf.length) goto L31;
        if (r03 <= 0) goto L30;
        this.zzg += r03;
        zzB();
        if (this.zzg < r8) goto L25;
        return true;
    L25:
        if (zzI(r8) == true) goto L40;
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

    private final byte[] zzJ(int r4, boolean r5) {
        byte[] r52 = zzU(r4);
        if (r52 == null) goto L5;
        return r52;
    L5:
        int r53 = this.zzi;
        int r0 = this.zzg;
        int r1 = r0 - r53;
        this.zzk += r0;
        this.zzi = 0;
        this.zzg = 0;
        List r2 = zzV(r4 - r1);
        byte[] r42 = new byte[r4];
        System.arraycopy(this.zzf, r53, r42, 0, r1);
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

    private final byte[] zzU(int r7) {
        if (r7 == 0) goto L4;
        int r0 = this.zzk;
        int r1 = this.zzi;
        int r2 = (r0 + r1) + r7;
        if (((-2147483647) + r2) > 0) goto L35;
        int r3 = this.zzl;
        if (r2 > r3) goto L34;
        int r02 = this.zzg - r1;
        int r12 = r7 - r02;
        if (r12 >= 4096) goto L36;
    L20:
        byte[] r13 = new byte[r7];
        System.arraycopy(this.zzf, this.zzi, r13, 0, r02);
        this.zzk += this.zzg;
        this.zzi = 0;
        this.zzg = 0;
    L21:
        if (r02 >= r7) goto L33;
        int r22 = this.zze.read(r13, r02, r7 - r02);     // Catch: zzaeh -> L30
        if (r22 == (-1)) goto L27;
        this.zzk += r22;
        r02 = r02 + r22;
        goto L21
    L27:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L30:
        e = move-exception;
        e.zza();
        throw e;
    L33:
        return r13;
    L36:
        if (r12 <= this.zze.available()) goto L20;
        return null;
    L17:
        e = move-exception;
        e.zza();
        throw e;
    L34:
        zzL((r3 - r0) - r1);
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return null;
    L35:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit. If reading multiple messages, consider resetting the counter between each message using CodedInputStream.resetSizeCounter().");
        return null;
    L4:
        return zzaed.zza;
    }

    private final List zzV(int r7) {
        ArrayList r0 = new ArrayList();
    L3:
        if (r7 <= 0) goto L17;
        int r1 = Math.min(r7, 4096);
        byte[] r2 = new byte[r1];
        int r3 = 0;
    L5:
        if (r3 >= r1) goto L16;
        int r4 = this.zze.read(r2, r3, r1 - r3);     // Catch: zzaeh -> L13
        if (r4 == (-1)) goto L11;
        this.zzk += r4;
        r3 = r3 + r4;
        goto L5
    L11:
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
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

    public final byte zzA() {
        if (this.zzi != this.zzg) goto L5;
        zzC(1);
    L5:
        byte[] r0 = this.zzf;
        int r1 = this.zzi;
        this.zzi = r1 + 1;
        return r0[r1];
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzD(int r3) {
        if (r3 < 0) goto L13;
        int r32 = r3 + (this.zzk + this.zzi);
        if (r32 < 0) goto L12;
        int r0 = this.zzl;
        if (r32 > r0) goto L9;
        this.zzl = r32;
        zzB();
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
        this.zzl = r1;
        zzB();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzF() {
        int r0 = this.zzl;
        if (r0 != Integer.MAX_VALUE) goto L7;
        return -1;
    L7:
        return r0 - (this.zzk + this.zzi);
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final boolean zzG() {
        if (this.zzi == this.zzg) goto L5;
        return false;
    L5:
        if (zzI(1) == true) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzH() {
        return this.zzk + this.zzi;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzK(byte[] r4, int r5, int r6) {
        zzacv.zzT(r4, r5, r6);
        if (r6 != 0) goto L6;
        return 0;
    L6:
        int r0 = this.zzg;
        int r1 = this.zzi;
        int r02 = r0 - r1;
        if (r02 <= 0) goto L10;
        int r62 = Math.min(r6, r02);
        System.arraycopy(this.zzf, this.zzi, r4, r5, r62);
        this.zzi += r62;
        return r62;
    L10:
        int r63 = Math.min(r6, (this.zzl - this.zzk) - r1);
        if (r63 > 0) goto L21;
        return -1;
    L21:
        int r42 = this.zze.read(r4, r5, r63);     // Catch: zzaeh -> L18
        if (r42 == (-1)) goto L17;
        this.zzk += r42;
    L17:
        return r42;
    L18:
        e = move-exception;
        e.zza();
        throw e;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final void zzL(int r11) {
        int r0 = this.zzg;
        int r1 = this.zzi;
        int r02 = r0 - r1;
        if (r11 > r02) goto L9;
        if (r11 < 0) goto L9;
        this.zzi = r1 + r11;
        return;
    L9:
        if (r11 < 0) goto L43;
        int r4 = this.zzk;
        int r5 = r4 + r1;
        int r6 = this.zzl;
        if ((r5 + r11) > r6) goto L41;
        this.zzk = r5;
        this.zzg = 0;
        this.zzi = 0;
    L13:
        if (r02 >= r11) goto L33;
    L26:
        th = move-exception;
        this.zzk += r02;
        zzB();
        throw th;
    L15:
        long r42 = r11 - r02;
        long r62 = this.zze.skip(r42);     // Catch: Throwable -> L26 zzaeh -> L28
    L18:
        if (r62 < 0) goto L24;
        if (r62 > r42) goto L24;
        if (r62 == 0) goto L33;
        r02 = r02 + ((int) r62);
    L24:
        String r12 = String.valueOf(this.zze.getClass());     // Catch: Throwable -> L26
        StringBuilder r52 = new StringBuilder(((r12.length() + 31) + String.valueOf(r62).length()) + 41);     // Catch: Throwable -> L26
        r52.append(r12);     // Catch: Throwable -> L26
        r52.append("#skip returned invalid result: ");     // Catch: Throwable -> L26
        r52.append(r62);     // Catch: Throwable -> L26
        r52.append("\nThe InputStream implementation is buggy.");     // Catch: Throwable -> L26
        throw new IllegalStateException(r52.toString());     // Catch: Throwable -> L26
    L28:
        e = move-exception;
        e.zza();     // Catch: Throwable -> L26
        throw e;     // Catch: Throwable -> L26
    L33:
        this.zzk += r02;
        zzB();
        if (r02 >= r11) goto L53;
        int r03 = this.zzg;
        int r13 = r03 - this.zzi;
        this.zzi = r03;
        zzC(1);
    L36:
        int r2 = r11 - r13;
        int r3 = this.zzg;
        if (r2 <= r3) goto L39;
        r13 = r13 + r3;
        this.zzi = r3;
        zzC(1);
        goto L36
    L39:
        this.zzi = r2;
        return;
    L53:
        return;
    L41:
        zzL((r6 - r4) - r1);
        zzmw$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return;
    L43:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zza() {
        if (zzG() == false) goto L6;
        this.zzj = 0;
        return 0;
    L6:
        int r0 = zzx();
        this.zzj = r0;
        if ((r0 >>> 3) == 0) goto L9;
        return r0;
    L9:
        zzmw$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final void zzb(int r1) {
        if (this.zzj != r1) goto L5;
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
        zzL(zzx());
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
        if ((this.zzg - this.zzi) < 10) goto L33;
    L27:
        if (r1 >= 10) goto L32;
        byte[] r72 = this.zzf;
        int r4 = this.zzi;
        this.zzi = r4 + 1;
        if (r72[r4] >= 0) goto L37;
        r1 = r1 + 1;
    L37:
        return true;
    L32:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return false;
    L33:
        if (r1 >= 10) goto L38;
        if (zzA() >= 0) goto L37;
        r1 = r1 + 1;
        goto L33
    L38:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final double zzd() {
        return Double.longBitsToDouble(zzy());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final float zze() {
        return Float.intBitsToFloat(zzw());
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
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzi() {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzj() {
        return zzw();
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
        int r0 = zzx();
        if (r0 <= 0) goto L9;
        int r1 = this.zzg;
        int r2 = this.zzi;
        if (r0 > (r1 - r2)) goto L9;
        String r3 = new String(this.zzf, r2, r0, StandardCharsets.UTF_8);
        this.zzi += r0;
        return r3;
    L9:
        if (r0 != 0) goto L12;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L12:
        if (r0 >= 0) goto L14;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L14:
        if (r0 > this.zzg) goto L18;
        zzC(r0);
        String r22 = new String(this.zzf, this.zzi, r0, StandardCharsets.UTF_8);
        this.zzi += r0;
        return r22;
    L18:
        return new String(zzJ(r0, false), StandardCharsets.UTF_8);
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final String zzm() {
        int r0 = zzx();
        int r1 = this.zzi;
        int r2 = this.zzg;
        if (r0 > (r2 - r1)) goto L6;
        if (r0 <= 0) goto L6;
        byte[] r22 = this.zzf;
        this.zzi = r1 + r0;
    L15:
        return zzagl.zzd(r22, r1, r0);
    L6:
        if (r0 != 0) goto L9;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (r0 < 0) goto L16;
        r1 = 0;
        if (r0 > r2) goto L13;
        zzC(r0);
        r22 = this.zzf;
        this.zzi = r0;
        goto L15
    L13:
        r22 = zzJ(r0, false);
        goto L15
    L16:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final zzacr zzn() {
        int r0 = zzx();
        int r1 = this.zzg;
        int r2 = this.zzi;
        if (r0 > (r1 - r2)) goto L7;
        if (r0 <= 0) goto L7;
        zzacr r12 = zzacr.zzk(this.zzf, r2, r0, false);
        this.zzi += r0;
        return r12;
    L7:
        if (r0 == 0) goto L9;
        if (r0 < 0) goto L24;
        byte[] r13 = zzU(r0);
        if (r13 != null) goto L14;
        int r14 = this.zzi;
        int r22 = this.zzg;
        int r4 = r22 - r14;
        this.zzk += r22;
        this.zzi = 0;
        this.zzg = 0;
        List r23 = zzV(r0 - r4);
        byte[] r02 = new byte[r0];
        System.arraycopy(this.zzf, r14, r02, 0, r4);
        Iterator r6 = r23.iterator();
    L17:
        if (r6.hasNext() == false) goto L26;
        byte[] r15 = (byte[]) r6.next();
        int r24 = r15.length;
        System.arraycopy(r15, 0, r02, r4, r24);
        r4 = r4 + r24;
        goto L17
    L26:
        return zzacr.zzl(r02, false);
    L21:
        e = move-exception;
        throw new AssertionError("Expected no InvalidProtocolBufferException as data UTF8 validity is not checked.", e);
    L14:
        return zzacr.zzk(r13, 0, r13.length, false);
    L24:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L9:
        return zzacr.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final byte[] zzo() {
        int r0 = zzx();
        int r1 = this.zzg;
        int r2 = this.zzi;
        if (r0 > (r1 - r2)) goto L8;
        if (r0 <= 0) goto L8;
        byte[] r12 = Arrays.copyOfRange(this.zzf, r2, r2 + r0);
        this.zzi += r0;
        return r12;
    L8:
        if (r0 >= 0) goto L10;
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return null;
    L10:
        return zzJ(r0, false);
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzp() {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzq() {
        return zzx();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzr() {
        return zzw();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzs() {
        return zzy();
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzt() {
        return zzacv.zzR(zzx());
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzu() {
        return zzacv.zzS(zzz());
    }

    public final long zzv() {
        int r0 = 0;
        long r1 = 0;
    L4:
        if (r0 >= 64) goto L9;
        r1 = r1 | ((r3 & ByteCompanionObject.MAX_VALUE) << r0);
        if ((zzA() & ByteCompanionObject.MIN_VALUE) == 0) goto L7;
        r0 = r0 + 7;
        goto L4
    L7:
        return r1;
    L9:
        zzmw$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered a malformed varint.");
        return 0;
    }

    public final int zzw() {
        int r0 = this.zzi;
        if ((this.zzg - r0) >= 4) goto L5;
        zzC(4);
        r0 = this.zzi;
    L5:
        byte[] r1 = this.zzf;
        this.zzi = r0 + 4;
        int r4 = r1[r0] & UByte.MAX_VALUE;
        int r2 = r1[r0 + 1] & UByte.MAX_VALUE;
        int r3 = r1[r0 + 2] & UByte.MAX_VALUE;
        int r02 = r1[r0 + 3] & UByte.MAX_VALUE;
        return ((r4 | (r2 << 8)) | (r3 << 16)) | (r02 << 24);
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final int zzx() {
        int r0 = this.zzi;
        int r1 = this.zzg;
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
        return (int) zzv();
    }

    public final long zzy() {
        int r1 = this.zzi;
        if ((this.zzg - r1) >= 8) goto L5;
        zzC(8);
        r1 = this.zzi;
    L5:
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
    }

    @Override // com.google.android.gms.internal.measurement.zzacv
    public final long zzz() {
        int r0 = this.zzi;
        int r1 = this.zzg;
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
        return zzv();
    }
}
