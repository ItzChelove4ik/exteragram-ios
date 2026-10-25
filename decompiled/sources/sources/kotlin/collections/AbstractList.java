package kotlin.collections;

import com.android.dex.Dex$$ExternalSyntheticBUOutline0;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.io.encoding.Base64$$ExternalSyntheticBUOutline0;
import kotlin.io.encoding.Base64$$ExternalSyntheticBUOutline1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0005\n\u0002\u0010*\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b'\u0018\u0000  *\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0004\u001d\u001e\u001f B\t\bD¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00020\u0007H¦\u0082\u0004¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0096\u0082\u0004J\u0017\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0096\u0080\u0004J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00142\u0006\u0010\u000b\u001a\u00020\u0007H\u0096\u0080\u0004J \u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0096\u0080\u0004J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0082\u0004J\n\u0010\u001c\u001a\u00020\u0007H\u0096\u0080\u0004R\u0013\u0010\u0006\u001a\u00020\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006!"}, d2 = {"Lkotlin/collections/AbstractList;", "E", "Lkotlin/collections/AbstractCollection;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "indexOf", "element", "(Ljava/lang/Object;)I", "lastIndexOf", "listIterator", _UrlKt.FRAGMENT_ENCODE_SET, "subList", "fromIndex", "toIndex", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "SubList", "IteratorImpl", "ListIteratorImpl", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAbstractList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AbstractList.kt\nkotlin/collections/AbstractList\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,181:1\n363#2,7:182\n391#2,7:189\n*S KotlinDebug\n*F\n+ 1 AbstractList.kt\nkotlin/collections/AbstractList\n*L\n27#1:182,7\n29#1:189,7\n*E\n"})
/* loaded from: classes.dex */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>, KMappedMarker {
    public static final Companion Companion = null;
    private static final int maxArraySize = 2147483639;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\tJ\u001f\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\u000bJ'\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\u000fJ'\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\u0013J\u001f\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007H\u0080\u0080\u0004¢\u0006\u0002\b\u0018J\u001b\u0010\u0019\u001a\u00020\u00072\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0080\u0080\u0004¢\u0006\u0002\b\u001cJ'\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001b2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0080\u0080\u0004¢\u0006\u0002\b R\u000f\u0010\u0014\u001a\u00020\u0007X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lkotlin/collections/AbstractList$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "checkElementIndex", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "size", "checkElementIndex$kotlin_stdlib", "checkPositionIndex", "checkPositionIndex$kotlin_stdlib", "checkRangeIndexes", "fromIndex", "toIndex", "checkRangeIndexes$kotlin_stdlib", "checkBoundsIndexes", "startIndex", "endIndex", "checkBoundsIndexes$kotlin_stdlib", "maxArraySize", "newCapacity", "oldCapacity", "minCapacity", "newCapacity$kotlin_stdlib", "orderedHashCode", "c", _UrlKt.FRAGMENT_ENCODE_SET, "orderedHashCode$kotlin_stdlib", "orderedEquals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "orderedEquals$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final void checkBoundsIndexes$kotlin_stdlib(int r7, int r8, int r9) {
            if (r7 < 0) goto L9;
            if (r8 > r9) goto L9;
            if (r7 > r8) goto L7;
            return;
        L7:
            Base64$$ExternalSyntheticBUOutline0.m("startIndex: ", r7, " > endIndex: ", r8);
            return;
        L9:
            Base64$$ExternalSyntheticBUOutline1.m("startIndex: ", r7, ", endIndex: ", r8, ", size: ", r9);
        }

        public final void checkElementIndex$kotlin_stdlib(int r2, int r3) {
            if (r2 < 0) goto L5;
            if (r2 >= r3) goto L5;
            return;
        L5:
            Dex$$ExternalSyntheticBUOutline0.m("index: ", r2, ", size: ", r3);
        }

        public final void checkPositionIndex$kotlin_stdlib(int r2, int r3) {
            if (r2 < 0) goto L5;
            if (r2 > r3) goto L5;
            return;
        L5:
            Dex$$ExternalSyntheticBUOutline0.m("index: ", r2, ", size: ", r3);
        }

        public final void checkRangeIndexes$kotlin_stdlib(int r7, int r8, int r9) {
            if (r7 < 0) goto L9;
            if (r8 > r9) goto L9;
            if (r7 > r8) goto L7;
            return;
        L7:
            Base64$$ExternalSyntheticBUOutline0.m("fromIndex: ", r7, " > toIndex: ", r8);
            return;
        L9:
            Base64$$ExternalSyntheticBUOutline1.m("fromIndex: ", r7, ", toIndex: ", r8, ", size: ", r9);
        }

        public final int newCapacity$kotlin_stdlib(int r2, int r3) {
            int r22 = r2 + (r2 >> 1);
            if ((r22 - r3) >= 0) goto L6;
            r22 = r3;
        L6:
            if ((r22 - AbstractList.maxArraySize) <= 0) goto L10;
            if (r3 <= AbstractList.maxArraySize) goto L11;
            return Integer.MAX_VALUE;
        L11:
            return AbstractList.maxArraySize;
        L10:
            return r22;
        }

        public final boolean orderedEquals$kotlin_stdlib(Collection<?> r3, Collection<?> r4) {
            if (r3.size() == r4.size()) goto L5;
            return false;
        L5:
            Iterator<?> r2 = r4.iterator();
            Iterator<?> r32 = r3.iterator();
        L7:
            if (r32.hasNext() == false) goto L11;
            if (Intrinsics.areEqual(r32.next(), r2.next()) == true) goto L7;
            return false;
        L11:
            return true;
        }

        public final int orderedHashCode$kotlin_stdlib(Collection<?> r2) {
            Iterator<?> r1 = r2.iterator();
            int r22 = 1;
        L4:
            if (r1.hasNext() == false) goto L10;
            Object r0 = r1.next();
            int r23 = r22 * 31;
            if (r0 == null) goto L8;
            int r02 = r0.hashCode();
        L9:
            r22 = r23 + r02;
            goto L4
        L8:
            r02 = 0;
            goto L9
        L10:
            return r22;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0092\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\n\u001a\u00020\u000bH\u0096\u0082\u0004J\u000f\u0010\f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\rR\u001b\u0010\u0004\u001a\u00020\u0005X\u0084\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlin/collections/AbstractList$IteratorImpl;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/collections/AbstractList;)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "getIndex", "()I", "setIndex", "(I)V", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public class IteratorImpl implements Iterator<E>, KMappedMarker {
        private int index;
        final /* synthetic */ AbstractList<E> this$0;

        public IteratorImpl(AbstractList r1) {
            this.this$0 = r1;
        }

        public final int getIndex() {
            return this.index;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.index >= this.this$0.size()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext() == false) goto L6;
            AbstractList<E> r0 = this.this$0;
            int r1 = this.index;
            this.index = r1 + 1;
            return r0.get(r1);
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public final void setIndex(int r1) {
            this.index = r1;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010*\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0092\u0004\u0018\u00002\f0\u0001R\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010\n\u001a\u00020\u0005H\u0096\u0080\u0004J\u000f\u0010\u000b\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\u0005H\u0096\u0080\u0004¨\u0006\u000e"}, d2 = {"Lkotlin/collections/AbstractList$ListIteratorImpl;", "Lkotlin/collections/AbstractList$IteratorImpl;", "Lkotlin/collections/AbstractList;", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/collections/AbstractList;I)V", "hasPrevious", _UrlKt.FRAGMENT_ENCODE_SET, "nextIndex", "previous", "()Ljava/lang/Object;", "previousIndex", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public class ListIteratorImpl extends AbstractList<E>.IteratorImpl implements ListIterator<E>, KMappedMarker {
        final /* synthetic */ AbstractList<E> this$0;

        public ListIteratorImpl(AbstractList r2, int r3) {
            this.this$0 = r2;
            super(r2);
            AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, r2.size());
            setIndex(r3);
        }

        @Override // java.util.ListIterator
        public void add(E r1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            if (getIndex() <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return getIndex();
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (hasPrevious() == false) goto L6;
            AbstractList<E> r0 = this.this$0;
            setIndex(getIndex() - 1);
            return r0.get(getIndex());
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return getIndex() - 1;
        }

        @Override // java.util.ListIterator
        public void set(E r1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010 \n\u0000\b\u0002\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B'\bF\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00028\u00012\u0006\u0010\r\u001a\u00020\u0007H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000eJ \u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0096\u0080\u0004R\u0015\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\u0007X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00020\u00078VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lkotlin/collections/AbstractList$SubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", "fromIndex", _UrlKt.FRAGMENT_ENCODE_SET, "toIndex", "<init>", "(Lkotlin/collections/AbstractList;II)V", "_size", "get", "index", "(I)Ljava/lang/Object;", "size", "getSize", "()I", "subList", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class SubList<E> extends AbstractList<E> implements RandomAccess {
        private int _size;
        private final int fromIndex;
        private final AbstractList<E> list;

        /* JADX WARN: Multi-variable type inference failed */
        public SubList(AbstractList<? extends E> r2, int r3, int r4) {
            this.list = r2;
            this.fromIndex = r3;
            AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r3, r4, r2.size());
            this._size = r4 - r3;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public E get(int r3) {
            AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this._size);
            return this.list.get(this.fromIndex + r3);
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this._size;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public List<E> subList(int r3, int r4) {
            AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r3, r4, this._size);
            AbstractList<E> r1 = this.list;
            int r2 = this.fromIndex;
            return new SubList(r1, r3 + r2, r2 + r4);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public AbstractList() {
    }

    @Override // java.util.List
    public void add(int r1, E r2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int r1, Collection<? extends E> r2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof List) == true) goto L10;
        return false;
    L10:
        return Companion.orderedEquals$kotlin_stdlib(this, (Collection) r2);
    }

    public abstract E get(int r1);

    @Override // kotlin.collections.AbstractCollection
    public abstract int getSize();

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return Companion.orderedHashCode$kotlin_stdlib(this);
    }

    public int indexOf(Object r3) {
        Iterator<E> r2 = iterator();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L9;
        if (Intrinsics.areEqual(r2.next(), r3) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new IteratorImpl(this);
    }

    public int lastIndexOf(Object r2) {
        ListIterator<E> r1 = listIterator(size());
    L4:
        if (r1.hasPrevious() == false) goto L9;
        if (Intrinsics.areEqual(r1.previous(), r2) == false) goto L4;
        return r1.nextIndex();
    L9:
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(this, 0);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int r2) {
        return new ListIteratorImpl(this, r2);
    }

    @Override // java.util.List
    public E remove(int r1) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int r1, E r2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int r2, int r3) {
        return new SubList(this, r2, r3);
    }
}
