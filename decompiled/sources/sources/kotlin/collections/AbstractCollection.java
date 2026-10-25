package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\t\bD¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nH¦\u0082\u0004J\u0017\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\u0017\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015H\u0095\u0080\u0004¢\u0006\u0002\u0010\u0017J)\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0015\"\u0004\b\u0001\u0010\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u0015H\u0094\u0080\u0004¢\u0006\u0002\u0010\u001aR\u0013\u0010\u0005\u001a\u00020\u0006X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u001b"}, d2 = {"Lkotlin/collections/AbstractCollection;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "isEmpty", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toArray", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "()[Ljava/lang/Object;", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAbstractCollection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractCollection.kt\nkotlin/collections/AbstractCollection\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,50:1\n1807#2,3:51\n1786#2,3:54\n*S KotlinDebug\n*F\n+ 1 AbstractCollection.kt\nkotlin/collections/AbstractCollection\n*L\n19#1:51,3\n22#1:54,3\n*E\n"})
/* loaded from: classes.dex */
public abstract class AbstractCollection<E> implements Collection<E>, KMappedMarker {
    /* renamed from: $r8$lambda$RRlQOBNvJqinm9A-K_sSPMZpvJY, reason: not valid java name */
    public static CharSequence m2373$r8$lambda$RRlQOBNvJqinm9AK_sSPMZpvJY(AbstractCollection r0, Object r1) {
        if (r1 != r0) goto L6;
        return "(this Collection)";
    L6:
        return String.valueOf(r1);
    }

    public AbstractCollection() {
    }

    @Override // java.util.Collection
    public boolean add(E r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean contains(Object r3) {
        if (isEmpty() == false) goto L5;
        return false;
    L5:
        Iterator<E> r2 = iterator();
    L7:
        if (r2.hasNext() == false) goto L12;
        if (Intrinsics.areEqual(r2.next(), r3) == false) goto L7;
        return true;
    L12:
        return false;
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

    public abstract int getSize();

    @Override // java.util.Collection
    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public abstract Iterator<E> iterator();

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
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] r1) {
        return (T[]) CollectionToArray.toArray(this, r1);
    }

    public String toString() {
        return CollectionsKt___CollectionsKt.joinToString$default(this, ", ", "[", "]", 0, null, new AbstractCollection$$ExternalSyntheticLambda0(this), 24, null);
    }
}
