package com.google.android.gms.internal.fido;

import com.android.dex.EncodedValueReader$$ExternalSyntheticBUOutline0;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzhs implements Closeable {
    private final InputStream zza;
    private zzhr zzb;
    private final byte[] zzc;
    private final zzht zzd;

    public zzhs(InputStream r2) {
        this.zzc = new byte[8];
        this.zzd = zzht.zza();
        this.zza = r2;
    }

    private final long zzh() {
        byte r1 = this.zzb.zza();
        zzhr r2 = this.zzb;
        if (r1 >= 24) goto L7;
        long r12 = r2.zza();
        this.zzb = null;
        return r12;
    L7:
        if (r2.zza() != 24) goto L15;
        int r13 = this.zza.read();
        if (r13 == (-1)) goto L12;
        this.zzb = null;
        return r13 & 255;
    L12:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L15:
        if (this.zzb.zza() != 25) goto L19;
        zzk(this.zzc, 2);
        byte[] r0 = this.zzc;
        return ((r0[0] & 255) << 8) | (r0[1] & 255);
    L19:
        if (this.zzb.zza() != 26) goto L23;
        zzk(this.zzc, 4);
        byte[] r02 = this.zzc;
        return ((((r02[0] & 255) << 24) | ((r02[1] & 255) << 16)) | ((r02[2] & 255) << 8)) | (r02[3] & 255);
    L23:
        if (this.zzb.zza() != 27) goto L27;
        zzk(this.zzc, 8);
        byte[] r03 = this.zzc;
        long r14 = r03[0];
        long r132 = r03[1];
        long r7 = r03[2];
        long r4 = r03[3];
        long r9 = r03[4];
        long r6 = r03[5];
        long r11 = r03[6];
        long r8 = r03[7];
        return ((((((((r14 & 255) << 56) | ((r132 & 255) << 48)) | ((r7 & 255) << 40)) | ((r4 & 255) << 32)) | ((r9 & 255) << 24)) | ((r6 & 255) << 16)) | ((r11 & 255) << 8)) | (r8 & 255);
    L27:
        throw new IOException(String.format("invalid additional information %s for major type %s", new Object[]{Byte.valueOf(this.zzb.zza()), Integer.valueOf(this.zzb.zzc())}));
    }

    private final void zzi() {
        zzd();
        if (this.zzb.zza() == 31) goto L5;
        return;
    L5:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("expected definite length but found %s", new Object[]{Byte.valueOf(this.zzb.zza())});
    }

    private final void zzj(byte r2) {
        zzd();
        if (this.zzb.zzb() != r2) goto L5;
        return;
    L5:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("expected major type %s but found %s", new Object[]{Integer.valueOf((r2 >> 5) & 7), Integer.valueOf(this.zzb.zzc())});
    }

    private final void zzk(byte[] r4, int r5) {
        int r0 = 0;
    L3:
        if (r0 == r5) goto L9;
        int r1 = this.zza.read(r4, r0, r5 - r0);
        if (r1 == (-1)) goto L7;
        r0 = r0 + r1;
        goto L3
    L7:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    L9:
        this.zzb = null;
    }

    private final byte[] zzl() {
        zzi();
        long r0 = zzh();
        if (r0 < 0) goto L13;
        if (r0 > 2147483647L) goto L13;
        if (this.zza.available() < r0) goto L10;
        int r02 = (int) r0;
        byte[] r1 = new byte[r02];
        zzk(r1, r02);
        return r1;
    L10:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return null;
    L13:
        throw new UnsupportedOperationException(String.format("the maximum supported byte/text string length is %s bytes", new Object[]{Integer.MAX_VALUE}));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.zza.close();
        this.zzd.zzb();
    }

    public final long zza() {
        zzj(ByteCompanionObject.MIN_VALUE);
        zzi();
        long r0 = zzh();
        if (r0 < 0) goto L8;
        if (r0 <= 0) goto L6;
        this.zzd.zzg(r0);
    L6:
        return r0;
    L8:
        throw new UnsupportedOperationException(String.format("the maximum supported array length is %s", new Object[]{Long.valueOf(LongCompanionObject.MAX_VALUE)}));
    }

    public final long zzb() {
        zzd();
        if (this.zzb.zzb() != 0) goto L6;
        boolean r0 = true;
    L8:
        long r3 = zzh();
        if (r3 < 0) goto L15;
        if (r0 == false) goto L13;
        return r3;
    L13:
        return ~r3;
    L15:
        throw new UnsupportedOperationException(String.format("the maximum supported unsigned/negative integer is %s", new Object[]{Long.valueOf(LongCompanionObject.MAX_VALUE)}));
    L6:
        if (this.zzb.zzb() != 32) goto L16;
        r0 = false;
        goto L8
    L16:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("expected major type 0 or 1 but found %s", new Object[]{Integer.valueOf(this.zzb.zzc())});
        return 0;
    }

    public final long zzc() {
        zzj((byte) -96);
        zzi();
        long r0 = zzh();
        if (r0 >= 0) goto L5;
    L9:
        ByteString$$ExternalSyntheticBUOutline0.m("the maximum supported map length is 4611686018427387903L");
        return 0;
    L5:
        if (r0 > DurationKt.MAX_MILLIS) goto L9;
        if (r0 <= 0) goto L8;
        this.zzd.zzg(r0 + r0);
    L8:
        return r0;
    }

    public final zzhr zzd() {
        if (this.zzb != null) goto L33;
        int r0 = this.zza.read();
        if (r0 != (-1)) goto L8;
        this.zzd.zzb();
        return null;
    L8:
        zzhr r1 = new zzhr(r0);
        this.zzb = r1;
        byte r02 = r1.zzb();
        if (r02 != Byte.MIN_VALUE) goto L11;
    L30:
        this.zzd.zzd();
    L31:
        this.zzd.zzf();
        goto L33
    L11:
        if (r02 == (-96)) goto L30;
        if (r02 == (-64)) goto L30;
        if (r02 == (-32)) goto L28;
        if (r02 == 0) goto L30;
        if (r02 == 32) goto L30;
        if (r02 != 64) goto L22;
        this.zzd.zze(-1);
        goto L31
    L22:
        if (r02 != 96) goto L24;
        this.zzd.zze(-2);
        goto L31
    L24:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("invalid major type: %s", new Object[]{Integer.valueOf(this.zzb.zzc())});
        return null;
    L28:
        if (this.zzb.zza() != 31) goto L30;
        this.zzd.zzc();
    L33:
        return this.zzb;
    }

    public final String zze() {
        zzj((byte) 96);
        return new String(zzl(), StandardCharsets.UTF_8);
    }

    public final boolean zzf() {
        zzj((byte) -32);
        if (this.zzb.zza() > 24) goto L14;
        int r2 = (int) zzh();
        if (r2 != 20) goto L8;
        return false;
    L8:
        if (r2 != 21) goto L11;
        return true;
    L11:
        EncodedValueReader$$ExternalSyntheticBUOutline0.m("expected FALSE or TRUE", new Object[0]);
        return false;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("expected simple value");
        return false;
    }

    public final byte[] zzg() {
        zzj((byte) 64);
        return zzl();
    }
}
