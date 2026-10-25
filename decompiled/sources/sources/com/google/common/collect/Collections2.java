package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes5.dex */
public abstract class Collections2 {

    public static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        public FilteredCollection(Collection<E> r1, Predicate<? super E> r2) {
            this.unfiltered = r1;
            this.predicate = r2;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E r2) {
            Preconditions.checkArgument(this.predicate.apply(r2));
            return this.unfiltered.add(r2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> r4) {
            Iterator<? extends E> r0 = r4.iterator();
        L4:
            if (r0.hasNext() == false) goto L7;
            E r1 = r0.next();
            Preconditions.checkArgument(this.predicate.apply(r1));
            goto L4
        L7:
            return this.unfiltered.addAll(r4);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object r2) {
            if (Collections2.safeContains(this.unfiltered, r2) == true) goto L5;
            return false;
        L5:
            return this.predicate.apply(r2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> r1) {
            return Collections2.containsAllImpl(this, r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object r2) {
            if (contains(r2) == true) goto L5;
            return false;
        L5:
            if (this.unfiltered.remove(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> r5) {
            Iterator<E> r0 = this.unfiltered.iterator();
            boolean r1 = false;
        L4:
            if (r0.hasNext() == false) goto L10;
            E r2 = r0.next();
            if (this.predicate.apply(r2) == false) goto L4;
            if (r5.contains(r2) == false) goto L4;
            r0.remove();
            r1 = true;
            goto L4
        L10:
            return r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> r5) {
            Iterator<E> r0 = this.unfiltered.iterator();
            boolean r1 = false;
        L4:
            if (r0.hasNext() == false) goto L10;
            E r2 = r0.next();
            if (this.predicate.apply(r2) == false) goto L4;
            if (r5.contains(r2) == true) goto L4;
            r0.remove();
            r1 = true;
            goto L4
        L10:
            return r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<E> r0 = this.unfiltered.iterator();
            int r1 = 0;
        L4:
            if (r0.hasNext() == false) goto L8;
            E r2 = r0.next();
            if (this.predicate.apply(r2) == false) goto L4;
            r1 = r1 + 1;
            goto L4
        L8:
            return r1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] r1) {
            return (T[]) Lists.newArrayList(iterator()).toArray(r1);
        }
    }

    public static boolean containsAllImpl(Collection<?> r1, Collection<?> r2) {
        Iterator<?> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L9;
        if (r1.contains(r22.next()) == true) goto L4;
        return false;
    L9:
        return true;
    }

    public static StringBuilder newStringBuilderForCollection(int r5) {
        CollectPreconditions.checkNonnegative(r5, "size");
        return new StringBuilder((int) Math.min(r5 * 8, 1073741824));
    }

    public static boolean safeContains(Collection<?> r0, Object r1) {
        Preconditions.checkNotNull(r0);
        return r0.contains(r1);
    L5:
        return false;
    }
}
