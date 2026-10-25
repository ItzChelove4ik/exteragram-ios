package androidx.media3.common.util;

import java.util.Arrays;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes3.dex */
public final class TimedValueQueue<V> {
    private int first;
    private int size;
    private long[] timestamps;
    private V[] values;

    public TimedValueQueue() {
        this(10);
    }

    public TimedValueQueue(int r2) {
        this.timestamps = new long[r2];
        this.values = (V[]) newArray(r2);
    }

    private void addUnchecked(long r5, V r7) {
        int r0 = this.first;
        int r1 = this.size;
        V[] r2 = this.values;
        int r02 = (r0 + r1) % r2.length;
        this.timestamps[r02] = r5;
        r2[r02] = r7;
        this.size = r1 + 1;
    }

    private void clearBufferOnTimeDiscontinuity(long r4) {
        if (this.size > 0) goto L5;
        return;
    L5:
        if (r4 > this.timestamps[((this.first + r0) - 1) % this.values.length]) goto L9;
        clear();
        return;
    }

    private void doubleCapacityIfFull() {
        int r0 = this.values.length;
        if (this.size >= r0) goto L5;
        return;
    L5:
        int r1 = r0 * 2;
        long[] r2 = new long[r1];
        V[] r12 = (V[]) newArray(r1);
        int r3 = this.first;
        int r02 = r0 - r3;
        System.arraycopy(this.timestamps, r3, r2, 0, r02);
        System.arraycopy(this.values, this.first, r12, 0, r02);
        int r32 = this.first;
        if (r32 <= 0) goto L8;
        System.arraycopy(this.timestamps, 0, r2, r02, r32);
        System.arraycopy(this.values, 0, r12, r02, this.first);
    L8:
        this.timestamps = r2;
        this.values = r12;
        this.first = 0;
    }

    private static <V> V[] newArray(int r0) {
        return (V[]) new Object[r0];
    }

    private V poll(long r8, boolean r10) {
        V r0 = null;
        long r1 = LongCompanionObject.MAX_VALUE;
    L4:
        if (this.size <= 0) goto L12;
        long r3 = r8 - this.timestamps[this.first];
        if (r3 >= 0) goto L11;
        if (r10 == true) goto L12;
        if ((-r3) >= r1) goto L12;
    L11:
        r0 = popFirst();
        r1 = r3;
    L12:
        return r0;
    }

    private V popFirst() {
        if (this.size <= 0) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        V[] r02 = this.values;
        int r2 = this.first;
        V r3 = r02[r2];
        r02[r2] = null;
        this.first = (r2 + 1) % r02.length;
        this.size--;
        return r3;
    L5:
        r0 = false;
        goto L6
    }

    public synchronized void add(long r1, V r3) {
        monitor-enter(this);
        clearBufferOnTimeDiscontinuity(r1);     // Catch: Throwable -> L6
        doubleCapacityIfFull();     // Catch: Throwable -> L6
        addUnchecked(r1, r3);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void clear() {
        monitor-enter(this);
        this.first = 0;     // Catch: Throwable -> L7
        this.size = 0;     // Catch: Throwable -> L7
        Arrays.fill(this.values, null);     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized V pollFirst() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (this.size != 0) goto L6;
        V r0 = null;
    L7:
        monitor-exit(this);
        return r0;
    L6:
        r0 = popFirst();     // Catch: Throwable -> L9
        goto L7
    }

    public synchronized V pollFloor(long r2) {
        monitor-enter(this);
        V r22 = poll(r2, true);     // Catch: Throwable -> L7
        monitor-exit(this);
        return r22;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized int size() {
        monitor-enter(this);
        int r0 = this.size;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
