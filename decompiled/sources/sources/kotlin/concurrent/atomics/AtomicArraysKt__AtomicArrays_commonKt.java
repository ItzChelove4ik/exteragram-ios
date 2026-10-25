package kotlin.concurrent.atomics;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001b\u0010\n\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001b\u0010\u000b\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a\u001b\u0010\f\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001a.\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u0005H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u0007\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\n\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u000b\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\f\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\b\u001a\u00020\u0003H\u0087\u0080\u0004¢\u0006\u0002\u0010\u0011\u001a<\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0013\"\u0006\b\u0000\u0010\u0014\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00140\u0005H\u0087\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a'\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00140\u0013\"\u0006\b\u0000\u0010\u0014\u0018\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004¢\u0006\u0002\u0010\u0017\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"AtomicIntArray", "Lkotlin/concurrent/atomics/AtomicIntArray;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "init", "Lkotlin/Function1;", "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicIntegerArray;", "fetchAndIncrementAt", "index", "(Ljava/util/concurrent/atomic/AtomicIntegerArray;I)I", "incrementAndFetchAt", "decrementAndFetchAt", "fetchAndDecrementAt", "AtomicLongArray", "Lkotlin/concurrent/atomics/AtomicLongArray;", _UrlKt.FRAGMENT_ENCODE_SET, "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicLongArray;", "(Ljava/util/concurrent/atomic/AtomicLongArray;I)J", "AtomicArray", "Lkotlin/concurrent/atomics/AtomicArray;", "T", "(ILkotlin/jvm/functions/Function1;)Ljava/util/concurrent/atomic/AtomicReferenceArray;", "atomicArrayOfNulls", "(I)Ljava/util/concurrent/atomic/AtomicReferenceArray;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/concurrent/atomics/AtomicArraysKt")
@SourceDebugExtension({"SMAP\nAtomicArrays.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AtomicArrays.common.kt\nkotlin/concurrent/atomics/AtomicArraysKt__AtomicArrays_commonKt\n*L\n1#1,769:1\n667#1:770\n*S KotlinDebug\n*F\n+ 1 AtomicArrays.common.kt\nkotlin/concurrent/atomics/AtomicArraysKt__AtomicArrays_commonKt\n*L\n679#1:770\n*E\n"})
/* loaded from: classes5.dex */
class AtomicArraysKt__AtomicArrays_commonKt {
    public AtomicArraysKt__AtomicArrays_commonKt() {
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final /* synthetic */ <T> AtomicReferenceArray<T> AtomicArray(int r3, Function1<? super Integer, ? extends T> r4) {
        int r1 = 0;
        Intrinsics.reifiedOperationMarker(0, "T");
        Object[] r0 = new Object[r3];
    L3:
        if (r1 >= r3) goto L6;
        r0[r1] = r4.invoke(Integer.valueOf(r1));
        r1 = r1 + 1;
        goto L3
    L6:
        return new AtomicReferenceArray(r0);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final AtomicIntegerArray AtomicIntArray(int r3, Function1<? super Integer, Integer> r4) {
        int[] r0 = new int[r3];
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L6;
        r0[r1] = r4.invoke(Integer.valueOf(r1)).intValue();
        r1 = r1 + 1;
        goto L3
    L6:
        return new AtomicIntegerArray(r0);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final AtomicLongArray AtomicLongArray(int r4, Function1<? super Integer, Long> r5) {
        long[] r0 = new long[r4];
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L6;
        r0[r1] = r5.invoke(Integer.valueOf(r1)).longValue();
        r1 = r1 + 1;
        goto L3
    L6:
        return new AtomicLongArray(r0);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.2")
    public static final /* synthetic */ <T> AtomicReferenceArray<T> atomicArrayOfNulls(int r3) {
        int r1 = 0;
        Intrinsics.reifiedOperationMarker(0, "T?");
        Object[] r0 = new Object[r3];
    L3:
        if (r1 >= r3) goto L6;
        r0[r1] = null;
        r1 = r1 + 1;
        goto L3
    L6:
        return new AtomicReferenceArray(r0);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int decrementAndFetchAt(AtomicIntegerArray r1, int r2) {
        return r1.addAndGet(r2, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long decrementAndFetchAt(AtomicLongArray r2, int r3) {
        return r2.addAndGet(r3, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndDecrementAt(AtomicIntegerArray r1, int r2) {
        return r1.getAndAdd(r2, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndDecrementAt(AtomicLongArray r2, int r3) {
        return r2.getAndAdd(r3, -1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int fetchAndIncrementAt(AtomicIntegerArray r1, int r2) {
        return r1.getAndAdd(r2, 1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long fetchAndIncrementAt(AtomicLongArray r2, int r3) {
        return r2.getAndAdd(r3, 1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final int incrementAndFetchAt(AtomicIntegerArray r1, int r2) {
        return r1.addAndGet(r2, 1);
    }

    @ExperimentalAtomicApi
    @SinceKotlin(version = "2.1")
    public static final long incrementAndFetchAt(AtomicLongArray r2, int r3) {
        return r2.addAndGet(r3, 1);
    }
}
