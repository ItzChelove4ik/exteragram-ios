package androidx.media3.exoplayer.source;

import androidx.media3.common.DataReader;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.upstream.Allocation;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Buffer$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
class SampleDataQueue {
    private final int allocationLength;
    private final Allocator allocator;
    private AllocationNode firstAllocationNode;
    private AllocationNode readAllocationNode;
    private final ParsableByteArray scratch;
    private long totalBytesWritten;
    private AllocationNode writeAllocationNode;

    public static final class AllocationNode implements Allocator.AllocationNode {
        public Allocation allocation;
        public long endPosition;
        public AllocationNode next;
        public long startPosition;

        public AllocationNode(long r1, int r3) {
            reset(r1, r3);
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode r1 = this.next;
            this.next = null;
            return r1;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator.AllocationNode
        public Allocation getAllocation() {
            return (Allocation) Assertions.checkNotNull(this.allocation);
        }

        public void initialize(Allocation r1, AllocationNode r2) {
            this.allocation = r1;
            this.next = r2;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator.AllocationNode
        public Allocator.AllocationNode next() {
            AllocationNode r1 = this.next;
            if (r1 != null) goto L5;
            return null;
        L5:
            if (r1.allocation == null) goto L10;
            return r1;
        L10:
            return null;
        }

        public void reset(long r3, int r5) {
            if (this.allocation != null) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkState(r0);
            this.startPosition = r3;
            this.endPosition = r3 + r5;
            return;
        L5:
            r0 = false;
            goto L6
        }

        public int translateOffset(long r3) {
            return ((int) (r3 - this.startPosition)) + this.allocation.offset;
        }
    }

    public SampleDataQueue(Allocator r4) {
        this.allocator = r4;
        int r42 = r4.getIndividualAllocationLength();
        this.allocationLength = r42;
        this.scratch = new ParsableByteArray(32);
        AllocationNode r0 = new AllocationNode(0, r42);
        this.firstAllocationNode = r0;
        this.readAllocationNode = r0;
        this.writeAllocationNode = r0;
    }

    private void clearAllocationNodes(AllocationNode r2) {
        if (r2.allocation != null) goto L5;
        return;
    L5:
        this.allocator.release(r2);
        r2.clear();
    }

    private static AllocationNode getNodeContainingPosition(AllocationNode r2, long r3) {
    L3:
        if (r3 < r2.endPosition) goto L5;
        r2 = r2.next;
        goto L3
    L5:
        return r2;
    }

    private void postAppend(int r5) {
        long r0 = this.totalBytesWritten + r5;
        this.totalBytesWritten = r0;
        AllocationNode r52 = this.writeAllocationNode;
        if (r0 != r52.endPosition) goto L6;
        this.writeAllocationNode = r52.next;
        return;
    }

    private int preAppend(int r7) {
        AllocationNode r0 = this.writeAllocationNode;
        if (r0.allocation != null) goto L6;
        r0.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
    L6:
        return Math.min(r7, (int) (this.writeAllocationNode.endPosition - this.totalBytesWritten));
    }

    private static AllocationNode readData(AllocationNode r3, long r4, ByteBuffer r6, int r7) {
        AllocationNode r32 = getNodeContainingPosition(r3, r4);
    L3:
        if (r7 <= 0) goto L7;
        int r0 = Math.min(r7, (int) (r32.endPosition - r4));
        r6.put(r32.allocation.data, r32.translateOffset(r4), r0);
        r7 = r7 - r0;
        r4 = r4 + r0;
        if (r4 != r32.endPosition) goto L3;
        r32 = r32.next;
        goto L3
    L7:
        return r32;
    }

    private static AllocationNode readData(AllocationNode r5, long r6, byte[] r8, int r9) {
        AllocationNode r52 = getNodeContainingPosition(r5, r6);
        int r0 = r9;
    L3:
        if (r0 <= 0) goto L7;
        int r1 = Math.min(r0, (int) (r52.endPosition - r6));
        System.arraycopy(r52.allocation.data, r52.translateOffset(r6), r8, r9 - r0, r1);
        r0 = r0 - r1;
        r6 = r6 + r1;
        if (r6 != r52.endPosition) goto L3;
        r52 = r52.next;
        goto L3
    L7:
        return r52;
    }

    private static AllocationNode readEncryptionData(AllocationNode r18, DecoderInputBuffer r19, SampleQueue.SampleExtrasHolder r20, ParsableByteArray r21) {
        long r2 = r20.offset;
        int r4 = 1;
        r21.reset(1);
        AllocationNode r5 = readData(r18, r2, r21.getData(), 1);
        long r22 = r2 + 1;
        int r7 = 0;
        byte r6 = r21.getData()[0];
        if ((r6 & ByteCompanionObject.MIN_VALUE) == 0) goto L5;
        boolean r8 = true;
    L6:
        int r62 = r6 & ByteCompanionObject.MAX_VALUE;
        CryptoInfo r9 = r19.cryptoInfo;
        byte[] r10 = r9.iv;
        if (r10 != null) goto L9;
        r9.iv = new byte[16];
    L10:
        AllocationNode r52 = readData(r5, r22, r9.iv, r62);
        long r23 = r22 + r62;
        if (r8 == false) goto L13;
        r21.reset(2);
        r52 = readData(r52, r23, r21.getData(), 2);
        r23 = r23 + 2;
        r4 = r21.readUnsignedShort();
    L13:
        int r102 = r4;
        int[] r42 = r9.numBytesOfClearData;
        if (r42 != null) goto L16;
    L19:
        r42 = new int[r102];
    L18:
        int[] r11 = r42;
        int[] r43 = r9.numBytesOfEncryptedData;
        if (r43 != null) goto L23;
    L26:
        r43 = new int[r102];
    L25:
        int[] r12 = r43;
        if (r8 == false) goto L31;
        int r44 = r102 * 6;
        r21.reset(r44);
        r52 = readData(r52, r23, r21.getData(), r44);
        r23 = r23 + r44;
        r21.setPosition(0);
    L29:
        if (r7 >= r102) goto L32;
        r11[r7] = r21.readUnsignedShort();
        r12[r7] = r21.readUnsignedIntToInt();
        r7 = r7 + 1;
    L32:
        TrackOutput.CryptoData r1 = (TrackOutput.CryptoData) Util.castNonNull(r20.cryptoData);
        r9.set(r102, r11, r12, r1.encryptionKey, r9.iv, r1.cryptoMode, r1.encryptedBlocks, r1.clearBlocks);
        long r63 = r20.offset;
        int r13 = (int) (r23 - r63);
        r20.offset = r63 + r13;
        r20.size -= r13;
        return r52;
    L31:
        r11[0] = 0;
        r12[0] = r20.size - ((int) (r23 - r20.offset));
        goto L32
    L23:
        if (r43.length >= r102) goto L25;
    L16:
        if (r42.length >= r102) goto L18;
    L9:
        Arrays.fill(r10, (byte) 0);
        goto L10
    L5:
        r8 = false;
        goto L6
    }

    private static AllocationNode readSampleData(AllocationNode r5, DecoderInputBuffer r6, SampleQueue.SampleExtrasHolder r7, ParsableByteArray r8) {
        if (r6.isEncrypted() == false) goto L6;
        r5 = readEncryptionData(r5, r6, r7, r8);
    L6:
        if (r6.hasSupplementalData() == false) goto L9;
        r8.reset(4);
        AllocationNode r52 = readData(r5, r7.offset, r8.getData(), 4);
        int r82 = r8.readUnsignedIntToInt();
        r7.offset += 4;
        r7.size -= 4;
        r6.ensureSpaceForWrite(r82);
        AllocationNode r53 = readData(r52, r7.offset, r6.data, r82);
        r7.offset += r82;
        int r0 = r7.size - r82;
        r7.size = r0;
        r6.resetSupplementalData(r0);
        return readData(r53, r7.offset, r6.supplementalData, r7.size);
    L9:
        r6.ensureSpaceForWrite(r7.size);
        return readData(r5, r7.offset, r6.data, r7.size);
    }

    public void discardDownstreamTo(long r4) {
        if (r4 == (-1)) goto L14;
    L5:
        AllocationNode r0 = this.firstAllocationNode;
        if (r4 < r0.endPosition) goto L9;
        this.allocator.release(r0.allocation);
        this.firstAllocationNode = this.firstAllocationNode.clear();
        goto L5
    L9:
        if (this.readAllocationNode.startPosition >= r0.startPosition) goto L13;
        this.readAllocationNode = r0;
        return;
    L13:
        return;
    }

    public void discardUpstreamSampleBytes(long r6) {
        if (r6 > this.totalBytesWritten) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        this.totalBytesWritten = r6;
        if (r6 == 0) goto L21;
        AllocationNode r02 = this.firstAllocationNode;
        if (r6 == r02.startPosition) goto L21;
    L11:
        long r62 = this.totalBytesWritten;
        long r1 = r02.endPosition;
        AllocationNode r7 = r02.next;
        if (r62 <= r1) goto L14;
        r02 = r7;
        goto L11
    L14:
        AllocationNode r63 = (AllocationNode) Assertions.checkNotNull(r7);
        clearAllocationNodes(r63);
        AllocationNode r72 = new AllocationNode(r02.endPosition, this.allocationLength);
        r02.next = r72;
        if (this.totalBytesWritten != r02.endPosition) goto L17;
        r02 = r72;
    L17:
        this.writeAllocationNode = r02;
        if (this.readAllocationNode != r63) goto L24;
        this.readAllocationNode = r72;
        return;
    L24:
        return;
    L21:
        clearAllocationNodes(this.firstAllocationNode);
        AllocationNode r64 = new AllocationNode(this.totalBytesWritten, this.allocationLength);
        this.firstAllocationNode = r64;
        this.readAllocationNode = r64;
        this.writeAllocationNode = r64;
        return;
    L5:
        r0 = false;
        goto L6
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void peekToBuffer(DecoderInputBuffer r2, SampleQueue.SampleExtrasHolder r3) {
        readSampleData(this.readAllocationNode, r2, r3, this.scratch);
    }

    public void readToBuffer(DecoderInputBuffer r3, SampleQueue.SampleExtrasHolder r4) {
        this.readAllocationNode = readSampleData(this.readAllocationNode, r3, r4, this.scratch);
    }

    public void reset() {
        clearAllocationNodes(this.firstAllocationNode);
        this.firstAllocationNode.reset(0, this.allocationLength);
        AllocationNode r0 = this.firstAllocationNode;
        this.readAllocationNode = r0;
        this.writeAllocationNode = r0;
        this.totalBytesWritten = 0;
        this.allocator.trim();
    }

    public void rewind() {
        this.readAllocationNode = this.firstAllocationNode;
    }

    public int sampleData(DataReader r5, int r6, boolean r7) {
        int r62 = preAppend(r6);
        AllocationNode r0 = this.writeAllocationNode;
        int r52 = r5.read(r0.allocation.data, r0.translateOffset(this.totalBytesWritten), r62);
        if (r52 != (-1)) goto L8;
        if (r7 == false) goto L6;
        return -1;
    L6:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    L8:
        postAppend(r52);
        return r52;
    }

    public void sampleData(ParsableByteArray r6, int r7) {
    L2:
        if (r7 <= 0) goto L4;
        int r0 = preAppend(r7);
        AllocationNode r1 = this.writeAllocationNode;
        r6.readBytes(r1.allocation.data, r1.translateOffset(this.totalBytesWritten), r0);
        r7 = r7 - r0;
        postAppend(r0);
        goto L2
    }
}
