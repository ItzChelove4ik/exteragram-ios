package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010(\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B!\bF\u0012\u000e\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u0011\u001a\u00020\u0006H\u0096\u0080\u0004J\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\u00062\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0080\u0004J\u0010\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096\u0082\u0004J\u0019\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001a0\u0004H\u0086\u0080\u0004¢\u0006\u0002\u0010\nR\u001f\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0004X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u00020\u0006X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u0015\u0010\r\u001a\u00020\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lkotlin/collections/ArrayAsCollection;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "values", _UrlKt.FRAGMENT_ENCODE_SET, "isVarargs", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Ljava/lang/Object;Z)V", "getValues", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "()Z", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "isEmpty", "contains", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "toArray", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,529:1\n1786#2,3:530\n*S KotlinDebug\n*F\n+ 1 Collections.kt\nkotlin/collections/ArrayAsCollection\n*L\n65#1:530,3\n*E\n"})
/* loaded from: classes.dex */
final class ArrayAsCollection<T> implements Collection<T>, KMappedMarker {
    private final boolean isVarargs;
    private final T[] values;

    public ArrayAsCollection(T[] r1, boolean r2) {
        this.values = r1;
        this.isVarargs = r2;
    }

    @Override // java.util.Collection
    public boolean add(T r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object r1) {
        return ArraysKt___ArraysKt.contains(this.values, r1);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> r3) {
        Collection<?> r32 = r3;
        if (r32.isEmpty() == false) goto L5;
        return true;
    L5:
        Iterator<T> r33 = r32.iterator();
    L7:
        if (r33.hasNext() == false) goto L12;
        if (contains(r33.next()) == true) goto L7;
        return false;
    L12:
        return true;
    }

    public int getSize() {
        return this.values.length;
    }

    public final T[] getValues() {
        return this.values;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        if (this.values.length != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isVarargs() {
        return this.isVarargs;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return ArrayIteratorKt.iterator(this.values);
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
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return CollectionsKt__CollectionsJVMKt.copyToArrayOfAny(this.values, this.isVarargs);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] r1) {
        return (T[]) CollectionToArray.toArray(this, r1);
    }
}
