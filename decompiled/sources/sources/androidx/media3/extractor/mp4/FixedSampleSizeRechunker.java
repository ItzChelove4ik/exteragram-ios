package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
abstract class FixedSampleSizeRechunker {

    /* renamed from: androidx.media3.extractor.mp4.FixedSampleSizeRechunker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Results {
        public final long duration;
        public final int[] flags;
        public final int maximumSize;
        public final long[] offsets;
        public final int[] sizes;
        public final long[] timestamps;
        public final long totalSize;

        private Results(long[] r1, int[] r2, int r3, long[] r4, int[] r5, long r6, long r8) {
            this.offsets = r1;
            this.sizes = r2;
            this.maximumSize = r3;
            this.timestamps = r4;
            this.flags = r5;
            this.duration = r6;
            this.totalSize = r8;
        }

        public /* synthetic */ Results(long[] r1, int[] r2, int r3, long[] r4, int[] r5, long r6, long r8, AnonymousClass1 r10) {
            this(r1, r2, r3, r4, r5, r6, r8);
        }
    }

    public static Results rechunk(int r17, long[] r18, int[] r19, long r20) {
        int[] r0 = r19;
        int r1 = 8192 / r17;
        int r2 = r0.length;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r4 >= r2) goto L5;
        r5 = r5 + Util.ceilDivide(r0[r4], r1);
        r4 = r4 + 1;
        goto L3
    L5:
        long[] r7 = new long[r5];
        int[] r8 = new int[r5];
        long[] r10 = new long[r5];
        int[] r11 = new int[r5];
        int r22 = 0;
        int r42 = 0;
        int r52 = 0;
        int r9 = 0;
    L7:
        if (r3 >= r0.length) goto L13;
        int r6 = r0[r3];
        long r12 = r18[r3];
    L9:
        if (r6 <= 0) goto L11;
        int r14 = Math.min(r1, r6);
        r7[r52] = r12;
        int r15 = r17 * r14;
        r8[r52] = r15;
        r42 = r42 + r15;
        r9 = Math.max(r9, r15);
        r10[r52] = r22 * r20;
        r11[r52] = 1;
        r12 = r12 + r8[r52];
        r22 = r22 + r14;
        r6 = r6 - r14;
        r52 = r52 + 1;
        r1 = r1;
        goto L9
    L11:
        r3 = r3 + 1;
        r0 = r19;
        goto L7
    L13:
        return new Results(r7, r8, r9, r10, r11, r20 * r22, r42, null);
    }
}
