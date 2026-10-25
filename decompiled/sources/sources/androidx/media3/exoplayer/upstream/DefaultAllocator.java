package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.upstream.Allocator;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class DefaultAllocator implements Allocator {
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;
    private int targetBufferSize;
    private final boolean trimOnReset;

    public DefaultAllocator(boolean r2, int r3) {
        this(r2, r3, 0);
    }

    public DefaultAllocator(boolean r5, int r6, int r7) {
        int r0 = 0;
        boolean r1 = true;
        if (r6 <= 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        if (r7 >= 0) goto L10;
        r1 = false;
    L10:
        Assertions.checkArgument(r1);
        this.trimOnReset = r5;
        this.individualAllocationSize = r6;
        this.availableCount = r7;
        this.availableAllocations = new Allocation[r7 + 100];
        if (r7 <= 0) goto L16;
        this.initialAllocationBlock = new byte[r7 * r6];
    L13:
        if (r0 >= r7) goto L15;
        this.availableAllocations[r0] = new Allocation(this.initialAllocationBlock, r0 * r6);
        r0 = r0 + 1;
        goto L13
    L15:
        return;
    L16:
        this.initialAllocationBlock = null;
        return;
    L5:
        r2 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized Allocation allocate() {
        monitor-enter(this);
        this.allocatedCount++;
        int r0 = this.availableCount;     // Catch: Throwable -> L6
        if (r0 <= 0) goto L8;
        Allocation[] r1 = this.availableAllocations;     // Catch: Throwable -> L6
        int r02 = r0 - 1;
        this.availableCount = r02;     // Catch: Throwable -> L6
        Allocation r03 = (Allocation) Assertions.checkNotNull(r1[r02]);     // Catch: Throwable -> L6
        this.availableAllocations[this.availableCount] = null;     // Catch: Throwable -> L6
    L11:
        monitor-exit(this);
        return r03;
    L8:
        r03 = new Allocation(new byte[this.individualAllocationSize], 0);     // Catch: Throwable -> L6
        int r12 = this.allocatedCount;     // Catch: Throwable -> L6
        Allocation[] r2 = this.availableAllocations;     // Catch: Throwable -> L6
        if (r12 <= r2.length) goto L11;
        this.availableAllocations = (Allocation[]) Arrays.copyOf(r2, r2.length * 2);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }

    public synchronized int getTotalBytesAllocated() {
        monitor-enter(this);
        int r0 = this.allocatedCount * this.individualAllocationSize;
        monitor-exit(this);
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void release(Allocation r4) {
        monitor-enter(this);
        Allocation[] r0 = this.availableAllocations;     // Catch: Throwable -> L6
        int r1 = this.availableCount;     // Catch: Throwable -> L6
        this.availableCount = r1 + 1;     // Catch: Throwable -> L6
        r0[r1] = r4;     // Catch: Throwable -> L6
        this.allocatedCount--;
        notifyAll();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void release(Allocator.AllocationNode r4) {
        monitor-enter(this);
    L3:
        if (r4 == null) goto L7;
        Allocation[] r0 = this.availableAllocations;     // Catch: Throwable -> L5
        int r1 = this.availableCount;     // Catch: Throwable -> L5
        this.availableCount = r1 + 1;     // Catch: Throwable -> L5
        r0[r1] = r4.getAllocation();     // Catch: Throwable -> L5
        this.allocatedCount--;
        r4 = r4.next();     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        throw th;
    L7:
        notifyAll();     // Catch: Throwable -> L5
        monitor-exit(this);
    }

    public synchronized void reset() {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (this.trimOnReset == false) goto L9;
        setTargetBufferSize(0);     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
    }

    public synchronized void setTargetBufferSize(int r2) {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L4:
        if (r2 >= this.targetBufferSize) goto L6;
        boolean r0 = true;
    L7:
        this.targetBufferSize = r2;     // Catch: Throwable -> L11
        if (r0 == false) goto L13;
        trim();     // Catch: Throwable -> L11
    L13:
        monitor-exit(this);
        return;
    L6:
        r0 = false;
        goto L7
    }

    @Override // androidx.media3.exoplayer.upstream.Allocator
    public synchronized void trim() {
        monitor-enter(this);
        int r1 = 0;
        int r0 = Math.max(0, Util.ceilDivide(this.targetBufferSize, this.individualAllocationSize) - this.allocatedCount);     // Catch: Throwable -> L18
        int r2 = this.availableCount;     // Catch: Throwable -> L18
        if (r0 < r2) goto L8;
        monitor-exit(this);
        return;
    L8:
        if (this.initialAllocationBlock == null) goto L24;
        int r22 = r2 - 1;
    L10:
        if (r1 > r22) goto L20;
        Allocation r3 = (Allocation) Assertions.checkNotNull(this.availableAllocations[r1]);     // Catch: Throwable -> L18
        if (r3.data == this.initialAllocationBlock) goto L13;
        Allocation r4 = (Allocation) Assertions.checkNotNull(this.availableAllocations[r22]);     // Catch: Throwable -> L18
        if (r4.data != this.initialAllocationBlock) goto L16;
        Allocation[] r5 = this.availableAllocations;     // Catch: Throwable -> L18
        r5[r1] = r4;     // Catch: Throwable -> L18
        r5[r22] = r3;     // Catch: Throwable -> L18
        r22 = r22 - 1;
        r1 = r1 + 1;     // Catch: Throwable -> L18
        goto L10
    L16:
        r22 = r22 - 1;
        goto L10
    L13:
        r1 = r1 + 1;     // Catch: Throwable -> L18
        goto L10
    L20:
        r0 = Math.max(r0, r1);     // Catch: Throwable -> L18
        if (r0 < this.availableCount) goto L24;
        monitor-exit(this);
        return;
    L24:
        Arrays.fill(this.availableAllocations, r0, this.availableCount, null);     // Catch: Throwable -> L18
        this.availableCount = r0;     // Catch: Throwable -> L18
        monitor-exit(this);
        return;
    L18:
        th = move-exception;
        throw th;
    }
}
