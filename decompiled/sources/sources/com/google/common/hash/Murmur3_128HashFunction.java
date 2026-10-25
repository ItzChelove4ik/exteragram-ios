package com.google.common.hash;

import androidx.media3.container.MdtaMetadataEntry$$ExternalSyntheticBackport0;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction GOOD_FAST_HASH_128 = null;
    static final HashFunction MURMUR3_128 = null;
    private final int seed;

    public static final class Murmur3_128Hasher extends AbstractStreamingHasher {
        private long h1;
        private long h2;
        private int length;

        public Murmur3_128Hasher(int r3) {
            super(16);
            long r0 = r3;
            this.h1 = r0;
            this.h2 = r0;
            this.length = 0;
        }

        private void bmix64(long r7, long r9) {
            long r0 = this.h1;
            long r72 = mixK1(r7) ^ r0;
            this.h1 = r72;
            long r73 = Long.rotateLeft(r72, 27);
            long r02 = this.h2;
            this.h1 = ((r73 + r02) * 5) + 1390208809;
            long r74 = mixK2(r9) ^ r02;
            this.h2 = r74;
            this.h2 = ((Long.rotateLeft(r74, 31) + this.h1) * 5) + 944331445;
        }

        private static long fmix64(long r3) {
            long r32 = (r3 ^ (r3 >>> 33)) * (-49064778989728563L);
            long r33 = (r32 ^ (r32 >>> 33)) * (-4265267296055464877L);
            return r33 ^ (r33 >>> 33);
        }

        private static long mixK1(long r2) {
            return Long.rotateLeft(r2 * (-8663945395140668459L), 31) * 5545529020109919103L;
        }

        private static long mixK2(long r2) {
            return Long.rotateLeft(r2 * 5545529020109919103L, 33) * (-8663945395140668459L);
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public HashCode makeHash() {
            long r0 = this.h1;
            int r2 = this.length;
            long r02 = r0 ^ r2;
            long r22 = this.h2 ^ r2;
            long r03 = r02 + r22;
            this.h1 = r03;
            this.h2 = r22 + r03;
            this.h1 = fmix64(r03);
            long r04 = fmix64(this.h2);
            long r23 = this.h1 + r04;
            this.h1 = r23;
            this.h2 = r04 + r23;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void process(ByteBuffer r5) {
            bmix64(r5.getLong(), r5.getLong());
            this.length += 16;
        }

        @Override // com.google.common.hash.AbstractStreamingHasher
        public void processRemaining(ByteBuffer r14) {
            this.length += r14.remaining();
            long r7 = 0;
            switch(r14.remaining()) {
                case 1: goto L25;
                case 2: goto L23;
                case 3: goto L21;
                case 4: goto L19;
                case 5: goto L17;
                case 6: goto L15;
                case 7: goto L14;
                case 8: goto L13;
                case 9: goto L12;
                case 10: goto L11;
                case 11: goto L10;
                case 12: goto L9;
                case 13: goto L8;
                case 14: goto L7;
                case 15: goto L6;
                default: goto L4;
            };
        L4:
            Buffer$$ExternalSyntheticBUOutline2.m("Should never get here.");
            return;
        L6:
            r7 = MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(14)) << 48;
        L7:
            r7 = r7 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(13)) << 40);
        L8:
            r7 = r7 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(12)) << 32);
        L9:
            r7 = r7 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(11)) << 24);
        L10:
            r7 = r7 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(10)) << 16);
        L11:
            r7 = r7 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(9)) << 8);
        L12:
            r7 = r7 ^ MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(8));
        L13:
            long r0 = r14.getLong();
        L27:
            this.h1 = mixK1(r0) ^ this.h1;
            this.h2 ^= mixK2(r7);
            return;
        L14:
            long r9 = MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(6)) << 48;
        L16:
            long r4 = (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(5)) << 40) ^ r9;
        L18:
            long r3 = r4 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(4)) << 32);
        L20:
            long r2 = r3 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(3)) << 24);
        L22:
            long r02 = (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(2)) << 16) ^ r2;
        L24:
            long r03 = r02 ^ (MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(1)) << 8);
        L26:
            r0 = r03 ^ MdtaMetadataEntry$$ExternalSyntheticBackport0.m(r14.get(0));
            goto L27
        L15:
            r9 = 0;
            goto L16
        L17:
            r4 = 0;
            goto L18
        L19:
            r3 = 0;
            goto L20
        L21:
            r2 = 0;
            goto L22
        L23:
            r02 = 0;
            goto L24
        L25:
            r03 = 0;
            goto L26
        }
    }

    static {
        MURMUR3_128 = new Murmur3_128HashFunction(0);
        GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    }

    public Murmur3_128HashFunction(int r1) {
        this.seed = r1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof Murmur3_128HashFunction) == true) goto L5;
    L8:
        return false;
    L5:
        if (this.seed != ((Murmur3_128HashFunction) r3).seed) goto L8;
        return true;
    }

    public int hashCode() {
        int r0 = Murmur3_128HashFunction.class.hashCode();
        return this.seed ^ r0;
    }

    @Override // com.google.common.hash.HashFunction
    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }
}
