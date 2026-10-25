package kotlin.ranges;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte$$ExternalSyntheticBackport4;
import kotlin.UInt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000b\u001a\u00020\fH\u0096\u0082\u0004J\u0011\u0010\r\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0002X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\nR\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0002X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\nR\u0011\u0010\r\u001a\u00020\u0002X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0010"}, d2 = {"Lkotlin/ranges/UIntProgressionIterator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UInt;", "first", "last", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "I", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "next-pVg5ArA", "()I", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class UIntProgressionIterator implements Iterator<UInt>, KMappedMarker {
    private final int finalElement;
    private boolean hasNext;
    private int next;
    private final int step;

    private UIntProgressionIterator(int r4, int r5, int r6) {
        this.finalElement = r5;
        boolean r0 = false;
        int r2 = UByte$$ExternalSyntheticBackport4.m(r4, r5);
        if (r6 <= 0) goto L6;
        if (r2 > 0) goto L8;
    L5:
        r0 = true;
    L8:
        this.hasNext = r0;
        this.step = UInt.m2113constructorimpl(r6);
        if (this.hasNext == true) goto L12;
        r4 = r5;
    L12:
        this.next = r4;
        return;
    L6:
        if (r2 < 0) goto L8;
        goto L8
    }

    public /* synthetic */ UIntProgressionIterator(int r1, int r2, int r3, DefaultConstructorMarker r4) {
        this(r1, r2, r3);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ UInt next() {
        return UInt.m2107boximpl(m3261nextpVg5ArA());
    }

    /* renamed from: next-pVg5ArA, reason: not valid java name */
    public int m3261nextpVg5ArA() {
        int r0 = this.next;
        if (r0 == this.finalElement) goto L5;
        this.next = UInt.m2113constructorimpl(this.step + r0);
        return r0;
    L5:
        if (this.hasNext == false) goto L8;
        this.hasNext = false;
        return r0;
    L8:
        Utils$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
