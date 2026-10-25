package com.google.android.gms.internal.clearcut;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.google.protobuf.FieldSet$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public abstract class zzbn extends zzba {
    private static final Logger logger = null;
    private static final boolean zzfy = false;
    zzbp zzfz;

    public static class zza extends zzbn {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        public zza(byte[] r5, int r6, int r7) {
            super(null);
            if (r5 == null) goto L10;
            int r3 = r6 + r7;
            if (((r6 | r7) | (r5.length - r3)) < 0) goto L8;
            this.buffer = r5;
            this.offset = r6;
            this.position = r6;
            this.limit = r3;
            return;
        L8:
            FieldSet$$ExternalSyntheticBUOutline0.m("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(r5.length), Integer.valueOf(r6), Integer.valueOf(r7)});
            throw null;
        L10:
            f$$ExternalSyntheticBUOutline2.m("buffer");
            throw null;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public void flush() {
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] r3, int r4, int r5) {
            System.arraycopy(r3, r4, this.buffer, this.position, r5);     // Catch: IndexOutOfBoundsException -> L4
            this.position += r5;
            return;
        L4:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(r5)}), e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte r4) {
            byte[] r0 = this.buffer;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.position;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = r4;     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long r3) {
            zzb(r2, 0);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb r3) {
            zzb(r2, 2);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo r3) {
            zzb(r2, 2);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r3, zzdo r4, zzef r5) {
            zzb(r3, 2);
            zzas r32 = (zzas) r4;
            int r0 = r32.zzs();
            if (r0 != (-1)) goto L5;
            r0 = r5.zzm(r32);
            r32.zzf(r0);
        L5:
            zzo(r0);
            r5.zza(r4, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String r3) {
            zzb(r2, 2);
            zzg(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb r2) {
            zzo(r2.size());
            r2.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzdo r4, zzef r5) {
            zzas r0 = (zzas) r4;
            int r1 = r0.zzs();
            if (r1 != (-1)) goto L5;
            r1 = r5.zzm(r0);
            r0.zzf(r1);
        L5:
            zzo(r1);
            r5.zza(r4, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] r1, int r2, int r3) {
            write(r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return this.limit - this.position;
        }

        public final int zzai() {
            return this.position - this.offset;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean r3) {
            zzb(r2, 0);
            zza(r3 ? 1 : 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long r10) {
            if (zzbn.zzah() == false) goto L11;
            if (zzag() < 10) goto L11;
        L6:
            long r6 = r10 & (-128);
            byte[] r62 = this.buffer;
            if (r6 == 0) goto L8;
            int r0 = this.position;
            this.position = r0 + 1;
            zzfd.zza(r62, r0, (byte) ((((int) r10) & 127) | 128));
            r10 = r10 >>> 7;
            goto L6
        L8:
            int r02 = this.position;
            this.position = r02 + 1;
            zzfd.zza(r62, r02, (byte) r10);
            return;
        L11:
            long r63 = r10 & (-128);
            byte[] r64 = this.buffer;
            if (r63 == 0) goto L13;
            int r03 = this.position;     // Catch: IndexOutOfBoundsException -> L15
            this.position = r03 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r64[r03] = (byte) ((((int) r10) & 127) | 128);     // Catch: IndexOutOfBoundsException -> L15
            r10 = r10 >>> 7;
        L15:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
        L13:
            int r04 = this.position;     // Catch: IndexOutOfBoundsException -> L15
            this.position = r04 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r64[r04] = (byte) r10;     // Catch: IndexOutOfBoundsException -> L15
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo r2) {
            zzo(r2.zzas());
            r2.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long r3) {
            zzb(r2, 1);
            zzd(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long r8) {
            byte[] r0 = this.buffer;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.position;     // Catch: IndexOutOfBoundsException -> L4
            int r2 = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r2;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = (byte) r8;     // Catch: IndexOutOfBoundsException -> L4
            int r3 = r1 + 2;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r3;     // Catch: IndexOutOfBoundsException -> L4
            r0[r2] = (byte) (r8 >> 8);     // Catch: IndexOutOfBoundsException -> L4
            int r22 = r1 + 3;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r22;     // Catch: IndexOutOfBoundsException -> L4
            r0[r3] = (byte) (r8 >> 16);     // Catch: IndexOutOfBoundsException -> L4
            int r32 = r1 + 4;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r32;     // Catch: IndexOutOfBoundsException -> L4
            r0[r22] = (byte) (r8 >> 24);     // Catch: IndexOutOfBoundsException -> L4
            int r23 = r1 + 5;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r23;     // Catch: IndexOutOfBoundsException -> L4
            r0[r32] = (byte) (r8 >> 32);     // Catch: IndexOutOfBoundsException -> L4
            int r33 = r1 + 6;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r33;     // Catch: IndexOutOfBoundsException -> L4
            r0[r23] = (byte) (r8 >> 40);     // Catch: IndexOutOfBoundsException -> L4
            int r24 = r1 + 7;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r24;     // Catch: IndexOutOfBoundsException -> L4
            r0[r33] = (byte) (r8 >> 48);     // Catch: IndexOutOfBoundsException -> L4
            this.position = r1 + 8;     // Catch: IndexOutOfBoundsException -> L4
            r0[r24] = (byte) (r8 >> 56);     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String r6) {
            int r0 = this.position;
            int r1 = zzbn.zzt(r6.length() * 3);     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            int r2 = zzbn.zzt(r6.length());     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            if (r2 != r1) goto L10;
            int r12 = r0 + r2;     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            this.position = r12;     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            int r13 = zzff.zza(r6, this.buffer, r12, zzag());     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            this.position = r0;     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            zzo((r13 - r0) - r2);     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
        L6:
            this.position = r13;     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            return;
        L10:
            zzo(zzff.zza(r6));     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
            r13 = zzff.zza(r6, this.buffer, this.position, zzag());     // Catch: zzfi -> L8 IndexOutOfBoundsException -> L12
        L8:
            e = move-exception;
            this.position = r0;
            zza(r6, e);
            return;
        L12:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) {
            if (r3 < 0) goto L5;
            zzo(r3);
            return;
        L5:
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r5) {
            if (zzbn.zzah() == false) goto L11;
            if (zzag() < 10) goto L11;
        L6:
            int r0 = r5 & (-128);
            byte[] r1 = this.buffer;
            if (r0 == 0) goto L8;
            int r02 = this.position;
            this.position = r02 + 1;
            zzfd.zza(r1, r02, (byte) ((r5 & 127) | 128));
            r5 = r5 >>> 7;
            goto L6
        L8:
            int r03 = this.position;
            this.position = r03 + 1;
            zzfd.zza(r1, r03, (byte) r5);
            return;
        L11:
            int r04 = r5 & (-128);
            byte[] r12 = this.buffer;
            if (r04 == 0) goto L13;
            int r05 = this.position;     // Catch: IndexOutOfBoundsException -> L15
            this.position = r05 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r12[r05] = (byte) ((r5 & 127) | 128);     // Catch: IndexOutOfBoundsException -> L15
            r5 = r5 >>> 7;
        L15:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
        L13:
            int r06 = this.position;     // Catch: IndexOutOfBoundsException -> L15
            this.position = r06 + 1;     // Catch: IndexOutOfBoundsException -> L15
            r12[r06] = (byte) r5;     // Catch: IndexOutOfBoundsException -> L15
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r6) {
            byte[] r0 = this.buffer;     // Catch: IndexOutOfBoundsException -> L4
            int r1 = this.position;     // Catch: IndexOutOfBoundsException -> L4
            int r2 = r1 + 1;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r2;     // Catch: IndexOutOfBoundsException -> L4
            r0[r1] = (byte) r6;     // Catch: IndexOutOfBoundsException -> L4
            int r3 = r1 + 2;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r3;     // Catch: IndexOutOfBoundsException -> L4
            r0[r2] = (byte) (r6 >> 8);     // Catch: IndexOutOfBoundsException -> L4
            int r22 = r1 + 3;     // Catch: IndexOutOfBoundsException -> L4
            this.position = r22;     // Catch: IndexOutOfBoundsException -> L4
            r0[r3] = (byte) (r6 >> 16);     // Catch: IndexOutOfBoundsException -> L4
            this.position = r1 + 4;     // Catch: IndexOutOfBoundsException -> L4
            r0[r22] = r6 >> 24;     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
        }
    }

    public static final class zzb extends zza {
        private final ByteBuffer zzga;
        private int zzgb;

        public zzb(ByteBuffer r4) {
            super(r4.array(), r4.arrayOffset() + r4.position(), r4.remaining());
            this.zzga = r4;
            this.zzgb = r4.position();
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn.zza, com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzga.position(this.zzgb + zzai());
        }
    }

    public static class zzc extends IOException {
        public zzc(String r3) {
            String r32 = String.valueOf(r3);
            if (r32.length() == 0) goto L5;
            String r33 = "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(r32);
        L6:
            super(r33);
            return;
        L5:
            r33 = new String("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
            goto L6
        }

        public zzc(String r3, Throwable r4) {
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

        public zzc(Throwable r2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", r2);
        }
    }

    public static final class zzd extends zzbn {
        private final int zzgb;
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;

        public zzd(ByteBuffer r3) {
            super(null);
            this.zzgc = r3;
            this.zzgd = r3.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzgb = r3.position();
        }

        private final void zzi(String r1) {
            zzff.zza(r1, this.zzgd);     // Catch: IndexOutOfBoundsException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzgc.position(this.zzgd.position());
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] r1, int r2, int r3) {
            this.zzgd.put(r1, r2, r3);     // Catch: BufferOverflowException -> L4 IndexOutOfBoundsException -> L7
            return;
        L7:
            e = move-exception;
            throw new zzc(e);
        L4:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte r1) {
            this.zzgd.put(r1);     // Catch: BufferOverflowException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long r3) {
            zzb(r2, 0);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb r3) {
            zzb(r2, 2);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo r3) {
            zzb(r2, 2);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo r3, zzef r4) {
            zzb(r2, 2);
            zza(r3, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String r3) {
            zzb(r2, 2);
            zzg(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb r2) {
            zzo(r2.size());
            r2.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzdo r4, zzef r5) {
            zzas r0 = (zzas) r4;
            int r1 = r0.zzs();
            if (r1 != (-1)) goto L5;
            r1 = r5.zzm(r0);
            r0.zzf(r1);
        L5:
            zzo(r1);
            r5.zza(r4, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] r1, int r2, int r3) {
            write(r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return this.zzgd.remaining();
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean r3) {
            zzb(r2, 0);
            zza(r3 ? 1 : 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long r5) {
        L2:
            long r0 = (-128) & r5;
            ByteBuffer r1 = this.zzgd;
            if (r0 == 0) goto L5;
            r1.put((byte) ((((int) r5) & 127) | 128));     // Catch: BufferOverflowException -> L9
            r5 = r5 >>> 7;
        L9:
            e = move-exception;
            throw new zzc(e);
        L5:
            r1.put((byte) r5);     // Catch: BufferOverflowException -> L9
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo r2) {
            zzo(r2.zzas());
            r2.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long r3) {
            zzb(r2, 1);
            zzd(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long r1) {
            this.zzgd.putLong(r1);     // Catch: BufferOverflowException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String r5) {
            int r0 = this.zzgd.position();
            int r1 = zzbn.zzt(r5.length() * 3);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            int r2 = zzbn.zzt(r5.length());     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            if (r2 != r1) goto L9;
            int r12 = this.zzgd.position() + r2;     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            this.zzgd.position(r12);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            zzi(r5);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            int r22 = this.zzgd.position();     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            this.zzgd.position(r0);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            zzo(r22 - r12);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            this.zzgd.position(r22);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            return;
        L9:
            zzo(zzff.zza(r5));     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            zzi(r5);     // Catch: zzfi -> L7 IllegalArgumentException -> L11
            return;
        L7:
            e = move-exception;
            this.zzgd.position(r0);
            zza(r5, e);
            return;
        L11:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) {
            if (r3 < 0) goto L5;
            zzo(r3);
            return;
        L5:
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r3) {
        L2:
            int r0 = r3 & (-128);
            ByteBuffer r1 = this.zzgd;
            if (r0 == 0) goto L5;
            r1.put((byte) ((r3 & 127) | 128));     // Catch: BufferOverflowException -> L9
            r3 = r3 >>> 7;
        L9:
            e = move-exception;
            throw new zzc(e);
        L5:
            r1.put((byte) r3);     // Catch: BufferOverflowException -> L9
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r1) {
            this.zzgd.putInt(r1);     // Catch: BufferOverflowException -> L4
            return;
        L4:
            e = move-exception;
            throw new zzc(e);
        }
    }

    public static final class zze extends zzbn {
        private final ByteBuffer zzgc;
        private final ByteBuffer zzgd;
        private final long zzge;
        private final long zzgf;
        private final long zzgg;
        private final long zzgh;
        private long zzgi;

        public zze(ByteBuffer r7) {
            super(null);
            this.zzgc = r7;
            this.zzgd = r7.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            long r0 = zzfd.zzb(r7);
            this.zzge = r0;
            long r2 = r7.position() + r0;
            this.zzgf = r2;
            long r02 = r0 + r7.limit();
            this.zzgg = r02;
            this.zzgh = r02 - 10;
            this.zzgi = r2;
        }

        private final void zzk(long r4) {
            this.zzgd.position((int) (r4 - this.zzge));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void flush() {
            this.zzgc.position((int) (this.zzgi - this.zzge));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void write(byte[] r10, int r11, int r12) {
            if (r10 == null) goto L12;
            if (r11 < 0) goto L12;
            if (r12 < 0) goto L12;
            if ((r10.length - r12) < r11) goto L12;
            long r7 = r12;
            long r0 = this.zzgg - r7;
            long r5 = this.zzgi;
            if (r0 < r5) goto L12;
            zzfd.zza(r10, r11, r5, r7);
            this.zzgi += r7;
            return;
        L12:
            if (r10 != null) goto L16;
            f$$ExternalSyntheticBUOutline2.m("value");
            return;
        L16:
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzgi), Long.valueOf(this.zzgg), Integer.valueOf(r12)}));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(byte r5) {
            long r0 = this.zzgi;
            if (r0 >= this.zzgg) goto L7;
            this.zzgi = 1 + r0;
            zzfd.zza(r0, r5);
            return;
        L7:
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(r0), Long.valueOf(this.zzgg), 1}));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, long r3) {
            zzb(r2, 0);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzbb r3) {
            zzb(r2, 2);
            zza(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo r3) {
            zzb(r2, 2);
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, zzdo r3, zzef r4) {
            zzb(r2, 2);
            zza(r3, r4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(int r2, String r3) {
            zzb(r2, 2);
            zzg(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzbb r2) {
            zzo(r2.size());
            r2.zza(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zza(zzdo r4, zzef r5) {
            zzas r0 = (zzas) r4;
            int r1 = r0.zzs();
            if (r1 != (-1)) goto L5;
            r1 = r5.zzm(r0);
            r0.zzf(r1);
        L5:
            zzo(r1);
            r5.zza(r4, this.zzfz);
        }

        @Override // com.google.android.gms.internal.clearcut.zzba
        public final void zza(byte[] r1, int r2, int r3) {
            write(r1, r2, r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final int zzag() {
            return (int) (this.zzgg - this.zzgi);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r1, int r2) {
            zzo((r1 << 3) | r2);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzbb r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r4, zzdo r5) {
            zzb(1, 3);
            zzd(2, r4);
            zza(3, r5);
            zzb(1, 4);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(int r2, boolean r3) {
            zzb(r2, 0);
            zza(r3 ? 1 : 0);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(long r13) {
            if (this.zzgi > this.zzgh) goto L9;
        L4:
            long r8 = r13 & (-128);
            long r82 = this.zzgi;
            if (r8 == 0) goto L6;
            this.zzgi = r82 + 1;
            zzfd.zza(r82, (byte) ((((int) r13) & 127) | 128));
            r13 = r13 >>> 7;
        L6:
            this.zzgi = 1 + r82;
            zzfd.zza(r82, (byte) r13);
            return;
        L9:
            r82 = this.zzgi;
            if (r82 >= this.zzgg) goto L16;
            if ((r13 & (-128)) == 0) goto L6;
            this.zzgi = r82 + 1;
            zzfd.zza(r82, (byte) ((((int) r13) & 127) | 128));
            r13 = r13 >>> 7;
            goto L9
        L16:
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(r82), Long.valueOf(this.zzgg), 1}));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzb(zzdo r2) {
            zzo(r2.zzas());
            r2.zzb(this);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, int r3) {
            zzb(r2, 0);
            zzn(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzc(int r2, long r3) {
            zzb(r2, 1);
            zzd(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(int r2, int r3) {
            zzb(r2, 0);
            zzo(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzd(long r6) {
            this.zzgd.putLong((int) (this.zzgi - this.zzge), r6);
            this.zzgi += 8;
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzf(int r2, int r3) {
            zzb(r2, 5);
            zzq(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzg(String r9) {
            long r0 = this.zzgi;
            int r2 = zzbn.zzt(r9.length() * 3);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            int r3 = zzbn.zzt(r9.length());     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            if (r3 != r2) goto L9;
            int r22 = ((int) (this.zzgi - this.zzge)) + r3;     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            this.zzgd.position(r22);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            zzff.zza(r9, this.zzgd);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            int r32 = this.zzgd.position() - r22;     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            zzo(r32);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            this.zzgi += r32;
            return;
        L9:
            int r23 = zzff.zza(r9);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            zzo(r23);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            zzk(this.zzgi);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            zzff.zza(r9, this.zzgd);     // Catch: zzfi -> L7 IndexOutOfBoundsException -> L11 IllegalArgumentException -> L14
            this.zzgi += r23;
            return;
        L7:
            e = move-exception;
            this.zzgi = r0;
            zzk(r0);
            zza(r9, e);
            return;
        L14:
            e = move-exception;
            throw new zzc(e);
        L11:
            e = move-exception;
            throw new zzc(e);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzn(int r3) {
            if (r3 < 0) goto L5;
            zzo(r3);
            return;
        L5:
            zzb(r3);
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzo(int r8) {
            if (this.zzgi > this.zzgh) goto L9;
        L4:
            int r0 = r8 & (-128);
            long r3 = this.zzgi;
            if (r0 == 0) goto L6;
            this.zzgi = r3 + 1;
            zzfd.zza(r3, (byte) ((r8 & 127) | 128));
            r8 = r8 >>> 7;
        L6:
            this.zzgi = 1 + r3;
            zzfd.zza(r3, (byte) r8);
            return;
        L9:
            r3 = this.zzgi;
            if (r3 >= this.zzgg) goto L16;
            if ((r8 & (-128)) == 0) goto L6;
            this.zzgi = r3 + 1;
            zzfd.zza(r3, (byte) ((r8 & 127) | 128));
            r8 = r8 >>> 7;
            goto L9
        L16:
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(r3), Long.valueOf(this.zzgg), 1}));
        }

        @Override // com.google.android.gms.internal.clearcut.zzbn
        public final void zzq(int r6) {
            this.zzgd.putInt((int) (this.zzgi - this.zzge), r6);
            this.zzgi += 4;
        }
    }

    static {
        logger = Logger.getLogger(zzbn.class.getName());
        zzfy = zzfd.zzed();
    }

    private zzbn() {
    }

    public /* synthetic */ zzbn(zzbo r1) {
        this();
    }

    public static zzbn zza(ByteBuffer r1) {
        if (r1.hasArray() == false) goto L7;
        return new zzb(r1);
    L7:
        if (r1.isDirect() == true) goto L9;
    L16:
        f$$ExternalSyntheticBUOutline1.m("ByteBuffer is read-only");
        return null;
    L9:
        if (r1.isReadOnly() == true) goto L16;
        if (zzfd.zzee() == false) goto L15;
        return new zze(r1);
    L15:
        return new zzd(r1);
    }

    public static /* synthetic */ boolean zzah() {
        return zzfy;
    }

    public static int zzb(double r0) {
        return 8;
    }

    public static int zzb(float r0) {
        return 4;
    }

    public static int zzb(int r0, double r1) {
        return zzr(r0) + 8;
    }

    public static int zzb(int r0, float r1) {
        return zzr(r0) + 4;
    }

    public static int zzb(int r0, zzdo r1, zzef r2) {
        return zzr(r0) + zzb(r1, r2);
    }

    public static int zzb(int r0, String r1) {
        return zzr(r0) + zzh(r1);
    }

    public static int zzb(zzbb r1) {
        int r12 = r1.size();
        return zzt(r12) + r12;
    }

    public static int zzb(zzdo r2, zzef r3) {
        zzas r22 = (zzas) r2;
        int r0 = r22.zzs();
        if (r0 != (-1)) goto L6;
        r0 = r3.zzm(r22);
        r22.zzf(r0);
    L6:
        return zzt(r0) + r0;
    }

    public static int zzb(boolean r0) {
        return 1;
    }

    public static int zzc(int r1, zzbb r2) {
        int r12 = zzr(r1);
        int r22 = r2.size();
        return r12 + (zzt(r22) + r22);
    }

    public static int zzc(int r0, zzdo r1) {
        return zzr(r0) + zzc(r1);
    }

    @Deprecated
    public static int zzc(int r2, zzdo r3, zzef r4) {
        int r22 = zzr(r2) << 1;
        zzas r32 = (zzas) r3;
        int r0 = r32.zzs();
        if (r0 != (-1)) goto L6;
        r0 = r4.zzm(r32);
        r32.zzf(r0);
    L6:
        return r22 + r0;
    }

    public static int zzc(int r0, boolean r1) {
        return zzr(r0) + 1;
    }

    public static int zzc(zzdo r1) {
        int r12 = r1.zzas();
        return zzt(r12) + r12;
    }

    public static zzbn zzc(byte[] r3) {
        return new zza(r3, 0, r3.length);
    }

    public static int zzd(int r0, long r1) {
        return zzr(r0) + zzf(r1);
    }

    public static int zzd(int r2, zzbb r3) {
        return ((zzr(1) << 1) + zzh(2, r2)) + zzc(3, r3);
    }

    public static int zze(int r0, long r1) {
        return zzr(r0) + zzf(r1);
    }

    public static int zze(long r0) {
        return zzf(r0);
    }

    public static int zzf(int r0, long r1) {
        return zzr(r0) + zzf(zzj(r1));
    }

    public static int zzf(long r6) {
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

    public static int zzg(int r0, int r1) {
        return zzr(r0) + zzs(r1);
    }

    public static int zzg(int r0, long r1) {
        return zzr(r0) + 8;
    }

    public static int zzg(long r0) {
        return zzf(zzj(r0));
    }

    public static int zzh(int r0, int r1) {
        return zzr(r0) + zzt(r1);
    }

    public static int zzh(int r0, long r1) {
        return zzr(r0) + 8;
    }

    public static int zzh(long r0) {
        return 8;
    }

    public static int zzh(String r1) {
        int r12 = zzff.zza(r1);     // Catch: zzfi -> L4
    L6:
        return zzt(r12) + r12;
    L4:
        r12 = r1.getBytes(zzci.UTF_8).length;
        goto L6
    }

    public static int zzi(int r0, int r1) {
        return zzr(r0) + zzt(zzy(r1));
    }

    public static int zzi(long r0) {
        return 8;
    }

    public static int zzj(int r0, int r1) {
        return zzr(r0) + 4;
    }

    private static long zzj(long r3) {
        long r0 = r3 << 1;
        return (r3 >> 63) ^ r0;
    }

    public static int zzk(int r0, int r1) {
        return zzr(r0) + 4;
    }

    public static int zzl(int r0, int r1) {
        return zzr(r0) + zzs(r1);
    }

    public static int zzr(int r0) {
        return zzt(r0 << 3);
    }

    public static int zzs(int r0) {
        if (r0 >= 0) goto L4;
        return 10;
    L4:
        return zzt(r0);
    }

    public static int zzt(int r1) {
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

    public static int zzu(int r0) {
        return zzt(zzy(r0));
    }

    public static int zzv(int r0) {
        return 4;
    }

    public static int zzw(int r0) {
        return 4;
    }

    public static int zzx(int r0) {
        return zzs(r0);
    }

    private static int zzy(int r1) {
        int r0 = r1 << 1;
        return (r1 >> 31) ^ r0;
    }

    public abstract void flush();

    public abstract void write(byte[] r1, int r2, int r3);

    public abstract void zza(byte r1);

    public final void zza(double r1) {
        zzd(Double.doubleToRawLongBits(r1));
    }

    public final void zza(float r1) {
        zzq(Float.floatToRawIntBits(r1));
    }

    public final void zza(int r1, double r2) {
        zzc(r1, Double.doubleToRawLongBits(r2));
    }

    public final void zza(int r1, float r2) {
        zzf(r1, Float.floatToRawIntBits(r2));
    }

    public abstract void zza(int r1, long r2);

    public abstract void zza(int r1, zzbb r2);

    public abstract void zza(int r1, zzdo r2);

    public abstract void zza(int r1, zzdo r2, zzef r3);

    public abstract void zza(int r1, String r2);

    public abstract void zza(zzbb r1);

    public abstract void zza(zzdo r1, zzef r2);

    public final void zza(String r7, zzfi r8) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", r8);
        byte[] r72 = r7.getBytes(zzci.UTF_8);
        zzo(r72.length);     // Catch: zzc -> L5 IndexOutOfBoundsException -> L8
        zza(r72, 0, r72.length);     // Catch: zzc -> L5 IndexOutOfBoundsException -> L8
        return;
    L5:
        e = move-exception;
        throw e;
    L8:
        e = move-exception;
        throw new zzc(e);
    }

    public final void zza(boolean r1) {
        zza(r1 ? 1 : 0);
    }

    public abstract int zzag();

    public abstract void zzb(int r1, int r2);

    public final void zzb(int r1, long r2) {
        zza(r1, zzj(r2));
    }

    public abstract void zzb(int r1, zzbb r2);

    public abstract void zzb(int r1, zzdo r2);

    public abstract void zzb(int r1, boolean r2);

    public abstract void zzb(long r1);

    public abstract void zzb(zzdo r1);

    public abstract void zzc(int r1, int r2);

    public abstract void zzc(int r1, long r2);

    public final void zzc(long r1) {
        zzb(zzj(r1));
    }

    public abstract void zzd(int r1, int r2);

    public abstract void zzd(long r1);

    public final void zze(int r1, int r2) {
        zzd(r1, zzy(r2));
    }

    public abstract void zzf(int r1, int r2);

    public abstract void zzg(String r1);

    public abstract void zzn(int r1);

    public abstract void zzo(int r1);

    public final void zzp(int r1) {
        zzo(zzy(r1));
    }

    public abstract void zzq(int r1);
}
