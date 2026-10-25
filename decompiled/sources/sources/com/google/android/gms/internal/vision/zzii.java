package com.google.android.gms.internal.vision;

import c.f$$ExternalSyntheticBUOutline2;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zzii extends zzhq {
    private static final Logger zzb = null;
    private static final boolean zzc = false;
    zzil zza;

    public static class zza extends zzii {
        private final byte[] zzb;
        private final int zzc;
        private final int zzd;
        private int zze;

        public zza(byte[] r3, int r4, int r5) {
            super(null);
            if (r3 != null) goto L5;
            f$$ExternalSyntheticBUOutline2.m("buffer");
            throw null;
        L5:
            if (((r3.length - r5) | r5) < 0) goto L8;
            this.zzb = r3;
            this.zzc = 0;
            this.zze = 0;
            this.zzd = r5;
            return;
        L8:
            FieldSet$$ExternalSyntheticBUOutline0.m("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(r3.length), 0, Integer.valueOf(r5)});
            throw null;
        }

        private final void zzc(byte[] r3, int r4, int r5) {
            System.arraycopy(r3, r4, this.zzb, this.zze, r5);     // Catch: IndexOutOfBoundsException -> L4
            this.zze += r5;
            return;
        L4:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), Integer.valueOf(r5)}), e);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final int zza() {
            return this.zzd - this.zze;
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(byte r4) {
            byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.zze;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = r4;     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r3) {
            if (r3 < 0) goto L5;
            zzb(r3);
            return;
        L5:
            zza(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r1, int r2) {
            zzb((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r2, long r3) {
            zza(r2, 0);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r2, zzht r3) {
            zza(r2, 2);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r4, zzkk r5) {
            zza(1, 3);
            zzc(2, r4);
            zza(3, 2);
            zza(r5);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r3, zzkk r4, zzlc r5) {
            zza(r3, 2);
            zzhf r32 = (zzhf) r4;
            int r0 = r32.zzi();
            if (r0 != (-1)) goto L5;
            r0 = r5.zzb(r32);
            r32.zzb(r0);
        L5:
            zzb(r0);
            r5.zza(r4, this.zza);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r2, String r3) {
            zza(r2, 2);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(int r2, boolean r3) {
            zza(r2, 0);
            zza(r3 ? 1 : 0);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(long r10) {
            if (zzii.zzc() == false) goto L11;
            if (zza() < 10) goto L11;
        L6:
            long r6 = r10 & (-128);
            byte[] r62 = this.zzb;
            if (r6 == 0) goto L8;
            int r0 = this.zze;
            this.zze = r0 + 1;
            zzma.zza(r62, r0, (byte) ((((int) r10) & 127) | 128));
            r10 = r10 >>> 7;
            goto L6
        L8:
            int r02 = this.zze;
            this.zze = r02 + 1;
            zzma.zza(r62, r02, (byte) r10);
            return;
        L11:
            long r63 = r10 & (-128);
            byte[] r64 = this.zzb;
            if (r63 == 0) goto L13;
            int r03 = this.zze;     // Catch: IndexOutOfBoundsException -> L15
            this.zze = r03 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r64[r03] = (byte) ((((int) r10) & 127) | 128);     // Catch: IndexOutOfBoundsException -> L15
            r10 = r10 >>> 7;
        L15:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
        L13:
            int r04 = this.zze;     // Catch: IndexOutOfBoundsException -> L15
            this.zze = r04 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r64[r04] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L15
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(zzht r2) {
            zzb(r2.zza());
            r2.zza(this);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(zzkk r2) {
            zzb(r2.zzm());
            r2.zza(this);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zza(String r6) {
            int r0 = this.zze;
            int r1 = zzii.zzg(r6.length() * 3);     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            int r2 = zzii.zzg(r6.length());     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            if (r2 != r1) goto L9;
            int r12 = r0 + r2;     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            this.zze = r12;     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            int r13 = zzmd.zza(r6, this.zzb, r12, zza());     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            this.zze = r0;     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            zzb((r13 - r0) - r2);     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            this.zze = r13;     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            return;
        L9:
            zzb(zzmd.zza(r6));     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            this.zze = zzmd.zza(r6, this.zzb, this.zze, zza());     // Catch: zzmg -> L7 IndexOutOfBoundsException -> L11
            return;
        L7:
            e = move-exception;
            this.zze = r0;
            zza(r6, e);
            return;
        L11:
            e = move-exception;
            throw new zzb(e);
        }

        @Override // com.google.android.gms.internal.vision.zzhq
        public final void zza(byte[] r1, int r2, int r3) {
            zzc(r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzb(int r6) {
            if (zzii.zzc() == false) goto L26;
            if (zzhi.zza() == true) goto L26;
            if (zza() < 5) goto L26;
            int r0 = r6 & (-128);
            byte[] r1 = this.zzb;
            if (r0 != 0) goto L12;
            int r02 = this.zze;
            this.zze = r02 + 1;
            zzma.zza(r1, r02, (byte) r6);
            return;
        L12:
            int r03 = this.zze;
            this.zze = r03 + 1;
            zzma.zza(r1, r03, (byte) (r6 | 128));
            int r04 = r6 >>> 7;
            int r12 = r04 & (-128);
            byte[] r2 = this.zzb;
            if (r12 != 0) goto L16;
            int r62 = this.zze;
            this.zze = r62 + 1;
            zzma.zza(r2, r62, (byte) r04);
            return;
        L16:
            int r13 = this.zze;
            this.zze = r13 + 1;
            zzma.zza(r2, r13, (byte) (r04 | 128));
            int r05 = r6 >>> 14;
            int r14 = r05 & (-128);
            byte[] r22 = this.zzb;
            if (r14 != 0) goto L20;
            int r63 = this.zze;
            this.zze = r63 + 1;
            zzma.zza(r22, r63, (byte) r05);
            return;
        L20:
            int r15 = this.zze;
            this.zze = r15 + 1;
            zzma.zza(r22, r15, (byte) (r05 | 128));
            int r06 = r6 >>> 21;
            int r16 = r06 & (-128);
            byte[] r23 = this.zzb;
            if (r16 != 0) goto L24;
            int r64 = this.zze;
            this.zze = r64 + 1;
            zzma.zza(r23, r64, (byte) r06);
            return;
        L24:
            int r17 = this.zze;
            this.zze = r17 + 1;
            zzma.zza(r23, r17, (byte) (r06 | 128));
            byte[] r07 = this.zzb;
            int r18 = this.zze;
            this.zze = r18 + 1;
            zzma.zza(r07, r18, (byte) (r6 >>> 28));
            return;
        L26:
            int r08 = r6 & (-128);
            byte[] r19 = this.zzb;
            if (r08 == 0) goto L28;
            int r09 = this.zze;     // Catch: IndexOutOfBoundsException -> L30
            this.zze = r09 + 1;     // Catch: IndexOutOfBoundsException -> L30
            r19[r09] = (byte) ((r6 & 127) | 128);     // Catch: IndexOutOfBoundsException -> L30
            r6 = r6 >>> 7;
        L30:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
        L28:
            int r010 = this.zze;     // Catch: IndexOutOfBoundsException -> L30
            this.zze = r010 + 1;     // Catch: IndexOutOfBoundsException -> L30
            r19[r010] = (byte) r6;     // Catch: IndexOutOfBoundsException -> L30
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzb(int r2, int r3) {
            zza(r2, 0);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzb(int r4, zzht r5) {
            zza(1, 3);
            zzc(2, r4);
            zza(3, r5);
            zza(1, 4);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzc(int r2, int r3) {
            zza(r2, 0);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzc(int r2, long r3) {
            zza(r2, 1);
            zzc(r3);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzc(long r8) {
            byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.zze;     // Catch: IndexOutOfBoundsException -> L4
            int r2 = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r2;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = (byte) r8;     // Catch: IndexOutOfBoundsException -> L4
            int r3 = r1 + 2;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r3;     // Catch: IndexOutOfBoundsException -> L4
            r0[r2] = (byte) (r8 >> 8);     // Catch: IndexOutOfBoundsException -> L4
            int r22 = r1 + 3;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r22;     // Catch: IndexOutOfBoundsException -> L4
            r0[r3] = (byte) (r8 >> 16);     // Catch: IndexOutOfBoundsException -> L4
            int r32 = r1 + 4;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r32;     // Catch: IndexOutOfBoundsException -> L4
            r0[r22] = (byte) (r8 >> 24);     // Catch: IndexOutOfBoundsException -> L4
            int r23 = r1 + 5;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r23;     // Catch: IndexOutOfBoundsException -> L4
            r0[r32] = (byte) (r8 >> 32);     // Catch: IndexOutOfBoundsException -> L4
            int r33 = r1 + 6;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r33;     // Catch: IndexOutOfBoundsException -> L4
            r0[r23] = (byte) (r8 >> 40);     // Catch: IndexOutOfBoundsException -> L4
            int r24 = r1 + 7;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r24;     // Catch: IndexOutOfBoundsException -> L4
            r0[r33] = (byte) (r8 >> 48);     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r1 + 8;     // Catch: IndexOutOfBoundsException -> L4
            r0[r24] = (byte) (r8 >> 56);     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zzd(int r6) {
            byte[] r0 = this.zzb;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.zze;     // Catch: IndexOutOfBoundsException -> L4
            int r2 = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r2;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = (byte) r6;     // Catch: IndexOutOfBoundsException -> L4
            int r3 = r1 + 2;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r3;     // Catch: IndexOutOfBoundsException -> L4
            r0[r2] = (byte) (r6 >> 8);     // Catch: IndexOutOfBoundsException -> L4
            int r22 = r1 + 3;     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r22;     // Catch: IndexOutOfBoundsException -> L4
            r0[r3] = (byte) (r6 >> 16);     // Catch: IndexOutOfBoundsException -> L4
            this.zze = r1 + 4;     // Catch: IndexOutOfBoundsException -> L4
            r0[r22] = (byte) (r6 >>> 24);     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.zze), Integer.valueOf(this.zzd), 1}), e);
        }

        @Override // com.google.android.gms.internal.vision.zzii
        public final void zze(int r2, int r3) {
            zza(r2, 5);
            zzd(r3);
        }
    }

    public static class zzb extends IOException {
        public zzb(String r3, Throwable r4) {
            String r32 = String.valueOf(r3);
            if (r32.length() == 0) goto L5;
            String r33 = "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(r32);
        L6:
            super(r33, r4);
            return;
        L5:
            r33 = new String("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            goto L6
        }

        public zzb(Throwable r2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", r2);
        }
    }

    static {
        zzb = Logger.getLogger(zzii.class.getName());
        zzc = zzma.zza();
    }

    private zzii() {
    }

    public /* synthetic */ zzii(zzik r1) {
        this();
    }

    public static int zza(zzkk r2, zzlc r3) {
        zzhf r22 = (zzhf) r2;
        int r0 = r22.zzi();
        if (r0 != (-1)) goto L6;
        r0 = r3.zzb(r22);
        r22.zzb(r0);
    L6:
        return zzg(r0) + r0;
    }

    public static zzii zza(byte[] r3) {
        return new zza(r3, 0, r3.length);
    }

    public static int zzb(double r0) {
        return 8;
    }

    public static int zzb(float r0) {
        return 4;
    }

    public static int zzb(int r0, double r1) {
        return zzg(r0 << 3) + 8;
    }

    public static int zzb(int r0, float r1) {
        return zzg(r0 << 3) + 4;
    }

    public static int zzb(int r0, zzkk r1, zzlc r2) {
        return zzg(r0 << 3) + zza(r1, r2);
    }

    public static int zzb(int r0, String r1) {
        return zzg(r0 << 3) + zzb(r1);
    }

    public static int zzb(int r0, boolean r1) {
        return zzg(r0 << 3) + 1;
    }

    public static int zzb(zzht r1) {
        int r12 = r1.zza();
        return zzg(r12) + r12;
    }

    public static int zzb(zzkk r1) {
        int r12 = r1.zzm();
        return zzg(r12) + r12;
    }

    public static int zzb(String r1) {
        int r12 = zzmd.zza(r1);     // Catch: zzmg -> L4
    L6:
        return zzg(r12) + r12;
    L4:
        r12 = r1.getBytes(zzjf.zza).length;
        goto L6
    }

    public static int zzb(boolean r0) {
        return 1;
    }

    public static int zzb(byte[] r1) {
        int r12 = r1.length;
        return zzg(r12) + r12;
    }

    public static int zzc(int r1, zzht r2) {
        int r12 = zzg(r1 << 3);
        int r22 = r2.zza();
        return r12 + (zzg(r22) + r22);
    }

    @Deprecated
    public static int zzc(int r2, zzkk r3, zzlc r4) {
        int r22 = zzg(r2 << 3) << 1;
        zzhf r32 = (zzhf) r3;
        int r0 = r32.zzi();
        if (r0 != (-1)) goto L6;
        r0 = r4.zzb(r32);
        r32.zzb(r0);
    L6:
        return r22 + r0;
    }

    @Deprecated
    public static int zzc(zzkk r0) {
        return r0.zzm();
    }

    public static /* synthetic */ boolean zzc() {
        return zzc;
    }

    public static int zzd(int r0, long r1) {
        return zzg(r0 << 3) + zze(r1);
    }

    public static int zzd(int r2, zzht r3) {
        return ((zzg(8) << 1) + zzg(2, r2)) + zzc(3, r3);
    }

    public static int zzd(long r0) {
        return zze(r0);
    }

    public static int zze(int r0) {
        return zzg(r0 << 3);
    }

    public static int zze(int r0, long r1) {
        return zzg(r0 << 3) + zze(r1);
    }

    public static int zze(long r6) {
        if (((-128) & r6) != 0) goto L6;
        return 1;
    L6:
        if (r6 >= 0) goto L10;
        return 10;
    L10:
        if (((-34359738368L) & r6) == 0) goto L12;
        r6 = r6 >>> 28;
        int r0 = 6;
    L14:
        if (((-2097152) & r6) == 0) goto L17;
        r0 = r0 + 2;
        r6 = r6 >>> 14;
    L17:
        if ((r6 & (-16384)) != 0) goto L19;
        return r0;
    L19:
        return r0 + 1;
    L12:
        r0 = 2;
        goto L14
    }

    public static int zzf(int r0) {
        if (r0 >= 0) goto L4;
        return 10;
    L4:
        return zzg(r0);
    }

    public static int zzf(int r0, int r1) {
        return zzg(r0 << 3) + zzf(r1);
    }

    public static int zzf(int r0, long r1) {
        return zzg(r0 << 3) + zze(zzi(r1));
    }

    public static int zzf(long r0) {
        return zze(zzi(r0));
    }

    public static int zzg(int r1) {
        if ((r1 & (-128)) != 0) goto L7;
        return 1;
    L7:
        if ((r1 & (-16384)) != 0) goto L11;
        return 2;
    L11:
        if (((-2097152) & r1) != 0) goto L15;
        return 3;
    L15:
        if ((r1 & (-268435456)) != 0) goto L18;
        return 4;
    L18:
        return 5;
    }

    public static int zzg(int r0, int r1) {
        return zzg(r0 << 3) + zzg(r1);
    }

    public static int zzg(int r0, long r1) {
        return zzg(r0 << 3) + 8;
    }

    public static int zzg(long r0) {
        return 8;
    }

    public static int zzh(int r0) {
        return zzg(zzm(r0));
    }

    public static int zzh(int r0, int r1) {
        return zzg(r0 << 3) + zzg(zzm(r1));
    }

    public static int zzh(int r0, long r1) {
        return zzg(r0 << 3) + 8;
    }

    public static int zzh(long r0) {
        return 8;
    }

    public static int zzi(int r0) {
        return 4;
    }

    public static int zzi(int r0, int r1) {
        return zzg(r0 << 3) + 4;
    }

    private static long zzi(long r3) {
        long r0 = r3 << 1;
        return (r3 >> 63) ^ r0;
    }

    public static int zzj(int r0) {
        return 4;
    }

    public static int zzj(int r0, int r1) {
        return zzg(r0 << 3) + 4;
    }

    public static int zzk(int r0) {
        return zzf(r0);
    }

    public static int zzk(int r0, int r1) {
        return zzg(r0 << 3) + zzf(r1);
    }

    @Deprecated
    public static int zzl(int r0) {
        return zzg(r0);
    }

    private static int zzm(int r1) {
        int r0 = r1 << 1;
        return (r1 >> 31) ^ r0;
    }

    public abstract int zza();

    public abstract void zza(byte r1);

    public final void zza(double r1) {
        zzc(Double.doubleToRawLongBits(r1));
    }

    public final void zza(float r1) {
        zzd(Float.floatToRawIntBits(r1));
    }

    public abstract void zza(int r1);

    public final void zza(int r1, double r2) {
        zzc(r1, Double.doubleToRawLongBits(r2));
    }

    public final void zza(int r1, float r2) {
        zze(r1, Float.floatToRawIntBits(r2));
    }

    public abstract void zza(int r1, int r2);

    public abstract void zza(int r1, long r2);

    public abstract void zza(int r1, zzht r2);

    public abstract void zza(int r1, zzkk r2);

    public abstract void zza(int r1, zzkk r2, zzlc r3);

    public abstract void zza(int r1, String r2);

    public abstract void zza(int r1, boolean r2);

    public abstract void zza(long r1);

    public abstract void zza(zzht r1);

    public abstract void zza(zzkk r1);

    public abstract void zza(String r1);

    public final void zza(String r7, zzmg r8) {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", r8);
        byte[] r72 = r7.getBytes(zzjf.zza);
        zzb(r72.length);     // Catch: zzb -> L5 IndexOutOfBoundsException -> L8
        zza(r72, 0, r72.length);     // Catch: zzb -> L5 IndexOutOfBoundsException -> L8
        return;
    L5:
        e = move-exception;
        throw e;
    L8:
        e = move-exception;
        throw new zzb(e);
    }

    public final void zza(boolean r1) {
        zza(r1 ? 1 : 0);
    }

    public final void zzb() {
        if (zza() != 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
    }

    public abstract void zzb(int r1);

    public abstract void zzb(int r1, int r2);

    public final void zzb(int r1, long r2) {
        zza(r1, zzi(r2));
    }

    public abstract void zzb(int r1, zzht r2);

    public final void zzb(long r1) {
        zza(zzi(r1));
    }

    public final void zzc(int r1) {
        zzb(zzm(r1));
    }

    public abstract void zzc(int r1, int r2);

    public abstract void zzc(int r1, long r2);

    public abstract void zzc(long r1);

    public abstract void zzd(int r1);

    public final void zzd(int r1, int r2) {
        zzc(r1, zzm(r2));
    }

    public abstract void zze(int r1, int r2);
}
