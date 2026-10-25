package kotlin.jvm.internal;

import kotlin.Metadata;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.collections.BooleanIterator;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0082\u0004J\n\u0010\n\u001a\u00020\tH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlin/jvm/internal/ArrayBooleanIterator;", "Lkotlin/collections/BooleanIterator;", "array", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Z)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "nextBoolean", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ArrayBooleanIterator extends BooleanIterator {
    private final boolean[] array;
    private int index;

    public ArrayBooleanIterator(boolean[] r1) {
        this.array = r1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.index >= this.array.length) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.collections.BooleanIterator
    public boolean nextBoolean() {
        boolean[] r0 = this.array;     // Catch: ArrayIndexOutOfBoundsException -> L4
        int r1 = this.index;     // Catch: ArrayIndexOutOfBoundsException -> L4
        this.index = r1 + 1;     // Catch: ArrayIndexOutOfBoundsException -> L4
        return r0[r1];
    L4:
        e = move-exception;
        this.index--;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(e.getMessage());
        return false;
    }
}
