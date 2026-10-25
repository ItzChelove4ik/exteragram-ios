package kotlin.jvm.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\f\u001a\u00020\rH\u0096\u0082\u0004J\u000f\u0010\u000e\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000fR\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u000f\u0010\n\u001a\u00020\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/jvm/internal/ArrayIterator;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "array", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Ljava/lang/Object;)V", "getArray", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ArrayIterator<T> implements Iterator<T>, KMappedMarker {
    private final T[] array;
    private int index;

    public ArrayIterator(T[] r1) {
        this.array = r1;
    }

    public final T[] getArray() {
        return this.array;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.index >= this.array.length) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Iterator
    public T next() {
        T[] r0 = this.array;     // Catch: ArrayIndexOutOfBoundsException -> L4
        int r1 = this.index;     // Catch: ArrayIndexOutOfBoundsException -> L4
        this.index = r1 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L4
        return r0[r1];
    L4:
        e = move-exception;
        this.index--;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
