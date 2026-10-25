package androidx.media3.exoplayer.source;

import java.util.Arrays;
import java.util.Random;

/* loaded from: classes4.dex */
public interface ShuffleOrder {

    public static class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int r2) {
            this(r2, new Random());
        }

        private DefaultShuffleOrder(int r1, Random r2) {
            this(createShuffledList(r1, r2), r2);
        }

        private DefaultShuffleOrder(int[] r3, Random r4) {
            this.shuffled = r3;
            this.random = r4;
            this.indexInShuffled = new int[r3.length];
            int r42 = 0;
        L4:
            if (r42 >= r3.length) goto L6;
            this.indexInShuffled[r3[r42]] = r42;
            r42 = r42 + 1;
            goto L4
        }

        private static int[] createShuffledList(int r5, Random r6) {
            int[] r0 = new int[r5];
            int r1 = 0;
        L3:
            if (r1 >= r5) goto L5;
            int r2 = r1 + 1;
            int r3 = r6.nextInt(r2);
            r0[r1] = r0[r3];
            r0[r3] = r1;
            r1 = r2;
            goto L3
        L5:
            return r0;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int r9, int r10) {
            int[] r0 = new int[r10];
            int[] r1 = new int[r10];
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r10) goto L5;
            r0[r3] = this.random.nextInt(this.shuffled.length + 1);
            int r5 = r3 + 1;
            int r4 = this.random.nextInt(r5);
            r1[r3] = r1[r4];
            r1[r4] = r3 + r9;
            r3 = r5;
            goto L3
        L5:
            Arrays.sort(r0);
            int[] r32 = new int[this.shuffled.length + r10];
            int r42 = 0;
            int r52 = 0;
        L6:
            int[] r6 = this.shuffled;
            if (r2 >= (r6.length + r10)) goto L18;
            if (r42 < r10) goto L10;
        L12:
            int r7 = r52 + 1;
            int r53 = r6[r52];
            r32[r2] = r53;
            if (r53 < r9) goto L15;
            r32[r2] = r53 + r10;
        L15:
            r52 = r7;
        L16:
            r2 = r2 + 1;
            goto L6
        L10:
            if (r52 != r0[r42]) goto L12;
            r32[r2] = r1[r42];
            r42 = r42 + 1;
            goto L16
        L18:
            return new DefaultShuffleOrder(r32, new Random(this.random.nextLong()));
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int r7, int r8) {
            int r0 = r8 - r7;
            int[] r1 = new int[this.shuffled.length - r0];
            int r2 = 0;
            int r3 = 0;
        L3:
            int[] r4 = this.shuffled;
            if (r2 >= r4.length) goto L15;
            int r42 = r4[r2];
            if (r42 < r7) goto L9;
            if (r42 >= r8) goto L9;
            r3 = r3 + 1;
        L13:
            r2 = r2 + 1;
        L9:
            int r5 = r2 - r3;
            if (r42 < r7) goto L12;
            r42 = r42 - r0;
        L12:
            r1[r5] = r42;
            goto L13
        L15:
            return new DefaultShuffleOrder(r1, new Random(this.random.nextLong()));
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getFirstIndex() {
            int[] r1 = this.shuffled;
            if (r1.length > 0) goto L5;
            return -1;
        L5:
            return r1[0];
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getLastIndex() {
            int[] r1 = this.shuffled;
            if (r1.length > 0) goto L5;
            return -1;
        L5:
            return r1[r1.length - 1];
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getLength() {
            return this.shuffled.length;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getNextIndex(int r2) {
            int r22 = this.indexInShuffled[r2] + 1;
            int[] r1 = this.shuffled;
            if (r22 < r1.length) goto L5;
            return -1;
        L5:
            return r1[r22];
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getPreviousIndex(int r2) {
            int r22 = this.indexInShuffled[r2] - 1;
            if (r22 >= 0) goto L5;
            return -1;
        L5:
            return this.shuffled[r22];
        }
    }

    public static final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public UnshuffledShuffleOrder(int r1) {
            this.length = r1;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndInsert(int r1, int r2) {
            return new UnshuffledShuffleOrder(this.length + r2);
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public ShuffleOrder cloneAndRemove(int r2, int r3) {
            return new UnshuffledShuffleOrder((this.length - r3) + r2);
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getFirstIndex() {
            if (this.length <= 0) goto L6;
            return 0;
        L6:
            return -1;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getLastIndex() {
            int r0 = this.length;
            if (r0 > 0) goto L5;
            return -1;
        L5:
            return r0 - 1;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getLength() {
            return this.length;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getNextIndex(int r1) {
            int r12 = r1 + 1;
            if (r12 >= this.length) goto L5;
            return r12;
        L5:
            return -1;
        }

        @Override // androidx.media3.exoplayer.source.ShuffleOrder
        public int getPreviousIndex(int r1) {
            int r12 = r1 - 1;
            if (r12 < 0) goto L5;
            return r12;
        L5:
            return -1;
        }
    }

    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int r1, int r2);

    default ShuffleOrder cloneAndMove(int r1, int r2, int r3) {
        return this;
    }

    ShuffleOrder cloneAndRemove(int r1, int r2);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int r1);

    int getPreviousIndex(int r1);
}
