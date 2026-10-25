package com.google.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* loaded from: classes5.dex */
public class UnmodifiableLazyStringList extends AbstractList<String> implements LazyStringList, RandomAccess {
    private final LazyStringList list;

    /* renamed from: com.google.protobuf.UnmodifiableLazyStringList$1, reason: invalid class name */
    public class AnonymousClass1 implements ListIterator<String> {
        ListIterator<String> iter;
        final /* synthetic */ UnmodifiableLazyStringList this$0;
        final /* synthetic */ int val$index;

        public AnonymousClass1(UnmodifiableLazyStringList r1, int r2) {
            this.this$0 = r1;
            this.val$index = r2;
            this.iter = UnmodifiableLazyStringList.access$000(r1).listIterator(r2);
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void add(String r1) {
            add2(r1);
        }

        /* renamed from: add, reason: avoid collision after fix types in other method */
        public void add2(String r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.iter.hasPrevious();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public String next() {
            return this.iter.next();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.iter.nextIndex();
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ String previous() {
            return previous2();
        }

        @Override // java.util.ListIterator
        /* renamed from: previous, reason: avoid collision after fix types in other method */
        public String previous2() {
            return this.iter.previous();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.iter.previousIndex();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public /* bridge */ /* synthetic */ void set(String r1) {
            set2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(String r1) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: com.google.protobuf.UnmodifiableLazyStringList$2, reason: invalid class name */
    public class AnonymousClass2 implements Iterator<String> {
        Iterator<String> iter;
        final /* synthetic */ UnmodifiableLazyStringList this$0;

        public AnonymousClass2(UnmodifiableLazyStringList r1) {
            this.this$0 = r1;
            this.iter = UnmodifiableLazyStringList.access$000(r1).iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ String next() {
            return next2();
        }

        @Override // java.util.Iterator
        /* renamed from: next, reason: avoid collision after fix types in other method */
        public String next2() {
            return this.iter.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public UnmodifiableLazyStringList(LazyStringList r1) {
        this.list = r1;
    }

    public static /* synthetic */ LazyStringList access$000(UnmodifiableLazyStringList r0) {
        return r0.list;
    }

    @Override // com.google.protobuf.LazyStringList
    public void add(ByteString r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int r1) {
        return (String) this.list.get(r1);
    }

    @Override // com.google.protobuf.LazyStringList
    public Object getRaw(int r1) {
        return this.list.getRaw(r1);
    }

    @Override // com.google.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return this.list.getUnderlyingElements();
    }

    @Override // com.google.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<String> iterator() {
        return new AnonymousClass2(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<String> listIterator(int r2) {
        return new AnonymousClass1(this, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }
}
