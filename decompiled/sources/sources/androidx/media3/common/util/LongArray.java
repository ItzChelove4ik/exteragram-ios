package androidx.media3.common.util;

import java.util.Arrays;
import kotlin.text.Regex$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public final class LongArray {
    private int size;
    private long[] values;

    public LongArray() {
        this(32);
    }

    public LongArray(int r1) {
        this.values = new long[r1];
    }

    public void add(long r4) {
        int r0 = this.size;
        long[] r1 = this.values;
        if (r0 != r1.length) goto L5;
        this.values = Arrays.copyOf(r1, r0 * 2);
    L5:
        long[] r02 = this.values;
        int r12 = this.size;
        this.size = r12 + 1;
        r02[r12] = r4;
    }

    public void addAll(long[] r6) {
        int r0 = this.size + r6.length;
        long[] r1 = this.values;
        if (r0 <= r1.length) goto L5;
        this.values = Arrays.copyOf(r1, Math.max(r1.length * 2, r0));
    L5:
        System.arraycopy(r6, 0, this.values, this.size, r6.length);
        this.size = r0;
    }

    public long get(int r3) {
        if (r3 >= 0) goto L4;
    L7:
        Regex$$ExternalSyntheticBUOutline0.m("Invalid index ", r3, ", size is ", this.size);
        return 0;
    L4:
        if (r3 >= this.size) goto L7;
        return this.values[r3];
    }

    public int size() {
        return this.size;
    }
}
