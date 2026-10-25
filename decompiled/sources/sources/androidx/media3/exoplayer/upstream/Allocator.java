package androidx.media3.exoplayer.upstream;

/* loaded from: classes4.dex */
public interface Allocator {

    public interface AllocationNode {
        Allocation getAllocation();

        AllocationNode next();
    }

    Allocation allocate();

    int getIndividualAllocationLength();

    void release(Allocation r1);

    void release(AllocationNode r1);

    void trim();
}
