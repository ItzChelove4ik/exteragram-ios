package kotlin.ranges;

import androidx.collection.MutableScatterMap$$ExternalSyntheticBackport0;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000b\u001a\u00020\fH\u0096\u0082\u0004J\u0011\u0010\r\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0002X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\nR\u000f\u0010\u000b\u001a\u00020\fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0002X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\nR\u0011\u0010\r\u001a\u00020\u0002X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0010"}, d2 = {"Lkotlin/ranges/ULongProgressionIterator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ULong;", "first", "last", "step", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "next-s-VKNKU", "()J", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ULongProgressionIterator implements Iterator<ULong>, KMappedMarker {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    private ULongProgressionIterator(long r4, long r6, long r8) {
        this.finalElement = r6;
        boolean r1 = false;
        if (r8 <= 0) goto L8;
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r4, r6) > 0) goto L10;
    L6:
        r1 = true;
    L10:
        this.hasNext = r1;
        this.step = ULong.m2192constructorimpl(r8);
        if (this.hasNext == true) goto L14;
        r4 = r6;
    L14:
        this.next = r4;
        return;
    L8:
        if (MutableScatterMap$$ExternalSyntheticBackport0.m(r4, r6) < 0) goto L10;
        goto L10
    }

    public /* synthetic */ ULongProgressionIterator(long r1, long r3, long r5, DefaultConstructorMarker r7) {
        this(r1, r3, r5);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ ULong next() {
        return ULong.m2186boximpl(m3270nextsVKNKU());
    }

    /* renamed from: next-s-VKNKU, reason: not valid java name */
    public long m3270nextsVKNKU() {
        long r0 = this.next;
        if (r0 == this.finalElement) goto L5;
        this.next = ULong.m2192constructorimpl(this.step + r0);
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
