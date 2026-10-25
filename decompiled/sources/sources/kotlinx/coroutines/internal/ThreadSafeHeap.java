package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000*\u0012\b\u0000\u0010\u0003*\u00020\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0004j\u0002`\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082\u0010¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0082\u0010¢\u0006\u0004\b\r\u0010\fJ\u0017\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0012\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0016\u0010\u0015J\u0015\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0011\u0010\u001b\u001a\u0004\u0018\u00018\u0000H\u0001¢\u0006\u0004\b\u001b\u0010\u0015J\u0017\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\bH\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\u001f\u0010 R \u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R$\u0010'\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b8F@BX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\fR\u0011\u0010(\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b(\u0010)R\u000b\u0010+\u001a\u00020*8\u0002X\u0082\u0004¨\u0006,"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", _UrlKt.FRAGMENT_ENCODE_SET, "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/SynchronizedObject;", "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "i", _UrlKt.FRAGMENT_ENCODE_SET, "siftUpFrom", "(I)V", "siftDownFrom", _UrlKt.FRAGMENT_ENCODE_SET, "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "j", "swap", "(II)V", "peek", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "node", _UrlKt.FRAGMENT_ENCODE_SET, "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "firstImpl", "index", "removeAtImpl", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "addImpl", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "a", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "value", "getSize", "()I", "setSize", "size", "isEmpty", "()Z", "Lkotlinx/atomicfu/AtomicInt;", "_size", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nThreadSafeHeap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,159:1\n26#2:160\n26#2:162\n26#2:164\n26#2:166\n26#2:168\n26#2:170\n26#2:172\n16#3:161\n16#3:163\n16#3:165\n16#3:167\n16#3:169\n16#3:171\n16#3:173\n1#4:174\n*S KotlinDebug\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n*L\n33#1:160\n41#1:162\n43#1:164\n51#1:166\n60#1:168\n63#1:170\n72#1:172\n33#1:161\n41#1:163\n43#1:165\n51#1:167\n60#1:169\n63#1:171\n72#1:173\n*E\n"})
/* loaded from: classes.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater _size$volatile$FU = null;
    private volatile /* synthetic */ int _size$volatile;

    /* renamed from: a, reason: collision with root package name */
    private T[] f499a;

    static {
        _size$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size$volatile");
    }

    public ThreadSafeHeap() {
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_size$volatile$FU() {
        return _size$volatile$FU;
    }

    private final T[] realloc() {
        T[] r0 = this.f499a;
        if (r0 != null) goto L7;
        T[] r02 = (T[]) new ThreadSafeHeapNode[4];
        this.f499a = r02;
        return r02;
    L7:
        if (getSize() < r0.length) goto L10;
        T[] r03 = (T[]) ((ThreadSafeHeapNode[]) Arrays.copyOf(r0, getSize() * 2));
        this.f499a = r03;
        return r03;
    L10:
        return r0;
    }

    private final void setSize(int r2) {
        get_size$volatile$FU().set(this, r2);
    }

    private final void siftDownFrom(int r6) {
    L2:
        int r0 = r6 * 2;
        int r1 = r0 + 1;
        if (r1 >= getSize()) goto L13;
        T[] r2 = this.f499a;
        int r02 = r0 + 2;
        if (r02 < getSize()) goto L8;
    L10:
        r02 = r1;
    L12:
        if (((Comparable) r2[r6]).compareTo(r2[r02]) <= 0) goto L17;
        swap(r6, r02);
        r6 = r02;
        goto L2
    L17:
        return;
    L8:
        if (((Comparable) r2[r02]).compareTo(r2[r1]) >= 0) goto L10;
    }

    private final void siftUpFrom(int r4) {
    L2:
        if (r4 <= 0) goto L6;
        T[] r0 = this.f499a;
        int r1 = (r4 - 1) / 2;
        if (((Comparable) r0[r1]).compareTo(r0[r4]) <= 0) goto L10;
        swap(r4, r1);
        r4 = r1;
        goto L2
    L10:
        return;
    }

    private final void swap(int r3, int r4) {
        T[] r2 = this.f499a;
        T r0 = r2[r4];
        T r1 = r2[r3];
        r2[r3] = r0;
        r2[r4] = r1;
        r0.setIndex(r3);
        r1.setIndex(r4);
    }

    @PublishedApi
    public final void addImpl(T r4) {
        r4.setHeap(this);
        ThreadSafeHeapNode[] r0 = realloc();
        int r1 = getSize();
        setSize(r1 + 1);
        r0[r1] = r4;
        r4.setIndex(r1);
        siftUpFrom(r1);
    }

    @PublishedApi
    public final T firstImpl() {
        T[] r1 = this.f499a;
        if (r1 != null) goto L5;
        return null;
    L5:
        return r1[0];
    }

    public final int getSize() {
        return get_size$volatile$FU().get(this);
    }

    public final boolean isEmpty() {
        if (getSize() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final T peek() {
        monitor-enter(this);
        T r0 = (T) firstImpl();
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final boolean remove(T r2) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (r2.getHeap() != null) goto L6;
        boolean r22 = false;
    L8:
        monitor-exit(this);
        return r22;
    L6:
        removeAtImpl(r2.getIndex());     // Catch: Throwable -> L10
        r22 = true;
        goto L8
    }

    @PublishedApi
    public final T removeAtImpl(int r6) {
        T[] r0 = this.f499a;
        setSize(getSize() - 1);
        if (r6 >= getSize()) goto L10;
        swap(r6, getSize());
        int r1 = (r6 - 1) / 2;
        if (r6 > 0) goto L7;
    L9:
        siftDownFrom(r6);
        goto L10
    L7:
        if (((Comparable) r0[r6]).compareTo(r0[r1]) >= 0) goto L9;
        swap(r6, r1);
        siftUpFrom(r1);
    L10:
        T r62 = r0[getSize()];
        r62.setHeap(null);
        r62.setIndex(-1);
        r0[getSize()] = null;
        return r62;
    }

    public final T removeFirstOrNull() {
        monitor-enter(this);
    L7:
        th = move-exception;
        throw th;
    L4:
        if (getSize() <= 0) goto L9;
        T r0 = (T) removeAtImpl(0);
    L10:
        monitor-exit(this);
        return r0;
    L9:
        r0 = null;
        goto L10
    }
}
