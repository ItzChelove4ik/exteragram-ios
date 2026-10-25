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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0011\bA\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\bV\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\u0005\u0010\tJ\u0019\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\bH\u0086\u0082\u0004¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0002H\u0086\u0082\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0018H\u0096\u0082\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010 \u001a\u00020\u001c2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0096\u0080\u0004¢\u0006\u0004\b\"\u0010#J\u0011\u0010$\u001a\u00020\u001cH\u0096\u0080\u0004¢\u0006\u0004\b%\u0010&J\u0014\u0010'\u001a\u00020\u001c2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0083\u0004J\n\u0010*\u001a\u00020\bHÖ\u0081\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004R\u0017\u0010\u0003\u001a\u00020\u00048\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u000bR\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0003\u0092\u0001\u00020\u0004¨\u0006."}, d2 = {"Lkotlin/UShortArray;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UShort;", "storage", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "([S)[S", "size", _UrlKt.FRAGMENT_ENCODE_SET, "(I)[S", "getStorage$annotations", "()V", "get", "index", "get-Mh2AYeg", "([SI)S", "set", _UrlKt.FRAGMENT_ENCODE_SET, "value", "set-01HTLdE", "([SIS)V", "getSize-impl", "([S)I", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "iterator-impl", "([S)Ljava/util/Iterator;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "contains-xj2QHRw", "([SS)Z", "containsAll", "elements", "containsAll-impl", "([SLjava/util/Collection;)Z", "isEmpty", "isEmpty-impl", "([S)Z", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Iterator", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalUnsignedTypes
@JvmInline
@SourceDebugExtension({"SMAP\nUShortArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UShortArray.kt\nkotlin/UShortArray\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,82:1\n1786#2,3:83\n*S KotlinDebug\n*F\n+ 1 UShortArray.kt\nkotlin/UShortArray\n*L\n58#1:83,3\n*E\n"})
/* loaded from: classes5.dex */
public final class UShortArray implements Collection<UShort>, KMappedMarker {
    private final short[] storage;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\t\u001a\u00020\nH\u0096\u0082\u0004J\u0011\u0010\u000b\u001a\u00020\u0002H\u0096\u0082\u0004¢\u0006\u0004\b\f\u0010\rR\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/UShortArray$Iterator;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/UShort;", "array", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([S)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "next-Mh2AYeg", "()S", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Iterator implements java.util.Iterator<UShort>, KMappedMarker {
        private final short[] array;
        private int index;

        public Iterator(short[] r1) {
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
        public /* bridge */ /* synthetic */ UShort next() {
            return UShort.m2293boximpl(m2367nextMh2AYeg());
        }

        /* renamed from: next-Mh2AYeg, reason: not valid java name */
        public short m2367nextMh2AYeg() {
            int r0 = this.index;
            short[] r1 = this.array;
            if (r0 >= r1.length) goto L6;
            this.index = r0 + 1;
            return UShort.m2299constructorimpl(r1[r0]);
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
    private /* synthetic */ UShortArray(short[] r1) {
        this.storage = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ UShortArray m2350boximpl(short[] r1) {
        return new UShortArray(r1);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static short[] m2351constructorimpl(int r0) {
        return m2352constructorimpl(new short[r0]);
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static short[] m2352constructorimpl(short[] r0) {
        return r0;
    }

    /* renamed from: contains-xj2QHRw, reason: not valid java name */
    public static boolean m2353containsxj2QHRw(short[] r0, short r1) {
        return ArraysKt.contains(r0, r1);
    }

    /* renamed from: containsAll-impl, reason: not valid java name */
    public static boolean m2354containsAllimpl(short[] r3, Collection<UShort> r4) {
        Collection<UShort> r42 = r4;
        if (r42.isEmpty() == false) goto L5;
        return true;
    L5:
        java.util.Iterator<T> r43 = r42.iterator();
    L7:
        if (r43.hasNext() == false) goto L15;
        Object r0 = r43.next();
        if ((r0 instanceof UShort) == false) goto L13;
        if (ArraysKt.contains(r3, ((UShort) r0).m2349unboximpl()) == true) goto L7;
        return false;
    L13:
        return false;
    L15:
        return true;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2355equalsimpl(short[] r2, Object r3) {
        if ((r3 instanceof UShortArray) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((UShortArray) r3).m2366unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2356equalsimpl0(short[] r0, short[] r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: get-Mh2AYeg, reason: not valid java name */
    public static final short m2357getMh2AYeg(short[] r0, int r1) {
        return UShort.m2299constructorimpl(r0[r1]);
    }

    /* renamed from: getSize-impl, reason: not valid java name */
    public static int m2358getSizeimpl(short[] r0) {
        return r0.length;
    }

    @PublishedApi
    public static /* synthetic */ void getStorage$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2359hashCodeimpl(short[] r0) {
        return Arrays.hashCode(r0);
    }

    /* renamed from: isEmpty-impl, reason: not valid java name */
    public static boolean m2360isEmptyimpl(short[] r0) {
        if (r0.length != 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* renamed from: iterator-impl, reason: not valid java name */
    public static java.util.Iterator<UShort> m2361iteratorimpl(short[] r1) {
        return new Iterator(r1);
    }

    /* renamed from: set-01HTLdE, reason: not valid java name */
    public static final void m2362set01HTLdE(short[] r0, int r1, short r2) {
        r0[r1] = r2;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2363toStringimpl(short[] r2) {
        return "UShortArray(storage=" + Arrays.toString(r2) + ')';
    }

    @Override // java.util.Collection
    public /* bridge */ /* synthetic */ boolean add(UShort r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-xj2QHRw, reason: not valid java name */
    public boolean m2364addxj2QHRw(short r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends UShort> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof UShort) == true) goto L7;
        return false;
    L7:
        return m2365containsxj2QHRw(((UShort) r2).m2349unboximpl());
    }

    /* renamed from: contains-xj2QHRw, reason: not valid java name */
    public boolean m2365containsxj2QHRw(short r1) {
        return m2353containsxj2QHRw(this.storage, r1);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> r1) {
        return m2354containsAllimpl(this.storage, r1);
    }

    @Override // java.util.Collection
    public boolean equals(Object r1) {
        return m2355equalsimpl(this.storage, r1);
    }

    public int getSize() {
        return m2358getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m2359hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m2360isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public java.util.Iterator<UShort> iterator() {
        return m2361iteratorimpl(this.storage);
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
        return m2363toStringimpl(this.storage);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ short[] m2366unboximpl() {
        return this.storage;
    }
}
