package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000b\u001a\u00020\fH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0003H\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000f\u0010\n\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlin/ranges/IntProgressionIterator;", "Lkotlin/collections/IntIterator;", "first", _UrlKt.FRAGMENT_ENCODE_SET, "last", "step", "<init>", "(III)V", "getStep", "()I", "finalElement", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "nextInt", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IntProgressionIterator extends IntIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public IntProgressionIterator(int r3, int r4, int r5) {
        this.step = r5;
        this.finalElement = r4;
        boolean r0 = false;
        if (r5 <= 0) goto L6;
        if (r3 > r4) goto L8;
    L5:
        r0 = true;
    L8:
        this.hasNext = r0;
        if (r0 == true) goto L12;
        r3 = r4;
    L12:
        this.next = r3;
        return;
    L6:
        if (r3 < r4) goto L8;
        goto L8
    }

    public final int getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.IntIterator
    public int nextInt() {
        int r0 = this.next;
        if (r0 == this.finalElement) goto L5;
        this.next = this.step + r0;
        return r0;
    L5:
        if (this.hasNext == false) goto L8;
        this.hasNext = false;
        return r0;
    L8:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
