package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public abstract class Sets {

    /* renamed from: com.google.common.collect.Sets$1, reason: invalid class name */
    abstract class AnonymousClass1 extends SetView<Object> {
    }

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* renamed from: com.google.common.collect.Sets$2, reason: invalid class name */
    public class AnonymousClass2<E> extends SetView<E> {
        final /* synthetic */ Set val$set1;
        final /* synthetic */ Set val$set2;

        /* renamed from: com.google.common.collect.Sets$2$1, reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<E> {
            final Iterator<E> itr;
            final /* synthetic */ AnonymousClass2 this$0;
            final /* synthetic */ Set val$set1;
            final /* synthetic */ Set val$set2;

            public AnonymousClass1(AnonymousClass2 r1, Set r2, Set r3) {
                this.val$set1 = r2;
                this.val$set2 = r3;
                this.this$0 = r1;
                this.itr = r2.iterator();
            }

            @Override // com.google.common.collect.AbstractIterator
            public E computeNext() {
            L3:
                if (this.itr.hasNext() == false) goto L8;
                E r0 = this.itr.next();
                if (this.val$set2.contains(r0) == false) goto L3;
                return r0;
            L8:
                return endOfData();
            }
        }

        public AnonymousClass2(Set r1, Set r2) {
            this.val$set1 = r1;
            this.val$set2 = r2;
            super(null);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object r2) {
            if (this.val$set1.contains(r2) == true) goto L5;
            return false;
        L5:
            if (this.val$set2.contains(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> r2) {
            if (this.val$set1.containsAll(r2) == true) goto L5;
            return false;
        L5:
            if (this.val$set2.containsAll(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return Collections.disjoint(this.val$set2, this.val$set1);
        }

        @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public UnmodifiableIterator<E> iterator() {
            return new AnonymousClass1(this, this.val$set1, this.val$set2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public /* bridge */ /* synthetic */ Iterator iterator() {
            return iterator();
        }

        @Override // com.google.common.collect.Sets.SetView
        public int maxSize() {
            return Math.min(SetView.maxSize(this.val$set1), SetView.maxSize(this.val$set2));
        }

        @Override // com.google.common.collect.Sets.SetView
        public int minSize() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            Iterator<E> r0 = this.val$set1.iterator();
            int r1 = 0;
        L4:
            if (r0.hasNext() == false) goto L8;
            E r2 = r0.next();
            if (this.val$set2.contains(r2) == false) goto L4;
            r1 = r1 + 1;
            goto L4
        L8:
            return r1;
        }
    }

    public static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        public FilteredSet(Set<E> r1, Predicate<? super E> r2) {
            super(r1, r2);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object r1) {
            return Sets.equalsImpl(this, r1);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    public static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        public FilteredSortedSet(SortedSet<E> r1, Predicate<? super E> r2) {
            super(r1, r2);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) Iterators.find(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E r3) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).headSet(r3), this.predicate);
        }

        @Override // java.util.SortedSet
        public E last() {
            SortedSet r0 = (SortedSet) this.unfiltered;
        L3:
            E r1 = (Object) r0.last();
            if (this.predicate.apply(r1) == true) goto L5;
            r0 = r0.headSet(r1);
            goto L3
        L5:
            return r1;
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E r3, E r4) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).subSet(r3, r4), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E r3) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(r3), this.predicate);
        }
    }

    public static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        public ImprovedAbstractSet() {
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> r1) {
            return Sets.removeAllImpl(this, r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> r1) {
            return super.retainAll((Collection) Preconditions.checkNotNull(r1));
        }
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        private SetView() {
        }

        public /* synthetic */ SetView(AnonymousClass1 r1) {
            this();
        }

        public static int maxSize(Set<?> r1) {
            if ((r1 instanceof SetView) == false) goto L7;
            return ((SetView) r1).maxSize();
        L7:
            return r1.size();
        }

        public static int minSize(Set<?> r1) {
            if ((r1 instanceof SetView) == false) goto L7;
            return ((SetView) r1).minSize();
        L7:
            return r1.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final boolean add(E r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final boolean addAll(Collection<? extends E> r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(Object r7) {
            if (r7 != this) goto L6;
            return true;
        L6:
            if ((r7 instanceof Set) == true) goto L8;
            return false;
        L8:
            Set r72 = (Set) r7;
            int r1 = maxSize(r72);
            if (minSize() <= r1) goto L11;
            return false;
        L11:
            int r3 = minSize(r72);
            if (maxSize() >= r3) goto L14;
            return false;
        L14:
            UnmodifiableIterator<E> r6 = iterator();
            int r4 = 0;
        L16:
            if (r6.hasNext() == false) goto L23;
            if (r72.contains(r6.next()) == false) goto L20;
            r4 = r4 + 1;
            goto L16
        L20:
            return false;
        L22:
            return false;
        L23:
            if (r4 != r1) goto L25;
            return true;
        L25:
            if (r4 >= r3) goto L27;
            return false;
        L27:
            Iterator<E> r62 = r72.iterator();
            int r73 = 0;
        L29:
            if (r62.hasNext() == false) goto L33;
            r62.next();
            r73 = r73 + 1;
            if (r73 <= r4) goto L29;
            return false;
        L33:
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract UnmodifiableIterator<E> iterator();

        public abstract int maxSize();

        public abstract int minSize();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final boolean remove(Object r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final boolean removeAll(Collection<?> r1) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final boolean retainAll(Collection<?> r1) {
            throw new UnsupportedOperationException();
        }
    }

    public static boolean equalsImpl(Set<?> r4, Object r5) {
        if (r4 != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Set) == false) goto L13;
        Set r52 = (Set) r5;
        if (r4.size() != r52.size()) goto L13;
        if (r4.containsAll(r52) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static <E> Set<E> filter(Set<E> r1, Predicate<? super E> r2) {
        if ((r1 instanceof SortedSet) == false) goto L7;
        return filter((SortedSet) r1, r2);
    L7:
        if ((r1 instanceof FilteredSet) == false) goto L11;
        FilteredSet r12 = (FilteredSet) r1;
        Predicate r22 = Predicates.and(r12.predicate, r2);
        return new FilteredSet((Set) r12.unfiltered, r22);
    L11:
        return new FilteredSet((Set) Preconditions.checkNotNull(r1), (Predicate) Preconditions.checkNotNull(r2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> SortedSet<E> filter(SortedSet<E> r1, Predicate<? super E> r2) {
        if ((r1 instanceof FilteredSet) == false) goto L7;
        FilteredSet r12 = (FilteredSet) r1;
        Predicate r22 = Predicates.and(r12.predicate, r2);
        return new FilteredSortedSet((SortedSet) r12.unfiltered, r22);
    L7:
        return new FilteredSortedSet((SortedSet) Preconditions.checkNotNull(r1), (Predicate) Preconditions.checkNotNull(r2));
    }

    public static int hashCodeImpl(Set<?> r3) {
        Iterator<?> r32 = r3.iterator();
        int r1 = 0;
    L4:
        if (r32.hasNext() == false) goto L10;
        Object r2 = r32.next();
        if (r2 == null) goto L8;
        int r22 = r2.hashCode();
    L9:
        r1 = ~(~(r1 + r22));
        goto L4
    L8:
        r22 = 0;
        goto L9
    L10:
        return r1;
    }

    public static <E> SetView<E> intersection(Set<E> r1, Set<?> r2) {
        Preconditions.checkNotNull(r1, "set1");
        Preconditions.checkNotNull(r2, "set2");
        return new AnonymousClass2(r1, r2);
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet();
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int r1) {
        return new HashSet(Maps.capacity(r1));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static boolean removeAllImpl(Set<?> r2, Collection<?> r3) {
        Preconditions.checkNotNull(r3);
        if ((r3 instanceof Multiset) == false) goto L6;
        r3 = ((Multiset) r3).elementSet();
    L6:
        if ((r3 instanceof Set) == false) goto L12;
        if (r3.size() <= r2.size()) goto L12;
        return Iterators.removeAll(r2.iterator(), r3);
    L12:
        return removeAllImpl(r2, r3.iterator());
    }

    public static boolean removeAllImpl(Set<?> r2, Iterator<?> r3) {
        boolean r0 = false;
    L4:
        if (r3.hasNext() == false) goto L6;
        r0 = r0 | r2.remove(r3.next());
        goto L4
    L6:
        return r0;
    }
}
