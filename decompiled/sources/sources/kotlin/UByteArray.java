package kotlin;

import java.util.Arrays;
import java.util.Collection;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0011\bA\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\bV\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0019\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\bH\u0086\u0082\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0086\u0082\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0096\u0082\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\u001c2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0096\u0080\u0004¢\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u001cH\u0096\u0080\u0004¢\u0006\u0004\b%\u0010&J\u0014\u0010'\u001a\u00020\u001c2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020\bHÖ\u0081\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004R\u0017\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000bR\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0003\u0092\u0001\u00020\u0004¨\u0006."}, d2 = {"Lkotlin/UByteArray;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UByte;", "storage", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "([B)[B", "size", _UrlKt.FRAGMENT_ENCODE_SET, "(I)[B", "getStorage$annotations", "()V", "get", "index", "get-w2LRezQ", "([BI)B", "set", _UrlKt.FRAGMENT_ENCODE_SET, "value", "set-VurrAj0", "([BIB)V", "getSize-impl", "([B)I", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "iterator-impl", "([B)Ljava/util/Iterator;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "contains-7apg3OU", "([BB)Z", "containsAll", "elements", "containsAll-impl", "([BLjava/util/Collection;)Z", "isEmpty", "isEmpty-impl", "([B)Z", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Iterator", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalUnsignedTypes
@JvmInline
@SourceDebugExtension({"SMAP\nUByteArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,82:1\n1786#2,3:83\n*S KotlinDebug\n*F\n+ 1 UByteArray.kt\nkotlin/UByteArray\n*L\n58#1:83,3\n*E\n"})
/* loaded from: classes5.dex */
public final class UByteArray implements Collection<UByte>, KMappedMarker {
    private final byte[] storage;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\t\u001a\u00020\nH\u0096\u0082\u0004J\u0011\u0010\u000b\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\f\u0010\rR\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/UByteArray$Iterator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UByte;", "array", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([B)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "next-w2LRezQ", "()B", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Iterator implements java.util.Iterator<UByte>, KMappedMarker {
        private final byte[] array;
        private int index;

        public Iterator(byte[] r1) {
            this.array = r1;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.index >= this.array.length) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ UByte next() {
            return UByte.m2030boximpl(m2104nextw2LRezQ());
        }

        /* renamed from: next-w2LRezQ, reason: not valid java name */
        public byte m2104nextw2LRezQ() {
            int r0 = this.index;
            byte[] r1 = this.array;
            if (r0 >= r1.length) goto L6;
            this.index = r0 + 1;
            return UByte.m2036constructorimpl(r1[r0]);
        L6:
            UIntArray$Iterator$$ExternalSyntheticBUOutline0.m(String.valueOf(r0));
            return 0;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @PublishedApi
    private /* synthetic */ UByteArray(byte[] r1) {
        this.storage = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UByteArray m2087boximpl(byte[] r1) {
        return new UByteArray(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m2088constructorimpl(int r0) {
        return m2089constructorimpl(new byte[r0]);
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m2089constructorimpl(byte[] r0) {
        return r0;
    }

    /* renamed from: contains-7apg3OU, reason: not valid java name */
    public static boolean m2090contains7apg3OU(byte[] r0, byte r1) {
        return ArraysKt.contains(r0, r1);
    }

    /* renamed from: containsAll-impl, reason: not valid java name */
    public static boolean m2091containsAllimpl(byte[] r3, Collection<UByte> r4) {
        Collection<UByte> r42 = r4;
        if (r42.isEmpty() == false) goto L5;
        return true;
    L5:
        java.util.Iterator<T> r43 = r42.iterator();
    L7:
        if (r43.hasNext() == false) goto L15;
        Object r0 = r43.next();
        if ((r0 instanceof UByte) == false) goto L13;
        if (ArraysKt.contains(r3, ((UByte) r0).m2086unboximpl()) == true) goto L7;
        return false;
    L13:
        return false;
    L15:
        return true;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2092equalsimpl(byte[] r2, Object r3) {
        if ((r3 instanceof UByteArray) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((UByteArray) r3).m2103unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2093equalsimpl0(byte[] r0, byte[] r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: get-w2LRezQ, reason: not valid java name */
    public static final byte m2094getw2LRezQ(byte[] r0, int r1) {
        return UByte.m2036constructorimpl(r0[r1]);
    }

    /* renamed from: getSize-impl, reason: not valid java name */
    public static int m2095getSizeimpl(byte[] r0) {
        return r0.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2096hashCodeimpl(byte[] r0) {
        return Arrays.hashCode(r0);
    }

    /* renamed from: isEmpty-impl, reason: not valid java name */
    public static boolean m2097isEmptyimpl(byte[] r0) {
        if (r0.length != 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: iterator-impl, reason: not valid java name */
    public static java.util.Iterator<UByte> m2098iteratorimpl(byte[] r1) {
        return new Iterator(r1);
    }

    /* renamed from: set-VurrAj0, reason: not valid java name */
    public static final void m2099setVurrAj0(byte[] r0, int r1, byte r2) {
        r0[r1] = r2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2100toStringimpl(byte[] r2) {
        return "UByteArray(storage=" + Arrays.toString(r2) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UByte r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-7apg3OU, reason: not valid java name */
    public boolean m2101add7apg3OU(byte r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UByte> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof UByte) == true) goto L7;
        return false;
    L7:
        return m2102contains7apg3OU(((UByte) r2).m2086unboximpl());
    }

    /* renamed from: contains-7apg3OU, reason: not valid java name */
    public boolean m2102contains7apg3OU(byte r1) {
        return m2090contains7apg3OU(this.storage, r1);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> r1) {
        return m2091containsAllimpl(this.storage, r1);
    }

    @Override // java.util.Collection
    public boolean equals(Object r1) {
        return m2092equalsimpl(this.storage, r1);
    }

    public int getSize() {
        return m2095getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m2096hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m2097isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator<UByte> iterator() {
        return m2098iteratorimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean remove(Object r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ int size() {
        return getSize();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] r1) {
        return (T[]) CollectionToArray.toArray(this, r1);
    }

    public String toString() {
        return m2100toStringimpl(this.storage);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte[] m2103unboximpl() {
        return this.storage;
    }
}
