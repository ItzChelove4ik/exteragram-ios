package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.CharIterator;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B!\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\f\u001a\u00020\rH\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0003H\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000f\u0010\u000b\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", _UrlKt.FRAGMENT_ENCODE_SET, "last", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(CCI)V", "getStep", "()I", "finalElement", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "nextChar", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class CharProgressionIterator extends CharIterator {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    public CharProgressionIterator(char r3, char r4, int r5) {
        this.step = r5;
        this.finalElement = r4;
        boolean r0 = false;
        if (r5 <= 0) goto L8;
        if (Intrinsics.compare(r3, r4) > 0) goto L10;
    L6:
        r0 = true;
    L10:
        this.hasNext = r0;
        if (r0 == true) goto L14;
        r3 = r4;
    L14:
        this.next = r3;
        return;
    L8:
        if (Intrinsics.compare(r3, r4) < 0) goto L10;
        goto L10
    }

    public final int getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.CharIterator
    public char nextChar() {
        int r0 = this.next;
        if (r0 == this.finalElement) goto L5;
        this.next = this.step + r0;
    L11:
        return (char) r0;
    L5:
        if (this.hasNext == false) goto L7;
        this.hasNext = false;
        goto L11
    L7:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }
}
