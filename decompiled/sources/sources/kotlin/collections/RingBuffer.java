package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Utf8$$ExternalSyntheticBUOutline0;
import okio.Utf8$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B!\bF\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB\u0011\bV\u0012\u0006\u0010\f\u001a\u00020\t¢\u0006\u0004\b\n\u0010\rJ\u0017\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\tH\u0096\u0082\u0004¢\u0006\u0002\u0010\u0016J\n\u0010\u0017\u001a\u00020\u0018H\u0086\u0080\u0004J\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0082\u0004J)\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006\"\u0004\b\u0001\u0010\u00012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0094\u0080\u0004¢\u0006\u0002\u0010\u001dJ\u0017\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006H\u0094\u0080\u0004¢\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010 \u001a\u00020\tH\u0086\u0080\u0004J\u0017\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010$J\u0012\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\tH\u0086\u0080\u0004J\u0016\u0010'\u001a\u00020\t*\u00020\t2\u0006\u0010&\u001a\u00020\tH\u0082\u0088\u0004R\u0019\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000eR\u000f\u0010\f\u001a\u00020\tX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000f\u001a\u00020\tX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001f\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t@RX\u0096\u008e\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006("}, d2 = {"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "buffer", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "filledSize", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Ljava/lang/Object;I)V", "capacity", "(I)V", "[Ljava/lang/Object;", "startIndex", "value", "size", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "isFull", _UrlKt.FRAGMENT_ENCODE_SET, "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "toArray", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "()[Ljava/lang/Object;", "expanded", "maxCapacity", "add", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)V", "removeFirst", "n", "forward", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSlidingWindow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,206:1\n204#1:208\n204#1:209\n204#1:210\n1#2:207\n*S KotlinDebug\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer\n*L\n106#1:208\n175#1:209\n188#1:210\n*E\n"})
/* loaded from: classes5.dex */
final class RingBuffer<T> extends AbstractList<T> implements RandomAccess {
    private final Object[] buffer;
    private final int capacity;
    private int size;
    private int startIndex;

    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\n\u0010\u0005\u001a\u00020\u0006H\u0094\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"kotlin/collections/RingBuffer$iterator$1", "Lkotlin/collections/AbstractIterator;", NotificationBadge.NewHtcHomeBadger.COUNT, _UrlKt.FRAGMENT_ENCODE_SET, "index", "computeNext", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSlidingWindow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer$iterator$1\n+ 2 SlidingWindow.kt\nkotlin/collections/RingBuffer\n*L\n1#1,206:1\n204#2:207\n*S KotlinDebug\n*F\n+ 1 SlidingWindow.kt\nkotlin/collections/RingBuffer$iterator$1\n*L\n121#1:207\n*E\n"})
    /* renamed from: kotlin.collections.RingBuffer$iterator$1, reason: invalid class name */
    public static final class AnonymousClass1 extends AbstractIterator<T> {
        private int count;
        private int index;
        final /* synthetic */ RingBuffer<T> this$0;

        public AnonymousClass1(RingBuffer<T> r2) {
            this.this$0 = r2;
            this.count = r2.size();
            this.index = RingBuffer.access$getStartIndex$p(r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.collections.AbstractIterator
        public void computeNext() {
            if (this.count != 0) goto L6;
            done();
            return;
        L6:
            setNext(RingBuffer.access$getBuffer$p(this.this$0)[this.index]);
            this.index = (this.index + 1) % RingBuffer.access$getCapacity$p(this.this$0);
            this.count--;
        }
    }

    public RingBuffer(int r2) {
        this(new Object[r2], 0);
    }

    public RingBuffer(Object[] r2, int r3) {
        this.buffer = r2;
        if (r3 >= 0) goto L5;
        Utf8$$ExternalSyntheticBUOutline1.m("ring buffer filled size should not be negative but it is ", r3);
        throw null;
    L5:
        if (r3 > r2.length) goto L8;
        this.capacity = r2.length;
        this.size = r3;
        return;
    L8:
        Utf8$$ExternalSyntheticBUOutline0.m("ring buffer filled size: ", r3, " cannot be larger than the buffer size: ", r2.length);
        throw null;
    }

    public static final /* synthetic */ Object[] access$getBuffer$p(RingBuffer r0) {
        return r0.buffer;
    }

    public static final /* synthetic */ int access$getCapacity$p(RingBuffer r0) {
        return r0.capacity;
    }

    public static final /* synthetic */ int access$getStartIndex$p(RingBuffer r0) {
        return r0.startIndex;
    }

    private final int forward(int r1, int r2) {
        return (r1 + r2) % access$getCapacity$p(this);
    }

    @Override // java.util.Collection, java.util.List
    public final void add(T r4) {
        if (isFull() == true) goto L6;
        this.buffer[(this.startIndex + size()) % access$getCapacity$p(this)] = r4;
        this.size = size() + 1;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("ring buffer is full");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final RingBuffer<T> expanded(int r3) {
        int r0 = this.capacity;
        int r32 = RangesKt.coerceAtMost((r0 + (r0 >> 1)) + 1, r3);
        if (this.startIndex != 0) goto L5;
        Object[] r33 = Arrays.copyOf(this.buffer, r32);
    L7:
        return new RingBuffer(r33, size());
    L5:
        r33 = toArray(new Object[r32]);
        goto L7
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int r3) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, size());
        return (T) this.buffer[(this.startIndex + r3) % access$getCapacity$p(this)];
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.size;
    }

    public final boolean isFull() {
        if (size() != this.capacity) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new AnonymousClass1(this);
    }

    public final void removeFirst(int r6) {
        if (r6 >= 0) goto L4;
        Utf8$$ExternalSyntheticBUOutline1.m("n shouldn't be negative but it is ", r6);
        return;
    L4:
        if (r6 > size()) goto L12;
        if (r6 <= 0) goto L16;
        int r0 = this.startIndex;
        int r1 = (r0 + r6) % access$getCapacity$p(this);
        Object[] r2 = this.buffer;
        if (r0 <= r1) goto L9;
        ArraysKt___ArraysJvmKt.fill(r2, null, r0, this.capacity);
        ArraysKt___ArraysJvmKt.fill(this.buffer, null, 0, r1);
    L10:
        this.startIndex = r1;
        this.size = size() - r6;
        return;
    L9:
        ArraysKt___ArraysJvmKt.fill(r2, null, r0, r1);
        goto L10
    L16:
        return;
    L12:
        Utf8$$ExternalSyntheticBUOutline0.m("n shouldn't be greater than the buffer size: n = ", r6, ", size = ", size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public <T> T[] toArray(T[] r6) {
        int r0 = r6.length;
        Object[] r62 = r6;
        if (r0 >= size()) goto L5;
        r62 = (T[]) Arrays.copyOf(r6, size());
    L5:
        int r02 = size();
        int r1 = this.startIndex;
        int r2 = 0;
        int r3 = 0;
    L6:
        if (r3 >= r02) goto L10;
        if (r1 >= this.capacity) goto L10;
        r62[r3] = this.buffer[r1];
        r3 = r3 + 1;
        r1 = r1 + 1;
    L10:
        if (r3 >= r02) goto L13;
        r62[r3] = this.buffer[r2];
        r3 = r3 + 1;
        r2 = r2 + 1;
        goto L10
    L13:
        return (T[]) CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(r02, r62);
    }
}
