package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0086\u0002¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/internal/ResizableAtomicArray;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "initialLength", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "array", "Ljava/util/concurrent/atomic/AtomicReferenceArray;", "currentLength", "get", "index", "(I)Ljava/lang/Object;", "setSynchronized", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(ILjava/lang/Object;)V", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ResizableAtomicArray<T> {
    private volatile AtomicReferenceArray<T> array;

    public ResizableAtomicArray(int r2) {
        this.array = new AtomicReferenceArray(r2);
    }

    public final int currentLength() {
        return this.array.length();
    }

    public final T get(int r2) {
        AtomicReferenceArray<T> r1 = this.array;
        if (r2 < r1.length()) goto L5;
        return null;
    L5:
        return r1.get(r2);
    }

    public final void setSynchronized(int r6, T r7) {
        AtomicReferenceArray<T> r0 = this.array;
        int r1 = r0.length();
        if (r6 >= r1) goto L6;
        r0.set(r6, r7);
        return;
    L6:
        AtomicReferenceArray<T> r2 = new AtomicReferenceArray(RangesKt.coerceAtLeast(r6 + 1, r1 * 2));
        int r3 = 0;
    L7:
        if (r3 >= r1) goto L9;
        r2.set(r3, r0.get(r3));
        r3 = r3 + 1;
        goto L7
    L9:
        r2.set(r6, r7);
        this.array = r2;
    }
}
