package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableListIterator;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\bH\u0096\u0082\u0004¢\u0006\u0002\u0010\rJ\n\u0010\u000e\u001a\u00020\u000fH\u0096\u0080\u0004J\u0017\u0010\u0010\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\bH\u0096\u0080\b¢\u0006\u0002\u0010\rJ\u001f\u0010\u0011\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0096\u0082\b¢\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0096\u0082\u0004J\u0010\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096\u0080\u0004J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\f\u001a\u00020\bH\u0096\u0080\u0004R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001a"}, d2 = {"Lkotlin/collections/ReversedList;", "T", "Lkotlin/collections/AbstractMutableList;", "delegate", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/util/List;)V", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "removeAt", "set", "element", "(ILjava/lang/Object;)Ljava/lang/Object;", "add", "(ILjava/lang/Object;)V", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "listIterator", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class ReversedList<T> extends AbstractMutableList<T> {
    private final List<T> delegate;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0010+\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\n\u0010\u0005\u001a\u00020\u0006H\u0096\u0082\u0004J\n\u0010\u0007\u001a\u00020\u0006H\u0096\u0080\u0004J\u000f\u0010\b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004J\u000f\u0010\f\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\tJ\n\u0010\r\u001a\u00020\u000bH\u0096\u0080\u0004J\u0017\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\n\u0010\u0012\u001a\u00020\u000fH\u0096\u0080\u0004J\u0017\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011R\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0014"}, d2 = {"kotlin/collections/ReversedList$listIterator$1", _UrlKt.FRAGMENT_ENCODE_SET, "delegateIterator", "getDelegateIterator", "()Ljava/util/ListIterator;", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", _UrlKt.FRAGMENT_ENCODE_SET, "previous", "previousIndex", "add", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)V", "remove", "set", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* renamed from: kotlin.collections.ReversedList$listIterator$1, reason: invalid class name */
    public static final class AnonymousClass1 implements ListIterator<T>, KMutableListIterator {
        private final ListIterator<T> delegateIterator;
        final /* synthetic */ ReversedList<T> this$0;

        public AnonymousClass1(ReversedList<T> r2, int r3) {
            this.this$0 = r2;
            this.delegateIterator = ReversedList.access$getDelegate$p(r2).listIterator(CollectionsKt__ReversedViewsKt.access$reversePositionIndex(r2, r3));
        }

        @Override // java.util.ListIterator
        public void add(T r2) {
            this.delegateIterator.add(r2);
            this.delegateIterator.previous();
        }

        public final ListIterator<T> getDelegateIterator() {
            return this.delegateIterator;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.delegateIterator.hasPrevious();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.delegateIterator.hasNext();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public T next() {
            return this.delegateIterator.previous();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return CollectionsKt__ReversedViewsKt.access$reverseIteratorIndex(this.this$0, this.delegateIterator.previousIndex());
        }

        @Override // java.util.ListIterator
        public T previous() {
            return this.delegateIterator.next();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return CollectionsKt__ReversedViewsKt.access$reverseIteratorIndex(this.this$0, this.delegateIterator.nextIndex());
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            this.delegateIterator.remove();
        }

        @Override // java.util.ListIterator
        public void set(T r1) {
            this.delegateIterator.set(r1);
        }
    }

    public ReversedList(List<T> r1) {
        this.delegate = r1;
    }

    public static final /* synthetic */ List access$getDelegate$p(ReversedList r0) {
        return r0.delegate;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int r2, T r3) {
        this.delegate.add(CollectionsKt__ReversedViewsKt.access$reversePositionIndex(this, r2), r3);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        this.delegate.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public T get(int r2) {
        return this.delegate.get(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, r2));
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.delegate.size();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<T> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<T> listIterator(int r2) {
        return new AnonymousClass1(this, r2);
    }

    @Override // kotlin.collections.AbstractMutableList
    public T removeAt(int r2) {
        return this.delegate.remove(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, r2));
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public T set(int r2, T r3) {
        return this.delegate.set(CollectionsKt__ReversedViewsKt.access$reverseElementIndex(this, r2), r3);
    }
}
