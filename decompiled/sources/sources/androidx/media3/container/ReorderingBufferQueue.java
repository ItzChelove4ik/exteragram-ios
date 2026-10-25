package androidx.media3.container;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* loaded from: classes3.dex */
public final class ReorderingBufferQueue {
    private BuffersWithTimestamp lastQueuedBuffer;
    private final OutputConsumer outputConsumer;
    private final PriorityQueue<BuffersWithTimestamp> pendingBuffers;
    private int reorderingQueueSize;
    private final ArrayDeque<BuffersWithTimestamp> unusedBuffersWithTimestamp;
    private final ArrayDeque<ParsableByteArray> unusedParsableByteArrays;

    public static final class BuffersWithTimestamp implements Comparable<BuffersWithTimestamp> {
        public final List<ParsableByteArray> nalBuffers;
        public long presentationTimeUs;

        public BuffersWithTimestamp() {
            this.presentationTimeUs = -9223372036854775807L;
            this.nalBuffers = new ArrayList();
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(BuffersWithTimestamp r3) {
            return Long.compare(this.presentationTimeUs, r3.presentationTimeUs);
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(BuffersWithTimestamp r1) {
            return compareTo2(r1);
        }

        public void init(long r3, ParsableByteArray r5) {
            if (r3 == (-9223372036854775807L)) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            Assertions.checkState(this.nalBuffers.isEmpty());
            this.presentationTimeUs = r3;
            this.nalBuffers.add(r5);
            return;
        L5:
            r0 = false;
            goto L6
        }
    }

    public interface OutputConsumer {
        void consume(long r1, ParsableByteArray r3);
    }

    public ReorderingBufferQueue(OutputConsumer r1) {
        this.outputConsumer = r1;
        this.unusedParsableByteArrays = new ArrayDeque();
        this.unusedBuffersWithTimestamp = new ArrayDeque();
        this.pendingBuffers = new PriorityQueue();
        this.reorderingQueueSize = -1;
    }

    private ParsableByteArray copy(ParsableByteArray r5) {
        if (this.unusedParsableByteArrays.isEmpty() == false) goto L5;
        ParsableByteArray r4 = new ParsableByteArray();
    L6:
        r4.reset(r5.bytesLeft());
        System.arraycopy(r5.getData(), r5.getPosition(), r4.getData(), 0, r4.bytesLeft());
        return r4;
    L5:
        r4 = this.unusedParsableByteArrays.pop();
        goto L6
    }

    private void flushQueueDownToSize(int r7) {
    L3:
        if (this.pendingBuffers.size() <= r7) goto L14;
        BuffersWithTimestamp r0 = (BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.poll());
        int r1 = 0;
    L6:
        if (r1 >= r0.nalBuffers.size()) goto L8;
        this.outputConsumer.consume(r0.presentationTimeUs, r0.nalBuffers.get(r1));
        this.unusedParsableByteArrays.push(r0.nalBuffers.get(r1));
        r1 = r1 + 1;
        goto L6
    L8:
        r0.nalBuffers.clear();
        BuffersWithTimestamp r12 = this.lastQueuedBuffer;
        if (r12 == null) goto L13;
        if (r12.presentationTimeUs != r0.presentationTimeUs) goto L13;
        this.lastQueuedBuffer = null;
    L13:
        this.unusedBuffersWithTimestamp.push(r0);
        goto L3
    }

    public void add(long r5, ParsableByteArray r7) {
        int r0 = this.reorderingQueueSize;
        if (r0 != 0) goto L5;
    L25:
        this.outputConsumer.consume(r5, r7);
        return;
    L5:
        if (r0 != (-1)) goto L7;
    L11:
        ParsableByteArray r72 = copy(r7);
        BuffersWithTimestamp r02 = this.lastQueuedBuffer;
        if (r02 == null) goto L18;
        if (r5 != r02.presentationTimeUs) goto L18;
        r02.nalBuffers.add(r72);
        return;
    L18:
        if (this.unusedBuffersWithTimestamp.isEmpty() == false) goto L20;
        BuffersWithTimestamp r03 = new BuffersWithTimestamp();
    L21:
        r03.init(r5, r72);
        this.pendingBuffers.add(r03);
        this.lastQueuedBuffer = r03;
        int r52 = this.reorderingQueueSize;
        if (r52 == (-1)) goto L27;
        flushQueueDownToSize(r52);
        return;
    L27:
        return;
    L20:
        r03 = this.unusedBuffersWithTimestamp.pop();
        goto L21
    L7:
        if (this.pendingBuffers.size() < this.reorderingQueueSize) goto L11;
        if (r5 >= ((BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.peek())).presentationTimeUs) goto L11;
        goto L11
    }

    public void clear() {
        this.pendingBuffers.clear();
    }

    public void flush() {
        flushQueueDownToSize(0);
    }

    public int getMaxSize() {
        return this.reorderingQueueSize;
    }

    public void setMaxSize(int r2) {
        if (r2 < 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkState(r0);
        this.reorderingQueueSize = r2;
        flushQueueDownToSize(r2);
        return;
    L4:
        r0 = false;
        goto L5
    }
}
