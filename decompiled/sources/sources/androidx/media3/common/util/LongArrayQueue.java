package androidx.media3.common.util;

import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class LongArrayQueue {
    private long[] data;
    private int headIndex;
    private int size;
    private int tailIndex;
    private int wrapAroundMask;

    public LongArrayQueue() {
        this(16);
    }

    public LongArrayQueue(int r4) {
        if (r4 >= 0) goto L5;
    L7:
        boolean r2 = false;
    L8:
        Assertions.checkArgument(r2);
        if (r4 != 0) goto L12;
        r4 = 1;
    L12:
        if (Integer.bitCount(r4) == 1) goto L14;
        r4 = Integer.highestOneBit(r4 - 1) << 1;
    L14:
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
        long[] r42 = new long[r4];
        this.data = r42;
        this.wrapAroundMask = r42.length - 1;
        return;
    L5:
        if (r4 > 1073741824) goto L7;
        r2 = true;
        goto L8
    }

    private void doubleArraySize() {
        long[] r0 = this.data;
        int r1 = r0.length << 1;
        if (r1 < 0) goto L6;
        long[] r12 = new long[r1];
        int r2 = r0.length;
        int r3 = this.headIndex;
        int r22 = r2 - r3;
        System.arraycopy(r0, r3, r12, 0, r22);
        System.arraycopy(this.data, 0, r12, r22, r3);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = r12;
        this.wrapAroundMask = r12.length - 1;
        return;
    L6:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    public void add(long r3) {
        if (this.size != this.data.length) goto L5;
        doubleArraySize();
    L5:
        int r0 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = r0;
        this.data[r0] = r3;
        this.size++;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public long element() {
        if (this.size != 0) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    L5:
        return this.data[this.headIndex];
    }

    public boolean isEmpty() {
        if (this.size != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public long remove() {
        int r0 = this.size;
        if (r0 == 0) goto L6;
        long[] r1 = this.data;
        int r2 = this.headIndex;
        long r3 = r1[r2];
        this.headIndex = this.wrapAroundMask & (r2 + 1);
        this.size = r0 - 1;
        return r3;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
